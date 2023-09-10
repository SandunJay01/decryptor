package com.cbctechsol.decrypt.service;


import java.io.IOException;

public interface FileHandlerService {
    public void writeBackup(String content,String fileName) throws IOException;
    public void writeToFile(String data,String fileName, boolean condition) throws IOException;
}
