package com.aragon.tu_cv_spring_boot.cv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component  //los modelos con @component se pueden inyectar en cualquier parte sin necesidad de hacer un New y darle atributos paso por paso
public class Person {

    @Value("${person.firstname}")  //este atributo lo cargamos en el apllicacion.properties
    private String firstName;
    private String lastName;
    private String job;

}
