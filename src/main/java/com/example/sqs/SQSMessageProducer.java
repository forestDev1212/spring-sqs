package com.example.sqs;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

public class SQSMessageProducer {
    private static final String SQS_QUEUE_URL = "https://sqs.{region}.amazonaws.com/{account-id}/{queue-name}";

    public void sendMessageToSQS(String key) {
        SqsClient sqsClient = SqsClient.builder().region(Region.US_EAST_1).build();
        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(SQS_QUEUE_URL)
                .messageBody(key)
                .build();
        sqsClient.sendMessage(sendMessageRequest);
    }
}
