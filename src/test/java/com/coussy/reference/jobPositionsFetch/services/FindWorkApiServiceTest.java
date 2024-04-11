package com.coussy.reference.jobPositionsFetch.services;

import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.JobPositionDatabase;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.JobPositionDatabaseRepository;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.SkillDatabase;
import com.coussy.reference.jobPositionsFetch.infrastructure.secondaryAdapters.SkillDatabaseRepository;
import com.coussy.reference.statistic.StatisticService;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FindWorkApiServiceTest {

    protected final static int HTTP_PORT = 18081;

    @Autowired
    JobPositionDatabaseRepository jobPositionDatabaseRepository;

    @Autowired
    SkillDatabaseRepository skillDatabaseRepository;

    @Autowired
    StatisticService statisticService;

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
            .withUsername("reference-user")
            .withPassword("pwd");

//    url: jdbc:postgresql://localhost:5435/reference
//    username:
//    password: pkuoir7842

    @DynamicPropertySource
    static void set(DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("spring.datasource.url", () -> container.getJdbcUrl());
        propertyRegistry.add("spring.datasource.username", () -> container.getUsername());
        propertyRegistry.add("spring.datasource.password", () -> container.getPassword());

        // TODO ça fonctionnerait en https ?
        propertyRegistry.add("findwork.url", () -> "http://localhost:%s/api".formatted(HTTP_PORT));
    }

    @Test
    public void WhenFetchTwoFindWorkRequestsApiThenDataIsPersisted() throws JsonProcessingException {

        // given
        Dispatcher dispatcher = new MockWSDipatcher();
        mockWebServer.setDispatcher(dispatcher);

        // when
        findWorkApiService.fetch();

        // then
        List<JobPositionDatabase> dbJobPositions = jobPositionDatabaseRepository.findAll();
        Assertions.assertEquals(6, dbJobPositions.size());

        List<SkillDatabase> dbSkills = skillDatabaseRepository.findAll();
        Assertions.assertEquals(21, dbSkills.size());

        JobPositionDatabase jp1 = jobPositionDatabaseRepository.findBySourceAndJobPlatformId(FindWorkApiService.JOB_PLATFORM_SOURCE, "nalrlrX");
        Assertions.assertEquals(4, jp1.getJobsFindWorkSkillsDatabase().size());
        JobPositionDatabase jp2 = jobPositionDatabaseRepository.findBySourceAndJobPlatformId(FindWorkApiService.JOB_PLATFORM_SOURCE, "zdljurU");
        Assertions.assertEquals(3, jp2.getJobsFindWorkSkillsDatabase().size());
        JobPositionDatabase jp3 = jobPositionDatabaseRepository.findBySourceAndJobPlatformId(FindWorkApiService.JOB_PLATFORM_SOURCE, "ujlroiP");
        Assertions.assertEquals(5, jp3.getJobsFindWorkSkillsDatabase().size());
        JobPositionDatabase jp4 = jobPositionDatabaseRepository.findBySourceAndJobPlatformId(FindWorkApiService.JOB_PLATFORM_SOURCE, "olltgrA");
        Assertions.assertEquals(1, jp4.getJobsFindWorkSkillsDatabase().size());
        JobPositionDatabase jp5 = jobPositionDatabaseRepository.findBySourceAndJobPlatformId(FindWorkApiService.JOB_PLATFORM_SOURCE, "relrfrS");
        Assertions.assertEquals(3, jp5.getJobsFindWorkSkillsDatabase().size());
        JobPositionDatabase jp6 = jobPositionDatabaseRepository.findBySourceAndJobPlatformId(FindWorkApiService.JOB_PLATFORM_SOURCE, "ijlrrtR");
        Assertions.assertEquals(5, jp6.getJobsFindWorkSkillsDatabase().size());

        List<Map.Entry<String, Long>> entries = statisticService.computeSkills();

        Optional<Long> node_js = entries.stream().filter(entry -> entry.getKey().equals("node js")).map(Map.Entry::getValue).findFirst();
        Assertions.assertTrue(node_js.isPresent());
        Assertions.assertEquals(node_js.get(), 4);

        Optional<Long> java = entries.stream().filter(entry -> entry.getKey().equals("java")).map(Map.Entry::getValue).findFirst();
        Assertions.assertTrue(java.isPresent());
        Assertions.assertEquals(java.get(), 3);

        Optional<Long> springboot = entries.stream().filter(entry -> entry.getKey().equals("spring boot")).map(Map.Entry::getValue).findFirst();
        Assertions.assertTrue(springboot.isPresent());
        Assertions.assertEquals(springboot.get(), 2);
    }

}