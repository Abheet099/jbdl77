package com.example.wallet;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.example.wallet.CommonConstants.TRANSACTION_CREATED_TOPIC_AMOUNT;
import static com.example.wallet.CommonConstants.TRANSACTION_CREATED_TOPIC_RECEIVER;
import static com.example.wallet.CommonConstants.TRANSACTION_CREATED_TOPIC_SENDER;
import static com.example.wallet.CommonConstants.TRANSACTION_CREATED_TOPIC_TRANSACTION_ID;
import static com.example.wallet.CommonConstants.USER_CREATED_TOPIC_IDENTIFIER_KEY;
import static com.example.wallet.CommonConstants.USER_CREATED_TOPIC_IDENTIFIER_VALUE;
import static com.example.wallet.CommonConstants.USER_CREATED_TOPIC_PHONE_NUMBER;
import static com.example.wallet.CommonConstants.USER_CREATED_TOPIC_USER_ID;
import static com.example.wallet.CommonConstants.WALLET_UPDATED_STATUS_FAILED;
import static com.example.wallet.CommonConstants.WALLET_UPDATED_STATUS_SUCCESS;
import static com.example.wallet.CommonConstants.WALLET_UPDATED_TOPIC_STATUS;

@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;
    @Autowired
    KafkaTemplate<String, String> kafkatemplate;
    @Autowired
    ObjectMapper objectMapper;

    private static Logger logger = LoggerFactory.getLogger(WalletService.class);

    @KafkaListener(topics = CommonConstants.USER_CREATED_TOPIC, groupId = "ewallet_group")
    public void createWallet(String message) throws ParseException {
        logger.debug("In WalletService.createWallet with message: {}", message);

        JSONObject data = (JSONObject) new JSONParser().parse(message);

        Long userId = (Long) data.get(USER_CREATED_TOPIC_USER_ID);
        String phoneNumber = (String) data.get(USER_CREATED_TOPIC_PHONE_NUMBER);
        String identifierKey = (String) data.get(USER_CREATED_TOPIC_IDENTIFIER_KEY);
        String identifierValue = (String) data.get(USER_CREATED_TOPIC_IDENTIFIER_VALUE);

        Wallet wallet = Wallet.builder()
                .userId(userId).phoneNumber(phoneNumber)
                .userIdentifier(UserIdentifier.valueOf(identifierKey)).identifierValue(identifierValue)
                .balance(20.0)
                .build();

        walletRepository.save(wallet);
        logger.debug("Out WalletService.createWallet");
    }

    @KafkaListener(topics = CommonConstants.TRANSACTION_CREATED_TOPIC, groupId = "ewallet_group")
    public void updateWalletForTransaction(String message) throws ParseException, JsonProcessingException {
        logger.debug("In WalletService:updateWalletForTransaction method with message: {}", message);

        JSONObject data = (JSONObject) new JSONParser().parse(message);

        String senderId = (String) data.get(TRANSACTION_CREATED_TOPIC_SENDER);
        String receiverId = (String) data.get(TRANSACTION_CREATED_TOPIC_RECEIVER);
        Double amount = (Double) data.get(CommonConstants.TRANSACTION_CREATED_TOPIC_AMOUNT);
        String transactionId = (String) data.get(TRANSACTION_CREATED_TOPIC_TRANSACTION_ID);

        // Validate if sender and receiver wallet's are in active state
        Wallet senderWallet = walletRepository.findByPhoneNumber(senderId);
        Wallet receiverWallet = walletRepository.findByPhoneNumber(receiverId);

        // publish the event after validating and updating wallet of sender and receiver
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(TRANSACTION_CREATED_TOPIC_SENDER, senderId);
        jsonObject.put(TRANSACTION_CREATED_TOPIC_RECEIVER, receiverId);
        jsonObject.put(TRANSACTION_CREATED_TOPIC_AMOUNT, amount);
        jsonObject.put(TRANSACTION_CREATED_TOPIC_TRANSACTION_ID, transactionId);

        if(senderWallet == null || receiverWallet == null || senderWallet.getBalance() < amount){
            jsonObject.put(WALLET_UPDATED_TOPIC_STATUS, WALLET_UPDATED_STATUS_FAILED);
        } else{

            // debit amount from sender's wallet
            walletRepository.updateWallet(senderId, 0-amount);

            //credit amount to receiver's wallet
            walletRepository.updateWallet(receiverId, amount);
            jsonObject.put(WALLET_UPDATED_TOPIC_STATUS, WALLET_UPDATED_STATUS_SUCCESS);
        }

        kafkatemplate.send(CommonConstants.WALLET_UPDATED_TOPIC, objectMapper.writeValueAsString(jsonObject));
        logger.debug("Out WalletService.updateWalletForTransaction method");
    }
}
