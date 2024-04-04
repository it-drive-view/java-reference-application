package com.coussy.reference;

import com.coussy.reference.jobPositionsFetch.services.FetchJobOrchestrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReferenceApplication {

    @Autowired
    FetchJobOrchestrator fetchJobOrchestrator;

    public static void main(String[] args) {
        SpringApplication.run(ReferenceApplication.class, args);
    }

}
