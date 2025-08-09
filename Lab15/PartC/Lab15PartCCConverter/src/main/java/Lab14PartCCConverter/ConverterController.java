package Lab14PartCCConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConverterController {
    @Autowired
    CurrencyConverter converter;

    @PostMapping("/to-peso")
    public ConverterResponse dollarsToPeso (@RequestBody Double amount){
        return new ConverterResponse(converter.dollarsToPeso(amount));
    }
}
