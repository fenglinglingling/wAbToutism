package com.abs;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptTest {
    public static void main(String[] args) {
        String password = "0122";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String miWen1 = encoder.encode(password);
        System.out.println(miWen1);
        System.out.println(encoder.matches("0122", miWen1));
    }
}
