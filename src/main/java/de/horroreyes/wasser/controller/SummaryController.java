package de.horroreyes.wasser.controller;

import de.horroreyes.wasser.model.Summary;
import de.horroreyes.wasser.services.SummaryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

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

    @GetMapping("/sendSummary")
    public boolean sendSummary() {
        return summaryService.sendSummary();
    }

    @GetMapping(value = "/fillSummary", produces = MediaType.TEXT_PLAIN_VALUE)
    public String fillSummary() throws UnsupportedEncodingException {
        return summaryService.fillSummary();
    }
}
