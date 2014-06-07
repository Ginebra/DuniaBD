/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package miPaquete;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author Ana
 */
public class encabezado extends javax.swing.JFrame {
 Conexiones con = new Conexiones();  
 ResultSet r;
 int aux;
 String numero, fecha, fechaEn, Query,s;
 Factura obj=new Factura();
 
 public encabezado() {
     initComponents();
     this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        folioEncabezado.setEditable(false);
        lugarEncabezado.requestFocus();
         Query = "SELECT FOLIO FROM ENCABEZADO_FACTURA";
         s = "FOLIO";
     try {
         aux = con.Folio(Query, s);
         
         numero=Integer.toString(aux);
         folioEncabezado.setText("000"+numero);
     } catch (SQLException ex) {
         Logger.getLogger(encabezado.class.getName()).log(Level.SEVERE, null, ex);
     } 
             Date fecha=new Date(); 
             SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy"); 
             fechaEncabezado.setText(sdf.format(fecha)); 
    }
    
    public void agregar(){
       int estatus = estatusEncabezado.isSelected() ? 1 : 0;
       int timbrado = 0;
       String SQL = "INSERT ENCABEZADO_FACTURA VALUES "
                + "("+folioEncabezado.getText()+","
                + "'"+fechaEncabezado.getText()+"',"
                + "'"+lugarEncabezado.getText()+"',"
                + "'"+estatus+"',"
                + "'"+timbrado+"',"
                + "'"+descuentoEncabezado.getText()+"')";
        con.Insertar(SQL);
     }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        folioEncabezado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        fechaEncabezado = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lugarEncabezado = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        descuentoEncabezado = new javax.swing.JTextField();
        estatusEncabezado = new javax.swing.JCheckBox();
        timbradoEncabezado = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Folio:");

        jLabel2.setText("Fecha:");

        fechaEncabezado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaEncabezadoActionPerformed(evt);
            }
        });
        fechaEncabezado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fechaEncabezadoKeyTyped(evt);
            }
        });

        jLabel3.setText("Lugar Expedicion");

        jLabel4.setText("Estatus");

        jLabel5.setText("Timbrado:");

        jLabel6.setText("Descuento:");

        lugarEncabezado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lugarEncabezadoActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/data-apply-icon.png"))); // NOI18N
        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        descuentoEncabezado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descuentoEncabezadoActionPerformed(evt);
            }
        });
        descuentoEncabezado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descuentoEncabezadoKeyTyped(evt);
            }
        });

        estatusEncabezado.setText("ACTIVO");

        timbradoEncabezado.setText("TIMBRADO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descuentoEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lugarEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))
                            .addComponent(estatusEncabezado)
                            .addComponent(timbradoEncabezado)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(fechaEncabezado))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(folioEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(folioEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fechaEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lugarEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(estatusEncabezado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(timbradoEncabezado))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(descuentoEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fechaEncabezadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaEncabezadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaEncabezadoActionPerformed

    private void lugarEncabezadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lugarEncabezadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lugarEncabezadoActionPerformed

    private void descuentoEncabezadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descuentoEncabezadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descuentoEncabezadoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        agregar();
        dispose(); 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void descuentoEncabezadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descuentoEncabezadoKeyTyped
        char c = evt.getKeyChar();
        if(c < '0' || c > '9'){
            evt.consume();
        }
    }//GEN-LAST:event_descuentoEncabezadoKeyTyped

    private void fechaEncabezadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fechaEncabezadoKeyTyped
        char c = evt.getKeyChar();
        if(c < '0' || c > '9'){
            evt.consume();
        }
        int tamaño = fechaEncabezado.getText().length();
            if(tamaño == 2 || tamaño == 5){
            fechaEncabezado.setText(fechaEncabezado.getText()+"/");
        }
        
            if(tamaño == 9){
                lugarEncabezado.requestFocus();
            }
    }//GEN-LAST:event_fechaEncabezadoKeyTyped

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(encabezado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(encabezado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(encabezado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(encabezado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new encabezado().setVisible(true);
            }
        });
    }
    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField descuentoEncabezado;
    private javax.swing.JCheckBox estatusEncabezado;
    public javax.swing.JTextField fechaEncabezado;
    public javax.swing.JTextField folioEncabezado;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField lugarEncabezado;
    private javax.swing.JCheckBox timbradoEncabezado;
    // End of variables declaration//GEN-END:variables
}
