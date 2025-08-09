package Lab15PartB;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.Document;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/search")
public class SearchController {
    private final SearchController service;

    public SearchController(SearchController service) {
        this.service = service;
    }

    @GetMapping
    public List<Map<String, Object>> search(@RequestParam String query, @RequestParam(defaultValue = "3") int k) {
        List<Document> results = service.search(query, k);

        return results.stream().map(d -> Map.of(
                "text", d.getText(),
                "metadata", d.getMetadata()
        )).toList();
    }
}
