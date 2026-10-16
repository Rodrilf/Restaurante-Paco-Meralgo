package DAO;

import Persistencias.ConexionBD;
import constructores.Ingrediente;
import constructores.Plato;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOPlatosMySQL implements DAOPlatos {

    // 3 metodos para poder insertar a la tabla de platos, a la tabla de ingredientes y a la tabla de plato_ingrediente

    @Override
    public void insertarPlato(Plato plato) {
        String SQLPlato = "INSERT INTO platos (nombre, precio, tipo) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = ConexionBD.getInstance().prepareStatement(SQLPlato);

            ps.setString(1, plato.getNombre());
            ps.setDouble(2, plato.getPrecio());
            ps.setString(3, plato.getTipo());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void insertarIngrediente (Ingrediente ingrediente){
        String SQLIngrediente = "INSERT INTO ingredientes (nombre) VALUES (?)";

        try {
            PreparedStatement ps = ConexionBD.getInstance().prepareStatement(SQLIngrediente);

            ps.setString(1, ingrediente.getNombre_ingrediente());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e){
            e.getMessage();
        }
    }

    @Override
    public void insertarPlatoIngrediente (String plato, String ingrediente) {
        String SQLPlatoIngrediente = "INSERT INTO plato_ingrediente (nombre_plato, nombre_ingrediente) VALUES (?, ?)";

        try {
            PreparedStatement ps = ConexionBD.getInstance().prepareStatement(SQLPlatoIngrediente);

            ps.setString(1, plato);
            ps.setString(2, ingrediente);

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e){
            e.getMessage();
        }
    }

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
            e.getMessage();
        }
        return platos;
    }

}
