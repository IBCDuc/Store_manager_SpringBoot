package com.example.demo.service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileUploadService {

    private final S3Client s3Client;

    public FileUploadService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadToS3(MultipartFile file, String bucketName) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path tempFile = Files.createTempFile("temp", fileName);
        Files.copy(file.getInputStream(), tempFile);

        // Upload lên S3
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        PutObjectResponse response = s3Client.putObject(request, tempFile);

        // Trả về URL của file trong bucket
        return "https://" + bucketName + ".s3.amazonaws.com/" + fileName;
    }
}
