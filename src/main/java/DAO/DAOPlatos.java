package DAO;

import constructores.Ingrediente;
import constructores.Plato;

import java.util.List;

public interface DAOPlatos {
    void insertarPlato(Plato plato);
    void insertarIngrediente(Ingrediente ingrediente);
    void insertarPlatoIngrediente (String plato, String ingrediente);
    List<Plato> listar();
}