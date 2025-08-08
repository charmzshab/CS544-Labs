package edu.miu.Lab14PartBSolution;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ChatClient chatClient;

    @GetMapping("/ask")
    public String ask(@RequestParam String question) {
        List<Product> products = repository.findAll();

        // Build product list string for prompt stuffing
        StringBuilder productData = new StringBuilder();
        products.forEach(p -> productData.append(
                String.format("Name: %s, Description: %s, Price: %.2f\n",
                        p.getName(), p.getDescription(), p.getPrice())
        ));

        return chatClient.prompt()
                .system("This is the product catalog:\n" + productData)
                .user(question)
                .call()
                .content();
    }
}
