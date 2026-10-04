package com.rodri.gestionplatos.controller;

import constructores.Ingrediente;
import constructores.Plato;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

// TODO Gestión de ingredientes
// TODO Relacionar ingredientes con platos
// TODO Listar platos
// TODO Ver detalle de un plato
// TODO Editar platos
// TODO Borrar platos
// TODO Filtrar por tipo de plato
// TODO Buscar por ingrediente

@Controller
public class FormController {

    private List<Plato> platos = new ArrayList<>();
    private List<Ingrediente> ingredientes = new ArrayList<>();

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

    @RequestMapping("/formularioIngredientes")
    String formularioIngredientes() {
        return "formularioIngredientes";
    }

    @PostMapping("/ingredientes")
    public String crearIngrediente(Ingrediente ingrediente, Model model) {
        ingredientes.add(ingrediente);
        model.addAttribute("ingredientes", ingredientes);
        return "formularioIngredientes";
    }

}
