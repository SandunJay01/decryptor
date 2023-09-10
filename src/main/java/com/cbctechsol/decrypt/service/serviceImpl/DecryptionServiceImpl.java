package com.cbctechsol.decrypt.service.serviceImpl;

import com.cbctechsol.decrypt.model.AES;
import com.cbctechsol.decrypt.service.DecryptionService;
import org.springframework.stereotype.Service;


/*
Contains the business logic related to decryption process
 */
@Service
public class DecryptionServiceImpl implements DecryptionService {

    private static final String AES_KEY = "TOKEN_SECURITY_MOGLIX_AES_KEY_IN_JWT";

/*
    @Override
    public String encrypt(String data) {
        AES aes = new AES(AES_KEY);
        return aes.encrypt(data);
    }

 */

    @Override
    public String decrypt(String data) {
        AES aes = new AES(AES_KEY);
        return aes.decrypt(data);
    }
}

