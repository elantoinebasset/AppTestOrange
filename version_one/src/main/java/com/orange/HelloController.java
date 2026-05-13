package com.orange;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    // private final PrenomRepository prenomRepository;

    // public HelloController(PrenomRepository prenomRepository) {
    //     this.prenomRepository = prenomRepository;
    // }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Remplis le formulaire !");
        // model.addAttribute("prenoms", prenomRepository.findAll());
        return "index";
    }

    // @PostMapping("/bonjour")
    // public String bonjour(@RequestParam String prenom, Model model) {
    //     prenomRepository.save(new Prenom(prenom));
    //     model.addAttribute("message", "Bonjour " + prenom + " !");
    //     model.addAttribute("prenoms", prenomRepository.findAll());
    //     return "index";
    // }
}
