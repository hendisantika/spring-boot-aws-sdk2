package id.my.hendisantika.awssdk2.service;

import id.my.hendisantika.awssdk2.config.AwsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.StorageClass;

import java.io.IOException;
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

    public List<String> getBucketObjects() {
        ListObjectsV2Request request = ListObjectsV2Request.builder()
                .bucket(awsProperties.getS3().getBucket())
                .build();
        return s3Client.listObjectsV2(request).contents().stream()
                .map(S3Object::key).toList();
    }

    public String uploadFile(MultipartFile multipartFile) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(awsProperties.getS3().getBucket())
                    .key(multipartFile.getOriginalFilename())
                    .contentLength(multipartFile.getSize())
                    .storageClass(StorageClass.GLACIER)
                    .build();

            s3Client.putObject(putObjectRequest,
                    RequestBody.fromBytes(multipartFile.getInputStream().readAllBytes()));
            return multipartFile.getOriginalFilename() + " Uploaded.";
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public void deleteFile(String fileName) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(awsProperties.getS3().getBucket())
                .key(fileName)
                .build();

        s3Client.deleteObject(deleteObjectRequest);
    }

    public String updateFile(String fileName, MultipartFile multipartFile) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(awsProperties.getS3().getBucket())
                    .key(fileName)
                    .contentLength(multipartFile.getSize())
                    .storageClass(StorageClass.GLACIER)
                    .build();

            s3Client.putObject(putObjectRequest,
                    RequestBody.fromBytes(multipartFile.getInputStream().readAllBytes()));
            return multipartFile.getOriginalFilename() + " Uploaded.";
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
