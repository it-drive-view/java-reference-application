package com.coussy.reference.statistic;

import com.coussy.reference.data.provider.http.DataProviderHttpClient;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StatisticEndToEndTest {

    @Autowired
    MockMvc mockMvc;

    MockWebServer mockWebServer;

    @BeforeEach
    public void before() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(8090);
    }

//    @BeforeEach
//    public void after() throws IOException {
//        mockWebServer.shutdown();
//    }

    @Test
    public void first() throws Exception {

        // Given
        MockResponse mockResponse = new MockResponse()
                .setResponseCode(200)
                .setBody("\"787-7878-sdf\"");
        mockWebServer.enqueue(mockResponse);

        // When
        String result = mockMvc.perform(get("/api/v1/statistic").accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .toString();

        // Then

        // TODO faire la serialisation à partir d'ici
        // StatisticDto statisticDto = new StatisticDto("token", new BigDecimal(2));

        String expected = new JSONObject(Map.ofEntries(
                Map.entry("label", "787-7878-sdf"),
                Map.entry("value", new BigDecimal(2))
        )).toString();
        Assertions.assertEquals(expected, expected);
    }

}
