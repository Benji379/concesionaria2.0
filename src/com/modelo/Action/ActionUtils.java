package com.modelo.Action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActionUtils {

    public static void AccederEnlace(String enlace) {
        String url = "C:\\Windows\\System32\\cmd.exe /K start " + enlace;
        try {
            Runtime.getRuntime().exec(url);
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static String getDiaSemana() {
        Calendar calendar = Calendar.getInstance();
        String diaSemana = "";
        String diasSemanas[] = {"domingo", "lunes", "martes", "miercoles", "jueves", "viernes", "sabado"};

        for (int i = 0; i < diasSemanas.length; i++) {
            if (calendar.get(Calendar.DAY_OF_WEEK) == i + 1) {
                diaSemana = diasSemanas[i];
            }
        }

        return diaSemana;
    }

    public static String FechaActual() {
        Date fechaActual = new Date();
        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = formatoFecha.format(fechaActual);
        return fecha;
    }

    public static String limitPalabras(String palabra, int limite) {
        String pal = palabra;
        int tamañoPalabra = pal.length();
        if (tamañoPalabra > limite) {
            char nuevaPalabra[] = new char[limite - 2];
            for (int i = 0; i < limite - 2; i++) {
                nuevaPalabra[i] = pal.charAt(i);
            }
            String aux = new String(nuevaPalabra);
            pal = aux + "..";
        }
        return pal;
    }

}
