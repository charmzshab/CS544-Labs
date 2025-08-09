package Lab14PartCCompany;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConverterService {
    private final RestTemplate restTemplate;

    public ConverterService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Tool(description = "convert the amount of money from dollars to pesos")
    public ConverterResponse convert(ConverterRequest request) {
        String url = "http://localhost:8082/to-peso";
        return restTemplate.postForObject(url, request.amount(), ConverterResponse.class);
    }
}
