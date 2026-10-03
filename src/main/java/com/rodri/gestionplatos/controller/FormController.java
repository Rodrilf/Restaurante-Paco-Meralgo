package com.rodri.gestionplatos.controller;

import constructores.Plato;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// TODO Almacenar platos en memoria
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

    @RequestMapping("/formulario")
    String formulario () {
        return "formulario";
    }

    @PostMapping("/datos")
    String datos(Plato plato, Model model) {
        System.out.println(plato);
        model.addAttribute("plato", plato);
        return "datosRespuestas";
    }
}
