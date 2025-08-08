package edu.miu.pet_health_app;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PetHealthController {

    @Autowired
    private ChatClient chatClient;

    @GetMapping("/ask")
    public ResponseEntity<Map<String, String>> ask(@RequestParam String question) {
        String answer = chatClient.prompt()
                .system("You are a helpful pet healthcare assistant. Give concise and accurate advice.")
                .user(question)
                .call()
                .content();

        Map<String, String> result = new HashMap<>();
        result.put("question", question);
        result.put("answer", answer);
        return ResponseEntity.ok(result);
    }

}
