package com.portfolio.my_portfolio_backen.service;


import com.portfolio.my_portfolio_backen.exception.ValidationException;
import com.portfolio.my_portfolio_backen.model.Education;
import com.portfolio.my_portfolio_backen.repository.IEducationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.List;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EducationServiceImplTest {

    @Mock
    private IEducationRepository educationRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private EducationServiceImpl educationService;

    @Test
    void testFindAllReturnsListOfEducations() {
        //preparacion
        List<Education> mockEducations = Arrays.asList(new Education() , new Education());
        when(educationRepository.findAll()).thenReturn(mockEducations);//busca de la lista recien creada no de la DB

        //accion
        List<Education> educations = educationService.findAll();

        //Assert - afirmacion
        assertNotNull(educations); //no sea nulo
        assertEquals(2, educations.size()); //cantidad de valores
        verify(educationRepository,times(1)).findAll(); //solo se llame una vez al repo
    }

    @Test
    void testFindByIdReturnsEducationsWhenFound (){
        //preparacion
        Long id = 1L;
        Education educationMock = new Education();
        when(educationRepository.findById(id)).thenReturn(Optional.of(educationMock));

        //acccion
        Optional <Education> educationFindById = educationService.findById(id);

        //assert
        assertTrue(educationFindById.isPresent()); //esta presente
        assertEquals(educationMock, educationFindById.get());// compara
        verify(educationRepository,times(1)).findById(id); //que solo una ves sea llamado el metodo findbyid
    }

    @Test
    void testSaveEducationThrowsExceptionWhenInvalid(){

        Education invalidEducation = new Education();
        doAnswer(invocationOnMock -> {
            BindingResult result = invocationOnMock.getArgument(1);
            result.rejectValue("degree", "NotBlank","El nombre de la carrera no puede estar vacio"); //simulamos una exceopcion
            return null; //no regresa nada
        }).when(validator).validate(any(Education.class),any(BindingResult.class));

        assertThrows(ValidationException.class,()->educationService.save(invalidEducation) , "Debe lanzarse una ValidationException si el objeto es invalido");

        verify(educationRepository,never()).save(any(Education.class));
    }

    @Test
    void testSaveEducationSavesValidEducation(){
        //preparacion
        Education validEducation= new Education(null,"Ingeniería en Sistemas", "Universidad XYZ", LocalDate.of(2015, 3, 1), LocalDate.of(2020, 3, 1), "Especialización en desarrollo de software y bases de datos.", 1L);
        when(educationRepository.save(any(Education.class))).thenReturn(validEducation);
        doNothing().when(validator).validate(any(Education.class), any(BindingResult.class));

        //accion
        Education result = educationService.save(validEducation);

        //assert
        assertNotNull(result);
        verify(educationRepository,times(1)).save(validEducation);
    }

}