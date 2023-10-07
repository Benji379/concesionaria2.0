package com.modelo.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConexionSQL {

    public Connection conexion() {
        Connection conectar = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/concesionaria2", "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return conectar;
    }

}
