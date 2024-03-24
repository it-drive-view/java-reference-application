package com.coussy.reference.findwork.data.fetch;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name= "job_position")
// étudier l'annotation
// @DynamicUpdate
public class JobPositionDatabase {

    public JobPositionDatabase() {}

    @GeneratedValue
    @Id
    @Column(name= "position_uuid")
    UUID positionUuid;

    String source;

    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "jobPositionDatabase")
    private List<SkillDatabase> JobsFindWorkSkillsDatabase;

    public List<SkillDatabase> getJobsFindWorkSkillsDatabase() {
        return JobsFindWorkSkillsDatabase;
    }

    public void setJobsFindWorkSkillsDatabase(List<SkillDatabase> jobsFindWorkSkillsDatabase) {
        JobsFindWorkSkillsDatabase = jobsFindWorkSkillsDatabase;
    }

    @Transient
    List<String> keywords;

    LocalDateTime datePosted;

    public UUID getPositionUuid() {
        return positionUuid;
    }

    public void setPositionUuid(UUID positionUuid) {
        this.positionUuid = positionUuid;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
