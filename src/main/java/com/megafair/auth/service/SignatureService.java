package com.megafair.auth.service;

import jakarta.inject.Singleton;

import static java.lang.System.nanoTime;

@Singleton
public class SignatureService {

    //Here must be logic for signature generation
    public String generateRandomSignature() {
        return String.valueOf(nanoTime());
    }
}
