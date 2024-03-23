package com.coussy.reference.findwork.data.fetch.http;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name= "jobs_find_work_skill")
public class JobsFindWorkSkillDatabase {

    public JobsFindWorkSkillDatabase() {}

    @Id
    @GeneratedValue
    private UUID uuid;

    private String skill;

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
