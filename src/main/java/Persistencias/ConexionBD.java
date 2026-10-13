package Persistencias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static Connection conexion;

    private static final String USUARIO = System.getenv("USUARIO");
    private static final String PASSWORD = System.getenv("PASSWORD");

    public static Connection getConexion() throws SQLException {
        if (conexion == null){
            conexion = DriverManager.getConnection("jdbc:mysql://para-recursos-ejemplo.c6evih14cs8v.us-east-1.rds.amazonaws.com:3306/recursosbd", USUARIO, PASSWORD);
        }
        return conexion;
    }

    // Intento de conexion a la base de datos con mensajes por consola para ver si funciona o no (NO ES OBLIGATORIO PERO SI RECOMENDABLE)

    public void intentoConexion () {
        try {
            ConexionBD.getConexion();
            System.out.println("Conectado a la base de datos");

        } catch (Exception e){
            System.out.println("No se ha podido conectar a la base de datos");
            e.printStackTrace();
        }
    }
}