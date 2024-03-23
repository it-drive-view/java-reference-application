package com.coussy.reference.findwork.data.fetch;

import com.coussy.reference.findwork.data.fetch.dto.ParentDto;
import com.coussy.reference.findwork.data.fetch.dto.ResultDto;
import com.coussy.reference.findwork.data.fetch.http.FindworkHttpClient;
import com.coussy.reference.findwork.data.fetch.http.JobsFindWorkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchJobAnnouncesService {

    private final FindworkHttpClient findworkHttpClient;
    private final JobsFindWorkRepository jobsFindWorkRepository;
    private final DtoMapper  dtoMapper ;

    public FetchJobAnnouncesService(FindworkHttpClient findworkHttpClient, JobsFindWorkRepository jobsFindWorkRepository, DtoMapper dtoMapper) {
        this.findworkHttpClient = findworkHttpClient;
        this.jobsFindWorkRepository = jobsFindWorkRepository;
        this.dtoMapper = dtoMapper;
    }

    public void fetch() {
        ParentDto jobs = findworkHttpClient.getJobs();
        System.out.println(jobs);
        List<ResultDto> results = jobs.results();
        List<JobsFindWorkDatabase>  list =  dtoMapper.toJobsFindWorks(results);
        jobsFindWorkRepository.saveAll(list);
    }

}
