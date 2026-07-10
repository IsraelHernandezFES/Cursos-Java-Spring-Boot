package com.portfolio.my_portfolio_backen.rest;

import com.portfolio.my_portfolio_backen.model.PersonalInfo;
import com.portfolio.my_portfolio_backen.service.IPersonalInfoService;
import com.portfolio.my_portfolio_backen.service.PersonalInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController //retorna
@RequestMapping("api/test-personal-info")
@RequiredArgsConstructor
public class PersonalInfoTestController {

    private final IPersonalInfoService personalInfoService; //principio Solid , depender de abstraccion


    @GetMapping("/all") //retorna una lista con todos los objetos de tipo PersonalInfo
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

    @PostMapping  //crear e insertar en base de datos
    public ResponseEntity<PersonalInfo> createPersonalInfo(@RequestBody PersonalInfo personalInfo){
        PersonalInfo newPersonalInfo = personalInfoService.save(personalInfo);
        return new ResponseEntity<>(newPersonalInfo,HttpStatus.CREATED);
    }

    @PutMapping("/{id}") //actualizar datos
    public PersonalInfo update (@PathVariable Long id , @RequestBody PersonalInfo personalInfo){
        personalInfo.setId(id);
        return personalInfoService.save(personalInfo);
    }

    @DeleteMapping("/{id}") //eleminar de la base de datos
    public void deleteBy(@PathVariable Long id){
        personalInfoService.deleteById(id);
    }
}
