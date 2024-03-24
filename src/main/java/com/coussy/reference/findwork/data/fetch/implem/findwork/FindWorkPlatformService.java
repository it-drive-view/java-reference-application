package com.coussy.reference.findwork.data.fetch.implem.findwork;

import com.coussy.reference.findwork.data.fetch.DtoMapper;
import com.coussy.reference.findwork.data.fetch.FetchJobs;
import com.coussy.reference.findwork.data.fetch.JobPositionDatabase;
import com.coussy.reference.findwork.data.fetch.dto.ParentDto;
import com.coussy.reference.findwork.data.fetch.dto.ResultDto;
import com.coussy.reference.findwork.data.fetch.http.FindworkHttpClient;
import com.coussy.reference.findwork.data.fetch.http.JobPositionDatabaseRepository;
import com.coussy.reference.findwork.data.fetch.SkillDatabase;
import com.coussy.reference.findwork.data.fetch.http.SkillDatabaseRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class FindWorkPlatformService implements FetchJobs {

    private final FindworkHttpClient findworkHttpClient;
    private final JobPositionDatabaseRepository jobPositionDatabaseRepository;
    private final SkillDatabaseRepository skillDatabaseRepository;
    private final DtoMapper dtoMapper;

    public FindWorkPlatformService(FindworkHttpClient findworkHttpClient, JobPositionDatabaseRepository jobPositionDatabaseRepository, SkillDatabaseRepository skillDatabaseRepository, DtoMapper dtoMapper) {
        this.findworkHttpClient = findworkHttpClient;
        this.jobPositionDatabaseRepository = jobPositionDatabaseRepository;
        this.skillDatabaseRepository = skillDatabaseRepository;
        this.dtoMapper = dtoMapper;
    }

//    @Transactional
    public void fetch() {
        ParentDto jobs = findworkHttpClient.getJobs();
        System.out.println(jobs);

        for (ResultDto resultDto : jobs.results()) {
            JobPositionDatabase jobPositionDatabase = dtoMapper.toJobsFindWorks(resultDto);

            // to get UUID
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
        }
    }

}
