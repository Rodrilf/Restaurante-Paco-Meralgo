
package Persistencias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static Connection conexion;

    private ConexionBD() {}

    public static Connection getInstance() {
        if (conexion == null) {
            try {
                conexion = DriverManager.getConnection("jdbc:mysql://paco-meralgo.c4wgub6fi2f1.us-east-1.rds.amazonaws.com:3306/pacomeralgo", "admin", "administrador");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conexion;
    }

     /*
    SCRIPT SQL USADO EN LA BASE DE DATOS:

    CREATE DATABASE pacomeralgo;
    USE pacomeralgo;

    CREATE TABLE platos (
        nombre VARCHAR(100) PRIMARY KEY,
        precio DOUBLE,
        tipo VARCHAR(50)
    );

    CREATE TABLE ingredientes (
        nombre VARCHAR(100) PRIMARY KEY
    );

    CREATE TABLE plato_ingrediente (
        nombre_plato VARCHAR(100),
        nombre_ingrediente VARCHAR(100),

        PRIMARY KEY (nombre_plato, nombre_ingrediente),

        FOREIGN KEY (nombre_plato)
            REFERENCES platos(nombre),

        FOREIGN KEY (nombre_ingrediente)
            REFERENCES ingredientes(nombre)
        );

     */
}