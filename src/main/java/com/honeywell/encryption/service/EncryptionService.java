package com.honeywell.encryption.service;

import com.honeywell.encryption.utils.AESEncryption;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    public AESEncryption aesEncryption;
    public EncryptionService() {
        aesEncryption = new AESEncryption();
    }

    public String encryptText(String message) {
        return aesEncryption.encrypt(message);
    }

    public String decryptText(String message) {
        return aesEncryption.decrypt(message);
    }
}
