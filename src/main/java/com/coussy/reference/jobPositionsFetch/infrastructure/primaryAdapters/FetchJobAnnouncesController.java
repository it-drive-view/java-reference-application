package com.coussy.reference.jobPositionsFetch.infrastructure.primaryAdapters;

import com.coussy.reference.jobPositionsFetch.services.FetchJob;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class FetchJobAnnouncesController {

    private final FetchJob fetchJob;

//    private final Job2ApiService job2ApiService;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(FetchJobAnnouncesController.class);

    public FetchJobAnnouncesController(FetchJob fetchJob) {
        this.fetchJob = fetchJob;
    }

    @GetMapping
    public String m() {
        fetchJob.fetch();
        return "called";
    }

    @GetMapping("/api2")
    public String m2() throws JsonProcessingException {
        return "api2 called";
    }

}
