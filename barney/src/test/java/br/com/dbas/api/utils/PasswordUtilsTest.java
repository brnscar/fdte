package br.com.dbas.api.utils;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;

public class PasswordUtilsTest {

    private static final String SENHA = "barney";
    private final BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();

    @Test
    public void gerarBCrypt() {
        String hash = PasswordUtils.gerarBCrypt(SENHA);
        System.out.println(hash.toString());
        assertTrue(bCryptEncoder.matches(SENHA, hash));
    }
}