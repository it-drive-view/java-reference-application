package com.coussy.reference.findwork.data.fetch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class FetchJobAnnouncesController {

    private final FetchJobs fetchJobs;

    public FetchJobAnnouncesController(FetchJobs findWorkPlatformService) {
        this.fetchJobs = findWorkPlatformService;
    }

    @GetMapping
    public String m() {
        fetchJobs.fetch();
        return "called";
    }

}
