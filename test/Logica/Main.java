package Logica;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        String h = "123456789123";
//        System.out.println(limitPalabras(h, 11));
        
        for (int i = 0; i < 0; i++) {
            System.out.println("Holaaaaaaaaaaa");
        }
        
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
            pal = aux+"..";
        }
        return pal;
    }

}
