package com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface SchedulerDatabaseRepository extends CrudRepository<SchedulerDatabase, UUID> {

    SchedulerDatabase findByImplementationAndLastFetchedAt(String implementation, LocalDate lastFetchedAt);

    SchedulerDatabase findByImplementation(String implementation);

}
