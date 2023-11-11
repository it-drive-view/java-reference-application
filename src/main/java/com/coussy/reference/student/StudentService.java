package com.coussy.reference.student;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {

    public List<Student> getStudents() {
        return List.of(
                new Student(1L, "alex", "alex@gmail.com", LocalDate.of(2023, Month.APRIL, 10), 12),
                new Student(2L, "coussy", "alex@gmail.com", LocalDate.of(2023, 11, 10), 12)
        );
    }

}
