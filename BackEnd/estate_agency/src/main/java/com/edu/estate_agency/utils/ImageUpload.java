package com.edu.estate_agency.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.PrePersist;

@Component
public class ImageUpload {
    private final String UPLOAD_FOlDER = "your path here";

    public boolean uploadFile(MultipartFile file) {
        boolean isUpload = false;
        try {
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_FOlDER + File.separator + file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            isUpload = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isUpload;
    }

    public boolean checkExist(MultipartFile multipartFile) {
        boolean isExist = false;
        try {
            File file = new File(UPLOAD_FOlDER + "\\" + multipartFile.getOriginalFilename());
            isExist = file.exists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isExist;
    }

}
