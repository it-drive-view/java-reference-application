package com.coussy.reference.job.platform.fetch;

import com.coussy.reference.job.platform.fetch.http.FindworkHttpClient;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class FetchJobAnnouncesController {

    private final FetchJobOrchestrator fetchJobOrchestrator;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(FetchJobAnnouncesController.class);

    public FetchJobAnnouncesController(FetchJobOrchestrator fetchJobOrchestrator) {
        this.fetchJobOrchestrator = fetchJobOrchestrator;
    }

    @GetMapping
    public String m() {
        fetchJobOrchestrator.fetch();
        return "called";
    }

}
