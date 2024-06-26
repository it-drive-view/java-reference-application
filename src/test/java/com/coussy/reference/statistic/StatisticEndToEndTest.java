package com.coussy.reference.statistic;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class StatisticEndToEndTest {

    @Autowired
    MockMvc mockMvc;

    MockWebServer mockWebServer;

    @Autowired
    Environment environment;

    private static final int MOCK_SERVER_PORT = 8091;

    @BeforeEach
    public void before() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(MOCK_SERVER_PORT);
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("dataprovider.url", () -> "http://localhost:%s/api/v1".formatted(MOCK_SERVER_PORT));
    }

    @AfterEach
    public void after() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void first() throws Exception {

        // Given
        MockResponse mockResponse = new MockResponse()
                .setResponseCode(200)
                .setBody("\"145-tyu-854-rty-965-rtg-875-trf\"");
        mockWebServer.enqueue(mockResponse);

        // When
        String result = mockMvc.perform(get("/api/v1/statistic").accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Then
        StatisticDto statisticDto = new StatisticDto("145-tyu-854-rty-965-rtg-875-trf", new BigDecimal(2));
        ObjectMapper objectMapper1 = new ObjectMapper();
        String expected2 = objectMapper1.writeValueAsString(statisticDto);
        Assertions.assertEquals(expected2, result);
    }

}
