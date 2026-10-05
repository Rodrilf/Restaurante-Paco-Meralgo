package com.rodri.gestionplatos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

    @RequestMapping ("/index")
    String indice (){
        return "index";
    }
}
