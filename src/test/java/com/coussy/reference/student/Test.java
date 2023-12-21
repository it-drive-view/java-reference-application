package com.coussy.reference.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(TodoController.class)
@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    MockMvc mockMvc;

//    @MockBean
//    TodoRepository todoRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldFindAllTodos() throws Exception {

        var todos = List.of(new Todo("test 1", true), new Todo("test 2",true));

        objectMapper.writeValueAsString(todos);

        mockMvc.perform(get("/api/v1/student/alexandre.coussy@laposte2.net"))
                .andExpect(        status().isOk())
                .andExpect(        content().json(objectMapper.writeValueAsString(""))
                );
    }

}