package com.coussy.reference.data.provider;

import com.coussy.reference.student.StudentDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RegisterStudentEndToEndTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void m() throws Exception {

        StudentDto studentDto = new StudentDto();
        studentDto.setEmail("johnny456@gmail.com");
        studentDto.setName("johnny");
        studentDto.setDob(LocalDate.of(2024 , Month.MAY , 10));

        String json = objectToJson(studentDto);

        ResultActions customerRegResultActions = mockMvc.perform(post("/api/v1/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(json))
        );

        customerRegResultActions.andExpect(status().isOk());
    }

    private String objectToJson(Object object) {
        try {
            return getMapper()
                    .writeValueAsString(object);
        } catch (JsonProcessingException e) {
            fail("failed to convert object to json.");
            return null;
        }
    }


    public ObjectMapper getMapper() {
        return new ObjectMapper().findAndRegisterModules().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
}
