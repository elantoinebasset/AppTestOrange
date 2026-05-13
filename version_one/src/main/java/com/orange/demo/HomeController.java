package com.orange.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

}
