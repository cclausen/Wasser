package de.horroreyes.wasser.controller;

import de.horroreyes.wasser.model.Day;
import de.horroreyes.wasser.model.Summary;
import de.horroreyes.wasser.services.DayService;
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
    private final DayService dayService;

    public SummaryController(SummaryService summaryService, DayService dayService) {
        this.summaryService = summaryService;
        this.dayService = dayService;
    }

    @GetMapping("/summary")
    public Summary summary() {
        Day today = dayService.getToday();
        return summaryService.summary(today);
    }

    @GetMapping("/sendSummary")
    public boolean sendSummary() {
        Day today = dayService.getToday();
        return summaryService.sendSummary(today);
    }

    @GetMapping(value = "/fillSummary", produces = MediaType.TEXT_PLAIN_VALUE)
    public String fillSummary() throws UnsupportedEncodingException {
        Day today = dayService.getToday();
        return summaryService.fillSummary(today);
    }
}
