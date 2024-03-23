package com.coussy.reference.findwork.data.fetch.http;


import com.coussy.reference.findwork.data.fetch.JobPositionDatabase;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.util.UUID;

@Entity
@Table(name= "skill")
public class SkillDatabase {

    public SkillDatabase() {}

    @Id
    @GeneratedValue
    private UUID uuid;

    private String skill;

    @ManyToOne
    @JoinColumn(name = "position_uuid")
    private JobPositionDatabase jobPositionDatabase;

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setJobPositionDatabase(JobPositionDatabase jobPositionDatabase) {
        this.jobPositionDatabase = jobPositionDatabase;
    }
}
