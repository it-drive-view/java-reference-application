package com.coussy.reference.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.APRIL;
import static java.time.Month.MAY;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student stud1 = new Student("alex", "alex@gmail.com", LocalDate.of(1987, APRIL, 15));
            Student stud2 = new Student("ted", "ted@gmail.com", LocalDate.of(2000, MAY, 15));
            studentRepository.saveAll(List.of(stud1, stud2));
        };
    }

}
