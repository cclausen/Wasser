package de.horroreyes.wasser.controller;

import de.horroreyes.wasser.model.Summary;
import de.horroreyes.wasser.services.SummaryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/summary", produces = MediaType.APPLICATION_JSON_VALUE)
public class SummaryController {
    private final SummaryService summaryService;

    public SummaryController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @GetMapping("/summary")
    public Summary summary() {
        return summaryService.summary();
    }
}
