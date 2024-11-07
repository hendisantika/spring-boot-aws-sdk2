package id.my.hendisantika.awssdk2.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-aws-sdk2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 07/11/24
 * Time: 11.44
 * To change this template use File | Settings | File Templates.
 */


@Configuration
@AllArgsConstructor
public class AmazonSQSConfig {

    private final AwsProperties awsProperties;
}
