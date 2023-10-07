package com.vista.principal;

import com.modelo.Action.ActionUtils;
import com.modelo.DAO.ConexionSQL;
import com.modelo.DAO.ModeloDAO;
import com.modelo.UIDesinger.UIController;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import textfield_suggestion.TextFieldSuggestion;

public final class moduloNuevaVenta extends javax.swing.JPanel {

    ArrayList<String> clientesAutos;
    Connection conexion;
    PreparedStatement consulta;
    ResultSet resultado;
    DefaultTableModel modelo;
    int filaSeleccionada = -1;

    public moduloNuevaVenta() {
        initComponents();

        clientesAutos = new ArrayList<>();
        rellenarArrayList();
        rellenar(clientesAutos, txtDniCliente);
        rellenarCombo();
        UIController.transparentarTxtField(txtPagoEfectivo);
        combobox1.setSelectedIndex(0);
        UIController.removerBordeBlanco(jScrollPane2);
        jScrollPane2.getViewport().setOpaque(false);
        String nombreEmpleado = ModeloDAO.getDatos(ModeloDAO.DNI_EMPLEADO, "nombre")
                + " " + ModeloDAO.getDatos(ModeloDAO.DNI_EMPLEADO, "apellido");
        txtNameEmpleado.setText("Trabajador: " + nombreEmpleado);
        txtNombreEmpleado.setText(nombreEmpleado);
    }

    private void añadirBoleta() {
        ArrayList<String> produc, cant, totl;
        produc = new ArrayList<>();
        cant = new ArrayList<>();
        totl = new ArrayList<>();

        for (int i = 0; i < tablaCompra.getRowCount(); i++) {
            produc.add(tablaCompra.getValueAt(i, 1).toString());
            cant.add(tablaCompra.getValueAt(i, 3).toString());
            totl.add(tablaCompra.getValueAt(i, 4).toString());
        }

        printPorducto(produc, cant, totl);
    }

    public void rellenarCombo() {
        try {
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("SELECT * FROM productos");
            resultado = consulta.executeQuery();
            while (resultado.next()) {
                comboProducto.addItem(resultado.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void rellenarSugetionPlacaAutos() {
        txtPlacaVehiculo.clearItemSuggestion();
        ArrayList<String> datosPlacaVehiculo;
        try {
            datosPlacaVehiculo = new ArrayList<>();
            String dni = txtDniCliente.getText();
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("SELECT * FROM vehiculos WHERE cliente=?");
            consulta.setString(1, dni);
            resultado = consulta.executeQuery();
            while (resultado.next()) {
                datosPlacaVehiculo.add(resultado.getString("placa"));
            }
            for (int i = 0; i < datosPlacaVehiculo.size(); i++) {
                txtPlacaVehiculo.addItemSuggestion(datosPlacaVehiculo.get(i));
            }
        } catch (SQLException e) {

        }
    }

    public void rellenar(ArrayList<String> datos, TextFieldSuggestion text) {
        for (int i = 0; i < datos.size(); i++) {
            text.addItemSuggestion(datos.get(i));
        }
    }

    private void rellenarArrayList() {
        try {
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("SELECT * FROM cliente");
            resultado = consulta.executeQuery();
            while (resultado.next()) {
                String clienteAut = resultado.getString("dniCliente");
                clientesAutos.add(clienteAut);
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void printPorducto(
            ArrayList<String> producto,
            ArrayList<String> cantidad,
            ArrayList<String> total) {

        String productos = "";
        String cantidades = "";
        String totales = "";
        for (int i = 0; i < producto.size(); i++) {
            productos = productos + "<br>" + ActionUtils.limitPalabras(producto.get(i), 23);
        }
        for (int i = 0; i < cantidad.size(); i++) {
            cantidades = cantidades + "<br>" + cantidad.get(i);
        }
        for (int i = 0; i < total.size(); i++) {
            totales = totales + "<br>" + total.get(i);

        }
        txtProductos.setText("<html>" + productos + "</html>");
        txtCantidad.setText("<html>" + cantidades + "</html>");
        txtTotal.setText("<html>" + totales + "</html>");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound2 = new com.modelo.UIDesinger.PanelRound();
        txtNameEmpleado = new javax.swing.JLabel();
        panelRound3 = new com.modelo.UIDesinger.PanelRound();
        jLabel14 = new javax.swing.JLabel();
        txtDniCliente = new textfield_suggestion.TextFieldSuggestion();
        txtPlacaVehiculo = new textfield_suggestion.TextFieldSuggestion();
        jLabel16 = new javax.swing.JLabel();
        btnFinalizarCompra = new button.Button();
        panelRound4 = new com.modelo.UIDesinger.PanelRound();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new com.modelo.UIDesinger.Scroll.ScrollPaneWin11();
        tablaCompra = new com.modelo.UIDesinger.TableDark();
        jLabel25 = new javax.swing.JLabel();
        comboProducto = new combo_suggestion.ComboBoxSuggestion();
        txtCantidadProducto = new textfield_suggestion.TextFieldSuggestion();
        txtPrecio = new javax.swing.JLabel();
        button1 = new button.Button();
        button2 = new button.Button();
        panelRound5 = new com.modelo.UIDesinger.PanelRound();
        jLabel17 = new javax.swing.JLabel();
        combobox1 = new combobox.Combobox();
        jLabel18 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        txtVuelto = new javax.swing.JLabel();
        txtPagoEfectivo = new javax.swing.JTextField();
        txtTotalGeneral = new javax.swing.JLabel();
        jScrollPane2 = new com.modelo.UIDesinger.Scroll.ScrollPaneWin11();
        panelRound6 = new com.modelo.UIDesinger.PanelRound();
        jLabel13 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        txtProductos = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtDireccionCliente = new javax.swing.JLabel();
        txtApellidoCliente = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JLabel();
        txtMarca = new javax.swing.JLabel();
        txtColor = new javax.swing.JLabel();
        txtNombreEmpleado = new javax.swing.JLabel();

        setBackground(new java.awt.Color(20, 20, 20));
        setPreferredSize(new java.awt.Dimension(1116, 1219));

        panelRound2.setBackground(new java.awt.Color(25, 25, 25));
        panelRound2.setRoundBottomLeft(10);
        panelRound2.setRoundBottomRight(10);
        panelRound2.setRoundTopLeft(10);
        panelRound2.setRoundTopRight(10);

        txtNameEmpleado.setFont(new java.awt.Font("Century Gothic", 1, 22)); // NOI18N
        txtNameEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        txtNameEmpleado.setText("Trabajador:");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(txtNameEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(txtNameEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        panelRound3.setBackground(new java.awt.Color(25, 25, 25));
        panelRound3.setRoundBottomLeft(10);
        panelRound3.setRoundBottomRight(10);
        panelRound3.setRoundTopLeft(10);
        panelRound3.setRoundTopRight(10);

        jLabel14.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("DniCliente");

        txtDniCliente.setForeground(new java.awt.Color(255, 255, 255));
        txtDniCliente.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtDniCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDniClienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniClienteKeyTyped(evt);
            }
        });

        txtPlacaVehiculo.setForeground(new java.awt.Color(255, 255, 255));
        txtPlacaVehiculo.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtPlacaVehiculo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPlacaVehiculoFocusGained(evt);
            }
        });
        txtPlacaVehiculo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPlacaVehiculoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlacaVehiculoKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Placa Vehículo");

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDniCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlacaVehiculo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlacaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        btnFinalizarCompra.setBackground(new java.awt.Color(25, 25, 25));
        btnFinalizarCompra.setForeground(new java.awt.Color(255, 255, 255));
        btnFinalizarCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/icons/venta.png"))); // NOI18N
        btnFinalizarCompra.setText("FINALIZAR COMPRA");
        btnFinalizarCompra.setCurrentCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFinalizarCompra.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        btnFinalizarCompra.setShadowColor(new java.awt.Color(0, 0, 0));
        btnFinalizarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarCompraActionPerformed(evt);
            }
        });

        panelRound4.setBackground(new java.awt.Color(25, 25, 25));
        panelRound4.setRoundBottomLeft(15);
        panelRound4.setRoundBottomRight(15);
        panelRound4.setRoundTopLeft(15);
        panelRound4.setRoundTopRight(15);

        jLabel15.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Prod.");

        tablaCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N", "Nombre", "Precio U.", "Cantidad", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCompraMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCompra);
        if (tablaCompra.getColumnModel().getColumnCount() > 0) {
            tablaCompra.getColumnModel().getColumn(0).setPreferredWidth(40);
            tablaCompra.getColumnModel().getColumn(1).setPreferredWidth(100);
            tablaCompra.getColumnModel().getColumn(2).setPreferredWidth(70);
            tablaCompra.getColumnModel().getColumn(3).setPreferredWidth(60);
            tablaCompra.getColumnModel().getColumn(4).setPreferredWidth(70);
        }

        jLabel25.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Cantidad:");

        comboProducto.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        comboProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProductoActionPerformed(evt);
            }
        });

        txtCantidadProducto.setForeground(new java.awt.Color(255, 255, 255));
        txtCantidadProducto.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtCantidadProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadProductoKeyTyped(evt);
            }
        });

        txtPrecio.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        txtPrecio.setForeground(new java.awt.Color(255, 255, 255));
        txtPrecio.setText("Precio:S/0.0");

        button1.setBackground(new java.awt.Color(30, 30, 30));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("+");
        button1.setCurrentCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button1.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        button1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button1.setShadowColor(new java.awt.Color(0, 0, 0));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button2.setBackground(new java.awt.Color(30, 30, 30));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("-");
        button2.setCurrentCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button2.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        button2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button2.setShadowColor(new java.awt.Color(0, 0, 0));
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(21, 21, 21)
                        .addComponent(comboProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound4Layout.createSequentialGroup()
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidadProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(comboProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelRound5.setBackground(new java.awt.Color(25, 25, 25));
        panelRound5.setRoundBottomLeft(10);
        panelRound5.setRoundBottomRight(10);
        panelRound5.setRoundTopLeft(10);
        panelRound5.setRoundTopRight(10);

        jLabel17.setFont(new java.awt.Font("Century", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Vuelto:     S/");

        combobox1.setBackground(new java.awt.Color(25, 25, 25));
        combobox1.setForeground(new java.awt.Color(255, 255, 255));
        combobox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Efectivo", "Tarjeta" }));
        combobox1.setSelectedIndex(-1);
        combobox1.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        combobox1.setLabeText("M. Pago");
        combobox1.setLineColor(new java.awt.Color(255, 255, 255));
        combobox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combobox1ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Century", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Total:       S/");

        txtVuelto.setFont(new java.awt.Font("Century", 1, 18)); // NOI18N
        txtVuelto.setForeground(new java.awt.Color(255, 255, 255));
        txtVuelto.setText("0.0");

        txtPagoEfectivo.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txtPagoEfectivo.setForeground(new java.awt.Color(255, 255, 255));
        txtPagoEfectivo.setText("0.0");
        txtPagoEfectivo.setBorder(null);
        txtPagoEfectivo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPagoEfectivoFocusLost(evt);
            }
        });
        txtPagoEfectivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtPagoEfectivoMouseExited(evt);
            }
        });
        txtPagoEfectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPagoEfectivoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPagoEfectivoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPagoEfectivoKeyTyped(evt);
            }
        });

        txtTotalGeneral.setFont(new java.awt.Font("Century", 1, 18)); // NOI18N
        txtTotalGeneral.setForeground(new java.awt.Color(255, 255, 255));
        txtTotalGeneral.setText("0.0");

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRound5Layout.createSequentialGroup()
                            .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel18)
                                .addComponent(combobox1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator3)
                                .addComponent(txtPagoEfectivo)
                                .addComponent(txtTotalGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))))
                    .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRound5Layout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addGap(15, 15, 15)
                            .addComponent(txtVuelto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound5Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound5Layout.createSequentialGroup()
                        .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combobox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPagoEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalGeneral))
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound5Layout.createSequentialGroup()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVuelto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jScrollPane2.setPreferredSize(new java.awt.Dimension(474, 718));

        panelRound6.setBackground(new java.awt.Color(25, 25, 25));
        panelRound6.setPreferredSize(new java.awt.Dimension(474, 864));
        panelRound6.setRoundBottomLeft(15);
        panelRound6.setRoundBottomRight(15);
        panelRound6.setRoundTopLeft(15);
        panelRound6.setRoundTopRight(15);

        jLabel13.setFont(new java.awt.Font("Century", 0, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Concesionaria");

        jLabel23.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Cliente");

        jLabel24.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Empleado:");

        txtNombreCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtNombreCliente.setForeground(new java.awt.Color(255, 255, 255));

        jLabel29.setFont(new java.awt.Font("Century", 0, 20)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("- Apellidos:");

        jLabel30.setFont(new java.awt.Font("Century", 0, 20)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("- Direccion:");

        jLabel27.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Vehículo");

        jLabel31.setFont(new java.awt.Font("Century", 0, 20)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("- Placa:");

        jLabel32.setFont(new java.awt.Font("Century", 0, 20)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("- Marca:");

        jLabel33.setFont(new java.awt.Font("Century", 0, 20)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("- Color:");

        jLabel34.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Servicios");

        txtTotal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(255, 255, 255));
        txtTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTotal.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        txtProductos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtProductos.setForeground(new java.awt.Color(255, 255, 255));
        txtProductos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtProductos.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        txtCantidad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCantidad.setForeground(new java.awt.Color(255, 255, 255));
        txtCantidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCantidad.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel36.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Total");

        jLabel37.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Cantidad");

        jLabel38.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Servicio");

        jLabel35.setFont(new java.awt.Font("Century", 0, 20)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("- Nombre:");

        txtDireccionCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtDireccionCliente.setForeground(new java.awt.Color(255, 255, 255));

        txtApellidoCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtApellidoCliente.setForeground(new java.awt.Color(255, 255, 255));

        txtPlaca.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtPlaca.setForeground(new java.awt.Color(255, 255, 255));

        txtMarca.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtMarca.setForeground(new java.awt.Color(255, 255, 255));

        txtColor.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtColor.setForeground(new java.awt.Color(255, 255, 255));

        txtNombreEmpleado.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtNombreEmpleado.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelRound6Layout = new javax.swing.GroupLayout(panelRound6);
        panelRound6.setLayout(panelRound6Layout);
        panelRound6Layout.setHorizontalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound6Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelRound6Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(62, 62, 62))
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound6Layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound6Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                            .addComponent(txtMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtApellidoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDireccionCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound6Layout.setVerticalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound6Layout.createSequentialGroup()
                        .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelRound6Layout.createSequentialGroup()
                                .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))
                            .addComponent(txtApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound6Layout.createSequentialGroup()
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30)))
                .addGap(23, 23, 23)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound6Layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33))
                    .addGroup(panelRound6Layout.createSequentialGroup()
                        .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38))
                .addGap(18, 18, 18)
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProductos)
                    .addComponent(txtCantidad)
                    .addComponent(txtTotal))
                .addContainerGap(196, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(panelRound6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(34, Short.MAX_VALUE)
                        .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(136, 136, 136)
                                .addComponent(btnFinalizarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addGap(55, 55, 55))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
                        .addGap(60, 60, 60))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFinalizarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFinalizarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarCompraActionPerformed
        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "Confirmar venta", "confirmacion", dialog);
        if (result == 0) {
            boolean todosDatosRellenados = UIController.datosCompletos(
                    txtNombreCliente.getText(), txtApellidoCliente.getText(), txtDireccionCliente.getText(),
                    txtPlaca.getText(), txtColor.getText(), txtMarca.getText());
            if (todosDatosRellenados) {
                if (Double.parseDouble(txtPagoEfectivo.getText()) >= Double.parseDouble(txtTotalGeneral.getText())) {
                    if (Double.parseDouble(txtTotalGeneral.getText()) > 0) {
                        ACTUALIZAR_STOCK();
                        REGISTRAR_VENTA();
                        for (int i = 0; i < tablaCompra.getRowCount(); i++) {
                            String nombreProducto = tablaCompra.getValueAt(i, 1).toString();
                            int cantidad = Integer.parseInt(tablaCompra.getValueAt(i, 3).toString());
                            double total = Double.parseDouble(tablaCompra.getValueAt(i, 4).toString());
                            REGISTRAR_VENTA_PRODUCTOS(valorActualID(), codigoProducto(nombreProducto), nombreProducto, cantidad, total);
                        }
                        JOptionPane.showMessageDialog(null, "Venta realizada con exito");

                    } else {
                        JOptionPane.showMessageDialog(null, "Registre un producto");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cancele el pago");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Rellene con datos válidos");
            }

        }
    }//GEN-LAST:event_btnFinalizarCompraActionPerformed

    private void REGISTRAR_VENTA_PRODUCTOS(String idVenta, String codigoProducto, String nombreProducto, int cantidad, double total) {
        try {
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("INSERT INTO ventaproductos (idVenta,codigoProducto,cantidad,total) VALUES (?,?,?,?)");
            consulta.setString(1, idVenta);
            consulta.setString(2, codigoProducto);
            consulta.setInt(3, cantidad);
            consulta.setDouble(4, total);
            consulta.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private String codigoProducto(String nombreProducto) {
        try {
            ResultSet res;
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("SELECT * FROM productos WHERE nombre=?");
            consulta.setString(1, nombreProducto);
            res = consulta.executeQuery();
            if (res.next()) {
                return res.getString("codigoProducto");
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
    }

    private void REGISTRAR_VENTA() {
        try {
            String dniTrabajador = ModeloDAO.DNI_EMPLEADO;
            String dniCliente = txtDniCliente.getText();
            String placaVehiculo = txtPlaca.getText();
            double total = Double.parseDouble(txtTotalGeneral.getText());
            Date fecha = new SimpleDateFormat("yyy-mm-dd").parse(ActionUtils.FechaActual());
            int valorActualID = Integer.parseInt(valorActualID()) + 1;
            String nuevaID = String.format("%08d", valorActualID);
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("INSERT INTO venta (idVenta,trabajador,cliente,placaVehiculo,total,fecha) VALUES (?,?,?,?,?,?)");
            consulta.setString(1, nuevaID);
            consulta.setString(2, dniTrabajador);
            consulta.setString(3, dniCliente);
            consulta.setString(4, placaVehiculo);
            consulta.setDouble(5, total);
            consulta.setDate(6, new java.sql.Date(fecha.getTime()));
            consulta.executeUpdate();
        } catch (SQLException | ParseException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private String valorActualID() {
        try {
            ResultSet res;
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("SELECT * FROM venta ORDER BY idVenta DESC LIMIT 1;");
            res = consulta.executeQuery();
            if (res.next()) {
                return res.getString("idVenta");
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return "00000000";
    }

    private void ACTUALIZAR_STOCK() {
        try {
            for (int i = 0; i < tablaCompra.getRowCount(); i++) {
                int cantidadDescontar = Integer.parseInt(tablaCompra.getValueAt(i, 3).toString());
                String nombreProducto = tablaCompra.getValueAt(i, 1).toString();
                descontarStock(cantidadDescontar, nombreProducto);
            }
        } catch (HeadlessException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("ERRRO: " + e.getMessage());
        }
    }

    private void descontarStock(int cantidadDescontar, String nameproducto) {
        int nuevoStock = obtenerStockProducto(nameproducto) - cantidadDescontar;
        try {
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("UPDATE productos set stock=? WHERE nombre=?");
            consulta.setInt(1, nuevoStock);
            consulta.setString(2, nameproducto);
            consulta.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private int obtenerStockProducto(String nameProducto) {
        try {
            ResultSet res;
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("SELECT * FROM productos WHERE nombre=?");
            consulta.setString(1, nameProducto);
            res = consulta.executeQuery();
            if (res.next()) {
                return res.getInt("stock");
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return 0;
    }

    int contRowsTb = 0;
    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        try {
            int cantidad = Integer.parseInt(txtCantidadProducto.getText());
            ResultSet resul;
            String productoElegido = (String) comboProducto.getSelectedItem();
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("SELECT * FROM productos WHERE nombre=?");
            consulta.setString(1, productoElegido);
            Object datos[] = new Object[5];
            modelo = (DefaultTableModel) tablaCompra.getModel();
            resul = consulta.executeQuery();

            int filaExistente = -1;
            if (stockSuficiente(cantidad, productoElegido)) {
                for (int j = 0; j < tablaCompra.getRowCount(); j++) {
                    String nombreTabla = tablaCompra.getValueAt(j, 1).toString();
                    if (productoElegido.equals(nombreTabla)) {
                        filaExistente = j;
                        break;
                    }
                }

                if (filaExistente != -1) {
                    int cantidadActualTabla = Integer.parseInt(tablaCompra.getValueAt(filaExistente, 3).toString());
                    int totalCantidadActual = cantidadActualTabla + cantidad;
                    if (stockSuficiente(totalCantidadActual, productoElegido)) {
                        tablaCompra.setValueAt(totalCantidadActual, filaExistente, 3);
                        double preci = Double.parseDouble(tablaCompra.getValueAt(filaExistente, 2).toString());
                        double mont = preci * totalCantidadActual;
//                        System.out.println("Precio: " + preci);
//                        System.out.println("Cantidada actual : " + totalCantidadActual);
                        tablaCompra.setValueAt(String.format(Locale.US, "%.2f", mont), filaExistente, 4);
                        txtTotalGeneral.setText("" + getTotalGeneral());
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Ah rebasado el stock");
                    }
                } else {
                    if (resul.next()) {
                        contRowsTb++;
                        double precio = resul.getDouble("precio");
                        double total = resul.getDouble("precio") * cantidad;
                        datos[0] = contRowsTb;
                        datos[1] = productoElegido;
                        datos[2] = precio;
                        datos[3] = cantidad;
                        datos[4] = total;
                        modelo.addRow(datos);
                        txtTotalGeneral.setText("" + getTotalGeneral());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: No hay stock suficiente");
            }

            tablaCompra.setModel(modelo);
        } catch (SQLException | NumberFormatException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        añadirBoleta();
    }//GEN-LAST:event_button1ActionPerformed

    private double getTotalGeneral() {
        double sumaTotal = 0;

        for (int i = 0; i < tablaCompra.getRowCount(); i++) {
            sumaTotal += Double.parseDouble(tablaCompra.getValueAt(i, 4).toString());
        }
        return sumaTotal;
    }

    private boolean stockSuficiente(int pedir, String nameProducto) {
        try {
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("SELECT * FROM productos WHERE nombre=?");
            consulta.setString(1, nameProducto);
            ResultSet rs = consulta.executeQuery();
            if (rs.next()) {
                int stockActual = rs.getInt("stock");
                if (pedir <= stockActual) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return false;
    }

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un producto");
        } else {
            modelo.removeRow(filaSeleccionada);
            txtTotalGeneral.setText("" + getTotalGeneral());
            actualizarContenidoTabla();
            añadirBoleta();
            filaSeleccionada = -1;
        }
    }//GEN-LAST:event_button2ActionPerformed
    private void actualizarContenidoTabla() {
        for (int i = 0; i < tablaCompra.getRowCount(); i++) {
            tablaCompra.setValueAt((i + 1), i, 0);
        }
    }
    private void txtPlacaVehiculoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacaVehiculoKeyTyped
        UIController.limitacionNumeros(txtPlacaVehiculo, evt, 7);
    }//GEN-LAST:event_txtPlacaVehiculoKeyTyped

    private void txtPlacaVehiculoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPlacaVehiculoFocusGained
        rellenarSugetionPlacaAutos();
    }//GEN-LAST:event_txtPlacaVehiculoFocusGained

    private void comboProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProductoActionPerformed
        seleccionar();
    }//GEN-LAST:event_comboProductoActionPerformed

    private void combobox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combobox1ActionPerformed
        if (combobox1.getSelectedIndex() == 0) {
            txtPagoEfectivo.setEditable(true);
            txtPagoEfectivo.setText("0.0");
        } else {
            txtPagoEfectivo.setEditable(false);
            txtPagoEfectivo.setText(txtTotalGeneral.getText());
        }
        generarVuelto();
    }//GEN-LAST:event_combobox1ActionPerformed

    private void txtPagoEfectivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagoEfectivoKeyPressed

    }//GEN-LAST:event_txtPagoEfectivoKeyPressed

    private void txtPagoEfectivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagoEfectivoKeyReleased
        generarVuelto();
    }//GEN-LAST:event_txtPagoEfectivoKeyReleased

    private void generarVuelto() {
        try {
            double vuelto = Double.parseDouble(txtPagoEfectivo.getText()) - Double.parseDouble(txtTotalGeneral.getText());
            txtVuelto.setText("" + vuelto);
        } catch (NumberFormatException e) {

        }
    }

    private void txtDniClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniClienteKeyReleased
        String nTabla = "cliente";
        String c1 = "nombre";
        String c2 = "apellido";
        String c3 = "direccion";
        consultarDatos(nTabla, "dniCliente", txtDniCliente.getText(), c1, c2, c3, txtNombreCliente, txtApellidoCliente, txtDireccionCliente);
    }//GEN-LAST:event_txtDniClienteKeyReleased

    private void txtPlacaVehiculoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacaVehiculoKeyReleased
        String nTabla = "vehiculos";
        String c1 = "placa";
        String c2 = "marca";
        String c3 = "color";
        consultarDatos(nTabla, "placa", txtPlacaVehiculo.getText(), c1, c2, c3, txtPlaca, txtMarca, txtColor);
    }//GEN-LAST:event_txtPlacaVehiculoKeyReleased

    private void tablaCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCompraMouseClicked
        filaSeleccionada = tablaCompra.getSelectedRow();
    }//GEN-LAST:event_tablaCompraMouseClicked

    private void txtCantidadProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadProductoKeyTyped
        UIController.limitacionNumeros(txtCantidadProducto, evt, 4);
    }//GEN-LAST:event_txtCantidadProductoKeyTyped

    private void txtPagoEfectivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagoEfectivoKeyTyped
        UIController.limitacionNumeros(txtPagoEfectivo, evt, 10, '.');
    }//GEN-LAST:event_txtPagoEfectivoKeyTyped

    private void txtDniClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniClienteKeyTyped
        UIController.limitacionNumeros(txtDniCliente, evt, 8);
    }//GEN-LAST:event_txtDniClienteKeyTyped

    private void txtPagoEfectivoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPagoEfectivoMouseExited

        generarVuelto();
    }//GEN-LAST:event_txtPagoEfectivoMouseExited

    private void txtPagoEfectivoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPagoEfectivoFocusLost
        if (txtPagoEfectivo.getText().equals("")) {
            txtPagoEfectivo.setText("0.0");
        }
    }//GEN-LAST:event_txtPagoEfectivoFocusLost

    private void seleccionar() {
        ResultSet resultadoDatos;

        try {
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("SELECT * FROM productos WHERE nombre=?");
            consulta.setString(1, (String) comboProducto.getSelectedItem());
            resultadoDatos = consulta.executeQuery();

            // Verificar si hay al menos un resultado antes de intentar acceder a los datos
            if (resultadoDatos.next()) {
                // Mover el cursor al primer resultado
                txtPrecio.setText("Precio: S/" + resultadoDatos.getDouble("precio"));
            } else {
                // Si no hay resultados, mostrar un mensaje
                System.out.println("No se encontraron resultados.");
            }

            // Cerrar el ResultSet y la conexión cuando hayas terminado
            resultadoDatos.close();
            consulta.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void consultarDatos(String nameTabla, String columnIdentificador,
            String identificador,
            String a, String b, String c,
            JLabel aa, JLabel bb, JLabel cc) {

        try {
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("SELECT * FROM " + nameTabla + " WHERE " + columnIdentificador + "=?");
            ResultSet res;
            consulta.setString(1, identificador);
            res = consulta.executeQuery();
            if (res.next()) {
                aa.setText(res.getString(a));
                bb.setText(res.getString(b));
                cc.setText(res.getString(c));
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button btnFinalizarCompra;
    private button.Button button1;
    private button.Button button2;
    private combo_suggestion.ComboBoxSuggestion comboProducto;
    private combobox.Combobox combobox1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private com.modelo.UIDesinger.PanelRound panelRound2;
    private com.modelo.UIDesinger.PanelRound panelRound3;
    private com.modelo.UIDesinger.PanelRound panelRound4;
    private com.modelo.UIDesinger.PanelRound panelRound5;
    private com.modelo.UIDesinger.PanelRound panelRound6;
    private com.modelo.UIDesinger.TableDark tablaCompra;
    private javax.swing.JLabel txtApellidoCliente;
    private javax.swing.JLabel txtCantidad;
    private textfield_suggestion.TextFieldSuggestion txtCantidadProducto;
    private javax.swing.JLabel txtColor;
    private javax.swing.JLabel txtDireccionCliente;
    private textfield_suggestion.TextFieldSuggestion txtDniCliente;
    private javax.swing.JLabel txtMarca;
    private javax.swing.JLabel txtNameEmpleado;
    private javax.swing.JLabel txtNombreCliente;
    private javax.swing.JLabel txtNombreEmpleado;
    private javax.swing.JTextField txtPagoEfectivo;
    private javax.swing.JLabel txtPlaca;
    private textfield_suggestion.TextFieldSuggestion txtPlacaVehiculo;
    private javax.swing.JLabel txtPrecio;
    private javax.swing.JLabel txtProductos;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JLabel txtTotalGeneral;
    private javax.swing.JLabel txtVuelto;
    // End of variables declaration//GEN-END:variables
}
