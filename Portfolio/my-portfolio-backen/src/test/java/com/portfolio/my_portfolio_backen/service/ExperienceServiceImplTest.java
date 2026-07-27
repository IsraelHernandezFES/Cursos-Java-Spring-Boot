package com.portfolio.my_portfolio_backen.service;

import com.portfolio.my_portfolio_backen.exception.ValidationException;
import com.portfolio.my_portfolio_backen.model.Experience;
import com.portfolio.my_portfolio_backen.model.Skill;
import com.portfolio.my_portfolio_backen.repository.IExperienceRepository;
import org.hibernate.validator.constraints.Mod10Check;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExperienceServiceImplTest {

    @Mock
    private IExperienceRepository experienceRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private ExperienceServiceImpl experienceService;

    @Test
    void testFindAllReturnsListOfExperience(){
        //Preparacion
        List<Experience> mockExperience = Arrays.asList(new Experience() ,new Experience() );
        when(experienceRepository.findAll()).thenReturn(mockExperience); //thenRetrun le dice que no busque los Skills directamente de la BD si no de la lista que acabamos de crear

        //Accion
        List<Experience> result = experienceService.findAll();

        //Assert - Afirmacion
        assertNotNull(result); //que sea valido y no nulo
        assertEquals(2, result.size()); //la cantidad de valores
        verify(experienceRepository,times(1)).findAll(); //verifica que se llame al repositorio y al metodo solo una vez
    }

    @Test
    void testFindByIdReturnsExperienesWhenFound (){
        //Preparacion
        Long id = 1L;
        Experience experienceMock = new Experience();
        when(experienceRepository.findById(id)).thenReturn(Optional.of(experienceMock));

        //Accion
        Optional<Experience> result = experienceService.findById(id);

        //Assert
        assertTrue(result.isPresent()); //que este presente
        assertEquals(experienceMock, result.get()); //la cantidad de valores
        verify(experienceRepository,times(1)).findById( id); //verifica que se llame al repositorio y al metodo solo una vez
    }

    @Test
    void testSaveExperienceThrowsExceptionWhenInvalid(){

        Experience invalidExperience = new Experience();
        doAnswer(invacationOnMock -> {
            BindingResult result = invacationOnMock.getArgument(1);
            result.rejectValue("jobTitle", "NotBlank", "El nombre del trabajo no puede estar vacio");//simulamos una excepcion
            return null ; //no regresa nada
        }).when(validator).validate(any(Experience.class), any(BindingResult.class));

        assertThrows(ValidationException.class,()->experienceService.save(invalidExperience), "Debe lanzarse una ValidationException si el objeto es invalido");

        verify(experienceRepository,never()).save(any(Experience.class)); //verificamos que nunca haya una conexion con base de datos con cualquier Skill

    }

    @Test
    void testSaveExperiencesSavesValidSkill (){
        //preparacion
        Experience validExperience =new Experience(null,"Desarrollador Full Stack Senior", "Tech Solutions S.A.", LocalDate.of(2020, 2, 2), null, "Desarrollo y mantenimiento de aplicaciones empresariales. Liderazgo técnico de equipo de 3 personas.", 1L);
        when(experienceRepository.save(any(Experience.class))).thenReturn(validExperience); //no va a la base de datos, le pedimos que devuelva el objeto que acabamos de crear
        doNothing().when(validator).validate(any(Experience.class),any(BindingResult.class)); //no hacer nada cuando el servicio se llame

        //accion
        Experience result = experienceService.save(validExperience);

        //Assert
        assertNotNull(result);
        verify(experienceRepository,times(1)).save(validExperience);
    }
}