package id.my.hendisantika.awssdk2.service;

import id.my.hendisantika.awssdk2.config.AwsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-aws-sdk2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 07/11/24
 * Time: 11.50
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Service
public class AmazonS3Service {

    private final S3Client s3Client;
    private final AwsProperties awsProperties;

    public AmazonS3Service(@Qualifier("s3Client") S3Client s3Client, AwsProperties awsProperties) {
        this.s3Client = s3Client;
        this.awsProperties = awsProperties;
    }

    public List<String> getAllBuckets() {
        return s3Client.listBuckets().buckets().stream()
                .map(Bucket::name).toList();
    }
}
