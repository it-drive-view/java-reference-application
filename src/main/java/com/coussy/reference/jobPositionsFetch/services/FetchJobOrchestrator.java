package com.coussy.reference.jobPositionsFetch.services;

import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StopWatch;

import java.util.List;

public class FetchJobOrchestrator {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(FetchJobOrchestrator.class);

    private final List<String> fetchJobsImplementations;
    private final ApplicationContext applicationContext;

    public FetchJobOrchestrator(List<String> fetchJobsImplementations, ApplicationContext applicationContext) {
        this.fetchJobsImplementations = fetchJobsImplementations;
        this.applicationContext = applicationContext;
    }

    public void fetch() {
        for (String implementation : fetchJobsImplementations) {
            FetchJobs fetchService = (FetchJobs) applicationContext.getBean(implementation);
            LOGGER.info("about to fetch job positions for implementation: %s.".formatted(implementation));
            StopWatch watch = new StopWatch();
            watch.start();
            fetchService.fetch();
            watch.stop();
            LOGGER.info("fetched job positions for implementation: %s. time elapsed: %s ms.".formatted(implementation, watch.getTotalTimeMillis()));
        }
    }

}
