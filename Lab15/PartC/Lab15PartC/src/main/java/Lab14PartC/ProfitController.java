package Lab14PartC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/profits")
public class ProfitController {
    @Autowired
    ProfitRepository profitRepository;

    @GetMapping("/profits")
    public Profit getProductsInfo(@RequestParam("month") String month) {
        return profitRepository.findByMonth(month).get();
    }
}
