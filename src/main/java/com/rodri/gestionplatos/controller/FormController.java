package com.rodri.gestionplatos.controller;

import constructores.Ingrediente;
import constructores.Plato;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FormController {

    /* Hacer un formulario para editar los platos y que dentro
    de ese formulario se puedan pasar ingredientes con
    un campo vacío y que se vayan añadiendo a una texto */

    private List<Plato> platos = new ArrayList<>();

    @GetMapping("/lista")
    public String lista(Model model){
        model.addAttribute("platos", platos);
        return "lista";
    }

    @RequestMapping("/formulario")
    String formulario () {
        return "formulario";
    }

    @PostMapping("/datos")
    String datos(Plato plato, Model model) {
        platos.add(plato);
        model.addAttribute("platos", platos);
        return "datosRespuestas";
    }
}
