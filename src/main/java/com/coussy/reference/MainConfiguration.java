package com.coussy.reference;

import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.SchedulerDatabaseRepository;
import com.coussy.reference.jobPositionsFetch.services.FetchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MainConfiguration {

    @Bean
    public FetchService fetchService(@Value("#{'${job.platforms}'.split(',')}") List<String> fetchJobsImplementations, ApplicationContext applicationContext, SchedulerDatabaseRepository schedulerDatabaseRepository) {
        return new FetchService(fetchJobsImplementations, applicationContext, schedulerDatabaseRepository);
    }

}
