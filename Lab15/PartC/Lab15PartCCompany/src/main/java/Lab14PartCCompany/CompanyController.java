package Lab14PartCCompany;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {
    @Autowired
    ChatClient chatClient;
    @Autowired
    ConverterService converterService;
    @Autowired
    ProfitService profitService;

    @GetMapping("/company")
    public String getProfitOfCompanyForMonth(@RequestParam("message") String message) {
        return chatClient
                .prompt()
                .tools(profitService)
//                .tools(converterService)
                .user(message)
                .call()
                .content();
    }
}
