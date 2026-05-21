package com.orange.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class HomeController {

    private Account currentAccount = new Account();

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("account", currentAccount);
        model.addAttribute("message", "Ceci est un test !");
        return "index"; // correspond à src/main/resources/templates/index.html
    }

    @GetMapping("/MaGallerie")
    public String magallerie(Model model) {
        model.addAttribute("message", "Ceci est la gallerie !");
        model .addAttribute("account", currentAccount);
        return "MaGallerie"; // correspond à src/main/resources/templates/MaGallerie.html
    }

    @GetMapping("/Account")
    public String account(Model model) {
        model.addAttribute("account", currentAccount);
        return "Account";
    }

    @PostMapping("/Account")
    public String updateAccount(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "passwordConfirm", required = false) String passwordConfirm,
            RedirectAttributes redirectAttributes) {

        currentAccount.setLoggedIn(true);
        redirectAttributes.addFlashAttribute("successMessage", "Compte mis à jour avec succès!");
        return "redirect:/Account";
    }

    // Création de compte
    @GetMapping("/CreateAccount")
    public String createAccount(Model model) {
        model.addAttribute("account", currentAccount);
        return "CreateAccount";
    }

    @PostMapping("/CreateAccount")
    public String createAccountPost(
            @RequestParam(value="username", required = false) String username,
            @RequestParam(value="email", required = false) String email,
            @RequestParam(value="password", required = false) String password,
            @RequestParam(value="passwordConfirm", required = false) String passwordConfirm,
            RedirectAttributes redirectAttributes) {

        if (username != null && !username.isEmpty() &&
            email != null && !email.isEmpty() &&
            password != null && !password.isEmpty() &&
            password.equals(passwordConfirm)) {
            currentAccount.setUsername(username);
            currentAccount.setEmail(email);
            currentAccount.setPassword(password);
            currentAccount.setLoggedIn(true);
            redirectAttributes.addFlashAttribute("successMessage", "Compte créé avec succès!");
            return "redirect:/Account";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Mot de passe incorrect !");
            return "redirect:/CreateAccount";
        }
    }

    // Connexion
    @PostMapping("/LogToAccount")
    public String LogToAccount(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "password", required = false) String password,
            RedirectAttributes redirectAttributes) {
        if (username != null && password != null && email != null &&
            username.equals(currentAccount.getUsername()) &&
            email.equals(currentAccount.getEmail()) &&
            password.equals(currentAccount.getPassword())) {
            currentAccount.setLoggedIn(true);
            redirectAttributes.addFlashAttribute("successMessage", "Connecté avec succès!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Nom d'utilisateur, email ou mot de passe incorrect !");
        }

                return "redirect:/Account";
    }


    // Déconnexion
    @PostMapping("/logout")
    public String logout(RedirectAttributes redirectAttributes) {
        currentAccount.setLoggedIn(false);
        redirectAttributes.addFlashAttribute("successMessage", "Déconnecté avec succès!");
        return "redirect:/Account";
    }
}
