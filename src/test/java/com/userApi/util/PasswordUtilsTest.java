package com.userApi.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilsTest {

    private final PasswordUtils utils = new PasswordUtils();

    @Test
    void generateSalt_deuxSaltsSontDifferents() {
        // assertNotEquals(a, b): test success if a et b deffrentes
        assertNotEquals(utils.generateSalt(), utils.generateSalt());
    }

    @Test
        //meme password + meme salt = toujour meme hash
    void hashPassword_memeMotDePasseEtMemeSalt_donneLeMemeHash() {
        String salt = utils.generateSalt();
        assertEquals(utils.hashPassword("12345678", salt), utils.hashPassword("12345678", salt));
    }

    @Test
        // hash ne doit pas etre comme password
    void hashPassword_ResultatDifferentDuMotDePasseEnClair() {
        String salt = utils.generateSalt();
        assertNotEquals("12345678", utils.hashPassword("12345678", salt));
    }

    @Test
        // meme password avec deux salts defferenets = deux hash defferentes
        // Hadchi howa l7ikma dyal salt: joj users b nafs password ma ykounouch 3ndhom nafs hash f base de données.
    void hashPassword_saltsDifferents_donnentDesHashDifferents() {
        // a chaque fois nouvelle salt
        String h1 = utils.hashPassword("12345678", utils.generateSalt());
        String h2 = utils.hashPassword("12345678", utils.generateSalt());
        assertNotEquals(h1, h2);
    }

    @Test
        // verifier password (password + salt = hash)
    void verifypassword_bonMotDePasse_retourneTrue() {
        String salt = utils.generateSalt();
        String hash = utils.hashPassword("12345678", salt);
        assertTrue(utils.verifypassword("12345678", hash, salt));
    }

    @Test
    //tester mauvaise password
    void verifypassword_mauvaisMotDePasse_retourneFalse() {
        String salt = utils.generateSalt();
        String hash = utils.hashPassword("12345678", salt);
        assertFalse(utils.verifypassword("autre-mot-de-passe", hash, salt));
    }
}