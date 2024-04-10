package com.coussy.reference.statistic;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/statistic")
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping
    public ResponseEntity<StatisticDto> getStatistic() {
        StatisticDto statDto = statisticService.computeStatistic();
        return new ResponseEntity<>(statDto, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/skills/by/keyword")
    public ResponseEntity<List<Map.Entry<String, Long>>> getSkills() {
        List<Map.Entry<String, Long>> result = statisticService.computeSkills();
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(200));
    }

}
