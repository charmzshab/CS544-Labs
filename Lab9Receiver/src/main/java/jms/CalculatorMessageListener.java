package jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class CalculatorMessageListener {

    private final CalculatorService calculator;

    public CalculatorMessageListener(CalculatorService calculator) {
        this.calculator = calculator;
    }

    // Because spring.jms.pub-sub-domain=true, this destination is a Topic
    @JmsListener(destination = "testTopic")
    public void onMessage(CalculatorCommand cmd) {
        int result = calculator.apply(cmd.getOperator(), cmd.getValue());
        System.out.printf(
                "Receiver applied: %s %d -> result = %d%n",
                cmd.getOperator(), cmd.getValue(), result
        );
    }
}
