
package com.rodri.gestionplatos.controller;

import DAO.DAOPlatos;
import DAO.DAOPlatosMySQL;
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

    // Listas temporales hasta implementar SELECT con JDBC
    private List<Plato> platos = new ArrayList<>();
    private List<Ingrediente> ingredientes = new ArrayList<>();

    // DAO para insertar platos en MySQL
    private DAOPlatos dao = new DAOPlatosMySQL();

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

    // Lista completa de platos
    @GetMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("platos", platos);
        return "lista";
    }

    // Búsqueda por tipo
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

    // Búsqueda por ingrediente
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

    // Detalles de los platos
    @GetMapping("/detalles")
    public String detalles(Model model) {
        model.addAttribute("platos", platos);
        return "detalles";
    }

    // Formulario
    @RequestMapping("/formulario")
    String formulario(Model model) {
        model.addAttribute("ingredientes", ingredientes);
        return "formulario";
    }

    // Insertar plato en MySQL
    @PostMapping("/datos")
    String datos(Plato plato, Model model) {
        dao.insertar(plato); // Es el que inserta en la base de datos
        platos.add(plato); // Mantiene el array temporalmente hasta que me haga los selects
        model.addAttribute("platos", platos);
        return "datosRespuestas";
    }

    // Ventana para editar
    @RequestMapping("/editar")
    String editar() {
        return "editar";
    }

    // Ventana para borrar
    @RequestMapping("/borrar")
    String borrar() {
        return "borrar";
    }
}
