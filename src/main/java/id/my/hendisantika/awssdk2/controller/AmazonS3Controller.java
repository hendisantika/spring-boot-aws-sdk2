package id.my.hendisantika.awssdk2.controller;

import id.my.hendisantika.awssdk2.service.AmazonS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-aws-sdk2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 07/11/24
 * Time: 11.53
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RestController
@RequestMapping("s3/buckets")
@RequiredArgsConstructor
public class AmazonS3Controller {

    private final AmazonS3Service amazonS3Service;

    @GetMapping
    public List<String> getAllBuckets() {
        return amazonS3Service.getAllBuckets();
    }

    @GetMapping("all")
    public List<String> getBucketObjects() {
        return amazonS3Service.getBucketObjects();
    }

    @PostMapping
    public List<String> uploadFile(@RequestParam("file") List<MultipartFile> multipartFiles) {
        return multipartFiles.stream()
                .map(amazonS3Service::uploadFile)
                .collect(Collectors.toList());
    }

    @PutMapping
    public String updateFile(@RequestParam String fileName, @RequestParam("file") MultipartFile multipartFile) {
        return amazonS3Service.updateFile(fileName, multipartFile);
    }

    @DeleteMapping
    public void deleteFile(@RequestParam String fileName) {
        amazonS3Service.deleteFile(fileName);
    }

    @PostMapping("multipart")
    public List<String> multipartUploadFile(@RequestParam("file") List<MultipartFile> multipartFiles) {
        log.info("Multipart Upload Started.");
        return multipartFiles.stream()
                .map(amazonS3Service::multipartUploadFile)
                .collect(Collectors.toList());
    }

    @PostMapping("download")
    public String downloadFile(@RequestParam String fileName) {
        return amazonS3Service.downloadFile(fileName);
    }
}
