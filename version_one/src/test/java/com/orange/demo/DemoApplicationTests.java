package com.orange.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

class DemoApplicationTests {

    // On crée une instance du Controller directement
    private HomeController homeController = new HomeController();

    // Test de la page d'accueil
    @Test
    void testHomePageView() {
        Model model = new ExtendedModelMap();
        String view = homeController.home(model);

        // On vérifie que la vue retournée est bien "index"
        assertEquals("index", view);
    }

    // Test que le message est bien "Ceci est un test !"
    @Test
    void testHomePageMessage() {
        Model model = new ExtendedModelMap();
        homeController.home(model);

        // On vérifie que le message est correct
        assertEquals("Ceci est un test !", model.getAttribute("message"));
    }

    // Test de la page MaGallerie
    @Test
    void testGaleriePageView() {
        Model model = new ExtendedModelMap();
        String view = homeController.magallerie(model);

        // On vérifie que la vue retournée est bien "MaGallerie"
        assertEquals("MaGallerie", view);
    }

    // Test que le message est bien "Ceci est la gallerie !"
    @Test
    void testGaleriePageMessage() {
        Model model = new ExtendedModelMap();
        homeController.magallerie(model);

        // On vérifie que le message est correct
        assertEquals("Ceci est la gallerie !", model.getAttribute("message"));
    }

    // Test de la page Account
    @Test
    void testAccountPageView() {
        Model model = new ExtendedModelMap();
        String view = homeController.account(model);

        // On vérifie que la vue retournée est bien "Account"
        assertEquals("Account", view);
    }

    // Test que le compte est bien initialisé
    @Test
    void testAccountInitialization() {
        Model model = new ExtendedModelMap();
        homeController.account(model);

        // On vérifie que le compte est présent dans le model
        Account account = (Account) model.getAttribute("account");
        assertEquals("Utilisateur", account.getUsername());
        assertEquals("user@example.com", account.getEmail());
        assertEquals(false, account.toString().contains("loggedIn=true"));
    }
}
