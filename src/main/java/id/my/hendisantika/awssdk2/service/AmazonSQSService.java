package id.my.hendisantika.awssdk2.service;

import id.my.hendisantika.awssdk2.config.AwsProperties;
import id.my.hendisantika.awssdk2.utils.MyTuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageBatchRequest;
import software.amazon.awssdk.services.sqs.model.DeleteMessageBatchRequestEntry;
import software.amazon.awssdk.services.sqs.model.DeleteMessageBatchResponse;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageBatchRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageBatchRequestEntry;
import software.amazon.awssdk.services.sqs.model.SendMessageBatchResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static id.my.hendisantika.awssdk2.utils.AwsUtils.getSQSQueueUrl;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-aws-sdk2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 07/11/24
 * Time: 11.52
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AmazonSQSService {

    private final SqsClient sqsClient;
    private final AwsProperties awsProperties;

    public MyTuple.MyTuple2<List<Message>, DeleteMessageBatchResponse> consume() {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(getSQSQueueUrl(awsProperties))
                .waitTimeSeconds(awsProperties.getSqs().getWaitTime())
                .visibilityTimeout(awsProperties.getSqs().getVisibilityTimeout())
                .maxNumberOfMessages(awsProperties.getSqs().getMaxMessage())
                .build();

        List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();
        if (messages.isEmpty()) return new MyTuple.MyTuple2<>(Collections.emptyList(), null);

        List<DeleteMessageBatchRequestEntry> entries = messages.stream()
                .map(message -> DeleteMessageBatchRequestEntry.builder()
                        .id(message.messageId())
                        .receiptHandle(message.receiptHandle())
                        .build())
                .toList();

        DeleteMessageBatchRequest deleteMessageBatchRequest = DeleteMessageBatchRequest.builder()
                .queueUrl(getSQSQueueUrl(awsProperties))
                .entries(entries)
                .build();

        DeleteMessageBatchResponse deleteMessageBatchResponse = sqsClient.deleteMessageBatch(deleteMessageBatchRequest);
        return new MyTuple.MyTuple2<>(messages, deleteMessageBatchResponse);
    }

    public SendMessageResponse produce(String message) {
        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(getSQSQueueUrl(awsProperties))
                .messageBody(message)
                .build();

        return sqsClient.sendMessage(sendMessageRequest);
    }

    public SendMessageBatchResponse produceBatch(List<String> messages) {
        List<SendMessageBatchRequestEntry> entries = messages.stream()
                .map(message -> SendMessageBatchRequestEntry.builder()
                        .id(UUID.randomUUID().toString())
                        .messageBody(message)
                        .build()
                ).toList();

        SendMessageBatchRequest sendMessageBatchRequest = SendMessageBatchRequest.builder()
                .queueUrl(getSQSQueueUrl(awsProperties))
                .entries(entries)
                .build();

        return sqsClient.sendMessageBatch(sendMessageBatchRequest);
    }
}
