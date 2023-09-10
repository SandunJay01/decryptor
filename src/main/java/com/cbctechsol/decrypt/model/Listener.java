package com.cbctechsol.decrypt.model;

import com.cbctechsol.decrypt.controller.DecryptionController;
import com.cbctechsol.decrypt.service.serviceImpl.DirectoryService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Component
public class Listener {
    String fileName;
    /*
        Creates an object of encryption controller to make the request to decrypt since we are
        not using api requests in this program we are directly accessing the controller methods
    */
    private final DecryptionController decryptionController;
    private final DirectoryService directoryService;

    public Listener(DecryptionController decryptionController, DirectoryService directoryService) {
        this.decryptionController = decryptionController;
        this.directoryService = directoryService;
    }


    // Event listener for when the application is ready
    @EventListener(ApplicationReadyEvent.class)
    /*
    Listner is used to keep monitoring a given folder for any changes of the
    content inside it. Whenever a new file is added the listner will collect the
    content of that particular text file and sends a request to encryption
    controller to decrypt collected data.
    */
    public void startListning(){
        try{
            Path dir= Paths.get(directoryService.getReceivedFilePath());
            // Create a WatchService for monitoring file events
            WatchService watchService= FileSystems.getDefault().newWatchService();
            // Register the directory for specific events (ENTRY_CREATE, ENTRY_MODIFY)
            dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.ENTRY_MODIFY);
            while (true){
                WatchKey key=watchService.take();
                for (WatchEvent<?> event : key.pollEvents()){
                    if (event.kind()==StandardWatchEventKinds.ENTRY_CREATE ||
                            event.kind()==StandardWatchEventKinds.ENTRY_MODIFY){
                        Path filePath = dir.resolve((Path) event.context());

                        fileName= (dir.resolve(filePath).toFile()).toString();
                        // Check if the file is a regular file and has a ".txt" extension
                        if (Files.isRegularFile(filePath) && filePath.toString().endsWith(".txt")){
                                try {
                                    List<String> lines = Files.readAllLines(filePath);
                                    StringBuilder stringBuilder=new StringBuilder();
                                    for (String line : lines){
                                        stringBuilder.append(line).append("");
                                    }
                                    String text=stringBuilder.toString().trim();
                                    decryptionController.decrypt(text,getFileName());
                                    break;
                                }catch (IOException e){
                                    e.printStackTrace();
                                }
                        }
                    }
                }
                key.reset();
            }
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

    /*
    Gets the file name inorder to save the decrypted file with the same name. This method restrict
    the name to be only the first name in its name and omit the rest of the parts in its name
     */
    public String getFileName(){
        {
            String fullPath = fileName;
            Path path = Paths.get(fullPath);
            String fileNameWithExtension = path.getFileName().toString();
            String[] fileNameParts = fileNameWithExtension.split("\\.");
            return fileNameParts[0];
        }
    }
}
