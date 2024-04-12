package com.coussy.reference.jobPositionsFetch.services;

import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;

public class FetchJobOrchestrator implements ApplicationRunner {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(FetchJobOrchestrator.class);

    private final FetchService fetchService;

    public FetchJobOrchestrator(FetchService fetchService) {
        this.fetchService = fetchService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        fetchService.fetch();
    }

    // each day at 11:10 pm
    @Scheduled(cron = "0 10 23 * * ?")
    public void schedule() {
        fetchService.fetch();
    }

}
