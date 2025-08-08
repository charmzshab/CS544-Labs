package edu.miu.Lab14PartBSolution;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab14PartBSolutionApplication implements CommandLineRunner {

    @Autowired
    private ProductRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Lab14PartBSolutionApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Product("Laptop", "High performance laptop for developers", 1200));
        repository.save(new Product("Smartphone", "Latest model with great camera", 800));
        repository.save(new Product("Headphones", "Noise cancelling over-ear headphones", 250));
    }

    @Bean
    public ChatClient chatClient(ChatModel chatModel, ChatMemory chatMemory) {
        ChatClient.Builder builder = ChatClient.builder(chatModel);

        builder.defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build());
        return builder.build();
    }

}
