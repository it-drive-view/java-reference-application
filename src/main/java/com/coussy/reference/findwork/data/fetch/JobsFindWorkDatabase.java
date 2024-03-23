package com.coussy.reference.findwork.data.fetch;

import com.coussy.reference.findwork.data.fetch.http.JobsFindWorkSkillDatabase;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name= "jobs_find_work")
public class JobsFindWorkDatabase {

    public JobsFindWorkDatabase() {}

    @GeneratedValue
    @Id
    UUID uuid;

    String id;

//    @OneToMany(mappedBy = "jobsFindWorkDatabase")
    @OneToMany
    private List<JobsFindWorkSkillDatabase> JobsFindWorkSkillsDatabase;

    public List<JobsFindWorkSkillDatabase> getJobsFindWorkSkillsDatabase() {
        return JobsFindWorkSkillsDatabase;
    }

    public void setJobsFindWorkSkillsDatabase(List<JobsFindWorkSkillDatabase> jobsFindWorkSkillsDatabase) {
        JobsFindWorkSkillsDatabase = jobsFindWorkSkillsDatabase;
    }

    @Transient
    List<String> keywords;

    LocalDateTime datePosted;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public LocalDateTime getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(LocalDateTime datePosted) {
        this.datePosted = datePosted;
    }
}
