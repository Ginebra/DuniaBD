/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package miPaquete;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author neko
 */
public class FacturaConsulta extends javax.swing.JFrame {

    DefaultTableModel m ;
    ResultSet r;
    Conexiones con = new Conexiones();  
    String codigo, descripcion, precio, cantidad, iva, descuento;
    
    public FacturaConsulta(){
        initComponents();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
        
    public void buscar (String id) throws SQLException{
        try{
        ResultSet resultado;
        String SQL;
               
        SQL = "SELECT * FROM PERSONA "
                + "INNER JOIN PERSONA_DOMICILIO "
                + "ON PERSONA_DOMICILIO.ID_PERSONA = PERSONA.ID_PERSONA "
                + "INNER JOIN DOMICILIO "
                + "ON PERSONA_DOMICILIO.ID_DOMICILIO = DOMICILIO.ID_DOMICILIO "
                + "INNER JOIN PERSONA_TELEFONO "
                + "ON PERSONA_TELEFONO.ID_PERSONA = PERSONA.ID_PERSONA "
                + "INNER JOIN TELEFONO "
                + "ON PERSONA_TELEFONO.ID_TELEFONO = TELEFONO.ID_TELEFONO "
                + "INNER JOIN PERSONA_PAGINA "
                + "ON PERSONA_PAGINA.ID_PERSONA = PERSONA.ID_PERSONA "
                + "INNER JOIN PAGINA_WEB "
                + "ON PERSONA_PAGINA.ID_PAGINA = PAGINA_WEB.ID_PAGINA "
                + "INNER JOIN PERSONA_LOGOTIPO "
                + "ON PERSONA_LOGOTIPO.ID_PERSONA = PERSONA.ID_PERSONA "
                + "INNER JOIN LOGOTIPO "
                + "ON PERSONA_LOGOTIPO.ID_LOGOTIPO = LOGOTIPO.ID_LOGOTIPO "
                + "WHERE PERSONA.ID_PERSONA = "+id+"";
        
        resultado = con.Consultar(Integer.parseInt(id),SQL);
        
         while (resultado.next()) {
             //Persona        
             nombreFactura.setText(resultado.getString("NOMBRE")+" "+resultado.getString("APELLIDO_PATERNO")+" "+resultado.getString("APELLIDO_MATERNO"));
             calleFactura.setText(resultado.getString("CALLE")+" #"+resultado.getString("NUMERO_EXT"));
             ciudadFactura.setText(resultado.getString("CIUDAD")+" "+resultado.getString("ESTADO"));
             coloniaFactura.setText(resultado.getString("COLONIA"));
             cpFactura.setText(resultado.getString("CP"));
             rsocialFactura.setText(resultado.getString("RAZON_SOCIAL"));
             rfcFactura.setText(resultado.getString("RFC"));
             rfiscalFactura.setText(resultado.getString("REGIMEN_FISCAL"));
             tipoFactura.setText(resultado.getString("TIPO_PERSONA"));
             telefonoFactura.setText(resultado.getString("NUMERO"));
         }
        resultado.close();
        }
        catch (SQLException e) {
            System.out.println("Error en Consultar");
            JOptionPane.showMessageDialog(null, "No hay datos");
        }
    }
    
    
    void GenerarTabla(){
        double calcula=0, x=0, total = 0, descue, ivas, subtotal = 0, sub, sub1;
        String importe;
        
        String SQL, id="";
        int buscarfactura = Integer.parseInt( JOptionPane.showInputDialog(
        null,"Introduce FOLIO de factura",
        "BUSCADOR",
        JOptionPane.QUESTION_MESSAGE) );
         try{        
           String titulos[]={"CODIGO", "DESCRIPCION","PRECIO","CANTIDAD",
               "%IVA","%DESCUENTO","TOTAL"};
           
            m = new DefaultTableModel(null, titulos);
            String fila[]=new String[17];
            
            SQL = "select * from FACTURA "
                + "INNER JOIN PRODUCTO "
                + "ON FACTURA.CODIGO = PRODUCTO.CODIGO "
                + "INNER JOIN PRODUCTO_IVA "
                + "ON PRODUCTO_IVA.CODIGO = PRODUCTO.CODIGO "
                + "INNER JOIN IVA "
                + "ON IVA.ID_IVA = PRODUCTO_IVA.ID_IVA "
                + "INNER JOIN PRODUCTO_DESCUENTO "
                + "ON PRODUCTO_DESCUENTO.CODIGO = PRODUCTO.CODIGO "
                + "INNER JOIN DESCUENTO "
                + "ON DESCUENTO.ID_DESCUENTO = PRODUCTO_DESCUENTO.ID_DESCUENTO "
                + "WHERE FACTURA.FOLIO = "+buscarfactura+"";
           
            r = con.generar(SQL);
            while(r.next()){   
             /////////////////////////////////////////////////////////////////
             
             descue = ((Double.parseDouble(r.getString(19))*Double.parseDouble(r.getString("PRECIO"))))/100;
             ivas = (((Double.parseDouble(r.getString(11)))/100)+1)*(Double.parseDouble(r.getString("PRECIO")));
             
             sub = (ivas-descue)*(Double.parseDouble(r.getString("CANTIDAD")));
             x = (Double.parseDouble(r.getString("CANTIDAD")))*ivas;
             total = total + sub;
             subtotal = subtotal + x;
             importe = Double.toString(sub);
         
           /////////////////////////////////////////////////////////////////
                
                fila[0] = r.getString("CODIGO");
                fila[1] = r.getString("DESCRIPCION_PRODUCTO");
                fila[2] = r.getString("PRECIO");
                fila[3] = r.getString("CANTIDAD");
                fila[4] = r.getString(11); //IVA
                fila[5] = r.getString(19); //DESCUENTO
                fila[6] = importe;
                
                m.addRow(fila);
                id = r.getString(1);
            }   
           tablaProducto.setModel(m);
           totalFactura.setText("$"+total);
           subtotalFactura.setText("$"+subtotal);
           
           System.out.println("BUSCAR CON ID "+id);
           buscar(id);
           this.setVisible(true);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "NO EXISTE FOLIO");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        tablaProducto = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        nombreFactura = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        calleFactura = new javax.swing.JLabel();
        ciudadFactura = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        coloniaFactura = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rsocialFactura = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        rfcFactura = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rfiscalFactura = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tipoFactura = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        telefonoFactura = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cpFactura = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        subtotalFactura = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        totalFactura = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablaProducto);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Generales"));

        jLabel1.setText("NOMBRE:");

        jLabel17.setText("CALLE:");

        nombreFactura.setText("Juan Aaron Garcia Sanchez");

        jLabel2.setText("CIUDAD:");

        calleFactura.setText("av. benito juarez #11");

        ciudadFactura.setText("Tuxpan Jalisco");

        jLabel5.setText("COLONIA:");

        coloniaFactura.setText("Rosendo G. Castro");

        jLabel7.setText("RAZON SOCIAL:");

        rsocialFactura.setText("Razon social Comunica2 enterprise");

        jLabel15.setText("RFC:");

        rfcFactura.setText("14323lkj2jlk545");

        jLabel4.setText("REGIMEN FISCAL:");

        rfiscalFactura.setText("Regimen Fiscal");

        jLabel10.setText("TIPO DE PERSONA:");

        tipoFactura.setText("Tipo de persona");

        jLabel12.setText("TELEFONO:");

        telefonoFactura.setText("3411247021");

        jLabel18.setText("CP:");

        cpFactura.setText("49800");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel17)
                            .addComponent(jLabel2))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(calleFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nombreFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ciudadFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel18))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cpFactura)
                            .addComponent(coloniaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel15))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rfcFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rsocialFactura)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(rfiscalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(telefonoFactura)
                            .addComponent(tipoFactura))))
                .addGap(66, 66, 66))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nombreFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(rsocialFactura))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(calleFactura)
                    .addComponent(jLabel15)
                    .addComponent(rfcFactura))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ciudadFactura)
                    .addComponent(jLabel4)
                    .addComponent(rfiscalFactura))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(coloniaFactura)
                    .addComponent(jLabel10)
                    .addComponent(tipoFactura))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(telefonoFactura)
                    .addComponent(jLabel18)
                    .addComponent(cpFactura))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jButton1.setText("EXPORTAR A PDF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setText("SUBTOTAL");

        jLabel24.setText("TOTAL");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(subtotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(67, 67, 67))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(subtotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(totalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductoMouseClicked
       
    }//GEN-LAST:event_tablaProductoMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      try {            
            Robot robot = new Robot();
            // Capture the screen shot of the area of the screen defined by the rectangle[797, 542]
            BufferedImage bi = robot.createScreenCapture(new Rectangle(839,610));
            ImageIO.write(bi, "jpg", new File("C:/Users/ABC/Desktop/factura.jpg"));
            
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
      CrearPDF crear = new CrearPDF();
      crear.imagePDF();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FacturaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FacturaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FacturaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FacturaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FacturaConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel calleFactura;
    private javax.swing.JLabel ciudadFactura;
    private javax.swing.JLabel coloniaFactura;
    private javax.swing.JLabel cpFactura;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel nombreFactura;
    private javax.swing.JLabel rfcFactura;
    private javax.swing.JLabel rfiscalFactura;
    private javax.swing.JLabel rsocialFactura;
    private javax.swing.JTextField subtotalFactura;
    private javax.swing.JTable tablaProducto;
    private javax.swing.JLabel telefonoFactura;
    private javax.swing.JLabel tipoFactura;
    private javax.swing.JTextField totalFactura;
    // End of variables declaration//GEN-END:variables
}
