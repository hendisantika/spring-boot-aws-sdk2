package id.my.hendisantika.awssdk2.utils;

import id.my.hendisantika.awssdk2.config.AwsProperties;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-aws-sdk2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 07/11/24
 * Time: 11.48
 * To change this template use File | Settings | File Templates.
 */
public class AwsUtils {
    public static String getSQSQueueUrl(AwsProperties awsProperties) {
        return "https://sqs." + awsProperties.getSqs().getRegion()
                + ".amazonaws.com/" + awsProperties.getAccountNo()
                + "/" + awsProperties.getSqs().getQueueName();
    }
}
