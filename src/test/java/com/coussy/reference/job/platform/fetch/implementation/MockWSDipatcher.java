package com.coussy.reference.job.platform.fetch.implementation;

import com.coussy.reference.job.platform.fetch.dto.ParentDto;
import com.coussy.reference.job.platform.fetch.dto.ResultDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;

public class MockWSDipatcher extends Dispatcher {

    ObjectMapper objectMapper = new ObjectMapper();

    {
        objectMapper
                .registerModule(new JavaTimeModule())
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm a z"));
    }

    @NotNull
    @Override
    public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) throws InterruptedException {

        if (!recordedRequest.getRequestUrl().toString().contains("page")) {

            ArrayList<ResultDto> results1 = new ArrayList<>();
            results1.add(new ResultDto("nalrlrX", Arrays.asList("java", "ansible", "python", "node js"), LocalDateTime.of(2024, Month.MAY, 10, 23, 59)));
            results1.add(new ResultDto("zdljurU", Arrays.asList("cobol", "java", "c++"), LocalDateTime.of(2024, Month.MAY, 9, 23, 59)));
            results1.add(new ResultDto("ujlroiP", Arrays.asList("vba", "scala", "spring boot", "node js", "cobol"), LocalDateTime.of(2024, Month.MAY, 9, 23, 59)));
            ParentDto parentDto1 = new ParentDto(3, "http://localhost:%s/api/jobs/?page=2".formatted(FindWorkApiServiceTest.HTTP_PORT), results1);

            String json;
            try {
                json = objectMapper.writeValueAsString(parentDto1);
            } catch (JsonProcessingException e) {
                // TODO
                // mettre en place ceci..
                // throw new DependencyError(HTTP_CLIENT_IDENTIFIER, e);
                throw new RuntimeException(e);
            }

            return new MockResponse()
                    .setHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody(json)
                    .setResponseCode(200);

        } else if (recordedRequest.getRequestUrl().toString().contains("page=2")) {

            {
                ArrayList<ResultDto> results1 = new ArrayList<>();
                results1.add(new ResultDto("olltgrA", Arrays.asList("java"), LocalDateTime.of(2024, Month.MAY, 10, 23, 59)));
                results1.add(new ResultDto("relrfrS", Arrays.asList("c", "c++", "node js"), LocalDateTime.of(2024, Month.MAY, 9, 23, 59)));
                results1.add(new ResultDto("ijlrrtR", Arrays.asList("kubernetes", "scala", "spring boot", "node js", "python"), LocalDateTime.of(2024, Month.MAY, 9, 23, 59)));
                ParentDto parentDto1 = new ParentDto(3, null, results1);

                String json;
                try {
                    json = objectMapper.writeValueAsString(parentDto1);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

                return new MockResponse()
                        .setHeader("Content-Type", "application/json; charset=utf-8")
                        .setBody(json)
                        .setResponseCode(200);

            }

        } else {
            return null;
        }
    }

}
