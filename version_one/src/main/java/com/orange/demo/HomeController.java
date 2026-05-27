package com.orange.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;



@Controller
public class HomeController {
    @Autowired
    private AccountRepository accountRepository;

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
    public String account(HttpSession session, Model model) {

        Account account = (Account) session.getAttribute("currentAccount");

        if (account == null) {
            System.out.println("No account in session, creating new account.");
            account = new Account();
        }

        model.addAttribute("account", account);
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
            @RequestParam(value="WhatsNumber", required = false) String WhatsNumber,

            RedirectAttributes redirectAttributes) {

        if (username != null && !username.isEmpty() &&
            email != null && !email.isEmpty() &&
            password != null && !password.isEmpty() &&
            password.equals(passwordConfirm) &&
            WhatsNumber != null && !WhatsNumber.isEmpty()
        ) {

            currentAccount.setUsername(username);
            currentAccount.setEmail(email);
            currentAccount.setPassword(password);
            currentAccount.setLoggedIn(true);
            currentAccount.setWhatsNumber(1);

            accountRepository.save(currentAccount);

            redirectAttributes.addFlashAttribute("successMessage", "Compt e créé avec succès!");
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
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        // On cherche l'utilisateur EN BASE et non en mémoire
        Account account = accountRepository.findByEmail(username);

        if (account != null &&
            account.getEmail().equals(email) &&
            account.getPassword().equals(password)) {

            // On stocke le compte trouvé en base dans la session
            session.setAttribute("currentAccount", account);
            redirectAttributes.addFlashAttribute("successMessage", "Connecté avec succès!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Identifiants incorrects !");
        }

        return "redirect:/Account";
    }



    // Déconnexion
    @PostMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {

        // On détruit la session
        session.invalidate();

        redirectAttributes.addFlashAttribute("successMessage", "Déconnecté avec succès!");
        return "redirect:/Account";
    }

}
