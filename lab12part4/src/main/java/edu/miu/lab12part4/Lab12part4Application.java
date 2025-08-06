package edu.miu.lab12part4;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@SpringBootApplication
public class Lab12part4Application implements CommandLineRunner {
    private static final String BASE_URL = "http://localhost:8080";
    @Override
    public void run(String... args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        // 1. Access /shop without credentials
        callEndpoint(client, "/shop", null, null);

        // 2. Access /orders as Bob
        callEndpoint(client, "/orders", "bob", "password");

        // 3. Access /payments as Bob (should fail)
        callEndpoint(client, "/payments", "bob", "password");

        // 4. Access /payments as Mary
        callEndpoint(client, "/payments", "mary", "password");
    }

    public static void main(String[] args) {
		SpringApplication.run(Lab12part4Application.class, args);
	}

    private static void callEndpoint(HttpClient client, String path, String username, String password) {
        try {
            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL + path))
                    .GET();

            if (username != null && password != null) {
                String encodedAuth = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
                builder.header("Authorization", "Basic " + encodedAuth);
            }

            HttpRequest request = builder.build();

            System.out.println("\nCalling " + path + (username != null ? " as " + username : " (no auth)"));
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status: " + response.statusCode());
            System.out.println("Response: " + response.body());

        } catch (Exception e) {
            System.out.println("Error calling " + path + ": " + e.getMessage());
        }
    }

}
