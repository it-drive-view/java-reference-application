package com.coussy.reference.jobPositionsFetch.services;

import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.JobPositionDatabase;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.JobPositionDatabaseRepository;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.SkillDatabase;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.SkillDatabaseRepository;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.response.UpworkResponse;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.FindworkHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class FindWorkApiService implements FetchJobs {

    public static final String JOB_PLATFORM_SOURCE = "findwork.dev";

    private final FindworkHttpClient findworkHttpClient;
    private final JobPositionDatabaseRepository jobPositionDatabaseRepository;
    private final SkillDatabaseRepository skillDatabaseRepository;

    public FindWorkApiService(FindworkHttpClient findworkHttpClient, JobPositionDatabaseRepository jobPositionDatabaseRepository, SkillDatabaseRepository skillDatabaseRepository) {
        this.findworkHttpClient = findworkHttpClient;
        this.jobPositionDatabaseRepository = jobPositionDatabaseRepository;
        this.skillDatabaseRepository = skillDatabaseRepository;
    }

    public void fetch() {

        String nextUrl = null;
        do {
            UpworkResponse jobs = findworkHttpClient.getJobs(nextUrl);

            // Thread sleep only from the 2nd REST http call.
            if (nextUrl != null) {
                try {
                    Thread.sleep(1000 * 5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println("=================================");
            System.out.println("=================================");
            System.out.println(jobs);
            System.out.println("=================================");
            System.out.println("=================================");
            System.out.println(jobs);

            for (UpworkResponse.JobPositionResponse jobPositionResponse : jobs.results()) {
                persistData(jobPositionResponse);
            }

            nextUrl = jobs.next();
        } while (StringUtils.isNotEmpty(nextUrl));

    }

    @Transactional
    private void persistData(UpworkResponse.JobPositionResponse jobPositionResponse) {

        JobPositionDatabase jobPosition = jobPositionDatabaseRepository.findBySourceAndJobPlatformId(JOB_PLATFORM_SOURCE, jobPositionResponse.id());
        if (jobPosition != null) {
            return;
        }

        JobPositionDatabase jobPositionDatabase = new JobPositionDatabase
                (JOB_PLATFORM_SOURCE, jobPositionResponse.id(), jobPositionResponse.date_posted());
        jobPositionDatabaseRepository.save(jobPositionDatabase);

        List<SkillDatabase> skills = jobPositionResponse.keywords().stream()
                .map(skill -> new SkillDatabase(skill, jobPositionDatabase))
                .toList();
        skillDatabaseRepository.saveAll(skills);
    }

}
