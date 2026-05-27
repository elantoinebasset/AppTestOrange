package com.orange.demo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * Classe de test pour les opérations de base de données (BDD - Behavior Driven Development)
 * Teste la connexion et les opérations CRUD sur la table Account
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class DemoAppBdd {

    @Autowired
    private AccountRepository accountRepository;

    // ===== TESTS DE CONNEXION À LA BASE DE DONNÉES =====

    /**
     * Test de connexion à la base de données
     * Vérifie que le repository est correctement injecté et accessible
     */
    @Test
    void testDatabaseConnection() {
        assertNotNull(accountRepository, "Le repository ne doit pas être null");
    }

    /**
     * Test de sauvegarde d'un compte dans la base de données
     * Crée un nouveau compte et le sauvegarde
     */
    @Test
    void testSaveAccountToDatabase() {
        // Créer un nouveau compte
        Account newAccount = new Account();
        newAccount.setUsername("testUser");
        newAccount.setEmail("test@example.com");
        newAccount.setPassword("testPassword123");
        newAccount.setLoggedIn(false);

        // Sauvegarder le compte dans la base de données
        Account savedAccount = accountRepository.save(newAccount);

        // Vérifier que le compte a été sauvegardé avec un ID
        assertNotNull(savedAccount.getId(), "L'ID du compte sauvegardé ne doit pas être null");
        assertEquals("testUser", savedAccount.getUsername());
        assertEquals("test@example.com", savedAccount.getEmail());
    }

    /**
     * Test de récupération d'un compte par username
     * Crée un compte, le sauvegarde, puis le récupère par nom d'utilisateur
     */
    @Test
    void testFindAccountByUsername() {
        // Créer et sauvegarder un compte
        Account account = new Account();
        account.setUsername("findMeUser");
        account.setEmail("findme@example.com");
        account.setPassword("password");
        accountRepository.save(account);

        // Récupérer le compte par username
        Account foundAccount = accountRepository.findByUsername("findMeUser");

        // Vérifier que le compte a été trouvé
        assertNotNull(foundAccount, "Le compte doit être trouvé dans la base de données");
        assertEquals("findMeUser", foundAccount.getUsername());
        assertEquals("findme@example.com", foundAccount.getEmail());
    }

    /**
     * Test de récupération d'un compte par email
     * Crée un compte, le sauvegarde, puis le récupère par email
     */
    @Test
    void testFindAccountByEmail() {
        // Créer et sauvegarder un compte
        Account account = new Account();
        account.setUsername("emailUser");
        account.setEmail("email@example.com");
        account.setPassword("password");
        accountRepository.save(account);

        // Récupérer le compte par email
        Account foundAccount = accountRepository.findByEmail("email@example.com");

        // Vérifier que le compte a été trouvé
        assertNotNull(foundAccount, "Le compte doit être trouvé par email");
        assertEquals("emailUser", foundAccount.getUsername());
    }

    /**
     * Test de mise à jour d'un compte
     * Crée un compte, le récupère, le modifie et vérifie les changements
     */
    @Test
    void testUpdateAccountInDatabase() {
        // Créer et sauvegarder un compte initial
        Account account = new Account();
        account.setUsername("updateMe");
        account.setEmail("update@example.com");
        account.setPassword("oldPassword");
        Account savedAccount = accountRepository.save(account);

        // Récupérer le compte
        Account foundAccount = accountRepository.findByUsername("updateMe");
        assertNotNull(foundAccount);

        // Mettre à jour le compte
        foundAccount.setPassword("newPassword");
        foundAccount.setLoggedIn(true);
        accountRepository.save(foundAccount);

        // Vérifier que les modifications ont été sauvegardées
        Account updatedAccount = accountRepository.findByUsername("updateMe");
        assertEquals("newPassword", updatedAccount.getPassword());
        assertEquals(true, updatedAccount.getLoggedIn());
    }

    /**
     * Test de suppression d'un compte
     * Crée un compte, le sauvegarde, puis le supprime
     */
    @Test
    void testDeleteAccountFromDatabase() {
        // Créer et sauvegarder un compte
        Account account = new Account();
        account.setUsername("deleteMe");
        account.setEmail("delete@example.com");
        account.setPassword("password");
        Account savedAccount = accountRepository.save(account);
        Long accountId = savedAccount.getId();

        // Vérifier que le compte existe
        assertTrue(accountRepository.existsById(accountId), "Le compte doit exister avant suppression");

        // Supprimer le compte
        accountRepository.deleteById(accountId);

        // Vérifier que le compte a été supprimé
        assertFalse(accountRepository.existsById(accountId), "Le compte ne doit pas exister après suppression");
    }

    /**
     * Test du nombre total de comptes dans la base de données
     * Crée plusieurs comptes et vérifie qu'ils sont tous sauvegardés
     */
    @Test
    void testFindAllAccounts() {
        // Créer et sauvegarder plusieurs comptes
        Account account1 = new Account();
        account1.setUsername("allUser1");
        account1.setEmail("user1@example.com");

        Account account2 = new Account();
        account2.setUsername("allUser2");
        account2.setEmail("user2@example.com");

        accountRepository.save(account1);
        accountRepository.save(account2);

        // Récupérer le nombre total de comptes
        long totalAccounts = accountRepository.count();

        // Vérifier qu'il y a au moins 2 comptes
        assertTrue(totalAccounts >= 2, "Il doit y avoir au moins 2 comptes dans la base de données");
    }

    /**
     * Test de persistance des données
     * Vérifie que un compte créé persiste après plusieurs appels
     */
    @Test
    void testAccountPersistence() {
        // Créer un compte unique
        Account account = new Account();
        account.setUsername("persistenceTest" + System.currentTimeMillis());
        account.setEmail("persistence@example.com");
        account.setPassword("persistencePassword");

        // Sauvegarder
        Account savedAccount = accountRepository.save(account);
        Long savedId = savedAccount.getId();

        // Récupérer immédiatement
        Account retrievedAccount = accountRepository.findById(savedId).orElse(null);
        assertNotNull(retrievedAccount, "Le compte doit être récupérable immédiatement après sauvegarde");
        assertEquals(account.getUsername(), retrievedAccount.getUsername());
    }
}
