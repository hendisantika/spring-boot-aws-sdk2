package id.my.hendisantika.awssdk2.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-aws-sdk2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 07/11/24
 * Time: 11.47
 * To change this template use File | Settings | File Templates.
 */
@Data
@Component
@ConfigurationProperties("aws")
public class AwsProperties {
    private String access;
    private String secret;
    private String accountNo;

    private S3Properties s3;
    private SqsProperties sqs;

    @Data
    public static class S3Properties {
        private String bucket;
        private String region;
    }

    @Data
    public static class SqsProperties {
        private String region;
        private String queueName;
        private Integer visibilityTimeout;
        private Integer maxMessage;
        private Integer waitTime;
    }
}
