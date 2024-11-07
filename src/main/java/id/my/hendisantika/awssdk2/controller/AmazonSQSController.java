package id.my.hendisantika.awssdk2.controller;

import id.my.hendisantika.awssdk2.service.AmazonSQSService;
import id.my.hendisantika.awssdk2.utils.MyTuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.sqs.model.DeleteMessageBatchResponse;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-aws-sdk2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 07/11/24
 * Time: 11.56
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RestController
@RequestMapping("sqs")
@RequiredArgsConstructor
public class AmazonSQSController {

    private final AmazonSQSService amazonSQSService;

    @GetMapping
    public MyTuple.MyTuple2<List<Message>, DeleteMessageBatchResponse> consumeMessage() {
        return amazonSQSService.consume();
    }

    @PostMapping
    public SendMessageResponse produceMessage(@RequestBody String message) {
        return amazonSQSService.produce(message);
    }
}
