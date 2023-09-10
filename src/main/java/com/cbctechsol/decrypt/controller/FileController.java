package com.cbctechsol.decrypt.controller;

import com.cbctechsol.decrypt.service.FileHandlerService;
import com.cbctechsol.decrypt.service.serviceImpl.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/*")
public class FileController {

    private final FileHandlerService fileHandlerService;


    @Autowired
    public FileController(FileHandlerService fileWriterService) {
        this.fileHandlerService = fileWriterService;
    }

    public ResponseEntity<String> writeToFile(@RequestBody String content,@RequestBody String fileName, @RequestBody boolean status) {
        try {
            fileHandlerService.writeToFile(content,fileName, status);
            return ResponseEntity.ok("File written successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error writing file.");
        }
    }
}
