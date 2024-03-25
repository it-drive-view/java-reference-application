package com.coussy.reference.job.platform.fetch.implementation;

import com.coussy.reference.job.platform.fetch.FetchJobs;
import com.coussy.reference.job.platform.fetch.JobPositionDatabase;
import com.coussy.reference.job.platform.fetch.dto.ParentDto;
import com.coussy.reference.job.platform.fetch.dto.ResultDto;
import com.coussy.reference.job.platform.fetch.http.FindworkHttpClient;
import com.coussy.reference.job.platform.fetch.JobPositionDatabaseRepository;
import com.coussy.reference.job.platform.fetch.SkillDatabase;
import com.coussy.reference.job.platform.fetch.SkillDatabaseRepository;

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
        ParentDto jobs = findworkHttpClient.getJobs(null);
        System.out.println(jobs);

        for (ResultDto resultDto : jobs.results()) {
            JobPositionDatabase jobPositionDatabase = new JobPositionDatabase(SOURCE_NAME, resultDto.id(), resultDto.date_posted());
            jobPositionDatabaseRepository.save(jobPositionDatabase);

            List<SkillDatabase> skills = new ArrayList<>();
            SkillDatabase skillDatabase;
            for (String keyword : resultDto.keywords()) {
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
