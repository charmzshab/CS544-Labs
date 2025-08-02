package jms;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class CalculatorCommandProducer implements CommandLineRunner {

    private final JmsTemplate jmsTemplate;

    public CalculatorCommandProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        // Simple script: +7, +8, *2, -3
        List<CalculatorCommand> script = List.of(
                new CalculatorCommand("+", 7),
                new CalculatorCommand("+", 8),
                new CalculatorCommand("*", 2),
                new CalculatorCommand("-", 3)
        );

        for (CalculatorCommand cmd : script) {
            System.out.printf("Sender will send: %s %d%n", cmd.getOperator(), cmd.getValue());
            jmsTemplate.convertAndSend("testTopic", cmd);
            Thread.sleep(300);
        }
    }
}
