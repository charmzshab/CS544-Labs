package Lab14PartCCConverter;

import org.springframework.stereotype.Service;

@Service
public class CurrencyConverter {
    public double pesoToDollars (double amount){
        System.out.println("CurrencyConverter: converting "+amount+" dollars to pesos");
        return 61.10 * amount;
    }

    public double dollarsToPeso (double amount){
        return 0.019 * amount;
    }
}
