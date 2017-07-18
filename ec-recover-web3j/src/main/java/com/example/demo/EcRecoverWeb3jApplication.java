package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.web3j.crypto.CipherException;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class EcRecoverWeb3jApplication implements CommandLineRunner {

    @Autowired
    private RawTransactionManager rawTransactionManager;

    public static void main(String[] args) throws IOException, CipherException, ExecutionException, InterruptedException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {

        SpringApplication.run(EcRecoverWeb3jApplication.class, args);


    }

    @Override
    public void run(String... args) throws Exception {
        rawTransactionManager.createRawTransaction();
    }
}
