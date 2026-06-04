package com.aragon.tu_cv_spring_boot.cv.controller;

import com.aragon.tu_cv_spring_boot.cv.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/cv") //ppdemos tener una ruta base
public class CvController {

    // por defecto busca el index.html
    //@RequestMapping (value = "/bola" ,method = RequestMethod.GET) //tambien podemos utilizar esta notacion
    @GetMapping({"/index" , "/" , ""}) //el html se ejecutara en cualquiera de las 3 rutas
    public String index(Model model){
        Person person = new Person("Israel","Hernandez","Desarrollador");
        model.addAttribute("name","Israel");
        model.addAttribute("persona",person); //enviamos el objeto persona a la vista
        return "index";
    }

//    @GetMapping("/bola") //el html se ejecutara en cualquiera de las 3 rutas
//    public String bola(){
//        return "bola";
//    }

}
