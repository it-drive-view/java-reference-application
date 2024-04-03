package com.coussy.reference.jobPositionsFetch.services;

import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.JobPositionDatabase;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.FindworkHttpClient;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.response.UpworkResponse;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.JobPositionDatabaseRepository;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.SkillDatabase;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.SkillDatabaseRepository;

import java.util.ArrayList;
import java.util.List;

public class SecondApiService implements FetchJobs {

    private final String SOURCE_NAME = "freejob.fr";

    private final FindworkHttpClient findworkHttpClient;
    private final JobPositionDatabaseRepository jobPositionDatabaseRepository;
    private final SkillDatabaseRepository skillDatabaseRepository;

    public SecondApiService(FindworkHttpClient findworkHttpClient, JobPositionDatabaseRepository jobPositionDatabaseRepository, SkillDatabaseRepository skillDatabaseRepository) {
        this.findworkHttpClient = findworkHttpClient;
        this.jobPositionDatabaseRepository = jobPositionDatabaseRepository;
        this.skillDatabaseRepository = skillDatabaseRepository;
    }

    public void fetch() {
        UpworkResponse jobs = findworkHttpClient.getJobs(null);
        System.out.println(jobs);

        for (UpworkResponse.JobPositionResponse jobPositionResponse : jobs.results()) {
            JobPositionDatabase jobPositionDatabase = new JobPositionDatabase(SOURCE_NAME, jobPositionResponse.id(), jobPositionResponse.date_posted());
            jobPositionDatabaseRepository.save(jobPositionDatabase);

            List<SkillDatabase> skills = new ArrayList<>();
            SkillDatabase skillDatabase;
            for (String keyword : jobPositionResponse.keywords()) {
                skillDatabase = new SkillDatabase();
                skillDatabase.setSkill(keyword);
                skillDatabase.setJobPositionDatabase(jobPositionDatabase);
                skills.add(skillDatabase);
            }

            List<SkillDatabase> skillsWithUuid = skillDatabaseRepository.saveAll(skills);
            jobPositionDatabase.setJobsFindWorkSkillsDatabase(skillsWithUuid);
            jobPositionDatabaseRepository.save(jobPositionDatabase);
        }
    }

}
