package org.poc.imageservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.StringJoiner;

@Service
public class StorageService {

    @Autowired
    private SHA1Service sha;

    @Value("${images.path}")
    private String path;

    public String store(MultipartFile input){
        String name;
        try {
            name = sha.getSha1Digest(input.getInputStream());
            name = new StringJoiner("-").add(name).add(input.getOriginalFilename()).toString();
            File file2 = new File("src/main/resources/static" + File.separator + name);
            FileCopyUtils.copy(input.getInputStream(), new FileOutputStream(file2));

        }catch (IOException e){
            throw new RuntimeException("Cause by: " + e.getStackTrace() + "--" + e.getMessage() + "--" + e.getClass());
        }

        return path + name;
    }
}
