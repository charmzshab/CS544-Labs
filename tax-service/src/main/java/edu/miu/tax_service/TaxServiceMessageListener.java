package edu.miu.tax_service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component

public class TaxServiceMessageListener {
    @JmsListener(destination = "testTopic")
    public void receiveMessage(String message) {
        System.out.println(message);
    }

}
