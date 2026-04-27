package com.aragon.tu_cv_spring_boot.cv.rest;

import com.aragon.tu_cv_spring_boot.cv.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api")
public class CvAPIController {
    @GetMapping ("/person")
    public Person getPerson (){
        return new Person("israel", "Hernandez " , "DeveloperJava"); //aqui recibimos un json en lugar de html 
    }

}
