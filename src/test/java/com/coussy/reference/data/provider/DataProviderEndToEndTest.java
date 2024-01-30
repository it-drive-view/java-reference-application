package com.coussy.reference.data.provider;


import com.coussy.reference.data.provider.dto.TemperatureDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

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

//    @BeforeEach
//    private






//    public void test() throws Exception {
//
//        // Given
//        List<TemperatureDto> temperatures = Arrays.asList(
//                new TemperatureDto("Paris", 14),
//                new TemperatureDto("London", 14),
//                new TemperatureDto("Bangkok", 38),
//                new TemperatureDto("Pekin", 8)
//        );
//        ObjectMapper objectMapper = new ObjectMapper();
//        String serializedTemp = objectMapper.writeValueAsString(temperatures);
//
//        // When
//
//        // FIXME elle est où l'implémentation de get() ci dessous ?
//
//        MockHttpServletRequestBuilder builder = get("/api/v1/data/provider/retrieve/temperatures")
//                .accept(MediaType.APPLICATION_JSON);
//
//        // Then
//        String contentAsString = mockMvc.perform(builder)
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//
//        Assertions.assertEquals(serializedTemp , contentAsString);
//    }

}

