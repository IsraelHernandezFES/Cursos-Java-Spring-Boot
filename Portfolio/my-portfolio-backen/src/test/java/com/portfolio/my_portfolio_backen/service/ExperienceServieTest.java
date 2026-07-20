package com.portfolio.my_portfolio_backen.service;

import com.portfolio.my_portfolio_backen.exception.ValidationException;
import com.portfolio.my_portfolio_backen.model.Experience;
import com.portfolio.my_portfolio_backen.model.PersonalInfo;
import com.portfolio.my_portfolio_backen.repository.IExperienceRepository;
import com.portfolio.my_portfolio_backen.repository.IPersonalInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ExperienceServieTest {

    @Autowired
    private IExperienceService experienceService;

    @Autowired
    private IExperienceRepository experienceRepository;

    @Test
    void testSaveValidExperience (){
        Experience validExperience = new Experience(null,"Desarrollador Full Stack Senior", "Tech Solutions S.A.", LocalDate.of(2020, 2, 2), null, "Desarrollo y mantenimiento de aplicaciones empresariales. Liderazgo técnico de equipo de 3 personas.", 1L);
        Experience savedExperience = experienceService.save(validExperience);

        assertNotNull(savedExperience.getId(),"El objeto guardado deberia de tener un id");
        assertNotNull(experienceRepository.findById(savedExperience.getId()).orElse(null), "El objeto guardado deberia existir en la base de datos");
    }

    @Test
    void testSaveInvalidPersonalInfo (){
        Experience invalidExperience = new Experience(null,"", "",LocalDate.of(2027, 2, 2), null, "Desarrollo y mantenimiento de aplicaciones empresariales. Liderazgo técnico de equipo de 3 personas.", 1L);
        assertThrows(ValidationException.class, ()->experienceService.save(invalidExperience), "debe lanzarse una excepcion");
    }
}
