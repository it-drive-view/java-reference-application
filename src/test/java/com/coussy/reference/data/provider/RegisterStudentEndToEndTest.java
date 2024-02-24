package com.coussy.reference.data.provider;

import com.coussy.reference.student.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// RAPPEL : pour voir les logs issus du container docker, faire :
// mvn clean -Dtest=RegisterStudentEndToEndTest test | grep org.testcontainers.containers.output.WaitingConsumer

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RegisterStudentEndToEndTest {

    private ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0")
            .withExposedPorts(5432)
            // rappel: tous ces éléments sont totalement inutiles. En effet :
            // 1- si je ne les mets pas, le code met des infos par défaut
            // 2- l'annotation @ServiceConnection recopie toutes ces propriétés vers les propriétés spring boot (par exemple)
            .withDatabaseName("db-test")
            .withUsername("user-test")
            .withPassword("password-test");

    //            .withClasspathResourceMapping("PostgresInit.sql", "/docker-entrypoint-initdb.d/script.sql", BindMode.READ_ONLY);

    @Autowired
    MockMvc mockMvc;

    @Test
    public void m() throws Exception {

        String email = "johnny456@gmail.com";

        Student studentDto = new Student();
        studentDto.setEmail(email);
        studentDto.setName("johnny");
        studentDto.setDob(LocalDate.of(2024 , Month.MAY , 10));

        String json = objectToJson(studentDto);

        ResultActions customerRegResultActions = mockMvc.perform(post("/api/v1/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        );
        customerRegResultActions.andExpect(status().isOk());

        ResultActions resultActions = mockMvc.perform(get("/api/v1/student/" + email));
        String serializedStudent = resultActions
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println("==> " + serializedStudent );
        Student student = mapper.readValue(serializedStudent, Student.class);

        Assertions.assertNotNull(student);
        Assertions.assertNotNull(student.getId());
        Assertions.assertEquals(student.getEmail() , "johnny456@gmail.com");
        Assertions.assertEquals(student.getName() , "johnny");
    }

    private String objectToJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            fail("failed to convert object to json.");
            return null;
        }
    }

}
