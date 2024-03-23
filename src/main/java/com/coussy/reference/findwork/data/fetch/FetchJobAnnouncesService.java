package com.coussy.reference.findwork.data.fetch;

import com.coussy.reference.findwork.data.fetch.dto.ParentDto;
import com.coussy.reference.findwork.data.fetch.dto.ResultDto;
import com.coussy.reference.findwork.data.fetch.http.FindworkHttpClient;
import com.coussy.reference.findwork.data.fetch.http.JobsFindWorkRepository;
import com.coussy.reference.findwork.data.fetch.http.JobsFindWorkSkillDatabase;
import com.coussy.reference.findwork.data.fetch.http.JobsFindWorkSkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchJobAnnouncesService {

    private final FindworkHttpClient findworkHttpClient;
    private final JobsFindWorkRepository jobsFindWorkRepository;
    private final JobsFindWorkSkillRepository jobsFindWorkSkillRepository;
    private final DtoMapper dtoMapper;

    public FetchJobAnnouncesService(FindworkHttpClient findworkHttpClient, JobsFindWorkRepository jobsFindWorkRepository, JobsFindWorkSkillRepository jobsFindWorkSkillRepository, DtoMapper dtoMapper) {
        this.findworkHttpClient = findworkHttpClient;
        this.jobsFindWorkRepository = jobsFindWorkRepository;
        this.jobsFindWorkSkillRepository = jobsFindWorkSkillRepository;
        this.dtoMapper = dtoMapper;
    }

    public void fetch() {
        ParentDto jobs = findworkHttpClient.getJobs();
        System.out.println(jobs);

        List<ResultDto> results = jobs.results();
        for (ResultDto resultDto : results) {
            JobsFindWorkDatabase jobsFindWorkDatabase = dtoMapper.toJobsFindWorks(resultDto);
            List<JobsFindWorkSkillDatabase> jobsFindWorkSkillsDatabase = jobsFindWorkDatabase.getJobsFindWorkSkillsDatabase();
            jobsFindWorkSkillRepository.saveAll(jobsFindWorkSkillsDatabase);
            jobsFindWorkRepository.save(jobsFindWorkDatabase);
        }
    }

}
