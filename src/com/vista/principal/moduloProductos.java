package com.vista.principal;

import com.modelo.Action.ActionUtils;
import com.modelo.DAO.ConexionSQL;
import com.modelo.UIDesinger.UIController;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class moduloProductos extends javax.swing.JPanel {

    Connection conexion;
    PreparedStatement consulta;
    ResultSet resultado;
    DefaultTableModel modelo;
    String idc;
    int filaSeleccionada = -1;

    public moduloProductos() {
        initComponents();
        initButton();
        mostrar();
    }

    private void initButton() {
        Cursor cursor = new Cursor(12);
        btnRegistrar.setCurrentCursor(cursor);
        btnBorrar.setCurrentCursor(cursor);
        btnActualizar.setCurrentCursor(cursor);
        btnLimpiar.setCurrentCursor(cursor);
    }

    private void mostrar() {
        try {
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("SELECT * FROM productos");
            resultado = consulta.executeQuery();
            Object datos[] = new Object[6];
            modelo = (DefaultTableModel) tablaProductos.getModel();
            modelo.setRowCount(0);
            while (resultado.next()) {
                datos[0] = resultado.getString("codigoProducto");
                datos[1] = resultado.getString("nombre");
                datos[2] = resultado.getDouble("precio");
                datos[3] = resultado.getInt("stock");
                datos[4] = resultado.getDate("fecha");
                modelo.addRow(datos);
            }
            tablaProductos.setModel(modelo);
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void registrar() {
        try {
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            double precio = Double.parseDouble(txtPrecio.getText());
            int stock = Integer.parseInt(txtStock.getText());
            if (UIController.datosCompletos(codigo, nombre, String.valueOf(precio), String.valueOf(stock))) {
                Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(ActionUtils.FechaActual());
                conexion = new ConexionSQL().conexion();
                consulta = conexion.prepareStatement("INSERT INTO productos (codigoProducto, nombre, precio, stock, fecha) VALUES (?,?,?,?,?)");
                consulta.setString(1, codigo);
                consulta.setString(2, nombre);
                consulta.setDouble(3, precio);
                consulta.setInt(4, stock);
                consulta.setDate(5, new java.sql.Date(fecha.getTime()));
                consulta.executeUpdate();
                filaSeleccionada = -1;
                limpiar();
                mostrar();
            } else {
                JOptionPane.showMessageDialog(null, "Datos incompletos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException | NumberFormatException | ParseException e) {
//            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage() + "Error" + JOptionPane.ERROR_MESSAGE);
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void actualizar() {
        try {
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            double precio = Double.parseDouble(txtPrecio.getText());
            int stock = Integer.parseInt(txtStock.getText());
            Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(ActionUtils.FechaActual());
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("UPDATE productos set nombre=?, precio=?, stock=?, fecha=? WHERE codigoProducto=?");
            consulta.setString(1, nombre);
            consulta.setDouble(2, precio);
            consulta.setInt(3, stock);
            consulta.setDate(4, new java.sql.Date(fecha.getTime()));
            consulta.setString(5, codigo);
            consulta.executeUpdate();
            filaSeleccionada = -1;
            limpiar();
            mostrar();
        } catch (SQLException | NumberFormatException | ParseException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void limpiar() {
        UIController.vaciarTxtField(txtCodigo, txtNombre, txtPrecio, txtStock);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new com.modelo.UIDesinger.Scroll.ScrollPaneWin11();
        tablaProductos = new com.modelo.UIDesinger.TableDark();
        jPanel1 = new javax.swing.JPanel();
        btnRegistrar = new button.Button();
        btnActualizar = new button.Button();
        btnBorrar = new button.Button();
        btnLimpiar = new button.Button();
        panelRound2 = new com.modelo.UIDesinger.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new textfield.TextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new textfield.TextField();
        jLabel4 = new javax.swing.JLabel();
        txtPrecio = new textfield.TextField();
        jLabel5 = new javax.swing.JLabel();
        txtStock = new textfield.TextField();

        setBackground(new java.awt.Color(20, 20, 20));

        jLabel2.setFont(new java.awt.Font("Century", 0, 55)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Productos");

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codigo", "nombre", "precio", "stock", "fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProductos);

        jPanel1.setBackground(new java.awt.Color(20, 20, 20));

        btnRegistrar.setBackground(new java.awt.Color(25, 25, 25));
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/icons/agregar.png"))); // NOI18N
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        btnRegistrar.setShadowColor(new java.awt.Color(0, 0, 0));
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(25, 25, 25));
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/icons/actualizar.png"))); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        btnActualizar.setShadowColor(new java.awt.Color(0, 0, 0));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnBorrar.setBackground(new java.awt.Color(25, 25, 25));
        btnBorrar.setForeground(new java.awt.Color(255, 255, 255));
        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/icons/eliminar.png"))); // NOI18N
        btnBorrar.setText("BORRAR");
        btnBorrar.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        btnBorrar.setShadowColor(new java.awt.Color(0, 0, 0));
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(25, 25, 25));
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/icons/limpiar.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        btnLimpiar.setShadowColor(new java.awt.Color(0, 0, 0));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelRound2.setBackground(new java.awt.Color(25, 25, 25));
        panelRound2.setRoundBottomLeft(15);
        panelRound2.setRoundBottomRight(15);
        panelRound2.setRoundTopLeft(15);
        panelRound2.setRoundTopRight(15);

        jLabel1.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Codigo");

        txtCodigo.setForeground(new java.awt.Color(0, 0, 0));
        txtCodigo.setToolTipText("");
        txtCodigo.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        txtCodigo.setName(""); // NOI18N
        txtCodigo.setSelectionColor(new java.awt.Color(0, 169, 206));
        txtCodigo.setShadowColor(new java.awt.Color(0, 0, 0));
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre");

        txtNombre.setForeground(new java.awt.Color(0, 0, 0));
        txtNombre.setToolTipText("");
        txtNombre.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        txtNombre.setName(""); // NOI18N
        txtNombre.setSelectionColor(new java.awt.Color(0, 169, 206));
        txtNombre.setShadowColor(new java.awt.Color(0, 0, 0));
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Precio");

        txtPrecio.setForeground(new java.awt.Color(0, 0, 0));
        txtPrecio.setToolTipText("");
        txtPrecio.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        txtPrecio.setName(""); // NOI18N
        txtPrecio.setSelectionColor(new java.awt.Color(0, 169, 206));
        txtPrecio.setShadowColor(new java.awt.Color(0, 0, 0));
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Stock");

        txtStock.setForeground(new java.awt.Color(0, 0, 0));
        txtStock.setToolTipText("");
        txtStock.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        txtStock.setName(""); // NOI18N
        txtStock.setSelectionColor(new java.awt.Color(0, 169, 206));
        txtStock.setShadowColor(new java.awt.Color(0, 0, 0));
        txtStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addGap(37, 37, 37))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE))
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        registrar();
    }//GEN-LAST:event_btnRegistrarActionPerformed
    
    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed

        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(null, "Selecciona una fila");
        } else {
            int dialog = JOptionPane.YES_NO_OPTION;
            int result = JOptionPane.showConfirmDialog(null, "Confirmar actualizacion", "Confirmacion", dialog);
            if (result == 0) {
                actualizar();
            }
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(null, "Selecciona una fila");
        } else {
            int dialog = JOptionPane.YES_NO_OPTION;
            int result = JOptionPane.showConfirmDialog(null, "Confirmar eliminacion", "Confirmacion", dialog);
            if (result == 0) {
                try {
                    String codigo = txtCodigo.getText();
                    conexion = new ConexionSQL().conexion();
                    consulta = conexion.prepareStatement("DELETE FROM productos WHERE codigoProducto=?");
                    consulta.setString(1, codigo);
                    consulta.executeUpdate();
                    filaSeleccionada = -1;
                    limpiar();
                    mostrar();
                } catch (SQLException e) {
                    System.out.println("ERROR: " + e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMouseClicked
        filaSeleccionada = tablaProductos.getSelectedRow();

        idc = tablaProductos.getValueAt(filaSeleccionada, 0).toString();
        String nombre = tablaProductos.getValueAt(filaSeleccionada, 1).toString();
        String precio = tablaProductos.getValueAt(filaSeleccionada, 2).toString();
        String stock = tablaProductos.getValueAt(filaSeleccionada, 3).toString();
        txtCodigo.setText(idc);
        txtNombre.setText(nombre);
        txtPrecio.setText(precio);
        txtStock.setText(stock);
    }//GEN-LAST:event_tablaProductosMouseClicked

    private void txtStockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyTyped
        UIController.limitacionNumeros(txtStock, evt, 4);
    }//GEN-LAST:event_txtStockKeyTyped

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        UIController.limitacionNumeros(txtPrecio, evt, 6, '.');
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        UIController.limitacionCaracteres(txtNombre, evt, 50, true);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        UIController.limitacionNumeros(txtCodigo, evt, 25);
    }//GEN-LAST:event_txtCodigoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button btnActualizar;
    private button.Button btnBorrar;
    private button.Button btnLimpiar;
    private button.Button btnRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.modelo.UIDesinger.PanelRound panelRound2;
    private com.modelo.UIDesinger.TableDark tablaProductos;
    private textfield.TextField txtCodigo;
    private textfield.TextField txtNombre;
    private textfield.TextField txtPrecio;
    private textfield.TextField txtStock;
    // End of variables declaration//GEN-END:variables
}
