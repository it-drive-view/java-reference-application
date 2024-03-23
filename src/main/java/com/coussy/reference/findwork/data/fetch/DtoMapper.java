package com.coussy.reference.findwork.data.fetch;

import com.coussy.reference.findwork.data.fetch.dto.ResultDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// pourquoi est ce que je l'appelle Component ?
@Component
public class DtoMapper {

    public List<JobsFindWorkDatabase> toJobsFindWorks(List<ResultDto> results) {

        List<JobsFindWorkDatabase> list = new ArrayList<>();
        JobsFindWorkDatabase jfw;
        for (ResultDto dto : results) {
            jfw = new JobsFindWorkDatabase();
            jfw.setId(dto.id());
            jfw.setDatePosted(dto.date_posted());
            list.add(jfw);
        }
        return list;
    }


}
