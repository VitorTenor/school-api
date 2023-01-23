package com.myapi.api.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class CripSenha {

    public static String criptografarSenha(String senha){
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder.encode(senha);
        } catch (Exception e) {
            return "Erro ao criptografar senha";
        }
    }
    public static boolean validarSenha(String senha, String senhaCriptografada){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(senha, senhaCriptografada);
    }
}
