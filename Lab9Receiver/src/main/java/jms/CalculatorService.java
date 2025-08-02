package jms;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CalculatorService {
    private final AtomicInteger total = new AtomicInteger(0);

    public int apply(String op, int value) {
        return switch (op) {
            case "+" -> total.addAndGet(value);
            case "-" -> total.addAndGet(-value);
            case "*" -> total.updateAndGet(t -> t * value);
            default -> throw new IllegalArgumentException("Unsupported operator: " + op);
        };
    }

    public int current() { return total.get(); }
    public void reset() { total.set(0); }

}
