package com.vista.principal;

import com.modelo.DAO.ConexionSQL;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import com.modelo.Action.ActionUtils;
import com.modelo.UIDesinger.UIController;
import java.text.ParseException;
import javax.swing.JOptionPane;

public class moduloTrabajador extends javax.swing.JPanel {

    Connection conexion;
    PreparedStatement consulta;
    ResultSet resultado;
    DefaultTableModel modelo;
    String idc;
    int filaSeleccionada = -1;

    public moduloTrabajador() {
        initComponents();
        scrollDatos.getViewport().setOpaque(false);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        scrollTable = new com.modelo.UIDesinger.Scroll.ScrollPaneWin11();
        tablaTrabajadores = new com.modelo.UIDesinger.TableDark();
        scrollDatos = new com.modelo.UIDesinger.Scroll.ScrollPaneWin11();
        panelRound1 = new com.modelo.UIDesinger.PanelRound();
        txtDni = new textfield.TextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new textfield.TextField();
        jLabel4 = new javax.swing.JLabel();
        txtApellido = new textfield.TextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCorreo = new textfield.TextField();
        jLabel7 = new javax.swing.JLabel();
        txtDireccion = new textfield.TextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtContraseña = new textfield.TextField();
        comboRango = new combobox.Combobox();
        comboSexo = new combobox.Combobox();
        jPanel1 = new javax.swing.JPanel();
        btnRegistrar = new button.Button();
        btnActualizar = new button.Button();
        btnBorrar = new button.Button();
        btnLimpiar = new button.Button();

        setBackground(new java.awt.Color(20, 20, 20));

        jLabel2.setFont(new java.awt.Font("Century", 0, 55)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Trabajadores");

        tablaTrabajadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "dni", "nombre", "apellido", "sexo", "correo", "direccion", "contraseña", "rango", "fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaTrabajadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaTrabajadoresMouseClicked(evt);
            }
        });
        scrollTable.setViewportView(tablaTrabajadores);

        scrollDatos.setBackground(new java.awt.Color(25, 25, 25));
        scrollDatos.setBorder(null);

        panelRound1.setBackground(new java.awt.Color(25, 25, 25));
        panelRound1.setRoundBottomLeft(15);
        panelRound1.setRoundBottomRight(15);
        panelRound1.setRoundTopLeft(15);
        panelRound1.setRoundTopRight(15);

        txtDni.setForeground(new java.awt.Color(0, 0, 0));
        txtDni.setToolTipText("");
        txtDni.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        txtDni.setName(""); // NOI18N
        txtDni.setSelectionColor(new java.awt.Color(0, 169, 206));
        txtDni.setShadowColor(new java.awt.Color(0, 0, 0));
        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Dni");

        jLabel3.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre");

        txtNombre.setForeground(new java.awt.Color(0, 0, 0));
        txtNombre.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        txtNombre.setSelectionColor(new java.awt.Color(0, 169, 206));
        txtNombre.setShadowColor(new java.awt.Color(0, 0, 0));
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Apellido");

        txtApellido.setForeground(new java.awt.Color(0, 0, 0));
        txtApellido.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        txtApellido.setSelectionColor(new java.awt.Color(0, 169, 206));
        txtApellido.setShadowColor(new java.awt.Color(0, 0, 0));
        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Sexo");

        jLabel6.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Correo");

        txtCorreo.setForeground(new java.awt.Color(0, 0, 0));
        txtCorreo.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        txtCorreo.setSelectionColor(new java.awt.Color(0, 169, 206));
        txtCorreo.setShadowColor(new java.awt.Color(0, 0, 0));
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Dirección");

        txtDireccion.setForeground(new java.awt.Color(0, 0, 0));
        txtDireccion.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        txtDireccion.setSelectionColor(new java.awt.Color(0, 169, 206));
        txtDireccion.setShadowColor(new java.awt.Color(0, 0, 0));
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Rango");

        jLabel9.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Contraseña");

        txtContraseña.setForeground(new java.awt.Color(0, 0, 0));
        txtContraseña.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        txtContraseña.setSelectionColor(new java.awt.Color(0, 169, 206));
        txtContraseña.setShadowColor(new java.awt.Color(0, 0, 0));
        txtContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContraseñaKeyTyped(evt);
            }
        });

        comboRango.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador", "Empleado" }));
        comboRango.setSelectedIndex(-1);
        comboRango.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        comboRango.setLabeText("Rango");

        comboSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "M", "F" }));
        comboSexo.setSelectedIndex(-1);
        comboSexo.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        comboSexo.setLabeText("Sexo");

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboRango, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboRango, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        scrollDatos.setViewportView(panelRound1);

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
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(scrollDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(41, 41, 41))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(scrollTable)
                .addGap(54, 54, 54))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(scrollDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrollTable, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void mostrar() {
        try {
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("SELECT * FROM empleados");
            resultado = consulta.executeQuery();
            Object datos[] = new Object[10];
            modelo = (DefaultTableModel) tablaTrabajadores.getModel();
            modelo.setRowCount(0);
            int i = 0;
            while (resultado.next()) {
                i++;
                datos[0] = i;
                datos[1] = resultado.getString("dni");
                datos[2] = resultado.getString("nombre");
                datos[3] = resultado.getString("apellido");
                datos[4] = resultado.getString("sexo");
                datos[5] = resultado.getString("correo");
                datos[6] = resultado.getString("direccion");
                datos[7] = resultado.getString("contraseña");
                datos[8] = resultado.getString("rango");
                datos[9] = resultado.getDate("fecha");
                modelo.addRow(datos);
            }
            tablaTrabajadores.setModel(modelo);
        } catch (SQLException e) {
            System.out.println("ERROR:");
        }
    }

    private void registrar() {
        try {
            String dni = txtDni.getText();
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String sexo = (String) comboSexo.getSelectedItem();
            String correo = txtCorreo.getText();
            String direccion = txtDireccion.getText();
            String contraseña = txtContraseña.getText();
            String rango = (String) comboRango.getSelectedItem();
            if (UIController.datosCompletos(dni, nombre, apellido, sexo, correo, direccion, contraseña, rango)) {
                Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(ActionUtils.FechaActual());
                conexion = new ConexionSQL().conexion();
                consulta = conexion.prepareStatement("INSERT INTO empleados (dni,nombre,apellido,sexo,correo,direccion,contraseña,rango, fecha) VALUES (?,?,?,?,?,?,?,?,?)");
                consulta.setString(1, dni);
                consulta.setString(2, nombre);
                consulta.setString(3, apellido);
                consulta.setString(4, sexo);
                consulta.setString(5, correo);
                consulta.setString(6, direccion);
                consulta.setString(7, contraseña);
                consulta.setString(8, rango);
                consulta.setDate(9, new java.sql.Date(fecha.getTime()));
                filaSeleccionada = -1;
                consulta.executeUpdate();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Complete los datos");
            }
        } catch (SQLException | ParseException | NullPointerException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void actualizar() {
        try {
            String dni = txtDni.getText();
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String sexo = (String) comboSexo.getSelectedItem();
            String correo = txtCorreo.getText();
            String direccion = txtDireccion.getText();
            String contraseña = txtContraseña.getText();
            String rango = (String) comboRango.getSelectedItem();
            if (UIController.datosCompletos(dni, nombre, apellido, sexo, correo, direccion, contraseña, rango)) {
                Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(ActionUtils.FechaActual());
                conexion = new ConexionSQL().conexion();
                consulta = conexion.prepareStatement("UPDATE empleados set nombre=?, apellido=?, sexo=?, correo=?, direccion=?, contraseña=?, rango=?, fecha=? WHERE dni=?");
                consulta.setString(1, nombre);
                consulta.setString(2, apellido);
                consulta.setString(3, sexo);
                consulta.setString(4, correo);
                consulta.setString(5, direccion);
                consulta.setString(6, contraseña);
                consulta.setString(7, rango);
                consulta.setDate(8, new java.sql.Date(fecha.getTime()));
                consulta.setString(9, dni);
                consulta.executeUpdate();
                filaSeleccionada = -1;
                mostrar();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Complete los datos");
            }
        } catch (SQLException | ParseException | NullPointerException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void limpiar() {
        UIController.vaciarTxtField(txtDni, txtNombre, txtApellido, txtCorreo, txtContraseña, txtDireccion);
        comboSexo.setSelectedIndex(-1);
        comboRango.setSelectedIndex(-1);
        filaSeleccionada = -1;
    }

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        registrar();
        mostrar();
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
                    String dni = txtDni.getText();
                    conexion = new ConexionSQL().conexion();
                    consulta = conexion.prepareStatement("DELETE FROM empleados WHERE dni=?");
                    consulta.setString(1, dni);
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
        txtDni.setEditable(true);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tablaTrabajadoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaTrabajadoresMouseClicked
        filaSeleccionada = tablaTrabajadores.getSelectedRow();

        idc = tablaTrabajadores.getValueAt(filaSeleccionada, 1).toString();
        String nombre = tablaTrabajadores.getValueAt(filaSeleccionada, 2).toString();
        String apellido = tablaTrabajadores.getValueAt(filaSeleccionada, 3).toString();
        String sexo = tablaTrabajadores.getValueAt(filaSeleccionada, 4).toString();
        String correo = tablaTrabajadores.getValueAt(filaSeleccionada, 5).toString();
        String direccion = tablaTrabajadores.getValueAt(filaSeleccionada, 6).toString();
        String contraseña = tablaTrabajadores.getValueAt(filaSeleccionada, 7).toString();
        String rango = tablaTrabajadores.getValueAt(filaSeleccionada, 8).toString();

        txtDni.setText(idc);
        txtDni.setEditable(false);
        txtNombre.setText(nombre);
        txtApellido.setText(apellido);
        comboSexo.setSelectedItem(sexo);
        txtCorreo.setText(correo);
        txtDireccion.setText(direccion);
        txtContraseña.setText(contraseña);
        comboRango.setSelectedItem(rango);

    }//GEN-LAST:event_tablaTrabajadoresMouseClicked

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
        UIController.limitacionNumeros(txtDni, evt, 8);
    }//GEN-LAST:event_txtDniKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        UIController.limitacionCaracteres(txtNombre, evt, 50, true);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        UIController.limitacionCaracteres(txtApellido, evt, 50, true);
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void txtCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyTyped
        UIController.limitacionCaracteres(txtCorreo, evt, 100, false);
    }//GEN-LAST:event_txtCorreoKeyTyped

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        UIController.limitacionCaracteres(txtDireccion, evt, 200, true);
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtContraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaKeyTyped
        UIController.limitacionCaracteres(txtContraseña, evt, 15, false);
    }//GEN-LAST:event_txtContraseñaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static button.Button btnActualizar;
    public static button.Button btnBorrar;
    public static button.Button btnLimpiar;
    public static button.Button btnRegistrar;
    public static combobox.Combobox comboRango;
    public static combobox.Combobox comboSexo;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    public static javax.swing.JLabel jLabel7;
    public static javax.swing.JLabel jLabel8;
    public static javax.swing.JLabel jLabel9;
    public static javax.swing.JPanel jPanel1;
    public static com.modelo.UIDesinger.PanelRound panelRound1;
    public static javax.swing.JScrollPane scrollDatos;
    public static javax.swing.JScrollPane scrollTable;
    public static com.modelo.UIDesinger.TableDark tablaTrabajadores;
    public static textfield.TextField txtApellido;
    public static textfield.TextField txtContraseña;
    public static textfield.TextField txtCorreo;
    public static textfield.TextField txtDireccion;
    public static textfield.TextField txtDni;
    public static textfield.TextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
