package com.orange.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class GalerieController {

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file) {

        String fileName = file.getOriginalFilename();

        if (fileName == null || fileName.isEmpty()) {
            return ResponseEntity.badRequest().body("Nom de fichier invalide");
        }

        return ResponseEntity.ok(fileName);
    }
}
