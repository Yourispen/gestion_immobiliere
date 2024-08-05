package com.javaproject.gestion_immobiliere.controllers;

import com.javaproject.gestion_immobiliere.utils.FileNameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/images")
public class ImageUploadController {

    @Value("${image.upload.dir}")
    private String uploadDir;

    @Value("${app.domain}")
    private String appDomain;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }

        try {
            // Create the upload directory if it doesn't exist
            Files.createDirectories(Paths.get(uploadDir));

            // Clean the file name
            String originalFileName = file.getOriginalFilename();
            String cleanedFileName = FileNameUtils.cleanFileName(originalFileName);

            // Add timestamp to the file name
            String timestamp = String.valueOf(System.currentTimeMillis());
            String extension = "";

            int extensionIndex = cleanedFileName.lastIndexOf(".");
            if (extensionIndex > 0) {
                extension = cleanedFileName.substring(extensionIndex);
                cleanedFileName = cleanedFileName.substring(0, extensionIndex);
            }

            String newFileName = cleanedFileName + "_" + timestamp + extension;

            // Save the file locally
            String filePath = uploadDir + File.separator + newFileName;
            file.transferTo(new File(filePath));

            // Generate the file URL using the domain from application.properties
            String fileUrl = appDomain + "/uploads/" + newFileName;

            return new ResponseEntity<>(fileUrl, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Could not upload the file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
