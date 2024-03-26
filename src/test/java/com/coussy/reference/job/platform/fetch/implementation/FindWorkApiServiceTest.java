package com.coussy.reference.job.platform.fetch.implementation;

import com.coussy.reference.job.platform.fetch.JobPositionDatabase;
import com.coussy.reference.job.platform.fetch.JobPositionDatabaseRepository;
import com.coussy.reference.job.platform.fetch.SkillDatabase;
import com.coussy.reference.job.platform.fetch.SkillDatabaseRepository;
import com.coussy.reference.job.platform.fetch.dto.ParentDto;
import com.coussy.reference.job.platform.fetch.dto.ResultDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FindWorkApiServiceTest {

    private final static int HTTP_PORT = 1296;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    JobPositionDatabaseRepository jobPositionDatabaseRepository;

    @Autowired
    SkillDatabaseRepository skillDatabaseRepository;

    @Autowired
    FindWorkApiService findWorkApiService;

    MockWebServer mockWebServer;

    @BeforeEach
    public void init() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(HTTP_PORT);
    }

    @AfterEach
    public void after() throws IOException {
        mockWebServer.shutdown();
    }

    @Container
    // est-ce qu'il faut pas mettre la version dans le fichier properties ?
    static PostgreSQLContainer container = new PostgreSQLContainer("postgres:12")
            .withDatabaseName("test")
            .withUsername("coussy")
            .withPassword("pwd");

    @DynamicPropertySource
    static void set(DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("spring.datasource.url", () -> container.getJdbcUrl());
        propertyRegistry.add("spring.datasource.username", () -> container.getUsername());
        propertyRegistry.add("spring.datasource.password", () -> container.getPassword());

        // TODO ça fonctionnerait en https ?
        propertyRegistry.add("findwork.url", () -> "http://localhost:%s/api".formatted(HTTP_PORT));
    }

    @Test
    public void m2() throws JsonProcessingException {

        Dispatcher dispatcher = new Dispatcher() {

            @NotNull
            @Override
            public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) throws InterruptedException {

                if (!recordedRequest.getRequestUrl().toString().contains("page")) {

                    ArrayList<ResultDto> results1 = new ArrayList<>();
                    results1.add(new ResultDto("API-KEY-00001", Arrays.asList("java", "ansible", "python", "node js"), LocalDateTime.of(2024, Month.MAY, 10, 23, 59)));
                    results1.add(new ResultDto("API-KEY-00002", Arrays.asList("cobol", "java", "c++", "node js"), LocalDateTime.of(2024, Month.MAY, 9, 23, 59)));
                    results1.add(new ResultDto("API-KEY-00003", Arrays.asList("vba", "scala", "spring boot", "node js"), LocalDateTime.of(2024, Month.MAY, 9, 23, 59)));
                    ParentDto parentDto1 = new ParentDto(3, "http://localhost:%s/api/jobs/?page=2".formatted(HTTP_PORT), results1);

                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
                    ObjectMapper objectMapper = new ObjectMapper();
                    String json = null;
                    try {
                        json = objectMapper
                                .registerModule(new JavaTimeModule())
                                .setDateFormat(df)
                                .writeValueAsString(parentDto1);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }

                    return new MockResponse()
                            .setHeader("Content-Type", "application/json; charset=utf-8")
                            .setBody(json)
                            .setResponseCode(200);

                } else if (recordedRequest.getRequestUrl().toString().contains("page=2")) {

                    {
                        ArrayList<ResultDto> results1 = new ArrayList<>();
                        results1.add(new ResultDto("API-KEY-00004", Arrays.asList("java", "ansible", "python", "node js"), LocalDateTime.of(2024, Month.MAY, 10, 23, 59)));
                        results1.add(new ResultDto("API-KEY-00005", Arrays.asList("cobol", "java", "c++", "node js"), LocalDateTime.of(2024, Month.MAY, 9, 23, 59)));
                        results1.add(new ResultDto("API-KEY-00006", Arrays.asList("vba", "scala", "spring boot", "node js"), LocalDateTime.of(2024, Month.MAY, 9, 23, 59)));
                        ParentDto parentDto1 = new ParentDto(3, null, results1);

                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
                        ObjectMapper objectMapper = new ObjectMapper();
                        String json = null;
                        try {
                            json = objectMapper
                                    .registerModule(new JavaTimeModule())
                                    .setDateFormat(df)
                                    .writeValueAsString(parentDto1);
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

        };
        mockWebServer.setDispatcher(dispatcher);

        // given
        findWorkApiService.fetch();

        List<JobPositionDatabase> dbJobPositions = jobPositionDatabaseRepository.findAll();
        Assertions.assertEquals(6, dbJobPositions.size());

        List<SkillDatabase> dbSkills = skillDatabaseRepository.findAll();
        Assertions.assertEquals(24, dbSkills.size());

        // when
        // then
    }


    Dispatcher dispatcher = new Dispatcher() {

        @NotNull
        @Override
        public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) throws InterruptedException {

            if (recordedRequest.getRequestUrl().toString().contains("/data")) {
                MockResponse mockResponse = new MockResponse()
                        .addHeader("Content-Type", "application/json; charset=utf-8")
                        .setResponseCode(200)
                        .setBody("{\"data\": \"duke\"}");
                return mockResponse;
            }
            // 1- le mockWebServer stocke la requête qui sera émise (ici, ce sera à partir de usersClientProdImplementation.getUserId..)
            // 2- en imaginant que l'url ne soit pas reconnue, on renverra ici un 404
            // 3- or, dans la méthode "usersClientProdImplementation.getUserId()", ce n'est pas géré.
            // 4- en effet, c'est le dto fallback qui est renvoyé (voir le code de "usersClientProdImplementation.getUserId()"
            // 5- ci-dessous, la MockResponse est bien renvoyée, le code lui renvoie un dto fallback.
            return new MockResponse()
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setResponseCode(404);
        }
    };

}