package com.vista.principal;

import com.modelo.UIDesinger.UIController;
import java.awt.Color;

public class moduloVentas extends javax.swing.JPanel {

    public moduloVentas() {
        initComponents();
        UIController.MostrarPanel(panelRound2, new moduloNuevaVenta(), 1120, 950);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        panelRound1 = new com.modelo.UIDesinger.PanelRound();
        btnListaVenta = new button.Button();
        btnNuevaVenta = new button.Button();
        panelRound2 = new com.modelo.UIDesinger.PanelRound();

        setBackground(new java.awt.Color(20, 20, 20));

        jPanel1.setBackground(new java.awt.Color(20, 20, 20));

        jLabel12.setFont(new java.awt.Font("Century Gothic", 3, 65)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Ventas");

        panelRound1.setBackground(new java.awt.Color(20, 20, 20));
        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);

        btnListaVenta.setBackground(new java.awt.Color(0, 153, 204));
        btnListaVenta.setForeground(new java.awt.Color(255, 255, 255));
        btnListaVenta.setText("Nueva Venta");
        btnListaVenta.setCurrentCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnListaVenta.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        btnListaVenta.setShadowColor(new java.awt.Color(0, 0, 0));
        btnListaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaVentaActionPerformed(evt);
            }
        });

        btnNuevaVenta.setBackground(new java.awt.Color(30, 30, 30));
        btnNuevaVenta.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevaVenta.setText("Lista Ventas");
        btnNuevaVenta.setCurrentCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevaVenta.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        btnNuevaVenta.setShadowColor(new java.awt.Color(0, 0, 0));
        btnNuevaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnListaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnNuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnListaVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevaVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        panelRound2.setBackground(new java.awt.Color(30, 30, 30));
        panelRound2.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnListaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaVentaActionPerformed
        btnListaVenta.setBackground(new Color(0, 153, 204));
        btnNuevaVenta.setBackground(new Color(30, 30, 30));
        UIController.MostrarPanel(panelRound2, new moduloNuevaVenta(), 1120, 950);
    }//GEN-LAST:event_btnListaVentaActionPerformed

    private void btnNuevaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaVentaActionPerformed
        btnNuevaVenta.setBackground(new Color(0, 153, 204));
        btnListaVenta.setBackground(new Color(30, 30, 30));
        UIController.MostrarPanel(panelRound2, new moduloListaVentas(), 920, 634);
    }//GEN-LAST:event_btnNuevaVentaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button btnListaVenta;
    private button.Button btnNuevaVenta;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private com.modelo.UIDesinger.PanelRound panelRound1;
    private com.modelo.UIDesinger.PanelRound panelRound2;
    // End of variables declaration//GEN-END:variables
}
