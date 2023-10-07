package com.modelo.UIDesinger;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UIController {

    public static void transparentarTxtField(JTextField... txtField) {
        for (JTextField txt : txtField) {
            txt.setBackground(new java.awt.Color(0, 0, 0, 1));
        }
    }

    public static void MostrarPanel(JPanel contenedor, JPanel panel, int ancho, int largo) {
        panel.setSize(ancho, largo);
        panel.setLocation(0, 0);

        contenedor.removeAll();
        contenedor.add(panel, BorderLayout.CENTER);
        contenedor.revalidate();
        contenedor.repaint();
    }

    public static void setPanelBackgroundColors(JPanel panel, String pressedColor, String rolloverColor) {
        Color normalColor = panel.getBackground();

        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel.setBackground(Color.decode(pressedColor));
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panel.setBackground(normalColor);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel.setBackground(Color.decode(rolloverColor));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel.setBackground(normalColor);
            }
        });
    }

    public static void removerBordeBlanco(JScrollPane scrollPane) {
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    public static void vaciarTxtField(JTextField... textField) {
        for (JTextField tx : textField) {
            tx.setText("");
        }
    }

    public static void limitacionCaracteres(JTextField txtField, KeyEvent evt, int cantNumeros, boolean conEspacio) {
        if (conEspacio) {
            if (txtField.getText().length() >= cantNumeros) {
                evt.consume();
            }
        } else {
            int key = evt.getKeyChar();
            boolean espacio = key == KeyEvent.VK_SPACE;
            if (espacio) {
                evt.consume();
            } else {
                if (txtField.getText().length() >= cantNumeros) {
                    evt.consume();
                }
            }
        }
    }

    public static void limitacionNumeros(JTextField txtField, KeyEvent evt, int CantNumeros) {
        int key = evt.getKeyChar();
        boolean numeros = key >= 48 && key <= 57; //boolean numeros = (key >= 48 && key <= 57) || key == 46; // Permitir números y el punto (.)
        boolean espacio = key == KeyEvent.VK_SPACE;
        if (!numeros || espacio) {
            evt.consume();
        } else {
            if (txtField.getText().length() >= CantNumeros) {
                evt.consume();
            }
        }
    }

    public static void limitacionNumeros(JTextField txtField, KeyEvent evt, int CantNumeros, char adicional) {
        int key = evt.getKeyChar();
        boolean numeros = (key >= 48 && key <= 57) || key == adicional; // Permitir números y el punto (.)
        boolean espacio = key == KeyEvent.VK_SPACE;
        if (!numeros || espacio) {
            evt.consume();
        } else {
            if (txtField.getText().length() >= CantNumeros) {
                evt.consume();
            }
        }
    }

    public static boolean datosCompletos(String... texto) {
        for (String txt : texto) {
            if (txt.equals("")) {
                return false;
            }
        }
        return true;
    }

}
