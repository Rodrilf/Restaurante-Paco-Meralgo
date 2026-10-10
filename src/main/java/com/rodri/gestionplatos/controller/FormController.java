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
        ingredientes.add(new Ingrediente("Tomate"));
        ingredientes.add(new Ingrediente("Pasta"));
        ingredientes.add(new Ingrediente("Sal"));
        ingredientes.add(new Ingrediente("Azúcar"));
        ingredientes.add(new Ingrediente("Arroz"));
        ingredientes.add(new Ingrediente("Pollo"));
        ingredientes.add(new Ingrediente("Carne"));
        ingredientes.add(new Ingrediente("Pescado"));
        ingredientes.add(new Ingrediente("Patata"));
        ingredientes.add(new Ingrediente("Cebolla"));
        ingredientes.add(new Ingrediente("Ajo"));
        ingredientes.add(new Ingrediente("Huevo"));
        ingredientes.add(new Ingrediente("Queso"));
        ingredientes.add(new Ingrediente("Leche"));
        ingredientes.add(new Ingrediente("Harina"));
        ingredientes.add(new Ingrediente("Aceite"));
        ingredientes.add(new Ingrediente("Pimiento"));
        ingredientes.add(new Ingrediente("Zanahoria"));
        ingredientes.add(new Ingrediente("Lechuga"));
        ingredientes.add(new Ingrediente("Champiñones"));
    }


    // lista completa de los platos

    @GetMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("platos", platos);
        return "lista";
    }

    // busqueda por tipo de plato

    @GetMapping("/buscarPorTipo")
    public String buscarPorTipo(String tipo, Model model) {
        List<Plato> filtrados = new ArrayList<>();
        for (Plato plato : platos) {
            if (plato.getTipo().equals(tipo)) {
                filtrados.add(plato);
            }
        }
        model.addAttribute("platos", filtrados);
        return "lista";
    }

    // busqueda por ingrediente

    @GetMapping("/busquedaPorIngrediente")
    public String buscarPorIngrediente(String ingrediente, Model model) {
        List<Plato> filtrados = new ArrayList<>();
        for (Plato plato : platos) {
            for (Ingrediente ing : plato.getIngredientes()) {
                if (ing.getNombre_ingrediente().equals(ingrediente)) {
                    filtrados.add(plato);
                }
            }
        }
        model.addAttribute("platos", filtrados);
        return "lista";
    }


    // detalles de los platos, pero mostrando todos los valores que no muestra lista (a priori misma funcionalidad)

    @GetMapping("/detalles")
    public String detalles(Model model){
        model.addAttribute("platos", platos);
        return "detalles";
    }

    // formulario

    @RequestMapping("/formulario")
    String formulario (Model model) {
        model.addAttribute("ingredientes", ingredientes);
        return "formulario";
    }

    // formulario de datos + vuelta al HTML que dice: se ha creado correctamente

    @PostMapping("/datos")
    String datos(Plato plato, Model model) {
        platos.add(plato);
        model.addAttribute("platos", platos);
        return "datosRespuestas";
    }
}
