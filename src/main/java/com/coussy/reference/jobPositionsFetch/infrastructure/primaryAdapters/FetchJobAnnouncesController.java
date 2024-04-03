package com.coussy.reference.jobPositionsFetch.infrastructure.primaryAdapters;

import com.coussy.reference.jobPositionsFetch.services.FetchJobOrchestrator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class FetchJobAnnouncesController {

    private final FetchJobOrchestrator fetchJobOrchestrator;

//    private final Job2ApiService job2ApiService;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(FetchJobAnnouncesController.class);

    public FetchJobAnnouncesController(FetchJobOrchestrator fetchJobOrchestrator) {
        this.fetchJobOrchestrator = fetchJobOrchestrator;
    }

    @GetMapping
    public String m() {
        fetchJobOrchestrator.fetch();
        return "called";
    }

    @GetMapping("/api2")
    public String m2() throws JsonProcessingException {
        return "api2 called";
    }

}
