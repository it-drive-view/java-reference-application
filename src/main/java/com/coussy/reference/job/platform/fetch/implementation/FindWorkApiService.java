package com.coussy.reference.job.platform.fetch.implementation;

import com.coussy.reference.job.platform.fetch.*;
import com.coussy.reference.job.platform.fetch.dto.ParentDto;
import com.coussy.reference.job.platform.fetch.dto.ResultDto;
import com.coussy.reference.job.platform.fetch.http.FindworkHttpClient;
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
            ParentDto jobs = findworkHttpClient.getJobs(nextUrl);

            System.out.println("=================================");
            System.out.println("=================================");
            System.out.println(jobs);
            System.out.println("=================================");
            System.out.println("=================================");
            System.out.println(jobs);

            for (ResultDto resultDto : jobs.results()) {
                persistData(resultDto);
            }

            nextUrl = jobs.next();
            try {
                Thread.sleep(1000 * 5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (StringUtils.isNotEmpty(nextUrl));

    }

    @Transactional
    private void persistData(ResultDto resultDto) {

        JobPositionDatabase jobPosition = jobPositionDatabaseRepository.findBySourceAndJobPlatformId(JOB_PLATFORM_SOURCE, resultDto.id());
        if (jobPosition != null) {
            System.out.println("==> already in database");
            return;
        }

        JobPositionDatabase jobPositionDatabase = new JobPositionDatabase
                (JOB_PLATFORM_SOURCE, resultDto.id(), resultDto.date_posted() );
        jobPositionDatabaseRepository.save(jobPositionDatabase);

        List<SkillDatabase> skills = resultDto.keywords().stream()
                .map(skill -> new SkillDatabase(skill, jobPositionDatabase))
                .toList();
        skillDatabaseRepository.saveAll(skills);
    }

}
