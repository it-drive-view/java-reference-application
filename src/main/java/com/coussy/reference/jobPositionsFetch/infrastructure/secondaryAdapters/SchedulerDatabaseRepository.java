package com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface SchedulerDatabaseRepository extends CrudRepository<SchedulerDatabase, UUID> {

    SchedulerDatabase findByImplementationAndFetchedAt(String implementation, LocalDate fetchedAt);

    SchedulerDatabase findByImplementation(String implementation);

}
