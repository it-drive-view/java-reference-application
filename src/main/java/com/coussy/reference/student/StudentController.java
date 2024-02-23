package com.coussy.reference.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public String registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
        return "student registered";
    }

    @GetMapping("/{email}")
    public Student getStudent(@PathVariable("email") String email) {
        return studentService.findStudent(email);
    }

    //  useless, but i keep it, just for the comprehension
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/plain", produces = MediaType.TEXT_PLAIN_VALUE)
    public String serviceOk() {
        return "OK";
    }


    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/available")
    public ResponseEntity<String> available() {
        return new ResponseEntity<>("\"service available\"", HttpStatus.OK);
    }

    @DeleteMapping(path = "/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "/{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam String name,
            @RequestParam String email
    ) {
        studentService.updateStudent(studentId, name, email);
    }


    @GetMapping("/data")
    public String getDataFromProvider() {

//        return studentService.getProduct().toString();
        return studentService.getTemperatures().toString();

//        return studentService.getToken()
//                + "\n"
//                + "\n"
//                + studentService.getProduct()
//                + "\n"
//                + "\n"
//                + studentService.getTemperatures();

//        return studentService.getProduct().toString();
    }








}
