package com.modelo.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ModeloDAO {

    public static String getDatos(String dniEmpleado, String nombreDato) {
        try {
            Connection conexion;
            PreparedStatement consulta;
            ResultSet resultado;

            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("SELECT * FROM empleados WHERE dni=?");
            consulta.setString(1, dniEmpleado);
            resultado = consulta.executeQuery();
            if (resultado.next()) {
                return resultado.getString(nombreDato);
            }

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
    }

    public static boolean existe(String nombreTabla, String nombreColumna, String datoBuscar) {
        boolean existe = false;
        Connection conexion;
        PreparedStatement consulta;
        ResultSet resultado;
        try {
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("SELECT * FROM " + nombreTabla + " WHERE " + nombreColumna + "=" + datoBuscar);
            resultado = consulta.executeQuery();
            if (resultado.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return existe;
    }

    public static String getRangoEmpleado(String dniEmpleado) {

        try {
            Connection conexion;
            PreparedStatement consulta;
            ResultSet resultado;

            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("SELECT * FROM empleados WHERE dni=?");
            consulta.setString(1, dniEmpleado);
            resultado = consulta.executeQuery();
            if (resultado.next()) {
                return resultado.getString("rango");
            }

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
    }

    public static String DNI_EMPLEADO;

    public static boolean usuarioValido(String usuario, String contraseña) {
        Connection conexion;
        ResultSet resultado;
        PreparedStatement consulta;
        try {
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("SELECT * FROM empleados WHERE dni=?");
            consulta.setString(1, usuario);
            resultado = consulta.executeQuery();
            if (resultado.next()) {
                String contraseñaBD = resultado.getString("contraseña");
                if (contraseña.equalsIgnoreCase(contraseñaBD)) {
                    DNI_EMPLEADO = usuario;
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no existe", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return false;
    }

    public static Object consultarDato(String nombreTabla, String nombreColumnaDatoBuscar, String datoBuscar, String nombreColumnDatoRetornar, String tipoDatoRetorno) {
        Connection conexion;
        ResultSet resultado;
        PreparedStatement consulta;

        try {
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("SELECT * FROM " + nombreTabla + " WHERE " + nombreColumnaDatoBuscar + "=?");
            consulta.setString(1, datoBuscar);
            resultado = consulta.executeQuery();
            if (resultado.next()) {
                switch (tipoDatoRetorno) {
                    case "String" -> {
                        return resultado.getString(nombreColumnDatoRetornar);
                    }
                    case "Double" -> {
                        return resultado.getDouble(nombreColumnDatoRetornar);
                    }
                    case "Int" -> {
                        return resultado.getInt(nombreColumnDatoRetornar);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
    }

}
