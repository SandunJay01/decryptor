package com.cbctechsol.decrypt.service.serviceImpl;

import com.cbctechsol.decrypt.model.FileHandler;
import com.cbctechsol.decrypt.service.FileHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileHandlerServiceImplementation implements FileHandlerService {
    @Autowired
    private FileHandler fileHandler;
    private final DirectoryService directoryService;

    public FileHandlerServiceImplementation(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    /*
    Method used to create the backup of the received file
    */
    public void writeBackup(String content,String fileName) throws IOException {
        String backUp = directoryService.getBackUpPath() +fileName + ".txt";
        try {
            Files.write(Paths.get(backUp), content.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    This method is used to write files.
    First this creates a backup of the received file in a different directory
    Then checks the return status from decrypt method for whether it successfully decrypted or not. Upon that
    it saves the file in a relevant location based on the status of the file
     */
    @Override
    public void writeToFile(String content,String fileName, boolean condition) {
        try {
            if (condition==true) {
                String filename = directoryService.getDecryptPath() +fileName+ "_decrypted" + ".txt";
                Files.write(Paths.get(filename), content.getBytes());
                fileHandler.setFilePath(filename);
            } else if (condition==false) {
                String filename = directoryService.getErrorPath() +fileName+ "_Error" + ".txt";
                Files.write(Paths.get(filename), content.getBytes());
                fileHandler.setFilePath(filename);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
