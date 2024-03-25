package com.coussy.reference.job.platform.fetch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class FetchJobAnnouncesController {

    private final FetchJobOrchestrator fetchJobOrchestrator;

    public FetchJobAnnouncesController(FetchJobOrchestrator fetchJobOrchestrator) {
        this.fetchJobOrchestrator = fetchJobOrchestrator;
    }

    @GetMapping
    public String m() {
        fetchJobOrchestrator.fetch();
        return "called";
    }

}
