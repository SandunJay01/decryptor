package com.cbctechsol.decrypt.model;

import org.springframework.stereotype.Component;

/*
File model to store the data related to file
 */
@Component
public class FileHandler {
    private String filePath;
    private String fileName;
    private String fileContent;
    private FileType fileType;

    public FileHandler() {
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }
}
