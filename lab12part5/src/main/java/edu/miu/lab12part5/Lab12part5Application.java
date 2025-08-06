package edu.miu.lab12part5;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
public class Lab12part5Application implements CommandLineRunner {
    private static final String BASE_URL = "http://localhost:8080/api";
    private static final String SIGNIN_URL = "http://localhost:8080/auth/signin";

	public static void main(String[] args) {
		SpringApplication.run(Lab12part5Application.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        // Public endpoint
        callEndpoint(client, "/all", null);

        // Login and get tokens
        String userToken = loginAndGetToken(client, "rshaba@gmail.com", "password");
        String adminToken = loginAndGetToken(client, "admin@admin.com", "password");

        // Secured endpoints
        callEndpoint(client, "/users", userToken);  // Requires user token
        callEndpoint(client, "/admins", adminToken); // Requires admin token
    }

    private String loginAndGetToken(HttpClient client, String email, String password) throws Exception {
        String jsonBody = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(SIGNIN_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response.body());
            String token = jsonNode.get("token").asText();
            System.out.println("Token for " + email + ": " + token);
            return token;
        } else {
            System.out.println("Failed to log in as " + email);
            return null;
        }


    }

    private void callEndpoint(HttpClient client, String path, String jwtToken) {
        try {
            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL + path))
                    .GET();

            if (jwtToken != null) {
                builder.header("Authorization", "Bearer " + jwtToken);
            }

            HttpRequest request = builder.build();

            System.out.println("\nCalling " + path + (jwtToken != null ? " with token" : " (no auth)"));
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status: " + response.statusCode());
            System.out.println("Response: " + response.body());

        } catch (Exception e) {
            System.out.println("Error calling " + path + ": " + e.getMessage());
        }
    }

}
