package com.coussy.reference.job.platform.fetch;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "job_position", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"source", "job_platform_id", "posted_at"})})
public class JobPositionDatabase {

    public JobPositionDatabase() {
    }

    public JobPositionDatabase(String source, String jobPlatformId, LocalDateTime postedAt) {
        this.source = source;
        this.jobPlatformId = jobPlatformId;
        this.postedAt = postedAt;
    }

    @GeneratedValue
    @Id
    @Column(name = "position_uuid")
    UUID positionUuid;

    String source;

    @Column(name = "job_platform_id")
    String jobPlatformId;

    public String getJobPlatformId() {
        return jobPlatformId;
    }

    public void setJobPlatformId(String jobPlatformId) {
        this.jobPlatformId = jobPlatformId;
    }

    @OneToMany(mappedBy = "jobPositionDatabase")
    private List<SkillDatabase> JobsFindWorkSkillsDatabase;

    public List<SkillDatabase> getJobsFindWorkSkillsDatabase() {
        return JobsFindWorkSkillsDatabase;
    }

    public void setJobsFindWorkSkillsDatabase(List<SkillDatabase> jobsFindWorkSkillsDatabase) {
        JobsFindWorkSkillsDatabase = jobsFindWorkSkillsDatabase;
    }

    @Column(name = "posted_at")
    LocalDateTime postedAt;

    public UUID getPositionUuid() {
        return positionUuid;
    }

    public void setPositionUuid(UUID positionUuid) {
        this.positionUuid = positionUuid;
    }

    public LocalDateTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
