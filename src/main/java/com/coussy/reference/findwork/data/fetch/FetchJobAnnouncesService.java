package com.coussy.reference.findwork.data.fetch;

import com.coussy.reference.findwork.data.fetch.dto.Dto;
import com.coussy.reference.findwork.data.fetch.http.FindworkHttpClient;
import org.springframework.stereotype.Service;

@Service
public class FetchJobAnnouncesService {

    private final FindworkHttpClient findworkHttpClient;

    public FetchJobAnnouncesService(FindworkHttpClient findworkHttpClient) {
        this.findworkHttpClient = findworkHttpClient;
    }

    public void fetch() {
        Dto jobs = findworkHttpClient.getJobs();
        System.out.println(jobs);
    }

}
