package com.coussy.reference.student;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class StudentControllerendToend {

    @Autowired
    MockMvc mockMvc;

    @Test
    void available() throws Exception {
        mockMvc.perform(get("/api/v1/student/available"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(contentToJson("service available")));

    }

    @Test
    void frameworkTesting() throws Exception {

        Student expected = new Student(1L, "alex", "alex@gmail.com", LocalDate.of(1987, Month.APRIL, 15));

        mockMvc.perform(get("/api/v1/student/alex@gmail.com"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(contentToJson(expected)));

        mockMvc.perform(get("/api/v1/student/alex@gmail.com"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(contentToJson(expected)));

    }

    @Test
    void itShouldCheckendtoend() throws Exception {

        // Given a student
        Student student = new Student("coussy", "alex@gmail.new.com", LocalDate.of(1974, Month.MAY, 10));

        // When
        // Then
        mockMvc.perform(post("/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(contentToJson(student))
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("student registered"))
        ;


    }


    private String contentToJson(Object object) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();

//                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");

            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");

            // allows serialization for java8 dates
            objectMapper.findAndRegisterModules();
            // another way to permit serialization
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.setDateFormat(sdFormat);

            return objectMapper.writeValueAsString(object);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}