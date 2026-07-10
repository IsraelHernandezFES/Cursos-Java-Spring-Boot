package com.portfolio.my_portfolio_backen.rest;

import com.portfolio.my_portfolio_backen.model.Education;
import com.portfolio.my_portfolio_backen.model.Experience;
import com.portfolio.my_portfolio_backen.service.IExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/experience")
@RequiredArgsConstructor
public class ExperienceController {

    private final IExperienceService experienceService;

    @GetMapping
    public List<Experience> getAll(){
        return experienceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Experience> getById (@PathVariable Long id){
        Optional<Experience> info = experienceService.findById(id);
        if(info.isPresent()){
            return new ResponseEntity<>(info.get(), HttpStatus.OK);
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Informacion no disponible en el ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Experience> save(@RequestBody Experience experience){
        Experience newExperience = experienceService.save(experience);
        return new ResponseEntity<>(newExperience , HttpStatus.CREATED);
    }

    @PutMapping("/{id}") //actualizar datos
    public Experience update (@PathVariable Long id , @RequestBody Experience experience){
        experience.setId(id);
        return experienceService.save(experience);
    }

    @DeleteMapping("/{id}") //eliminar de db
    public void deleteById (@PathVariable Long id){
        experienceService.deleteById(id);
    }

}
