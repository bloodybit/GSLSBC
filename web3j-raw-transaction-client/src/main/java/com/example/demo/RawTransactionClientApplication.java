package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class RawTransactionClientApplication implements CommandLineRunner {

    private final String USER_AGENT = "Mozilla/5.0";


    @Value("${server-url}")
    private String serverUrl;

    @Autowired
    private RawTransactionManager rawTransactionManager;

    public static void main(String[] args) {
        SpringApplication.run(RawTransactionClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String hexValue = rawTransactionManager.createRawTransaction();
        System.out.println("Transaction hash: " + sendPost(hexValue));


    }

    // HTTP POST request
    private String sendPost(String hexValue) throws Exception {



        String url = serverUrl + "/transaction/raw/" + hexValue;

        RestTemplate restTemplate = new RestTemplate();

        HashMap<String, String> headers = new HashMap<>();
        headers.put("User-Agent", USER_AGENT);
        headers.put("Accept-Language", "en-US,en;q=0.5");

        HttpEntity request = new HttpEntity(headers);

       String response = restTemplate.postForObject(url, request, String.class);
       return response;

    }
}
