package DAO;

import Persistencias.ConexionBD;
import constructores.Plato;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override

    public List <Plato> listar(){
        List <Plato> platos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM platos";
            PreparedStatement ps = ConexionBD.getInstance().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String nombre = rs.getString("nombre");
                Double precio = rs.getDouble("precio");
                String tipo = rs.getString("tipo");

                Plato plato = new Plato(nombre,precio, tipo);
                platos.add(plato);
            }

            rs.close();
            ps.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return platos;
    }

}
