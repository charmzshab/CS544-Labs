package edu.miu.Lab15PartD;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.messages.*;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
public class RagPDFService {
    private final VectorStore vectorStore;
    private final ChatClient chat;

    public RagPdfService(VectorStore vectorStore, ChatClient.Builder chatClientBuilder) {
        this.vectorStore = vectorStore;
        this.chat = chatClientBuilder.build();
    }

    public RagResponse ask(String question, int k) {
        var req = SearchRequest.builder()
                .query(question)
                .topK(k)
                .build();
        List<Document> hits = vectorStore.similaritySearch(req);

        StringBuilder context = new StringBuilder();
        for (int i = 0; i < hits.size(); i++) {
            var d = hits.get(i);
            context.append("### Chunk ").append(i + 1).append("\n")
                    .append(d.getText()).append("\n")            // <-- was getContent()
                    .append("— meta: ").append(d.getMetadata()).append("\n\n");
        }

        var system = new SystemMessage("""
        You are a helpful real-estate assistant. Answer only using the provided CONTEXT.
        If the answer isn't in CONTEXT, say you don't know. Keep answers concise and
        cite chunk numbers like [chunk N] when relevant.
        """);

        var contextMsg = new UserMessage("CONTEXT:\n" + context);
        var user = new UserMessage("QUESTION: " + question);

        var answer = chat.prompt(new Prompt(List.of(system, contextMsg, user))).call().content();

        return new RagResponse(answer, hits, context.toString());
    }

    public record RagResponse(String answer, List<Document> usedChunks, String injectedContext) {}
}
