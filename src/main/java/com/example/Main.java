package com.example;

import com.example.redis.RedisMessageProducer;
import com.example.redis.RedisMessageConsumer;
import com.example.sqs.SQSMessageProducer;
import com.example.sqs.SQSMessageConsumer;

public class Main {

    public static void main(String[] args) {
        // Instantiate RedisMessageProducer and SQSMessageProducer
        RedisMessageProducer redisMessageProducer = new RedisMessageProducer();
        SQSMessageProducer sqsMessageProducer = new SQSMessageProducer();

        // Store a message in Redis and send its key to SQS
        String key = "message_key";
        String message = "Hello, Redis!";
        redisMessageProducer.storeMessage(key, message);
        sqsMessageProducer.sendMessageToSQS(key);

        // Instantiate RedisMessageConsumer and SQSMessageConsumer
        RedisMessageConsumer redisMessageConsumer = new RedisMessageConsumer();
        SQSMessageConsumer sqsMessageConsumer = new SQSMessageConsumer();

        // Retrieve the message from Redis using the key
        String retrievedMessage = redisMessageConsumer.getMessage(key);
        System.out.println("Retrieved message from Redis: " + retrievedMessage);

        // Consume messages from SQS
        sqsMessageConsumer.consumeMessages();
    }
}
