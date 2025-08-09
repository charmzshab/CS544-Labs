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
        repository.save(new Product("MacBook Pro", "High performance laptop for developers", 1200));
        repository.save(new Product("Iphone 16", "Latest model with great camera", 800));
        repository.save(new Product("Beats By Dre", "Noise cancelling over-ear headphones", 250));
        repository.save(new Product("PlayStation 6", "Best gaming console", 400));
        repository.save(new Product("HD Pavilion Security Cameras", "Have the security of your home in your palms", 250));
    }

    @Bean
    public ChatClient chatClient(ChatModel chatModel, ChatMemory chatMemory) {
        ChatClient.Builder builder = ChatClient.builder(chatModel);

        builder.defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build());
        return builder.build();
    }

}
