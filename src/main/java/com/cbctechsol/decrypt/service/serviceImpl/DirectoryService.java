package com.cbctechsol.decrypt.service.serviceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class DirectoryService {
    @Value("${decrypt.file.dir}")
    private String receivedFilePath;

    @Value("${decrypt.decrypt.dir}")
    private String decryptPath;

    @Value("${decrypt.error.dir}")
    private String errorPath;

    @Value("${decrypt.backup.dir}")
    private String backUpPath;

    public String getReceivedFilePath() {
        return receivedFilePath;
    }

    public String getDecryptPath() {
        return decryptPath;
    }

    public String getErrorPath() {
        return errorPath;
    }

    public String getBackUpPath() {
        return backUpPath;
    }
}
