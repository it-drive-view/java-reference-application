package com.coussy.reference.student;

import com.coussy.reference.data.provider.dto.ProductDto;
import com.coussy.reference.data.provider.dto.TemperatureDto;
import com.coussy.reference.data.provider.http.DataProviderHttpClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final DataProviderHttpClient dataProviderHttpClient;

    @Autowired
    public StudentService(StudentRepository studentRepository, DataProviderHttpClient dataProviderHttpClient) {
        this.studentRepository = studentRepository;
        this.dataProviderHttpClient = dataProviderHttpClient;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        if (studentRepository.findStudentByEmail(student.getEmail()).isPresent()) {
            throw new IllegalStateException("email already exists.");
        }
        studentRepository.save(student);
    }

    public Student findStudent(String email) {
        Optional<Student> optional = studentRepository.findStudentByEmail(email);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student" + studentId + "does not exist.");
        }
        studentRepository.deleteById(studentId);
    }

    // avec cette annotation, le simple fait de setter un field dans l'objet java l'enregsitre en base!
    // il n'est pas nécessaire de solliciter la commande save de la couche dao!
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository
                .findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student id " + studentId + " does not exist."));

        if (name != null
                && name.length() > 0
                && !Objects.equals(name, student.getName())) {
            student.setName(name);
        }

        if (email != null
                && email.length() > 0
                && !Objects.equals(email, student.getEmail())) {
            Optional<Student> optionalStudent = studentRepository.findStudentByEmail(email);
            if (optionalStudent.isPresent()) {
                throw new IllegalStateException("email is already taken.");
            }
            student.setEmail(email);
        }

    }

    public String getToken() {
        return dataProviderHttpClient.getToken();
    }

    public ProductDto getProduct() {
        return dataProviderHttpClient.getProduct();
    }

    public List<TemperatureDto> getTemperatures() {
        return dataProviderHttpClient.getTemperatures();
    }


}
