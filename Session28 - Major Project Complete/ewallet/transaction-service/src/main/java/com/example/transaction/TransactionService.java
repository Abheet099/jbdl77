package com.example.transaction;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.wallet.CommonConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Slf4j
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    ObjectMapper objectMapper;

    public String transact(String sender, String receiver, double amount, String reason) throws JsonProcessingException {

        // Validation to verify sender, receiver and sender's balance, etc.
        Transaction transaction = Transaction.builder()
                .transactionId(UUID.randomUUID().toString())
                .senderId(sender).receiverId(receiver).amount(amount)
                .reason(reason).transactionStatusEnum(TransactionStatusEnum.PENDING)
                .build();
        transactionRepository.save(transaction);

        // Publish the event after initializing teh transaction which will be listened by all consumers
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CommonConstants.TRANSACTION_CREATED_TOPIC_SENDER, sender);
        jsonObject.put(CommonConstants.TRANSACTION_CREATED_TOPIC_RECEIVER, receiver);
        jsonObject.put(CommonConstants.TRANSACTION_CREATED_TOPIC_AMOUNT, amount);
        jsonObject.put(CommonConstants.TRANSACTION_CREATED_TOPIC_TRANSACTION_ID, transaction.getTransactionId());

        kafkaTemplate.send(CommonConstants.TRANSACTION_CREATED_TOPIC, objectMapper.writeValueAsString(jsonObject));
        return transaction.getTransactionId();
    }

    @KafkaListener(topics = CommonConstants.WALLET_UPDATED_TOPIC, groupId = "ewallet_group")
    public void updateTransaction(String message) throws ParseException, JsonProcessingException {
        log.debug("In TransactionService.updateTransaction method with message: {}", message);
        JSONObject data = (JSONObject) new JSONParser().parse(message);

        String senderId = (String) data.get(CommonConstants.TRANSACTION_CREATED_TOPIC_SENDER);
        String receiverId = (String) data.get(CommonConstants.TRANSACTION_CREATED_TOPIC_RECEIVER);
        Double amount = (Double) data.get(CommonConstants.TRANSACTION_CREATED_TOPIC_AMOUNT);
        String transactionId = (String) data.get(CommonConstants.TRANSACTION_CREATED_TOPIC_TRANSACTION_ID);
        String walletUpdateStatus = (String) data.get(CommonConstants.WALLET_UPDATED_TOPIC_STATUS);

        TransactionStatusEnum transactionStatusEnum;
        String receiverEmail = null;
        JSONObject senderObj = getUserFromUserService(senderId);
        String senderEmail = (String) senderObj.get("email");

        if (walletUpdateStatus.equalsIgnoreCase("success")) {
            JSONObject receiverObj = getUserFromUserService(receiverId);
            receiverEmail = (String) receiverObj.get("email");
            transactionStatusEnum = TransactionStatusEnum.SUCCESS;
        } else {
            transactionStatusEnum = TransactionStatusEnum.FAILED;
        }

        String senderMessage = "Hi, Your transaction with id: " + transactionId + " got " + walletUpdateStatus;

        JSONObject senderEmailObj = new JSONObject();
        senderEmailObj.put("email", senderEmail);
        senderEmailObj.put("message", senderMessage);

        kafkaTemplate.send(CommonConstants.TRANSACTION_COMPLETED_TOPIC, objectMapper.writeValueAsString(senderEmailObj));

        if(walletUpdateStatus.equalsIgnoreCase("success")){
            String receiverMessage = "Hi, You have received an amount of Rs. "+amount+ "from " + senderId +
                    "in your wallet linked with phone number " + receiverId;

            JSONObject receiverObj = new JSONObject();
            receiverObj.put("email",receiverEmail);
            receiverObj.put("message", receiverMessage);
            kafkaTemplate.send(CommonConstants.TRANSACTION_COMPLETED_TOPIC, objectMapper.writeValueAsString(receiverObj));

        }

    }
}
