package id.my.hendisantika.awssdk2.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

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

    @Bean("sqsAsyncClient")
    public SqsAsyncClient sqsAsyncClient(@Qualifier("awsCredentials") AwsCredentialsProvider awsCredentials) {
        return SqsAsyncClient
                .builder()
                .credentialsProvider(awsCredentials)
                .region(Region.of(awsProperties.getSqs().getRegion()))
                .build();
    }
}
