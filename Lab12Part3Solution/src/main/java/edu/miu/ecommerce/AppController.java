package edu.miu.ecommerce;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    @GetMapping("/shop")
    public String shop(){
        return "Welcome to the shop";
    }

    @GetMapping("/orders")
    public String orders(){
        return "Welcome to the orders";
    }

    @GetMapping("/payments")
    public String payments(){
        return "Finance Department - payment section";
    }

    @GetMapping("/managers")
    public String manager(){
        return "For managers only";
    }

    @GetMapping("/topmanager")
    public String topManager(){
        return "For top managers only";
    }

}
