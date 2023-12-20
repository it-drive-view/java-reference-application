package com.coussy.reference;

import com.coussy.reference.student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
//@EntityScan(basePackageClasses = {
//        Student.class,
//        Jsr310JpaConverters.class
//})
public class ReferenceApplication {

    public static void main(String[] args) {

        SpringApplication.run(ReferenceApplication.class, args);
    }

}
