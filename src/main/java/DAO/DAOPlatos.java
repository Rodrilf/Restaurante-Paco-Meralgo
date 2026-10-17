package DAO;

import constructores.Ingrediente;
import constructores.Plato;

import java.util.List;

public interface DAOPlatos {
    void insertarPlato(Plato plato);
    void insertarIngrediente(Ingrediente ingrediente);
    void insertarPlatoIngrediente (String plato, String ingrediente);
    void eliminarPlato(String nombre);
    void actualizarPlato(String nombreOriginal, Plato plato);

    Plato buscarPorNombre(String nombre);
    List<Plato> listar();
    List<Ingrediente> listarIngredientes();
    List<Plato> buscarPorIngrediente(String ingrediente);
    List<Plato> buscarPorTipo(String tipo);

}