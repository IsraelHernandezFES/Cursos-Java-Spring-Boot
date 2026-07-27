package com.portfolio.my_portfolio_backen.service;

import com.portfolio.my_portfolio_backen.exception.ValidationException;
import com.portfolio.my_portfolio_backen.model.Skill;
import com.portfolio.my_portfolio_backen.repository.ISkillRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


//Pruebas Unitarias con mockito
@ExtendWith(MockitoExtension.class) //Mockito es un framework para pruebas unitarias que simula objetos y no interactua de manera permanente
public class SkillServiceImplTest {

    @Mock
    private ISkillRepository skillRepository;

    @Mock
    private Validator validator;

    @InjectMocks //crea una instanica e injecta el Mock del repositorio
    private SkillServiceImpl skillService;

    @Test
    void testFindAllReturnsListOfSkills(){
        //Preparacion
        List<Skill> mockSkills = Arrays.asList(new Skill() ,new Skill() );
        when(skillRepository.findAll()).thenReturn(mockSkills); //thenRetrun le dice que no busque los Skills directamente de la BD si no de la lista que acabamos de crear

        //Accion
        List<Skill> skills = skillService.findAll();

        //Assert - Afirmacion
        assertNotNull(skills); //que sea valido y no nulo
        assertEquals(2, skills.size()); //la cantidad de valores
        verify(skillRepository,times(1)).findAll(); //verifica que se llame al repositorio y al metodo solo una vez
    }

    @Test
    void testFindByIdReturnsSkillsWhenFound (){
        //Preparacion
        Long id = 1L;
        Skill skillMock = new Skill();
        when(skillRepository.findById(id)).thenReturn(Optional.of(skillMock));

        //Accion
        Optional<Skill> skillFindById = skillService.findById(id);

        //Assert
        assertTrue(skillFindById.isPresent()); //que este presente
        assertEquals(skillMock, skillFindById.get()); //la cantidad de valores
        verify(skillRepository,times(1)).findById( id); //verifica que se llame al repositorio y al metodo solo una vez
    }

    @Test
    void testSaveSkillThrowsExceptionWhenInvalid(){

        Skill invalidSkill = new Skill();
        doAnswer(invacationOnMock -> {
            BindingResult result = invacationOnMock.getArgument(1);
            result.rejectValue("name", "NotBlank", "El nombre no puede estar vacio");//simulamos una excepcion
            return null ; //no regresa nada
        }).when(validator).validate(any(Skill.class), any(BindingResult.class));

        assertThrows(ValidationException.class,()->skillService.save(invalidSkill), "Debe lanzarse una ValidationException si el objeto es invalido");

        verify(skillRepository,never()).save(any(Skill.class)); //verificamos que nunca haya una conexion con base de datos con cualquier Skill

    }

    @Test
    void testSaveSkillSavesValidSkill (){
        //preparacion
        Skill validSkill = new Skill(null , "java", 90, "fab fa-java" , 1L);
        when(skillRepository.save(any(Skill.class))).thenReturn(validSkill); //no va a la base de datos, le pedimos que devuelva el objeto que acabamos de crear
        doNothing().when(validator).validate(any(Skill.class),any(BindingResult.class)); //no hacer nada cuando el servicio se llame

        //accion
        Skill saveSkill = skillService.save(validSkill);

        //Assert
        assertNotNull(saveSkill);
        verify(skillRepository,times(1)).save(validSkill);
    }
}
