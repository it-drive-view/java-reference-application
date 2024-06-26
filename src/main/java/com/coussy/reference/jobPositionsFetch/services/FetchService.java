package com.coussy.reference.jobPositionsFetch.services;

import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.SchedulerDatabase;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.SchedulerDatabaseRepository;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StopWatch;

import java.time.LocalDate;
import java.util.List;

public class FetchService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(FetchJobOrchestrator.class);

    private final SchedulerDatabaseRepository schedulerDatabaseRepository;
    private final ApplicationContext applicationContext;
    private final List<String> fetchJobsImplementations;

    public FetchService(List<String> fetchJobsImplementations, ApplicationContext applicationContext, SchedulerDatabaseRepository schedulerDatabaseRepository) {
        this.fetchJobsImplementations = fetchJobsImplementations;
        this.applicationContext = applicationContext;
        this.schedulerDatabaseRepository = schedulerDatabaseRepository;
    }

    public void fetch() {

        for (String implementation : fetchJobsImplementations) {

            SchedulerDatabase schedule = schedulerDatabaseRepository.findByImplementationAndLastFetchedAt(implementation, LocalDate.now());
            if (schedule != null) {
                LOGGER.info("implementation %s : jobs positions have already been fetched this day. skipping..".formatted(implementation));
                continue;
            }

            FetchJobs fetchService;
            try {
                fetchService = (FetchJobs) applicationContext.getBean(implementation);
            } catch (Exception e) {
                // c'est pas dangereux ici de ne pas logguer ???????
                // 1- appelé par le front ent --> on envoie une exception
                // 2- appelé par le scheduler interne  --> on loggue
                LOGGER.error("requested implementation [%s] does not seem to be handled by the application.".formatted(implementation));
                throw new IllegalArgumentException("requested implementation [%s] does not seem to be handled by the application.".formatted(implementation));
            }

            LOGGER.info("about to fetch job positions for implementation: %s.".formatted(implementation));
            StopWatch watch = new StopWatch();
            watch.start();
            fetchService.fetch();
            persistScheduler(implementation);
            watch.stop();
            LOGGER.info("fetched job positions for implementation: %s. time elapsed: %s ms.".formatted(implementation, watch.getTotalTimeMillis()));
        }
    }

    private void persistScheduler(String implementation) {

        SchedulerDatabase schedule = schedulerDatabaseRepository.findByImplementation(implementation);
        if (schedule != null) {
            schedule.setLastFetchedAt(LocalDate.now());
        } else {
            schedule = new SchedulerDatabase(implementation, LocalDate.now());
        }
        schedulerDatabaseRepository.save(schedule);
    }

}
