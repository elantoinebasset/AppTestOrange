package com.orange.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Ceci est un test !");
        return "index"; // correspond à src/main/resources/templates/index.html
    }

    @GetMapping("/MaGallerie")
    public String magallerie(Model model) {
        model.addAttribute("message", "Ceci est la gallerie !");
        return "MaGallerie"; // correspond à src/main/resources/templates/MaGallerie.html
    }

    @GetMapping("/")
    public String monindex(Model model) {
        model.addAttribute("messageindex", "Ceci est un message pour valider l'envoie");
        return "index";
    }

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

}
