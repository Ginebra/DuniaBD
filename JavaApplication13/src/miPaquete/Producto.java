/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package miPaquete;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author neko & Ana
 */
public class Producto extends javax.swing.JInternalFrame {
DefaultTableModel m ;
String SQL;
Conexiones con = new Conexiones();  
 Connection conect;
 ResultSet r;
   int c=1, id;
 String Query,s;
 public int iddescuento=300, idiva=300, flag=-1, band=0;
  
 public Producto(){
        initComponents();
         GenerarTabla();
         inhabilitar();
      
    }

    public void limpiar(){
        codigoProducto.setText("");
        precioProducto.setText("");
        ivaProducto.setText("");
        descripcionProducto.setText("");
        inicioProducto.setText("");
        finProducto.setText("");
        cantidadProducto.setText("");
        promocionProducto.setText("");  
    }
    
    public void inhabilitar(){
        codigoProducto.setEditable(false);
        precioProducto.setEditable(false);
        ivaProducto.setEditable(false);
        descripcionProducto.setEditable(false); 
        inicioProducto.setEditable(false); 
        finProducto.setEditable(false);
        ivaProducto.setEditable(false);
        promocionProducto.setEditable(false); 
    }
    
    void habilitar(){
        codigoProducto.setEditable(true);
        precioProducto.setEditable(true);
        ivaProducto.setEditable(true);
        descripcionProducto.setEditable(true);
        inicioProducto.setEditable(true);
        finProducto.setEditable(true);
        ivaProducto.setEditable(true);
        promocionProducto.setEditable(true);
    }
    public void insertar() throws SQLException{
        System.out.println("inicia metodo de insertar");
        Query = "SELECT ID_IVA FROM IVA";
        s = "ID_IVA";
        idiva = con.Aleatorio(Query, s);
        System.out.println("El id de iva : "+idiva);
        
        Query = "SELECT ID_DESCUENTO FROM DESCUENTO";
        s = "ID_DESCUENTO";
        iddescuento = con.Aleatorio(Query, s);
        System.out.println("El id de descuento : "+iddescuento);
        
      
        ////Llenar PRODUCTO
        SQL = "INSERT PRODUCTO VALUES "
                + "('"+codigoProducto.getText()+"',"
                + "'"+precioProducto.getText()+"',"
                + "'"+descripcionProducto.getText()+"')";
        con.Insertar(SQL);
        
        ////Llenar IVA
        SQL = "INSERT IVA VALUES "
                + "('"+ivaProducto.getText()+"',"
                +idiva+")";
        con.Insertar(SQL);
        
        //Llenar  DESCUENTO
        SQL = "INSERT DESCUENTO VALUES "
                + "("+iddescuento+","
                + "'"+inicioProducto.getText()+"',"
                + "'"+finProducto.getText()+"',"
                + "'"+promocionProducto.getText()+"',"
                + "'"+cantidadProducto.getText()+"')";       
        con.Insertar(SQL);
        
        SQL = "INSERT PRODUCTO_DESCUENTO VALUES "
                + "('"+codigoProducto.getText()+"',"
                + ""+iddescuento+")";
        con.Insertar(SQL);
        
        SQL = "INSERT PRODUCTO_IVA VALUES "
                + "("+codigoProducto.getText()+","
                + ""+idiva+")";
        con.Insertar(SQL);
        
        limpiar();
        
    }
    @SuppressWarnings("unchecked")
    
    
     public void modificar() throws SQLException{     
        try{
           codigoProducto.setEnabled(false);
            int id = Integer.parseInt(codigoProducto.getText());
             SQL="select * From PRODUCTO INNER JOIN PRODUCTO_DESCUENTO "
                    + "ON PRODUCTO_DESCUENTO.CODIGO = PRODUCTO.CODIGO INNER JOIN DESCUENTO"
                    + " ON PRODUCTO_DESCUENTO.ID_DESCUENTO= DESCUENTO.ID_DESCUENTO INNER JOIN PRODUCTO_IVA"
                    + " ON PRODUCTO_IVA.CODIGO = PRODUCTO.CODIGO INNER JOIN IVA "
                    + "ON PRODUCTO_IVA.ID_IVA= IVA.ID_IVA WHERE PRODUCTO.CODIGO="+id;
                  
        r = con.Consultar(id,SQL);
         while (r.next()) {
             //ASIGNACION DE ID's
             idiva = Integer.parseInt(r.getString("ID_IVA"));
             System.out.println("ID IVA: "+idiva);
             iddescuento = Integer.parseInt(r.getString("ID_DESCUENTO"));
             System.out.println("ID DESCUENTO: "+iddescuento); 
         }
          r.close();
            ////modificar tabla PRODUCTO
         SQL = "UPDATE PRODUCTO set PRECIO = '"+precioProducto.getText()+"',"
                 + " DESCRIPCION_PRODUCTO = '"+descripcionProducto.getText()
                 +"' where CODIGO="+id;
         con.Insertar(SQL);
         System.out.println("primer modificacion id:"+id);
         
         ///modificar tabla DESCUENTO
         SQL = "UPDATE DESCUENTO SET "
                    + "FECHA_INICIO = '"+inicioProducto.getText()+"', "
                    + "FECHA_FIN= '"+finProducto.getText()+"', "
                    + "DESCRIPCION_DESCUENTO = '"+promocionProducto.getText()+"', "
                    + "CANTIDAD = '"+cantidadProducto.getText()+"' "
                    + "WHERE ID_DESCUENTO= "+iddescuento;
          System.out.println("ID DES: "+iddescuento);
          con.Insertar(SQL);
   
          ///modificar tabla IVA  
          SQL = "UPDATE IVA SET "
                    + "CANTIDAD = '"+ivaProducto.getText()+"'"
                    + "WHERE ID_IVA = "+idiva;
          con.Insertar(SQL);
          
          JOptionPane.showMessageDialog(null, "Datos Modificados");
          
    }catch(SQLException e ){
        
    } GenerarTabla(); limpiar(); promocionProducto.requestFocus();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        codigoProducto = new javax.swing.JTextField();
        precioProducto = new javax.swing.JTextField();
        descripcionProducto = new javax.swing.JTextField();
        ivaProducto = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        PROMOCION = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        inicioProducto = new javax.swing.JTextField();
        cantidadProducto = new javax.swing.JTextField();
        finProducto = new javax.swing.JTextField();
        promocionProducto = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnGuardar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaProducto = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        setPreferredSize(new java.awt.Dimension(950, 550));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("CODIGO");

        jLabel2.setText("PRECIO");

        jLabel3.setText("DESCRIPCION");

        jLabel4.setText("CANTIDAD(IVA)");

        codigoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                codigoProductoKeyTyped(evt);
            }
        });

        precioProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                precioProductoKeyTyped(evt);
            }
        });

        ivaProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ivaProductoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(descripcionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(codigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(precioProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(ivaProducto))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(codigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(precioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(descripcionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ivaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setText("FECHA INICIO");

        jLabel6.setText("FECHA FIN");

        PROMOCION.setText("PROMOCION");

        jLabel8.setText("CANTIDAD");

        inicioProducto.setText("dd/mm/yyyy");
        inicioProducto.setToolTipText("");
        inicioProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inicioProductoKeyTyped(evt);
            }
        });

        cantidadProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantidadProductoKeyTyped(evt);
            }
        });

        finProducto.setText("dd/mm/yyyy");
        finProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                finProductoKeyTyped(evt);
            }
        });

        promocionProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                promocionProductoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cantidadProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(inicioProducto))
                .addGap(67, 67, 67)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PROMOCION)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(finProducto)
                    .addComponent(promocionProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(inicioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(PROMOCION)
                    .addComponent(cantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(promocionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/delivery-icon.png"))); // NOI18N
        jButton1.setText("NUEVO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/phone-icon.png"))); // NOI18N
        jButton2.setText("MODIFICAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/gift-icon.png"))); // NOI18N
        jButton3.setText("ELIMINAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/Package-accept-icon.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setToolTipText("");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jButton1)
                .addGap(173, 173, 173)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(61, 61, 61))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(29, 29, 29)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator1))
                .addGap(16, 16, 16))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(265, 265, 265))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

public void GenerarTabla() {
  
      try{        
           String titulos[]={"CODIGO", "PRECIO","DESCRIPCION","IVA%",
               "FECHA-INICIO","FECHA-FIN","DESCUENTO%", "PROMOCION"};
            m = new DefaultTableModel(null, titulos);
            String fila[]=new String[17];
            
            SQL="  select * From PRODUCTO INNER JOIN PRODUCTO_DESCUENTO "
                    + "ON PRODUCTO_DESCUENTO.CODIGO = PRODUCTO.CODIGO INNER JOIN DESCUENTO"
                    + " ON PRODUCTO_DESCUENTO.ID_DESCUENTO= DESCUENTO.ID_DESCUENTO INNER JOIN PRODUCTO_IVA"
                    + " ON PRODUCTO_IVA.CODIGO = PRODUCTO.CODIGO INNER JOIN IVA "
                    + "ON PRODUCTO_IVA.ID_IVA= IVA.ID_IVA WHERE PRODUCTO.CODIGO!=-1";
           
            r = con.generar(SQL);
            while(r.next()){
                fila[0]=r.getString("CODIGO");
                fila[1]=r.getString("PRECIO");
                fila[2]=r.getString("DESCRIPCION_PRODUCTO");
                fila[3]=r.getString(13);
                fila[4]=r.getString("FECHA_INICIO");
                fila[5]=r.getString("FECHA_FIN");
                fila[6]=r.getString(10);
                fila[7]=r.getString("DESCRIPCION_DESCUENTO");
                
             m.addRow(fila);
                 c++;
            }   
           tablaProducto.setModel(m);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }
          
}


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    try {
        modificar();
    } catch (SQLException ex) {
        Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        habilitar();
        limpiar();
        flag = 0;
        codigoProducto.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
      if(     codigoProducto.getText().equals("") || 
                precioProducto.getText().equals("") ||
                descripcionProducto.getText().equals("") ||
                ivaProducto.getText().equals("") ||
                inicioProducto.getText().equals("") ||
                finProducto.getText().equals("") ||
                cantidadProducto.getText().equals("") ||
                promocionProducto.getText().equals(""))
        {
        JOptionPane.showMessageDialog(null, "FALTAN DATOS..!!!");
        }
        else{
        int f1 = Integer.parseInt(inicioProducto.getText().substring(6));
        int f2 = Integer.parseInt(finProducto.getText().substring(6));
        System.out.println("f1= "+f1+"\nf2= "+f2);
        if(f2 - f1 < 10){
        if(flag == 1){  
          try {
              modificar();
              btnGuardar.setText("modificar");
              JOptionPane.showMessageDialog(null, "Datos Modificados");
              
          } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "Error al modificar");
              System.out.println("error MODIFICAR");
          }
            
        }
        else{           
            System.out.println("LLAMANDO INSERTAR");
            try {
                insertar();
                GenerarTabla();
                JOptionPane.showMessageDialog(null, "Datos insertados");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al insertar");
                System.out.println("ERROR INSERTAR");
            }
        }
        }
        else{
            JOptionPane.showMessageDialog(null, "FECHAS NO VALIDAS");
        }
      }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tablaProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductoMouseClicked
       if(evt.getButton()==1){
           flag=1;
           int fila=tablaProducto.getSelectedRow();
           try{
               habilitar();
               SQL ="select * From PRODUCTO INNER JOIN PRODUCTO_DESCUENTO "
                    + "ON PRODUCTO_DESCUENTO.CODIGO = PRODUCTO.CODIGO INNER JOIN DESCUENTO"
                    + " ON PRODUCTO_DESCUENTO.ID_DESCUENTO= DESCUENTO.ID_DESCUENTO INNER JOIN PRODUCTO_IVA"
                    + " ON PRODUCTO_IVA.CODIGO = PRODUCTO.CODIGO INNER JOIN IVA "
                    + "ON PRODUCTO_IVA.ID_IVA= IVA.ID_IVA WHERE PRODUCTO.CODIGO="+tablaProducto.getValueAt(fila,0);
               r = con.generar(SQL);
            
            while(r.next()){
                codigoProducto.setText(r.getString("CODIGO"));
                precioProducto.setText(r.getString("PRECIO"));
                descripcionProducto.setText(r.getString("DESCRIPCION_PRODUCTO"));
                ivaProducto.setText(r.getString(13));
                inicioProducto.setText(r.getString(7));
                finProducto.setText(r.getString(8));
                cantidadProducto.setText(r.getString(10));
                promocionProducto.setText(r.getString(9));
                
            }
           }catch(Exception e){
                 System.out.println("ERROR CLICK");
           }
       }
    }//GEN-LAST:event_tablaProductoMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    //BOTON ELIMINAR
         int id = Integer.parseInt(codigoProducto.getText());
        try{
            
             SQL="select * From PRODUCTO INNER JOIN PRODUCTO_DESCUENTO "
                    + "ON PRODUCTO_DESCUENTO.CODIGO = PRODUCTO.CODIGO INNER JOIN DESCUENTO"
                    + " ON PRODUCTO_DESCUENTO.ID_DESCUENTO= DESCUENTO.ID_DESCUENTO INNER JOIN PRODUCTO_IVA"
                    + " ON PRODUCTO_IVA.CODIGO = PRODUCTO.CODIGO INNER JOIN IVA "
                    + "ON PRODUCTO_IVA.ID_IVA= IVA.ID_IVA WHERE PRODUCTO.CODIGO="+id;
                  
        r = con.Consultar(id,SQL);
        
         while (r.next()) {
             //ASIGNACION DE ID's
             idiva = Integer.parseInt(r.getString("ID_IVA"));
             System.out.println("ID IVA: "+idiva);
             iddescuento = Integer.parseInt(r.getString("ID_DESCUENTO"));
             System.out.println("ID DESCUENTO: "+iddescuento); 
         }
         r.close();
       
        }
        catch(Exception e){
            
        } 
        SQL = "delete FACTURA where codigo = ";
        con.Eliminar(id,SQL);
        SQL = "delete PRODUCTO_IVA where CODIGO = ";
        con.Eliminar(id,SQL);
        SQL = "delete PRODUCTO_DESCUENTO where CODIGO = ";
        con.Eliminar(id,SQL);
                
        SQL = "DELETE PRODUCTO_IVA WHERE ID_IVA =";
        con.Eliminar(idiva,SQL);
        SQL = "DELETE PRODUCTO_DESCUENTO WHERE ID_DESCUENTO=";
        con.Eliminar(iddescuento,SQL);   
        SQL = "DELETE IVA WHERE ID_IVA =";
        con.Eliminar(idiva,SQL);
        SQL = "DELETE DESCUENTO WHERE ID_DESCUENTO =";
        con.Eliminar(iddescuento,SQL);
        SQL = "DELETE PRODUCTO WHERE CODIGO =";
        con.Eliminar(id,SQL);
        
        limpiar();
        GenerarTabla();
        JOptionPane.showMessageDialog(null, "Datos ELIMINADOS");
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void codigoProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoProductoKeyTyped
        char c = evt.getKeyChar();
        if(c < '0' || c > '9'){
            evt.consume();
        }
        int tamaño = codigoProducto.getText().length();
            if(tamaño == 4)
                precioProducto.requestFocus();
    }//GEN-LAST:event_codigoProductoKeyTyped

    private void precioProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precioProductoKeyTyped
        char c = evt.getKeyChar();
        if(c < '0' || c > '9'){
            evt.consume();
        }
        int tamaño = precioProducto.getText().length();
            if(tamaño == 5)
                descripcionProducto.requestFocus();
    }//GEN-LAST:event_precioProductoKeyTyped

    private void ivaProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ivaProductoKeyTyped
        char c = evt.getKeyChar();
        if(c < '0' || c > '9'){
            evt.consume();
        }
        int tamaño = ivaProducto.getText().length();
            if(tamaño == 1)
                inicioProducto.requestFocus();
    }//GEN-LAST:event_ivaProductoKeyTyped

    private void cantidadProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadProductoKeyTyped
        char c = evt.getKeyChar();
        if(c < '0' || c > '9'){
            evt.consume();
        }
        int tamaño = cantidadProducto.getText().length();
            if(tamaño == 2)
                promocionProducto.requestFocus();
    }//GEN-LAST:event_cantidadProductoKeyTyped

    private void promocionProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_promocionProductoKeyTyped

    }//GEN-LAST:event_promocionProductoKeyTyped

    private void inicioProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inicioProductoKeyTyped
        char c = evt.getKeyChar();
        if(c < '0' || c > '9'){
            evt.consume();
        }
        int tamaño = inicioProducto.getText().length();
            if(tamaño == 2 || tamaño == 5){
            inicioProducto.setText(inicioProducto.getText()+"/");
        }
        
        if(tamaño == 9){
            finProducto.requestFocus();
        }
    }//GEN-LAST:event_inicioProductoKeyTyped

    private void finProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_finProductoKeyTyped
        char c = evt.getKeyChar();
        if(c < '0' || c > '9'){
            evt.consume();
        }
        int tamaño = finProducto.getText().length();
            if(tamaño == 2 || tamaño == 5){
            finProducto.setText(finProducto.getText()+"/");
        }
        
        if(tamaño == 9){
            cantidadProducto.requestFocus();
        }
    }//GEN-LAST:event_finProductoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PROMOCION;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JTextField cantidadProducto;
    private javax.swing.JTextField codigoProducto;
    private javax.swing.JTextField descripcionProducto;
    private javax.swing.JTextField finProducto;
    private javax.swing.JTextField inicioProducto;
    private javax.swing.JTextField ivaProducto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField precioProducto;
    private javax.swing.JTextField promocionProducto;
    private javax.swing.JTable tablaProducto;
    // End of variables declaration//GEN-END:variables
}
