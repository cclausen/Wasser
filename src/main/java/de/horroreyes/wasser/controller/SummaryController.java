package de.horroreyes.wasser.controller;

import de.horroreyes.wasser.model.Duty;
import de.horroreyes.wasser.model.Summary;
import de.horroreyes.wasser.services.DutyService;
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
    private final DutyService dutyService;

    public SummaryController(SummaryService summaryService, DutyService dutyService) {
        this.summaryService = summaryService;
        this.dutyService = dutyService;
    }

    @GetMapping("/summary")
    public Summary summary() {
        Duty today = dutyService.getToday();
        return summaryService.summary(today);
    }

    @GetMapping("/sendSummary")
    public boolean sendSummary() {
        Duty today = dutyService.getToday();
        return summaryService.sendSummary(today);
    }

    @GetMapping(value = "/fillSummary", produces = MediaType.TEXT_PLAIN_VALUE)
    public String fillSummary() throws UnsupportedEncodingException {
        Duty today = dutyService.getToday();
        return summaryService.fillSummary(today);
    }
}
