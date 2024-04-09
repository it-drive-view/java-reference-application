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

    @Column(name = "last_fetched_at")
    private LocalDate lastFetchedAt;

    public SchedulerDatabase(String implementation, LocalDate lastFetchedAt) {
        this.implementation = implementation;
        this.lastFetchedAt = lastFetchedAt;
    }

    public SchedulerDatabase() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDate getLastFetchedAt() {
        return lastFetchedAt;
    }

    public void setLastFetchedAt(LocalDate lastFetchedAt) {
        this.lastFetchedAt = lastFetchedAt;
    }

    public String getImplementation() {
        return implementation;
    }

    public void setImplementation(String implementation) {
        this.implementation = implementation;
    }
}
