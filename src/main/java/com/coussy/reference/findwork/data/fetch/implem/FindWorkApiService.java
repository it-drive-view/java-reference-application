package com.coussy.reference.findwork.data.fetch.implem;

import com.coussy.reference.findwork.data.fetch.FetchJobs;
import com.coussy.reference.findwork.data.fetch.JobPositionDatabase;
import com.coussy.reference.findwork.data.fetch.SkillDatabase;
import com.coussy.reference.findwork.data.fetch.dto.ParentDto;
import com.coussy.reference.findwork.data.fetch.dto.ResultDto;
import com.coussy.reference.findwork.data.fetch.http.FindworkHttpClient;
import com.coussy.reference.findwork.data.fetch.JobPositionDatabaseRepository;
import com.coussy.reference.findwork.data.fetch.SkillDatabaseRepository;


import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class FindWorkApiService implements FetchJobs {

    private final String SOURCE_NAME = "findwork";

    private final FindworkHttpClient findworkHttpClient;
    private final JobPositionDatabaseRepository jobPositionDatabaseRepository;
    private final SkillDatabaseRepository skillDatabaseRepository;

    public FindWorkApiService(FindworkHttpClient findworkHttpClient, JobPositionDatabaseRepository jobPositionDatabaseRepository, SkillDatabaseRepository skillDatabaseRepository) {
        this.findworkHttpClient = findworkHttpClient;
        this.jobPositionDatabaseRepository = jobPositionDatabaseRepository;
        this.skillDatabaseRepository = skillDatabaseRepository;
    }

    // comment faire pour faire du transactionnal par bloc de code
    @Transactional
    public void fetch() {

        String nextUrl = null;
        do {
            ParentDto jobs = findworkHttpClient.getJobs(nextUrl);

            System.out.println("=================================");
            System.out.println("=================================");
            System.out.println(jobs);
            System.out.println("=================================");
            System.out.println("=================================");
            System.out.println(jobs);

            for (ResultDto resultDto : jobs.results()) {
                JobPositionDatabase jobPositionDatabase = new JobPositionDatabase
                        ("findwork", resultDto.id(), resultDto.date_posted());
                jobPositionDatabaseRepository.save(jobPositionDatabase);

                List<SkillDatabase> skills = resultDto.keywords().stream()
                        .map(skill -> new SkillDatabase(skill, jobPositionDatabase))
                        .toList();
                skillDatabaseRepository.saveAll(skills);
            }

            nextUrl = jobs.next();
            try {
                Thread.sleep(1000 * 5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (StringUtils.isNotEmpty(nextUrl));

    }

}
