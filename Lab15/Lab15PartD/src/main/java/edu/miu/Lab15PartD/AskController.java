package edu.miu.Lab15PartD;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ask")
public class AskController {
    private final RagPdfService rag;

    public AskController(RagPdfService rag) {
        this.rag = rag;
    }

    @GetMapping
    public RagPdfService.RagResponse ask(@RequestParam("query") String query, @RequestParam(value = "count", defaultValue = "4") int count
    ) {
        return rag.ask(query, count);
    }
}
