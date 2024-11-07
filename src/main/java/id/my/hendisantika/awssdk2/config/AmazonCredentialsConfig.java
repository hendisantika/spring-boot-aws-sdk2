package id.my.hendisantika.awssdk2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-aws-sdk2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 07/11/24
 * Time: 11.42
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@RequiredArgsConstructor
public class AmazonCredentialsConfig {

    private final AwsProperties awsProperties;

    @Bean("awsCredentials")
    public AwsCredentialsProvider awsCredentials() {
        return DefaultCredentialsProvider.create();
    }
}
