package com.vista.principal;

import com.modelo.Action.ActionUtils;
import com.modelo.DAO.ConexionSQL;
import com.modelo.DAO.ModeloDAO;
import com.modelo.UIDesinger.UIController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import textfield_suggestion.TextFieldSuggestion;

public final class moduloAutoCliente extends javax.swing.JPanel {

    Connection conexion;
    PreparedStatement consulta;
    ResultSet resultado;

    DefaultTableModel cliente_modelo;
    String cliente_idc;
    int cliente_filaSeleccionada = -1;

    DefaultTableModel auto_modelo;
    String auto_idc;
    int auto_filaSeleccionada = -1;

    ArrayList<String> clientesAutos;

    public moduloAutoCliente() {
        initComponents();

        cliente_consultar();
        auto_consultar();

        clientesAutos = new ArrayList<>();
        rellenarArrayList();
        rellenar(clientesAutos, auto_txtDni);
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

    private void cliente_consultar() {
        try {
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("SELECT * FROM cliente");
            resultado = consulta.executeQuery();
            Object datos[] = new Object[4];
            cliente_modelo = (DefaultTableModel) tablaCliente.getModel();
            cliente_modelo.setRowCount(0);
            while (resultado.next()) {
                datos[0] = resultado.getString("dniCliente");
                datos[1] = resultado.getString("nombre");
                datos[2] = resultado.getString("apellido");
                datos[3] = resultado.getString("direccion");
                cliente_modelo.addRow(datos);
            }
            tablaCliente.setModel(cliente_modelo);
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void cliente_registrar() {
        try {
            String dni = cliente_txtDni.getText();
            String nombre = cliente_txtNombre.getText();
            String apellido = cliente_txtApellido.getText();
            String direccion = cliente_txtDireccion.getText();

            if (UIController.datosCompletos(dni, nombre, apellido, direccion)) {
                if (!ModeloDAO.existe("cliente", "dniCliente", dni)) {
                    conexion = new ConexionSQL().conexion();
                    consulta = conexion.prepareStatement("INSERT INTO cliente (dniCliente, nombre, apellido, direccion) VALUES (?,?,?,?)");
                    consulta.setString(1, dni);
                    consulta.setString(2, nombre);
                    consulta.setString(3, apellido);
                    consulta.setString(4, direccion);
                    consulta.executeUpdate();
                    cliente_consultar();
                    cliente_limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR: Cliente existe");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Datos incompletos", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void cliente_modificar() {
        try {
            String nombre = cliente_txtNombre.getText();
            String apellido = cliente_txtApellido.getText();
            String direccion = cliente_txtDireccion.getText();

            if (UIController.datosCompletos(nombre, apellido, direccion)) {
                conexion = new ConexionSQL().conexion();
                consulta = conexion.prepareStatement("UPDATE cliente set nombre=?, apellido=?, direccion=? WHERE dniCliente=?");
                consulta.setString(1, nombre);
                consulta.setString(2, apellido);
                consulta.setString(3, direccion);
                consulta.setString(4, cliente_idc);
                consulta.executeUpdate();
                cliente_consultar();
                cliente_limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Datos incompletos", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void cliente_borrar() {
        try {
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("DELETE FROM cliente WHERE dniCliente=?");
            consulta.setString(1, cliente_idc);
            consulta.executeUpdate();
            cliente_consultar();
            cliente_limpiar();
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void cliente_limpiar() {
        UIController.vaciarTxtField(
                cliente_txtDni, cliente_txtNombre,
                cliente_txtApellido, cliente_txtDireccion
        );
        cliente_filaSeleccionada = -1;
        cliente_txtDni.setEditable(true);
    }

    public void rellenar(ArrayList<String> datos, TextFieldSuggestion text) {
        for (int i = 0; i < datos.size(); i++) {
            text.addItemSuggestion(datos.get(i));
        }
    }

    /*
        ==================================================
        ==================================================
     */
    private void auto_consultar() {
        try {
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("SELECT * FROM vehiculos");
            resultado = consulta.executeQuery();
            auto_modelo = (DefaultTableModel) tablaAutos.getModel();
            Object datos[] = new Object[7];
            auto_modelo.setRowCount(0);
            while (resultado.next()) {
                datos[0] = resultado.getString("placa");
                datos[1] = resultado.getString("cliente");
                datos[2] = resultado.getString("marca");
                datos[3] = resultado.getString("modelo");
                datos[4] = resultado.getInt("antiguedad");
                datos[5] = resultado.getString("color");
                datos[6] = resultado.getDate("fecha");
                auto_modelo.addRow(datos);
            }
            tablaAutos.setModel(auto_modelo);
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void auto_registrar() {
        try {
            String placa = auto_txtPlaca.getText();
            String marca = (String) auto_comboMarca.getSelectedItem();
            String modelo = (String) auto_comboModelo.getSelectedItem();
            String cliente = auto_txtDni.getText();
            String antiguedad = auto_txtAntiguedad.getText();
            String color = auto_txtColor.getText();
            if (UIController.datosCompletos(placa, marca, modelo, cliente, antiguedad, color)) {
                if (ModeloDAO.existe("cliente", "dniCliente", cliente)) {
                    Date fecha = new SimpleDateFormat("yyy-mm-dd").parse(ActionUtils.FechaActual());
                    conexion = new ConexionSQL().conexion();
                    consulta = conexion.prepareStatement("INSERT INTO vehiculos (placa, marca, modelo, cliente, antiguedad, color, fecha) VALUES (?,?,?,?,?,?,?)");
                    consulta.setString(1, placa);
                    consulta.setString(2, marca);
                    consulta.setString(3, modelo);
                    consulta.setString(4, cliente);
                    consulta.setInt(5, Integer.parseInt(antiguedad));
                    consulta.setString(6, color);
                    consulta.setDate(7, new java.sql.Date(fecha.getTime()));
                    consulta.executeUpdate();
                    auto_limpiar();
                    auto_consultar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Cliente no existe");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Complete todos los datos");
            }
        } catch (SQLException | ParseException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void auto_modificar() {
        try {
            String placa = auto_txtPlaca.getText();
            String marca = (String) auto_comboMarca.getSelectedItem();
            String modelo = (String) auto_comboModelo.getSelectedItem();
            String cliente = auto_txtDni.getText();
            String antiguedad = auto_txtAntiguedad.getText();
            String color = auto_txtColor.getText();
            if (UIController.datosCompletos(placa, marca, modelo, cliente, antiguedad, color)) {
                if (ModeloDAO.existe("cliente", "dniCliente", cliente)) {
                    Date fecha = new SimpleDateFormat("yyy-mm-dd").parse(ActionUtils.FechaActual());
                    conexion = new ConexionSQL().conexion();
                    consulta = conexion.prepareStatement("UPDATE vehiculos set marca=?, modelo=?, cliente=?, antiguedad=?, color=?, fecha=? WHERE placa=?");
                    consulta.setString(1, marca);
                    consulta.setString(2, modelo);
                    consulta.setString(3, cliente);
                    consulta.setInt(4, Integer.parseInt(antiguedad));
                    consulta.setString(5, color);
                    consulta.setDate(6, new java.sql.Date(fecha.getTime()));
                    consulta.setString(7, placa);
                    consulta.executeUpdate();
                    auto_limpiar();
                    auto_consultar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Cliente no existe");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Complete todos los datos");
            }
        } catch (SQLException | ParseException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void auto_borrar() {
        try {
            conexion = new ConexionSQL().conexion();
            consulta = conexion.prepareStatement("DELETE FROM vehiculos WHERE placa=?");
            consulta.setString(1, auto_idc);
            consulta.executeUpdate();
            auto_limpiar();
            auto_consultar();
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void auto_limpiar() {
        UIController.vaciarTxtField(auto_txtDni, auto_txtPlaca, auto_txtAntiguedad, auto_txtColor);
        auto_comboMarca.setSelectedIndex(0);
        auto_comboModelo.setSelectedIndex(0);
        auto_filaSeleccionada = -1;
        auto_txtPlaca.setEditable(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound3 = new com.modelo.UIDesinger.PanelRound();
        pictureBox1 = new com.modelo.UIDesinger.PictureBox();
        panelRound2 = new com.modelo.UIDesinger.PanelRound();
        jLabel6 = new javax.swing.JLabel();
        panelajksd = new com.modelo.UIDesinger.PanelRound();
        jLabel7 = new javax.swing.JLabel();
        auto_txtDni = new textfield_suggestion.TextFieldSuggestion();
        jLabel8 = new javax.swing.JLabel();
        auto_txtPlaca = new textfield_suggestion.TextFieldSuggestion();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        auto_txtAntiguedad = new textfield_suggestion.TextFieldSuggestion();
        auto_txtColor = new textfield_suggestion.TextFieldSuggestion();
        jLabel14 = new javax.swing.JLabel();
        auto_comboMarca = new combo_suggestion.ComboBoxSuggestion();
        auto_comboModelo = new combo_suggestion.ComboBoxSuggestion();
        jScrollPane3 = new com.modelo.UIDesinger.Scroll.ScrollPaneWin11();
        tablaAutos = new com.modelo.UIDesinger.TableDark();
        panelRound1 = new com.modelo.UIDesinger.PanelRound();
        auto_btnRegistrar = new button.Button();
        auto_btnBorrar = new button.Button();
        auto_btnActualizar = new button.Button();
        auto_btnLimpiar = new button.Button();
        panelRound4 = new com.modelo.UIDesinger.PanelRound();
        jLabel12 = new javax.swing.JLabel();
        panelRound5 = new com.modelo.UIDesinger.PanelRound();
        jLabel13 = new javax.swing.JLabel();
        cliente_txtDni = new textfield_suggestion.TextFieldSuggestion();
        cliente_txtNombre = new textfield_suggestion.TextFieldSuggestion();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cliente_txtApellido = new textfield_suggestion.TextFieldSuggestion();
        cliente_txtDireccion = new textfield_suggestion.TextFieldSuggestion();
        jLabel17 = new javax.swing.JLabel();
        panelRound6 = new com.modelo.UIDesinger.PanelRound();
        cliente_btnRegistrar = new button.Button();
        cliente_btnActualizar = new button.Button();
        cliente_btnLimpiar = new button.Button();
        cliente_btnBorrar = new button.Button();
        jScrollPane1 = new com.modelo.UIDesinger.Scroll.ScrollPaneWin11();
        tablaCliente = new com.modelo.UIDesinger.TableDark();

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(20, 20, 20));

        panelRound2.setBackground(new java.awt.Color(25, 25, 25));
        panelRound2.setRoundBottomLeft(15);
        panelRound2.setRoundBottomRight(15);
        panelRound2.setRoundTopLeft(15);
        panelRound2.setRoundTopRight(15);

        jLabel6.setFont(new java.awt.Font("Century", 0, 55)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Auto");

        panelajksd.setBackground(new java.awt.Color(20, 20, 20));
        panelajksd.setRoundBottomLeft(15);
        panelajksd.setRoundBottomRight(15);
        panelajksd.setRoundTopLeft(15);
        panelajksd.setRoundTopRight(15);
        panelajksd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelajksdMouseEntered(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Dni Cliente");

        auto_txtDni.setForeground(new java.awt.Color(255, 255, 255));
        auto_txtDni.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        auto_txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                auto_txtDniKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Placa");

        auto_txtPlaca.setForeground(new java.awt.Color(255, 255, 255));
        auto_txtPlaca.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        auto_txtPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                auto_txtPlacaKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Modelo");

        jLabel10.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Marca");

        jLabel11.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Antigüedad");

        auto_txtAntiguedad.setForeground(new java.awt.Color(255, 255, 255));
        auto_txtAntiguedad.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        auto_txtAntiguedad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                auto_txtAntiguedadKeyTyped(evt);
            }
        });

        auto_txtColor.setForeground(new java.awt.Color(255, 255, 255));
        auto_txtColor.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        auto_txtColor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                auto_txtColorKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Color");

        auto_comboMarca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Abarth", "Alfa Romeo", "Aston Martin", "Audi", "Bentley", "BMW", "Cadillac", "Caterham", "Chevrolet", "Citroen", "Dacia", "Ferrari", "Fiat", "Ford", "Honda", "Infiniti", "Isuzu", "Iveco", "Jaguar", "Jeep", "Kia", "KTM", "Lada", "Lamborghini", "Lancia", "Land Rover", "Lexus", "Lotus", "Maserati", "Mazda", "Mercedes-Benz", "Mini", "Mitsubishi", "Morgan", "Nissan", "Opel", "Peugeot", "Piaggio", "Porsche", "Renault", "Rolls-Royce", "Seat", "Skoda", "Smart", "SsangYong", "Subaru", "Suzuki", "Tata", "Tesla", "Toyota", "Volkswagen" }));
        auto_comboMarca.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        auto_comboModelo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sedán", "Hatchback", "Coupé", "SUV", "Station Wagon", "Crossover", "Convertibles", "MPV", "Pick Up" }));
        auto_comboModelo.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        javax.swing.GroupLayout panelajksdLayout = new javax.swing.GroupLayout(panelajksd);
        panelajksd.setLayout(panelajksdLayout);
        panelajksdLayout.setHorizontalGroup(
            panelajksdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelajksdLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelajksdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(auto_txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(auto_comboModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelajksdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(auto_txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(auto_txtAntiguedad, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addGroup(panelajksdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(auto_txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(auto_comboMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );
        panelajksdLayout.setVerticalGroup(
            panelajksdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelajksdLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelajksdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelajksdLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(auto_txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119))
                    .addGroup(panelajksdLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addGroup(panelajksdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(auto_txtAntiguedad, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(auto_comboModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelajksdLayout.createSequentialGroup()
                        .addGroup(panelajksdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelajksdLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(auto_txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelajksdLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(auto_comboMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)))
                        .addGroup(panelajksdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelajksdLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel9)
                                .addGap(64, 64, 64))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelajksdLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(auto_txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        tablaAutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Placa", "DniCliente", "Marca", "Modelo", "Antigüedad", "Color", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaAutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAutosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaAutos);

        panelRound1.setBackground(new java.awt.Color(25, 25, 25));

        auto_btnRegistrar.setBackground(new java.awt.Color(25, 25, 25));
        auto_btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        auto_btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/icons/agregar.png"))); // NOI18N
        auto_btnRegistrar.setText("REGISTRAR");
        auto_btnRegistrar.setCurrentCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        auto_btnRegistrar.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        auto_btnRegistrar.setShadowColor(new java.awt.Color(0, 0, 0));
        auto_btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                auto_btnRegistrarActionPerformed(evt);
            }
        });

        auto_btnBorrar.setBackground(new java.awt.Color(25, 25, 25));
        auto_btnBorrar.setForeground(new java.awt.Color(255, 255, 255));
        auto_btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/icons/eliminar.png"))); // NOI18N
        auto_btnBorrar.setText("BORRAR");
        auto_btnBorrar.setCurrentCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        auto_btnBorrar.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        auto_btnBorrar.setShadowColor(new java.awt.Color(0, 0, 0));
        auto_btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                auto_btnBorrarActionPerformed(evt);
            }
        });

        auto_btnActualizar.setBackground(new java.awt.Color(25, 25, 25));
        auto_btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        auto_btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/icons/actualizar.png"))); // NOI18N
        auto_btnActualizar.setText("ACTUALIZAR");
        auto_btnActualizar.setCurrentCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        auto_btnActualizar.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        auto_btnActualizar.setShadowColor(new java.awt.Color(0, 0, 0));
        auto_btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                auto_btnActualizarActionPerformed(evt);
            }
        });

        auto_btnLimpiar.setBackground(new java.awt.Color(25, 25, 25));
        auto_btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        auto_btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/icons/limpiar.png"))); // NOI18N
        auto_btnLimpiar.setText("LIMPIAR");
        auto_btnLimpiar.setCurrentCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        auto_btnLimpiar.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        auto_btnLimpiar.setShadowColor(new java.awt.Color(0, 0, 0));
        auto_btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                auto_btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(auto_btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(auto_btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(auto_btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(auto_btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(auto_btnRegistrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(auto_btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(auto_btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(auto_btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(300, 300, 300))
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelajksd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(panelajksd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelRound4.setBackground(new java.awt.Color(25, 25, 25));
        panelRound4.setRoundBottomLeft(15);
        panelRound4.setRoundBottomRight(15);
        panelRound4.setRoundTopLeft(15);
        panelRound4.setRoundTopRight(15);

        jLabel12.setFont(new java.awt.Font("Century", 0, 55)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Cliente");

        panelRound5.setBackground(new java.awt.Color(20, 20, 20));
        panelRound5.setRoundBottomLeft(15);
        panelRound5.setRoundBottomRight(15);
        panelRound5.setRoundTopLeft(15);
        panelRound5.setRoundTopRight(15);

        jLabel13.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Dni");

        cliente_txtDni.setForeground(new java.awt.Color(255, 255, 255));
        cliente_txtDni.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        cliente_txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cliente_txtDniKeyTyped(evt);
            }
        });

        cliente_txtNombre.setForeground(new java.awt.Color(255, 255, 255));
        cliente_txtNombre.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        cliente_txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cliente_txtNombreKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Nombre");

        jLabel16.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Apellido");

        cliente_txtApellido.setForeground(new java.awt.Color(255, 255, 255));
        cliente_txtApellido.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        cliente_txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cliente_txtApellidoKeyTyped(evt);
            }
        });

        cliente_txtDireccion.setForeground(new java.awt.Color(255, 255, 255));
        cliente_txtDireccion.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        cliente_txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cliente_txtDireccionKeyTyped(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Century", 1, 22)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Direccion");

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cliente_txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cliente_txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cliente_txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cliente_txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound5Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(cliente_txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound5Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(cliente_txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound5Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(cliente_txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound5Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(cliente_txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
        );

        panelRound6.setBackground(new java.awt.Color(25, 25, 25));

        cliente_btnRegistrar.setBackground(new java.awt.Color(25, 25, 25));
        cliente_btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        cliente_btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/icons/agregar.png"))); // NOI18N
        cliente_btnRegistrar.setText("REGISTRAR");
        cliente_btnRegistrar.setCurrentCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cliente_btnRegistrar.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        cliente_btnRegistrar.setShadowColor(new java.awt.Color(0, 0, 0));
        cliente_btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cliente_btnRegistrarActionPerformed(evt);
            }
        });

        cliente_btnActualizar.setBackground(new java.awt.Color(25, 25, 25));
        cliente_btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        cliente_btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/icons/actualizar.png"))); // NOI18N
        cliente_btnActualizar.setText("ACTUALIZAR");
        cliente_btnActualizar.setCurrentCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cliente_btnActualizar.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        cliente_btnActualizar.setShadowColor(new java.awt.Color(0, 0, 0));
        cliente_btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cliente_btnActualizarActionPerformed(evt);
            }
        });

        cliente_btnLimpiar.setBackground(new java.awt.Color(25, 25, 25));
        cliente_btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        cliente_btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/icons/limpiar.png"))); // NOI18N
        cliente_btnLimpiar.setText("LIMPIAR");
        cliente_btnLimpiar.setCurrentCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cliente_btnLimpiar.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        cliente_btnLimpiar.setShadowColor(new java.awt.Color(0, 0, 0));
        cliente_btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cliente_btnLimpiarActionPerformed(evt);
            }
        });

        cliente_btnBorrar.setBackground(new java.awt.Color(25, 25, 25));
        cliente_btnBorrar.setForeground(new java.awt.Color(255, 255, 255));
        cliente_btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/icons/eliminar.png"))); // NOI18N
        cliente_btnBorrar.setText("BORRAR");
        cliente_btnBorrar.setCurrentCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cliente_btnBorrar.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        cliente_btnBorrar.setShadowColor(new java.awt.Color(0, 0, 0));
        cliente_btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cliente_btnBorrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound6Layout = new javax.swing.GroupLayout(panelRound6);
        panelRound6.setLayout(panelRound6Layout);
        panelRound6Layout.setHorizontalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound6Layout.createSequentialGroup()
                        .addComponent(cliente_btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cliente_btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound6Layout.createSequentialGroup()
                        .addComponent(cliente_btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cliente_btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelRound6Layout.setVerticalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cliente_btnRegistrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cliente_btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cliente_btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cliente_btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        tablaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dni", "Nombre", "Apellido", "Direccion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCliente);

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelRound5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelRound4Layout.createSequentialGroup()
                        .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound4Layout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelRound4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRound6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void auto_btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_auto_btnRegistrarActionPerformed
        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "Confirmar registro", "Confirmacion", dialog);
        if (result == 0) {
            auto_registrar();
        }
    }//GEN-LAST:event_auto_btnRegistrarActionPerformed

    private void auto_btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_auto_btnBorrarActionPerformed
        if (auto_filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            int dialog = JOptionPane.YES_NO_OPTION;
            int result = JOptionPane.showConfirmDialog(null, "Confirmar eliminacion", "Confirmacion", dialog);
            if (result == 0) {
                auto_borrar();
            }
        }
    }//GEN-LAST:event_auto_btnBorrarActionPerformed

    private void auto_btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_auto_btnLimpiarActionPerformed
        auto_limpiar();
    }//GEN-LAST:event_auto_btnLimpiarActionPerformed

    private void auto_btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_auto_btnActualizarActionPerformed
        if (auto_filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            int dialog = JOptionPane.YES_NO_OPTION;
            int result = JOptionPane.showConfirmDialog(null, "Confirmar actualizacion", "Confirmacion", dialog);
            if (result == 0) {
                auto_modificar();
            }
        }
    }//GEN-LAST:event_auto_btnActualizarActionPerformed

    private void cliente_btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cliente_btnRegistrarActionPerformed
        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "Confirmar ingreso", "Confirmacion", dialog);
        if (result == 0) {
            cliente_registrar();
        }
    }//GEN-LAST:event_cliente_btnRegistrarActionPerformed

    private void cliente_btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cliente_btnBorrarActionPerformed
        if (cliente_filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(null, "Selecciona una fila");
        } else {
            int dialogo = JOptionPane.YES_NO_OPTION;
            int result = JOptionPane.showConfirmDialog(null, "Confirmar eliminacion", "Confirmacion", dialogo);
            if (result == 0) {
                cliente_borrar();
            }
        }
    }//GEN-LAST:event_cliente_btnBorrarActionPerformed

    private void cliente_btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cliente_btnActualizarActionPerformed
        if (cliente_filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            int dialog = JOptionPane.YES_NO_OPTION;
            int result = JOptionPane.showConfirmDialog(null, "Confirmar actualizacion", "Confirmacion", dialog);
            if (result == 0) {
                cliente_modificar();
            }
        }
    }//GEN-LAST:event_cliente_btnActualizarActionPerformed

    private void cliente_btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cliente_btnLimpiarActionPerformed
        cliente_limpiar();
    }//GEN-LAST:event_cliente_btnLimpiarActionPerformed

    private void tablaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClienteMouseClicked
        cliente_filaSeleccionada = tablaCliente.getSelectedRow();

        cliente_idc = tablaCliente.getValueAt(cliente_filaSeleccionada, 0).toString();
        String nombre = tablaCliente.getValueAt(cliente_filaSeleccionada, 1).toString();
        String apellido = tablaCliente.getValueAt(cliente_filaSeleccionada, 2).toString();
        String direccion = tablaCliente.getValueAt(cliente_filaSeleccionada, 3).toString();

        cliente_txtDni.setText(cliente_idc);
        cliente_txtDni.setEditable(false);
        cliente_txtNombre.setText(nombre);
        cliente_txtApellido.setText(apellido);
        cliente_txtDireccion.setText(direccion);


    }//GEN-LAST:event_tablaClienteMouseClicked

    private void panelajksdMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelajksdMouseEntered
        rellenarArrayList();
        rellenar(clientesAutos, auto_txtDni);
    }//GEN-LAST:event_panelajksdMouseEntered

    private void tablaAutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAutosMouseClicked
        auto_filaSeleccionada = tablaAutos.getSelectedRow();
        auto_idc = tablaAutos.getValueAt(auto_filaSeleccionada, 0).toString();
        String dniCliente = tablaAutos.getValueAt(auto_filaSeleccionada, 1).toString();
        String marca = tablaAutos.getValueAt(auto_filaSeleccionada, 2).toString();
        String modelo = tablaAutos.getValueAt(auto_filaSeleccionada, 3).toString();
        String antiguedad = tablaAutos.getValueAt(auto_filaSeleccionada, 4).toString();
        String color = tablaAutos.getValueAt(auto_filaSeleccionada, 5).toString();

        auto_txtPlaca.setText(auto_idc);
        auto_txtPlaca.setEditable(false);
        auto_txtDni.setText(dniCliente);
        auto_comboMarca.setSelectedItem(marca);
        auto_comboModelo.setSelectedItem(modelo);
        auto_txtAntiguedad.setText(antiguedad);
        auto_txtColor.setText(color);

    }//GEN-LAST:event_tablaAutosMouseClicked

    private void cliente_txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cliente_txtDniKeyTyped
        UIController.limitacionNumeros(cliente_txtDni, evt, 8);
    }//GEN-LAST:event_cliente_txtDniKeyTyped

    private void cliente_txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cliente_txtNombreKeyTyped
        UIController.limitacionCaracteres(cliente_txtNombre, evt, 50, true);
    }//GEN-LAST:event_cliente_txtNombreKeyTyped

    private void cliente_txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cliente_txtApellidoKeyTyped
        UIController.limitacionCaracteres(cliente_txtApellido, evt, 50, true);
    }//GEN-LAST:event_cliente_txtApellidoKeyTyped

    private void cliente_txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cliente_txtDireccionKeyTyped
        UIController.limitacionCaracteres(cliente_txtDireccion, evt, 100, true);
    }//GEN-LAST:event_cliente_txtDireccionKeyTyped

    private void auto_txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_auto_txtDniKeyTyped
        UIController.limitacionNumeros(auto_txtDni, evt, 8);
    }//GEN-LAST:event_auto_txtDniKeyTyped

    private void auto_txtPlacaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_auto_txtPlacaKeyTyped
        UIController.limitacionCaracteres(auto_txtPlaca, evt, 7, true);
    }//GEN-LAST:event_auto_txtPlacaKeyTyped

    private void auto_txtAntiguedadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_auto_txtAntiguedadKeyTyped
        UIController.limitacionNumeros(auto_txtAntiguedad, evt, 3);
    }//GEN-LAST:event_auto_txtAntiguedadKeyTyped

    private void auto_txtColorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_auto_txtColorKeyTyped
        UIController.limitacionCaracteres(auto_txtColor, evt, 25, false);
    }//GEN-LAST:event_auto_txtColorKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button auto_btnActualizar;
    private button.Button auto_btnBorrar;
    private button.Button auto_btnLimpiar;
    private button.Button auto_btnRegistrar;
    private combo_suggestion.ComboBoxSuggestion auto_comboMarca;
    private combo_suggestion.ComboBoxSuggestion auto_comboModelo;
    private textfield_suggestion.TextFieldSuggestion auto_txtAntiguedad;
    private textfield_suggestion.TextFieldSuggestion auto_txtColor;
    private textfield_suggestion.TextFieldSuggestion auto_txtDni;
    private textfield_suggestion.TextFieldSuggestion auto_txtPlaca;
    private button.Button cliente_btnActualizar;
    private button.Button cliente_btnBorrar;
    private button.Button cliente_btnLimpiar;
    private button.Button cliente_btnRegistrar;
    private textfield_suggestion.TextFieldSuggestion cliente_txtApellido;
    private textfield_suggestion.TextFieldSuggestion cliente_txtDireccion;
    private textfield_suggestion.TextFieldSuggestion cliente_txtDni;
    private textfield_suggestion.TextFieldSuggestion cliente_txtNombre;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private com.modelo.UIDesinger.PanelRound panelRound1;
    private com.modelo.UIDesinger.PanelRound panelRound2;
    private com.modelo.UIDesinger.PanelRound panelRound3;
    private com.modelo.UIDesinger.PanelRound panelRound4;
    private com.modelo.UIDesinger.PanelRound panelRound5;
    private com.modelo.UIDesinger.PanelRound panelRound6;
    private com.modelo.UIDesinger.PanelRound panelajksd;
    private com.modelo.UIDesinger.PictureBox pictureBox1;
    private com.modelo.UIDesinger.TableDark tablaAutos;
    private com.modelo.UIDesinger.TableDark tablaCliente;
    // End of variables declaration//GEN-END:variables
}
