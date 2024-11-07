package id.my.hendisantika.awssdk2.controller;

import id.my.hendisantika.awssdk2.service.AmazonSQSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
