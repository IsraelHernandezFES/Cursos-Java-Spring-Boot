package com.portfolio.my_portfolio_backen.service;

import com.portfolio.my_portfolio_backen.exception.ValidationException;
import com.portfolio.my_portfolio_backen.model.Skill;
import com.portfolio.my_portfolio_backen.repository.ISkillRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //una clase de test
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) //cada que se realiza una prueba regresa la base de datos al estado inicial
public class SkillServiceTest {
    @Autowired //para SpringBootTest utilizamos esta anotacion para los constructos asi evitamos conflictos con LomBock
     private ISkillService skillService;
    @Autowired
     private ISkillRepository skillRepository;

    //creamos nuestros metodo de test
    @Test
    void testSaveValidSkill() {
        Skill validSkill = new Skill(null , "java", 90, "fab fa-java" , 1L);
        Skill savedSkill = skillService.save(validSkill);

        //dos pruebas independientes , del servicio como repositorio para testear conexion y por si algun paso falla
        //serviece
        assertNotNull(savedSkill.getId(),"El objeto guardado deberia de tener un ID asignado"); //si retorna un id es que lo registro con exito
        //repository
        assertNotNull(skillRepository.findById(savedSkill.getId()).orElse(null), "El objeto guardado deberia existir en la base de datos");
    }

    @Test
    void testSaveInvalidSkill(){
        Skill invalidSkill = new Skill(null , "", 90, "fab fa-java" , 1L);
        assertThrows(ValidationException.class, ()->skillService.save(invalidSkill), "debe lanzanrse una Exception si el nombre esta vacio");

    }
}
