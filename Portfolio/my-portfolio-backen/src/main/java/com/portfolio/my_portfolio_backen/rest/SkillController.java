package com.portfolio.my_portfolio_backen.rest;

import com.portfolio.my_portfolio_backen.model.Skill;
import com.portfolio.my_portfolio_backen.service.ISkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.support.ReactivePageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.xml.sax.SAXNotRecognizedException;

import javax.swing.plaf.SliderUI;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/skill")
@RequiredArgsConstructor
public class SkillController {

    private final ISkillService skillService;

    @GetMapping
    public List<Skill> getAll (){
        return skillService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> getById (@PathVariable Long id){
        Optional<Skill> info = skillService.findById(id);
        if (info.isPresent()){
            return new ResponseEntity<>(info.get(), HttpStatus.OK);
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/personal-info/{id}")
    public List<Skill> findSkillsByPersonalInfoId(@PathVariable("id") Long personalInfoId){
        return skillService.findByPersonalInfoId(personalInfoId);
    }

    @PostMapping
    public ResponseEntity<Skill> save ( @RequestBody Skill skill){
        Skill newSkill = skillService.save(skill);
        return new ResponseEntity<>(newSkill,HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public Skill update (@PathVariable Long id , @RequestBody Skill skill){
        skill.setId(id);
        return skillService.save(skill);
    }

    @DeleteMapping("/{id}")
    public void deteleById(@PathVariable Long id){skillService.deleteById(id);}

}
