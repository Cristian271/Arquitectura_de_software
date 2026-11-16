package com.aquitectura.spring.proyecto.proyecto_final.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller 
public class MenuController {

   @GetMapping("/Menu")

    public String info(){
        return "Menu";
    }


}
