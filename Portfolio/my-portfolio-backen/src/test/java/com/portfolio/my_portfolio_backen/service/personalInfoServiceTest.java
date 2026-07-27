package com.portfolio.my_portfolio_backen.service;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.portfolio.my_portfolio_backen.exception.ValidationException;
import com.portfolio.my_portfolio_backen.model.PersonalInfo;
import com.portfolio.my_portfolio_backen.repository.IPersonalInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.swing.text.ParagraphView;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext (classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//@ExtendWith(MockitoExtension.class) //mokito es un frameword para pruebas unitarias que hace objetos simulados
public class personalInfoServiceTest {

    @Autowired
    private IPersonalInfoService personalInfoService;

    @Autowired
    private IPersonalInfoRepository personalInfoRepository;


//    para pruebas unitarias podemos hacer objetos simulados atravez de Mockito

//    @InjectMocks
//    private IPersonalInfoService personalInfoService;
//
//    @Mock
//    private IPersonalInfoRepository personalInfoRepository;


    @Test
    void testSaveValidPersonalInfo (){
        PersonalInfo validPersonalInfo = new PersonalInfo(null ,"Juan", "Perez", "Full Stack Developer", "Apasionado por el desarrollo web con experiencia en Java, Spring Boot y React. Disfruto construyendo soluciones robustas y escalables.", "img/profile-placeholder.jpg", 5, "juan.perez@example.com", "5539400043", "https://linkedin.com/in/juanperez", "https://github.com/juanperez");
        PersonalInfo savedPersonalInfo = personalInfoService.save(validPersonalInfo);

        assertNotNull(savedPersonalInfo.getId(),"El objeto guardado deberia de tener un id");
        assertNotNull(personalInfoRepository.findById(savedPersonalInfo.getId()).orElse(null), "El objeto guardado deberia existir en la base de datos");
    }

    @Test
    void testSaveInvalidPersonalInfo (){
        PersonalInfo invalidPersonalInfo = new PersonalInfo(null , "" , "" , "personalInfo" , "descripcion" , "rutadeimagen.jpg",2,"jesusgmail.com","55394004512","dadawdw" , "jfjfjf");
        assertThrows(ValidationException.class, ()->personalInfoService.save(invalidPersonalInfo), "debe lanzarse una excepcion");
    }
}
