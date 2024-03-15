package com.example.sqs;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;

import java.util.List;

public class SQSMessageConsumer {
    private static final String SQS_QUEUE_URL = "https://sqs.{region}.amazonaws.com/{account-id}/{queue-name}";

    public void consumeMessages() {
        SqsClient sqsClient = SqsClient.builder().region(Region.US_EAST_1).build();
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(SQS_QUEUE_URL)
                .build();

        List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();
        for (Message message : messages) {
            String key = message.body();
            // Process the message
            System.out.println("Received message key: " + key);
            
            // Delete the message from the queue
            sqsClient.deleteMessage(DeleteMessageRequest.builder()
                    .queueUrl(SQS_QUEUE_URL)
                    .receiptHandle(message.receiptHandle())
                    .build());
        }
    }
}
