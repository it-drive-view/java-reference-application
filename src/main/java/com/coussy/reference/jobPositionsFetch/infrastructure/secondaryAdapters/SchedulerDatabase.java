package com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(
        name = "scheduler",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"implementation"})
        }
)
public class SchedulerDatabase {

    @Id
    @GeneratedValue
    private UUID uuid;

    @Column(name = "implementation")
    private String implementation;

    @Column(name = "fetched_at")
    private LocalDate fetchedAt;

    public SchedulerDatabase(String implementation, LocalDate fetchedAt) {
        this.implementation = implementation;
        this.fetchedAt = fetchedAt;
    }

    public SchedulerDatabase() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDate getFetchedAt() {
        return fetchedAt;
    }

    public void setFetchedAt(LocalDate fetchedAt) {
        this.fetchedAt = fetchedAt;
    }

    public String getImplementation() {
        return implementation;
    }

    public void setImplementation(String implementation) {
        this.implementation = implementation;
    }
}
