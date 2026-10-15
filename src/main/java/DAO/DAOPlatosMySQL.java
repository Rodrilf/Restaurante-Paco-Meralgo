package DAO;

import Persistencias.ConexionBD;
import constructores.Plato;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOPlatosMySQL implements DAOPlatos {

    @Override
    public void insertar(Plato plato) {

        String sql = "INSERT INTO platos (nombre, precio, tipo) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = ConexionBD.getInstance().prepareStatement(sql);

            ps.setString(1, plato.getNombre());
            ps.setDouble(2, plato.getPrecio());
            ps.setString(3, plato.getTipo());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
