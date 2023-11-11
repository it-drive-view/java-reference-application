package com.coussy.reference.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private StudentService studentService = new StudentService();

    // useless, but i keep it, just for the comprehension
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = new StudentService();
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

}
