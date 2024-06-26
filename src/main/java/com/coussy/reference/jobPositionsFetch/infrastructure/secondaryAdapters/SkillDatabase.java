package com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name= "skill")
public class SkillDatabase {

    public SkillDatabase() {}

    public SkillDatabase(String skill, JobPositionDatabase jobPositionDatabase) {
        this.skill = skill;
        this.jobPositionDatabase = jobPositionDatabase;
    }

    @Id
    @GeneratedValue
    private UUID skill_uuid;

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

    public String getSkill() {
        return skill;
    }
}
