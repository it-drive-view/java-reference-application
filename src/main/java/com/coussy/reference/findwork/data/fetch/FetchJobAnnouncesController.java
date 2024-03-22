package com.coussy.reference.findwork.data.fetch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class FetchJobAnnouncesController {

    private final FetchJobAnnouncesService fetchJobAnnouncesService;

    public FetchJobAnnouncesController(FetchJobAnnouncesService fetchJobAnnouncesService) {
        this.fetchJobAnnouncesService = fetchJobAnnouncesService;
    }

    @GetMapping
    public String m() {
        fetchJobAnnouncesService.fetch();
        return "called";
    }

}
