package com.example.wallet;

public class CommonConstants {

    // User related constants
    // topic used for publishing event when new user is created
    public static final String USER_CREATED_TOPIC = "user_created";
    public static final String USER_CREATED_TOPIC_USER_ID = "userId";
    public static final String USER_CREATED_TOPIC_PHONE_NUMBER = "phoneNumber";
    public static final String USER_CREATED_TOPIC_EMAIL = "emailId";
    public static final String USER_CREATED_TOPIC_IDENTIFIER_KEY = "userIdentifier";
    public static final String USER_CREATED_TOPIC_IDENTIFIER_VALUE = "identifierValue";

    // Transaction related constants
    public static final String TRANSACTION_CREATED_TOPIC = "transaction_created";
    public static final String TRANSACTION_CREATED_TOPIC_SENDER = "sender";
    public static final String TRANSACTION_CREATED_TOPIC_RECEIVER = "receiver";
    public static final String TRANSACTION_CREATED_TOPIC_AMOUNT = "amount";
    public static final String TRANSACTION_CREATED_TOPIC_TRANSACTION_ID = "transactionId";

    // Wallet related constants
    public static final String WALLET_UPDATED_TOPIC = "wallet_updated";
    public static final String WALLET_UPDATED_TOPIC_STATUS = "walletUpdateStatus";
    public static final String WALLET_UPDATED_STATUS_SUCCESS = "success";
    public static final String WALLET_UPDATED_STATUS_FAILED = "failed";





}
