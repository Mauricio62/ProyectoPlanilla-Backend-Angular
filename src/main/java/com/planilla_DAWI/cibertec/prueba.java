package com.planilla_DAWI.cibertec;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class prueba {
    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String numero = "123456789";
        String contrauno= encoder.encode(numero);
        System.out.println(contrauno);
    }
}
