package com.coussy.reference.findwork.data.fetch;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.coussy.reference.findwork.data.fetch.SkillDatabase     ;

@Entity
@Table(name= "job_position")
// étudier l'annotation
// @DynamicUpdate
public class JobPositionDatabase {

    public JobPositionDatabase() {}

    public JobPositionDatabase(String source, String apiId, LocalDateTime datePosted) {
        this.source = source;
        this.apiId = apiId;
        this.datePosted = datePosted;
    }

    @GeneratedValue
    @Id
    @Column(name= "position_uuid")
    UUID positionUuid;

    String source;

    @Column(name= "api_id")
    String apiId;

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    @OneToMany(mappedBy = "jobPositionDatabase")
    private List<SkillDatabase> JobsFindWorkSkillsDatabase;

    public List<SkillDatabase> getJobsFindWorkSkillsDatabase() {
        return JobsFindWorkSkillsDatabase;
    }

    public void setJobsFindWorkSkillsDatabase(List<SkillDatabase> jobsFindWorkSkillsDatabase) {
        JobsFindWorkSkillsDatabase = jobsFindWorkSkillsDatabase;
    }

    LocalDateTime datePosted;

    public UUID getPositionUuid() {
        return positionUuid;
    }

    public void setPositionUuid(UUID positionUuid) {
        this.positionUuid = positionUuid;
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
