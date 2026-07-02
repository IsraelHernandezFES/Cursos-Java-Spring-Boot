package com.portfolio.my_portfolio_backen.rest;

import com.portfolio.my_portfolio_backen.model.PersonalInfo;
import com.portfolio.my_portfolio_backen.service.IPersonalInfoService;
import com.portfolio.my_portfolio_backen.service.PersonalInfoServiceImpl;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController //retorna
@RequestMapping("api/test-personal-info")
public class PersonalInfoTestController {

    private final IPersonalInfoService personalInfoService; //principio Solid , depender de abstraccion

    public PersonalInfoTestController(IPersonalInfoService personalInfoService) {
        this.personalInfoService = personalInfoService;
    }

    @GetMapping("/all")
    public List<PersonalInfo> getAll(){
        return personalInfoService.findAll();
    }

    @GetMapping("/{id}") //le pasamos el id por la ruta
    public PersonalInfo getPersonalInfoById (@PathVariable Long id){
        Optional<PersonalInfo> info = personalInfoService.findById(id);
        if(info.isPresent()){
            return info.get();
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Informacion no disponible en el ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<PersonalInfo> createPersonalInfo(@RequestBody PersonalInfo personalInfo){
        PersonalInfo newPersonalInfo = personalInfoService.save(personalInfo);
        return new ResponseEntity<>(newPersonalInfo,HttpStatus.CREATED);
    }
}
