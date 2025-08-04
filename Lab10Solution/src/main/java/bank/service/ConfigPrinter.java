package bank.service;

import bank.domain.AppConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class ConfigPrinter {
    private final AppConfig config;
    public ConfigPrinter(AppConfig config){this.config = config;}

    @PostConstruct
    public void print(){
        System.out.println("App Name: " + config.getName());
        System.out.println("Countries: " + config.getCountries());
    }
}
