package com.honeywell.encryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController()
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend access
public class EncryptionController {

    @Autowired
    public EncryptionService encryptionService;

    @PostMapping("/encrypt")
    public ResponseEntity<Object> encryptText(@RequestBody Map<String, String> message) {
        Map<String, String> response = new HashMap<>();
        response.put("encryptedText", encryptionService.encryptText(message.get("message")));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/decrypt")
    public ResponseEntity<Object> decryptText(@RequestBody Map<String, String> cipherText) {
        Map<String, String> response = new HashMap<>();
        response.put("decryptedText", encryptionService.decryptText(cipherText.get("message")));

        return ResponseEntity.ok().body(response);
    }
}
