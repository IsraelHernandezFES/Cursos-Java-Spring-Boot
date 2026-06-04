package com.aragon.tu_cv_spring_boot.cv.controller;

import com.aragon.tu_cv_spring_boot.cv.model.Person;
import com.aragon.tu_cv_spring_boot.cv.model.Skill;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//ejercicio , crear un controller, un metodo get que retorne la ruta skills con la vista skills.html con una lista de habilidades
@Controller
@RequestMapping("/skills")
public class SkillsController {

    private final List<Skill> skills = new ArrayList<>();


    //mostrar lista de skills
    @GetMapping
    public String showSkills (Model model){

        model.addAttribute("skills", skills);
        return "skills";
    }

    //abrir formulario para agregar nueva skills
    @GetMapping ("/new")
    public String ShowForm (Model model){

        model.addAttribute("skills", new Skill());
        return "add-skills";
    }


    //al momento de enviar los datos se crea una nueva skill que se agrega a la lista y nos derige de nuevo a la pagina de skills
    @GetMapping ("/add")
    public String addSkill (@ModelAttribute Skill skill){ // agregamos @ModelAttribute que es una vinculacion entre el form html y el code Java

        skills.add(skill);
        return "redirect:/skills";
    }



}
