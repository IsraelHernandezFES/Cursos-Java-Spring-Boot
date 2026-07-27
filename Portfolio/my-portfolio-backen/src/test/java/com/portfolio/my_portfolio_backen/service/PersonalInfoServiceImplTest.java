package com.portfolio.my_portfolio_backen.service;

import com.portfolio.my_portfolio_backen.exception.ValidationException;
import com.portfolio.my_portfolio_backen.model.Education;
import com.portfolio.my_portfolio_backen.model.PersonalInfo;
import com.portfolio.my_portfolio_backen.model.Skill;
import com.portfolio.my_portfolio_backen.repository.IPersonalInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonalInfoServiceImplTest {

    @Mock
    private IPersonalInfoRepository personalInfoRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private PersonalInfoServiceImpl personalInfoService;

    @Test
    void testFindAllReturnsListOfPersonalInfo (){
        //Preparacion
        List<PersonalInfo> mockPersonal = Arrays.asList(new PersonalInfo() ,new PersonalInfo() );
        when(personalInfoRepository.findAll()).thenReturn(mockPersonal); //thenRetrun le dice que no busque los Skills directamente de la BD si no de la lista que acabamos de crear

        //Accion
        List<PersonalInfo> result = personalInfoService.findAll();

        //Assert - Afirmacion
        assertNotNull(result); //que sea valido y no nulo
        assertEquals(2, result.size()); //la cantidad de valores
        verify(personalInfoRepository,times(1)).findAll(); //verifica que se llame al repositorio y al metodo solo una vez

    }

    @Test
    void testFindByIdReturnsSkillsWhenFound (){
        //Preparacion
        Long id = 1L;
        PersonalInfo MockPersonal = new PersonalInfo();
        when(personalInfoRepository.findById(id)).thenReturn(Optional.of(MockPersonal));

        //Accion
        Optional<PersonalInfo> result =personalInfoService.findById(id);

        //Assert
        assertTrue(result.isPresent()); //que este presente
        assertEquals(MockPersonal, result.get()); //la cantidad de valores
        verify(personalInfoRepository,times(1)).findById( id); //verifica que se llame al repositorio y al metodo solo una vez
    }


    @Test
    void testSaveSkillThrowsExceptionWhenInvalid(){

        PersonalInfo invalidPersonal = new PersonalInfo();
        doAnswer(invacationOnMock -> {
            BindingResult result = invacationOnMock.getArgument(1);
            result.rejectValue("firstName", "NotBlank", "El nombre no puede estar vacio");//simulamos una excepcion
            return null ; //no regresa nada
        }).when(validator).validate(any(PersonalInfo.class), any(BindingResult.class));

        assertThrows(ValidationException.class,()->personalInfoService.save(invalidPersonal), "Debe lanzarse una ValidationException si el objeto es invalido");

        verify(personalInfoRepository,never()).save(any(PersonalInfo.class)); //verificamos que nunca haya una conexion con base de datos con cualquier Skill

    }

    @Test
    void testSaveSkillSavesValidSkill (){
        //preparacion
        PersonalInfo validPersonal = new PersonalInfo(null ,"Juan", "Perez", "Full Stack Developer", "Apasionado por el desarrollo web con experiencia en Java, Spring Boot y React. Disfruto construyendo soluciones robustas y escalables.", "img/profile-placeholder.jpg", 5, "juan.perez@example.com", "5539400043", "https://linkedin.com/in/juanperez", "https://github.com/juanperez");
        when(personalInfoRepository.save(any(PersonalInfo.class))).thenReturn(validPersonal); //no va a la base de datos, le pedimos que devuelva el objeto que acabamos de crear
        doNothing().when(validator).validate(any(PersonalInfo.class),any(BindingResult.class)); //no hacer nada cuando el servicio se llame

        //accion
        PersonalInfo result = personalInfoService.save(validPersonal);

        //Assert
        assertNotNull(result);
        verify(personalInfoRepository,times(1)).save(result);
    }
}