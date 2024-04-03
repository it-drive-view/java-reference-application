package com.coussy.reference.jobPositionsFetch.services;

import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.response.UpworkResponse;
import com.coussy.reference.jobPositionsFetch.services.FindWorkApiServiceTest;
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

            ArrayList<UpworkResponse.JobPositionResponse> results1 = new ArrayList<>();
            results1.add(new UpworkResponse.JobPositionResponse("nalrlrX", Arrays.asList("java", "ansible", "python", "node js"), LocalDateTime.of(2024, Month.MAY, 10, 23, 59)));
            results1.add(new UpworkResponse.JobPositionResponse("zdljurU", Arrays.asList("cobol", "java", "c++"), LocalDateTime.of(2024, Month.MAY, 9, 23, 59)));
            results1.add(new UpworkResponse.JobPositionResponse("ujlroiP", Arrays.asList("vba", "scala", "spring boot", "node js", "cobol"), LocalDateTime.of(2024, Month.MAY, 9, 23, 59)));
            UpworkResponse upworkResponse1 = new UpworkResponse(3, "http://localhost:%s/api/jobs/?page=2".formatted(FindWorkApiServiceTest.HTTP_PORT), results1, null);

            String json;
            try {
                json = objectMapper.writeValueAsString(upworkResponse1);
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
                ArrayList<UpworkResponse.JobPositionResponse> results1 = new ArrayList<>();
                results1.add(new UpworkResponse.JobPositionResponse("olltgrA", Arrays.asList("java"), LocalDateTime.of(2024, Month.MAY, 10, 23, 59)));
                results1.add(new UpworkResponse.JobPositionResponse("relrfrS", Arrays.asList("c", "c++", "node js"), LocalDateTime.of(2024, Month.MAY, 9, 23, 59)));
                results1.add(new UpworkResponse.JobPositionResponse("ijlrrtR", Arrays.asList("kubernetes", "scala", "spring boot", "node js", "python"), LocalDateTime.of(2024, Month.MAY, 9, 23, 59)));
                UpworkResponse upworkResponse1 = new UpworkResponse(3, null, results1, null);

                String json;
                try {
                    json = objectMapper.writeValueAsString(upworkResponse1);
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
