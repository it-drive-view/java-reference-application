package com.coussy.reference.data.provider;


import com.coussy.reference.data.provider.dto.TemperatureDto;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DataProviderEndToEndTest {

    @Autowired
    private MockMvc mockMvc;

    private MockWebServer mockWebServer;

    private static final int MOCK_SERVER_PORT = 8097;

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        // TODO le supplier n'est pas vraiment util ici, il suffit de lui donner une valeur
        // le code de Spring est mal gaulé ?
        registry.add("data.provider.url" , () -> "http://localhost:%s/api/v1".formatted(MOCK_SERVER_PORT));
    }

    @BeforeEach
    private void beforeEach() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(MOCK_SERVER_PORT);
    }

    @AfterEach
    private void afterEach() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void test() throws Exception {

        // Given
        List<TemperatureDto> temperatures = Arrays.asList(
                new TemperatureDto("Paris", 14),
                new TemperatureDto("London", 14),
                new TemperatureDto("Bangkok", 38),
                new TemperatureDto("Pekin", 8)
        );
        ObjectMapper objectMapper = new ObjectMapper();
        String serializedTemp = objectMapper.writeValueAsString(temperatures);



        MockResponse mockResponse = new MockResponse()
                .setResponseCode(200)
                .setBody(serializedTemp);

        mockWebServer.enqueue(mockResponse);

        // When

        // FIXME elle est où l'implémentation de get() ci dessous ?

        MockHttpServletRequestBuilder builder = get("/api/v1/data/provider/retrieve/temperatures")
                .accept(MediaType.APPLICATION_JSON);

        // Then
        String contentAsString = mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertEquals(serializedTemp , contentAsString);
    }

}

