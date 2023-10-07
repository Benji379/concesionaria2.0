package com.vista.principal;

import com.modelo.DAO.ConexionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class moduloListaVentas extends javax.swing.JPanel {

    Connection conexion;
    PreparedStatement consulta;
    ResultSet resultado;
    DefaultTableModel modelo;
    int filaSeleccionada = -1;
    String idc;

    public moduloListaVentas() {
        initComponents();
        consultaDatosVenta();
    }

    private void consultaDatosVenta() {
        try {
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("SELECT * FROM venta");
            resultado = consulta.executeQuery();
            Object datos[] = new Object[6];
            modelo = (DefaultTableModel) tablaVentas.getModel();
            modelo.setRowCount(0);
            while (resultado.next()) {
                datos[0] = resultado.getString("idVenta");
                datos[1] = resultado.getString("trabajador");
                datos[2] = resultado.getString("cliente");
                datos[3] = resultado.getString("placaVehiculo");
                datos[4] = resultado.getDouble("total");
                datos[5] = resultado.getDate("fecha");
                modelo.addRow(datos);
            }
            tablaVentas.setModel(modelo);
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel26 = new javax.swing.JLabel();
        jScrollPane1 = new com.modelo.UIDesinger.Scroll.ScrollPaneWin11();
        tablaVentas = new com.modelo.UIDesinger.TableDark();

        setBackground(new java.awt.Color(20, 20, 20));

        jLabel26.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("LISTA DE VENTAS");

        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idVenta", "Trabajador", "Cliente", "PlacaVehiculo", "Total", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaVentasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaVentas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
                .addGap(37, 37, 37))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel26)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel26)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public static String codigoVenta;
    public static String codigoEmpleado;
    public static String codigoCliente;
    public static String codigoAuto;

    private void tablaVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVentasMouseClicked
        filaSeleccionada = tablaVentas.getSelectedRow();
        if (evt.getClickCount() == 2) {
            codigoVenta = tablaVentas.getValueAt(filaSeleccionada, 0).toString();
            codigoEmpleado = tablaVentas.getValueAt(filaSeleccionada, 1).toString();
            codigoCliente = tablaVentas.getValueAt(filaSeleccionada, 2).toString();
            codigoAuto = tablaVentas.getValueAt(filaSeleccionada, 3).toString();
            BoletaVenta abrir = new BoletaVenta();
            abrir.setVisible(true);
        }
    }//GEN-LAST:event_tablaVentasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel26;
    private javax.swing.JScrollPane jScrollPane1;
    private com.modelo.UIDesinger.TableDark tablaVentas;
    // End of variables declaration//GEN-END:variables
}
