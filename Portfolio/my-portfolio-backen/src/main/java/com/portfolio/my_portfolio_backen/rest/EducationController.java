package com.portfolio.my_portfolio_backen.rest;

import com.portfolio.my_portfolio_backen.model.Education;
import com.portfolio.my_portfolio_backen.service.IEducatonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/education")
@RequiredArgsConstructor
public class EducationController {

    private final IEducatonService educationService;

    @GetMapping //retorna una lista con todos los elementos de tipo Education
    public List<Education> getAll(){return educationService.findAll();}

    @GetMapping("/{id}") //buscamos por el id en ruta usando PathVariable
    public Education getById (@PathVariable Long id){
        Optional<Education> info = educationService.findById(id);
        if(info.isPresent()){
            return info.get();
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Informacion no disponible en el ID: " + id);
        }
    }

    @PostMapping //crear e insertar en base de datos
    public ResponseEntity<Education> save(@RequestBody Education education){
        Education newEducation = educationService.save(education);
        return new ResponseEntity<>(newEducation , HttpStatus.CREATED);
    }

    @PutMapping("/{id}") //actualizar datos
    public Education update (@PathVariable Long id , @RequestBody Education education){
        education.setId(id); //nos aseguramos que sea el id correcto
        return educationService.save(education);
    }

    @DeleteMapping("/{id}") //eliminar de db
    public void deleteById (@PathVariable Long id){

        educationService.deleteById(id);

    }

}
