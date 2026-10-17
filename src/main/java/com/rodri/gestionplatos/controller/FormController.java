
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

@Controller
public class FormController {

    // DAO para trabajar con MySQL
    private DAOPlatos dao = new DAOPlatosMySQL();

    // Lista completa de platos
    @GetMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("platos", dao.listar());
        return "lista";
    }

    // Búsqueda por tipo
    @GetMapping("/buscarPorTipo")
    public String buscarPorTipo(String tipo, Model model) {
        model.addAttribute("platos", dao.buscarPorTipo(tipo));
        return "lista";
    }

    // Búsqueda por ingrediente
    @GetMapping("/busquedaPorIngrediente")
    public String buscarPorIngrediente(String ingrediente, Model model) {
        model.addAttribute("platos", dao.buscarPorIngrediente(ingrediente));
        return "lista";
    }

    // Detalles de los platos
    @GetMapping("/detalles")
    public String detalles(String nombre, Model model) {
        Plato plato = dao.buscarPorNombre(nombre);
        model.addAttribute("plato", plato);
        return "detalles";
    }

    // Formulario para añadir platos
    @RequestMapping("/formulario")
    String formulario(Model model) {
        model.addAttribute("ingredientes", dao.listarIngredientes());
        return "formulario";
    }

    // Insertar plato e ingredientes en MySQL
    @PostMapping("/datos")
    String datos(Plato plato) {

        dao.insertarPlato(plato);

        if (plato.getIngredientes() != null) {
            for (Ingrediente ingrediente : plato.getIngredientes()) {
                dao.insertarIngrediente(ingrediente);
                dao.insertarPlatoIngrediente(
                        plato.getNombre(),
                        ingrediente.getNombre_ingrediente()
                );
            }
        }

        return "datosRespuestas";
    }

    // metodo para editar
    @GetMapping("/editar")
    public String editar(String nombre, Model model) {
        model.addAttribute("plato", dao.buscarPorNombre(nombre));
        model.addAttribute("ingredientes", dao.listarIngredientes());
        return "editar";
    }

    @PostMapping("/actualizar")
    public String actualizar(String nombreOriginal, Plato plato) {
        dao.actualizarPlato(nombreOriginal, plato);
        return "redirect:/lista";
    }

    // metodo para borrar
    @PostMapping("/borrar")
    public String borrar(String nombre) {
        dao.eliminarPlato(nombre);
        return "redirect:/lista";
    }
}