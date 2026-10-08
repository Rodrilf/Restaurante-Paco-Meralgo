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

    private List<Plato> platos = new ArrayList<>();
    private List<Ingrediente> ingredientes = new ArrayList<>();

    public FormController() {
        ingredientes.add(new Ingrediente("Leche"));
        ingredientes.add(new Ingrediente("Harina"));
        ingredientes.add(new Ingrediente("Huevo"));
        ingredientes.add(new Ingrediente("Azúcar"));
    }


    @GetMapping("/lista")
    public String lista(Model model){
        model.addAttribute("platos", platos);
        return "lista";
    }

    @RequestMapping("/formulario")
    String formulario (Model model) {
        model.addAttribute("ingredientes", ingredientes);
        return "formulario";
    }

    @PostMapping("/datos")
    String datos(Plato plato, Model model) {
        platos.add(plato);
        model.addAttribute("platos", platos);
        return "datosRespuestas";
    }
}
