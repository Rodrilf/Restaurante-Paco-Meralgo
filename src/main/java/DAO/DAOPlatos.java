package DAO;

import constructores.Plato;

import java.util.List;

public interface DAOPlatos {
    void insertar(Plato plato);

    List<Plato> listar();
}