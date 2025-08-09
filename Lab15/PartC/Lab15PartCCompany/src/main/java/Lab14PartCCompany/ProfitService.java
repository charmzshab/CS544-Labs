package Lab14PartCCompany;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProfitService {
    private final RestTemplate restTemplate;

    public ProfitService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Tool(name="get_profit", description = "Get the profit data from at specific month")
    public ProfitResponse getPriceFromStockTicker(ProfitRequest profitRequest) {
        String month = profitRequest.month();
        String url = "http://localhost:8080/profits?month=" + month;
        ProfitResponse response = restTemplate.getForObject(url, ProfitResponse.class);

        return response;
    }
}
