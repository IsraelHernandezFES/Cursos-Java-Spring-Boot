package com.portfolio.my_portfolio_backen.service;

import com.portfolio.my_portfolio_backen.exception.ValidationException;
import com.portfolio.my_portfolio_backen.model.Education;
import com.portfolio.my_portfolio_backen.model.Experience;
import com.portfolio.my_portfolio_backen.repository.IEducationRepository;
import com.portfolio.my_portfolio_backen.repository.IExperienceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EducationServiceTest {

    @Autowired
    private IEducatonService educationService;

    @Autowired
    private IEducationRepository educationRepository;

    @Test
    void testSaveValidExperience (){
        Education validEducation = new Education(null,"Ingeniería en Sistemas", "Universidad XYZ", LocalDate.of(2015, 3, 1), LocalDate.of(2020, 3, 1), "Especialización en desarrollo de software y bases de datos.", 1L);
        Education savedEducation = educationRepository.save(validEducation);

        assertNotNull(savedEducation.getId(),"El objeto guardado deberia de tener un id");
        assertNotNull(educationRepository.findById(savedEducation.getId()).orElse(null), "El objeto guardado deberia existir en la base de datos");
    }

    @Test
    void testSaveInvalidPersonalInfo (){
        Education invalidEducation = new Education(null,"", "", LocalDate.of(2030, 3, 1), LocalDate.of(2020, 3, 1), "Especialización en desarrollo de software y bases de datos.", 1L);
        assertThrows(ValidationException.class, ()->educationService.save(invalidEducation), "debe lanzarse una excepcion");
    }
}