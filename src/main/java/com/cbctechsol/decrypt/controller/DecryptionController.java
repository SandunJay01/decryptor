package com.cbctechsol.decrypt.controller;


import com.cbctechsol.decrypt.service.DecryptionService;
import com.cbctechsol.decrypt.service.FileHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.IllegalFormatException;

@RestController
@Controller
@RequestMapping("/api/*")
/*
    This layer serves the decryption requests which are coming from listener. This controller interacts with the service layer which
    is consisting the business logic of this progarm
 */
public class DecryptionController {
    @Autowired
    private DecryptionService decryptionService;

    @Autowired
    private FileHandlerService fileHandlerService;

    /*
    Method which is used to create connection with service layer. First collects the returned decrypted text and then
    sends it to file writer to write into a new file. If any exception occur during this the error file will be saved
    in a different location
     */
    @GetMapping("decrypt")
    public void decrypt(@RequestParam String decrypt,@RequestParam String fileName) throws IOException {
        fileHandlerService.writeBackup(decrypt,fileName);
        String decryptedText;
        try {
            decryptedText = decryptionService.decrypt(decrypt);
            try {
                fileHandlerService.writeToFile(decryptedText,fileName, true);
            } catch (Exception e) {
                fileHandlerService.writeToFile(decrypt,fileName, false);
                e.printStackTrace();
            }
        } catch (Exception e) {
            fileHandlerService.writeToFile(decrypt,fileName, false);
            e.printStackTrace();
        }
    }

}

