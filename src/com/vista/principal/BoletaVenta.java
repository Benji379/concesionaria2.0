package com.vista.principal;

import com.modelo.DAO.ConexionSQL;
import com.modelo.DAO.ModeloDAO;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class BoletaVenta extends javax.swing.JFrame {

    Connection conexion;
    ResultSet resultado;
    PreparedStatement consulta;
    DefaultTableModel modelo;
    String CodigoVenta = moduloListaVentas.codigoVenta;
    String codigoEmpleado = moduloListaVentas.codigoEmpleado;
    String codigoCliente = moduloListaVentas.codigoCliente;
    String codigoAuto = moduloListaVentas.codigoAuto;

    String empleado = ModeloDAO.consultarDato("empleados", "dni", codigoEmpleado, "nombre", "String").toString() + " "
            + ModeloDAO.consultarDato("empleados", "dni", codigoEmpleado, "apellido", "String").toString();

    String nombreCliente = ModeloDAO.consultarDato("cliente", "dniCliente", codigoCliente, "nombre", "String").toString();
    String apellidoCliente = ModeloDAO.consultarDato("cliente", "dniCliente", codigoCliente, "apellido", "String").toString();
    String direccion = ModeloDAO.consultarDato("cliente", "dniCliente", codigoCliente, "direccion", "String").toString();

    String placa = codigoAuto;
    String marca = ModeloDAO.consultarDato("vehiculos", "placa", codigoAuto, "marca", "String").toString();
    String color = ModeloDAO.consultarDato("vehiculos", "placa", codigoAuto, "color", "String").toString();

    public BoletaVenta() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(0, 0, 0, 0));
        consultar(CodigoVenta);
//        System.out.println(CodigoVenta);
        double acum = 0;
        for (int i = 0; i < tableDark1.getRowCount(); i++) {
            acum = acum + Double.parseDouble(tableDark1.getValueAt(i, 3).toString());
        }
        txtTotal.setText("" + acum);
    }

    private void consultar(String codigoVenta) {
        try {
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("SELECT * FROM ventaProductos ventaP inner join productos prod on ventaP.codigoProducto = prod.codigoProducto WHERE idVenta=?");
            consulta.setString(1, codigoVenta);
            resultado = consulta.executeQuery();
            Object datos[] = new Object[4];
            modelo = (DefaultTableModel) tableDark1.getModel();
            modelo.setRowCount(0);
            while (resultado.next()) {
                datos[0] = resultado.getString("nombre");
                datos[1] = resultado.getDouble("precio");
                datos[2] = resultado.getInt("cantidad");
                datos[3] = resultado.getDouble("total");
                modelo.addRow(datos);
            }
            txtNombreEmpleado.setText(empleado);

            txtNombreCliente.setText(nombreCliente);
            txtApellidoCliente.setText(apellidoCliente);
            txtDireccionCliente.setText(direccion);

            txtPlaca.setText(placa);
            txtMarca.setText(marca);
            txtColor.setText(color);
            tableDark1.setModel(modelo);
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound6 = new com.modelo.UIDesinger.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDark1 = new com.modelo.UIDesinger.TableDark();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtDireccionCliente = new javax.swing.JLabel();
        txtApellidoCliente = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        txtMarca = new javax.swing.JLabel();
        txtColor = new javax.swing.JLabel();
        BarraTitulo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtNombreEmpleado = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Boleta");
        setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/img/icons/factura.png")));
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound6.setBackground(new java.awt.Color(25, 25, 25));
        panelRound6.setPreferredSize(new java.awt.Dimension(474, 560));
        panelRound6.setRoundBottomLeft(25);
        panelRound6.setRoundBottomRight(25);
        panelRound6.setRoundTopLeft(25);
        panelRound6.setRoundTopRight(25);
        panelRound6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableDark1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Servicio", "Precio", "Cantidad", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableDark1);

        panelRound6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 390, 230));

        jLabel23.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Cliente");
        panelRound6.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 103, 33));

        jLabel24.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Empleado:");
        panelRound6.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 123, 33));

        txtNombreCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtNombreCliente.setForeground(new java.awt.Color(255, 255, 255));
        txtNombreCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelRound6.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 158, 27));

        jLabel27.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Total: S/.");
        panelRound6.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, 103, 33));

        txtDireccionCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtDireccionCliente.setForeground(new java.awt.Color(255, 255, 255));
        txtDireccionCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelRound6.add(txtDireccionCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 158, 27));

        txtApellidoCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtApellidoCliente.setForeground(new java.awt.Color(255, 255, 255));
        txtApellidoCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelRound6.add(txtApellidoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 158, 27));

        txtTotal.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(255, 255, 255));
        txtTotal.setText("0.0");
        panelRound6.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 490, 158, 30));

        txtMarca.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtMarca.setForeground(new java.awt.Color(255, 255, 255));
        txtMarca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelRound6.add(txtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 158, 27));

        txtColor.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtColor.setForeground(new java.awt.Color(255, 255, 255));
        txtColor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelRound6.add(txtColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 158, 27));

        BarraTitulo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BarraTitulo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                BarraTituloMouseDragged(evt);
            }
        });
        BarraTitulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BarraTituloMousePressed(evt);
            }
        });
        panelRound6.add(BarraTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 30));

        jLabel5.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("X");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        panelRound6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 30, -1));

        jLabel28.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Vehículo");
        panelRound6.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 103, 33));

        txtNombreEmpleado.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtNombreEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        panelRound6.add(txtNombreEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 180, 27));

        txtPlaca.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtPlaca.setForeground(new java.awt.Color(255, 255, 255));
        txtPlaca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelRound6.add(txtPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 158, 27));

        getContentPane().add(panelRound6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    int LayoutX;
    int LayoutY;
    private void BarraTituloMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarraTituloMouseDragged
        this.setLocation(evt.getXOnScreen() - LayoutX, evt.getYOnScreen() - LayoutY);
    }//GEN-LAST:event_BarraTituloMouseDragged

    private void BarraTituloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarraTituloMousePressed

        if (evt.getButton() == java.awt.event.MouseEvent.BUTTON1) {
            LayoutX = evt.getX();
            LayoutY = evt.getY();
        }
    }//GEN-LAST:event_BarraTituloMousePressed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BoletaVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BoletaVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BoletaVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BoletaVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new BoletaVenta().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BarraTitulo;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private com.modelo.UIDesinger.PanelRound panelRound6;
    private com.modelo.UIDesinger.TableDark tableDark1;
    private javax.swing.JLabel txtApellidoCliente;
    private javax.swing.JLabel txtColor;
    private javax.swing.JLabel txtDireccionCliente;
    private javax.swing.JLabel txtMarca;
    private javax.swing.JLabel txtNombreCliente;
    private javax.swing.JLabel txtNombreEmpleado;
    private javax.swing.JLabel txtPlaca;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
}
