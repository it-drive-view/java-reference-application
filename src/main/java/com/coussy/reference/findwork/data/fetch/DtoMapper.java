package com.coussy.reference.findwork.data.fetch;

import com.coussy.reference.findwork.data.fetch.dto.ResultDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

// pourquoi est ce que je l'appelle Component ?
@Component
public class DtoMapper {

    public JobPositionDatabase toJobsFindWorks(ResultDto dto) {

        JobPositionDatabase jfw = new JobPositionDatabase();
        jfw.setPositionUuid(UUID.randomUUID());
        jfw.setId(dto.id());
            jfw.setDatePosted(dto.date_posted());
        return jfw;
    }

}
