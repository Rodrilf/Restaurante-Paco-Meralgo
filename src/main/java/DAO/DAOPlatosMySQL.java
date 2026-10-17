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

    public List <Ingrediente> listarIngredientes(){
        List <Ingrediente> ingredientes = new ArrayList<>();

        try {
            String sql = "SELECT * FROM ingredientes";
            PreparedStatement ps = ConexionBD.getInstance().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String nombre = rs.getString("nombre");
                Ingrediente ingrediente = new Ingrediente(nombre);
                ingredientes.add(ingrediente);
            }

            rs.close();
            ps.close();

        } catch (SQLException e){
            e.getMessage();
        }
        return ingredientes;
    }

    @Override
    public List<Plato> buscarPorIngrediente(String ingrediente) {

        List<Plato> platos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM platos WHERE nombre IN " +
                    "(SELECT nombre_plato FROM plato_ingrediente WHERE nombre_ingrediente = ?)";

            PreparedStatement ps = ConexionBD.getInstance().prepareStatement(sql);
            ps.setString(1, ingrediente);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                String tipo = rs.getString("tipo");

                Plato plato = new Plato(nombre, precio, tipo);
                platos.add(plato);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return platos;
    }

    @Override
    public List<Plato> buscarPorTipo(String tipo) {

        List<Plato> platos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM platos WHERE tipo = ?";

            PreparedStatement ps = ConexionBD.getInstance().prepareStatement(sql);
            ps.setString(1, tipo);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                String tipoPlato = rs.getString("tipo");

                Plato plato = new Plato(nombre, precio, tipoPlato);
                platos.add(plato);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return platos;
    }

    @Override
    public Plato buscarPorNombre(String nombre) {

        Plato plato = null;

        try {
            String sql = "SELECT * FROM platos WHERE nombre = ?";

            PreparedStatement ps = ConexionBD.getInstance().prepareStatement(sql);
            ps.setString(1, nombre);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) { // hecho con if porque while a veces devuelve más de un plato
                String nombrePlato = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                String tipo = rs.getString("tipo");

                plato = new Plato(nombrePlato, precio, tipo);
            }

            rs.close();
            ps.close();

            if (plato != null) {

                List<Ingrediente> ingredientes = new ArrayList<>();

                String sql2 = "SELECT nombre_ingrediente FROM plato_ingrediente WHERE nombre_plato = ?";

                PreparedStatement ps2 = ConexionBD.getInstance().prepareStatement(sql2);
                ps2.setString(1, nombre);

                ResultSet rs2 = ps2.executeQuery();

                while (rs2.next()) {
                    String nombreIngrediente = rs2.getString("nombre_ingrediente");

                    Ingrediente ingrediente = new Ingrediente(nombreIngrediente);
                    ingredientes.add(ingrediente);
                }

                rs2.close();
                ps2.close();

                plato.setIngredientes(ingredientes);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return plato;
    }

    @Override
    public void eliminarPlato(String nombre) {

        try {
            String sql1 = "DELETE FROM plato_ingrediente WHERE nombre_plato = ?";
            PreparedStatement ps1 = ConexionBD.getInstance().prepareStatement(sql1);
            ps1.setString(1, nombre);
            ps1.executeUpdate();
            ps1.close();

            String sql2 = "DELETE FROM platos WHERE nombre = ?";
            PreparedStatement ps2 = ConexionBD.getInstance().prepareStatement(sql2);
            ps2.setString(1, nombre);
            ps2.executeUpdate();
            ps2.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actualizarPlato(String nombreOriginal, Plato plato) {
        try {
            // Borrar las relaciones antiguas
            String sql1 = "DELETE FROM plato_ingrediente WHERE nombre_plato = ?";
            PreparedStatement ps1 = ConexionBD.getInstance().prepareStatement(sql1);
            ps1.setString(1, nombreOriginal);
            ps1.executeUpdate();
            ps1.close();

            // Actualizar nombre, precio y tipo
            String sql2 = "UPDATE platos SET nombre = ?, precio = ?, tipo = ? WHERE nombre = ?";
            PreparedStatement ps2 = ConexionBD.getInstance().prepareStatement(sql2);
            ps2.setString(1, plato.getNombre());
            ps2.setDouble(2, plato.getPrecio());
            ps2.setString(3, plato.getTipo());
            ps2.setString(4, nombreOriginal);
            ps2.executeUpdate();
            ps2.close();

            // Añadir los ingredientes seleccionados
            if (plato.getIngredientes() != null) {
                for (Ingrediente ingrediente : plato.getIngredientes()) {
                    insertarPlatoIngrediente(
                            plato.getNombre(),
                            ingrediente.getNombre_ingrediente()
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
