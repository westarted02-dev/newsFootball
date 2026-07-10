package com.blitzScore.news.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GuardianPollingScheduler {

    private final GuardianFetcherService guardianFetcherService;

    public GuardianPollingScheduler(GuardianFetcherService guardianFetcherService) {
        this.guardianFetcherService = guardianFetcherService;
    }

    @Scheduled(fixedDelayString = "${guardian.polling.interval-ms:900000}", initialDelayString = "5000")
    public void poll() {
        guardianFetcherService.fetchAndSave();
    }
}
