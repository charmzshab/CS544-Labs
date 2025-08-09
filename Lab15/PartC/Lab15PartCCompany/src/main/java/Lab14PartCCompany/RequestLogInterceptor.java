package Lab14PartCCompany;

import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;

public class RequestLogInterceptor implements CallAdvisor {
    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        // Log request details
        System.out.println("----- REQUEST: " + chatClientRequest);

        // Make the actual call
        ChatClientResponse response = callAdvisorChain.nextCall(chatClientRequest);

        // Log response details
        System.out.println("----- RESPONSE: " + response);

        return response;
    }

    @Override
    public String getName() {
        return "RequestLogAdvisor";
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
