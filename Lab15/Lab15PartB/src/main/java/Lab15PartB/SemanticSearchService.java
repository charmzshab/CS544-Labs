package Lab15PartB;

import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ai.document.Document;

import java.util.ArrayList;
import java.util.List;

@Service
public class SemanticSearchService {
    private final VectorStore vectorStore;

    public SemanticSearchService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public List<Document> search(String query, int k) {
        var req = SearchRequest.builder()
                .query(query)
                .topK(k)
                .build();
        return vectorStore.similaritySearch(req);
    }
}
