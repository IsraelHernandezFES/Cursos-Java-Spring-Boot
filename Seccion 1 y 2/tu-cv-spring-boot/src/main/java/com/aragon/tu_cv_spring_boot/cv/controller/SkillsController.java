package com.aragon.tu_cv_spring_boot.cv.controller;

import com.aragon.tu_cv_spring_boot.cv.model.Person;
import com.aragon.tu_cv_spring_boot.cv.model.Skill;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
//    @GetMapping
//    public String showSkills (Model model){
//
//        model.addAttribute("skills", skills);
//        return "skills";
//    }


    //@RequestParam es para mandar parametros atravez de la peticion ya sea de URL o formulario
    //podemos hacer filtros de busqueda o mandar valores por defectos con la peticion
    //Ej.  http://localhost:8080/skills?filter=java
    @GetMapping
    public String showSkills (@RequestParam(defaultValue = "",required = false) String filter , Model model){
        //realizamos un filtro de busqueda
        List<Skill> skillsFilter = skills.stream()
                .filter(skill -> skill.getName().toLowerCase().contains(filter.toLowerCase()))
                .toList();

        model.addAttribute("skills",skillsFilter);
        model.addAttribute("filter", filter);
        return "skills";
    }


    //@PathVariable se utiliza para extrar valores que vienen diractamente en la URL
    //Ej.  localhost:8080/skills/0
    @GetMapping("/{index}") //@GetMapping obligatoriamente recibe un valor
    public String showSkillDetail (@PathVariable int index , Model model){
        if (index >= 0 && index < skills.size()){
            Skill skill = skills.get(index);
            model.addAttribute("skill", skill);
            return "skill-detail"; //mandamos la vista
        }

        return "redirect:/skills"; //si no se encontro un valor en el indice retornamos al inicio

    }

    //podemos trabajar con 2 filtros
    //Ej. http://localhost:8080/skills/Canto/Bajo
    @GetMapping("/{name}/{level}") //@GetMapping obligatoriamente recibe 2 valores
    public String showFilteredSkill (@PathVariable String name , @PathVariable String level , Model model){

        List<Skill> skillsFilter = skills.stream()
                .filter(skill -> skill.getName().equalsIgnoreCase(name)&& skill.getLevel().equalsIgnoreCase(level))
                .toList(); //igual el filtro lo trabajamos con 2 valores

        model.addAttribute("skills", skillsFilter);
        model.addAttribute("filterMessage", "Filtro" + name + "--" + level);
        return "skills"; //trabajamos dentro de la misma vista

    }

    //solucionar rutas invalidas en el filtro


    //abrir formulario para agregar nueva skills
    @GetMapping ("/new")
    public String ShowForm (Model model){

        model.addAttribute("skill", new Skill());
        return "add-skill";
    }


    //al momento de enviar los datos se crea una nueva skill que se agrega a la lista y nos derige de nuevo a la pagina de skills
    @PostMapping("/add") //@PostMaping para que escuche el envio del formulario
    public String addSkill (@ModelAttribute Skill skill){ // agregamos @ModelAttribute que es una vinculacion entre el form html y el code Java
        //vemos la lista actualizada
        skills.add(skill);
        return "redirect:/skills";
    }

}
