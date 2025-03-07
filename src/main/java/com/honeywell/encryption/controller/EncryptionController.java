package com.honeywell.encryption.controller;

import com.honeywell.encryption.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend access
public class EncryptionController {

    @Autowired
    public EncryptionService encryptionService;

    @PostMapping("/encrypt")
    public ResponseEntity<Object> encryptText(@RequestBody Map<String, String> message) {
        Map<String, String> response = new HashMap<>();
        try {
            response.put("encryptedText", encryptionService.encryptText(message.get("message")));
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.internalServerError().body(response);
    }

    @PostMapping("/decrypt")
    public ResponseEntity<Object> decryptText(@RequestBody Map<String, String> cipherText) {
        Map<String, String> response = new HashMap<>();
        try{
            response.put("decryptedText", encryptionService.decryptText(cipherText.get("message")));
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.internalServerError().body(response);
    }
}
