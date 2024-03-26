package com.coussy.reference.job.platform.fetch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE )
@AutoConfigureMockMvc
class FetchJobAnnouncesControllerTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    MockMvc mockMvc;

    @Container
    // est-ce qu'il faut pas mettre la version dans le fichier properties ?
    static PostgreSQLContainer container = new PostgreSQLContainer("postgres:12")
            .withDatabaseName("test")
            .withUsername("coussy")
            .withPassword("pwd");

    @DynamicPropertySource
    static void set(DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("spring.datasource.url" ,  () -> container.getJdbcUrl() );
        propertyRegistry.add("spring.datasource.username" ,  () -> container.getUsername() );
        propertyRegistry.add("spring.datasource.password" ,  () -> container.getPassword() );
    }

    @Test
    public void m() {


        try {
            Thread.sleep(1000 * 600);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertNotNull(applicationContext);
    }

}