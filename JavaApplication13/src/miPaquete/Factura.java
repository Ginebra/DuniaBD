/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package miPaquete;

import java.awt.HeadlessException;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Factura extends javax.swing.JInternalFrame {
    Conexiones con = new Conexiones();  
    ResultSet r;
    DefaultTableModel m ;
    String SQL, fila[]=new String[17],s, importe,
           codigo, cant,precio, iva, descripcion,descuento;
    int c=1, idBuscar, band=1,idpago,canti=0, flag=0;
    double calcula=0, x=0, total, descue, ivas, subtotal, sub, sub1;

    public Factura() {
        initComponents();
        jPanel1.setVisible(false); 
    }
    
    public void limpiar(){
        buscarIdFactura.setText("");
        nombreFactura.setText("");
        apellidoFactura.setText("");
        rfcFactura.setText("");
        direccionFactura.setText("");
        fechaFact.setText("");
        nofactura.setText("");
        buscarIdFactura.requestFocus();
    }
    public void buscarUsuario() {
        apellidoFactura.setEditable(false);
        nombreFactura.setEditable(false);
        rfcFactura.setEditable(false);
     try{
        int id_buscar=Integer.parseInt(buscarIdFactura.getText());
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
                + "WHERE PERSONA.ID_PERSONA = "+id_buscar+"";
        r = con.Consultar(id_buscar,SQL);
        
        while(r.next()){
                nombreFactura.setText(r.getString("NOMBRE"));
                apellidoFactura.setText(r.getString("APELLIDO_PATERNO"));
                rfcFactura.setText(r.getString("RFC"));
                direccionFactura.setText(r.getString("RFC"));
           }
        }catch(SQLException e){
              System.out.println("error");
        }
    }
    
   public void buscarFolio(){
        int folio = Integer.parseInt( JOptionPane.showInputDialog(
        null,"Introduzca el No.Factura",
        "BUSCADOR",JOptionPane.QUESTION_MESSAGE) );
        
        try{
           SQL= "select * from ENCABEZADO_FACTURA where FOLIO="+folio;
           r= con.Consultar(folio,SQL);
           
           while(r.next()){
               jPanel1.setVisible(true);
               fechaFact.setText(r.getString("FECHA"));
                nofactura.setText(r.getString("FOLIO"));
                JOptionPane.showMessageDialog(null, "Folio encontrado");
               }
        }
        catch(Exception e){
              JOptionPane.showMessageDialog(null, "Folio NO encontrado");
            }
        
   }
   
   public void Insertar(String codigo, String cant){
      if(flag==0){
       SQL = "SELECT ID_PAGO FROM FORMA_PAGO";
        s = "ID_PAGO";
     try {
         idpago = con.Aleatorio(SQL,s);
     } catch (SQLException ex) {
         Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
     }
    
        SQL = "INSERT FORMA_PAGO VALUES "
                + "('"+idpago+"',"
                + "'"+formapagoFact.getSelectedItem()+"')";
        con.Insertar(SQL);
     }flag=1;
       System.out.println("codigo"+codigo+"Cant:"+cant+"idpago:"+idpago+"no:"+nofactura.getText()+"id"+buscarIdFactura.getText());
         SQL = " INSERT FACTURA VALUES "
                + "('"+buscarIdFactura.getText()+"',"
                + "'"+idpago+"'," 
                + "'"+nofactura.getText()+"',"
                + "'"+codigo+"',"
                + "'"+cant+"')";
     
        con.Insertar(SQL);
   
   }
    public void eliminarFactura(){
        int folioFact = Integer.parseInt( JOptionPane.showInputDialog(
        null,"Introduzca el No.Factura",
        "BUSCADOR",JOptionPane.QUESTION_MESSAGE) );
        try{
           SQL = "delete from ENCABEZADO_FACTURA WHERE FOLIO=";
           con.Eliminar(folioFact,SQL);
           
               JOptionPane.showMessageDialog(null, "Factura eliminada");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Folio NO encontrado");
            
            }
        limpiar();
   }
         
    public void seleccionarProducto(){
        int filaselect=tablaproducto.getSelectedRow();
     try{
         if(filaselect==-1){
            JOptionPane.showMessageDialog(null, "Seleccione un producto");
         }else{
             m=(DefaultTableModel)tablaproducto.getModel();
             codigo=tablaproducto.getValueAt(filaselect,0).toString();
             precio=tablaproducto.getValueAt(filaselect,1).toString();
             descripcion=tablaproducto.getValueAt(filaselect,2).toString();
             cant=cantProduct.getText();
             descuento=tablaproducto.getValueAt(filaselect,6).toString();
             iva=tablaproducto.getValueAt(filaselect,3).toString();
         //operaciones
             x=(Integer.parseInt(cant)*Double.parseDouble(precio));
             descue=((Double.parseDouble(descuento)*x))/100;
             ivas=(Double.parseDouble(iva)*(x))/100;
             sub=(x-descue)+ivas;
             importe=Double.toString(sub);               

             m=(DefaultTableModel)tablafact.getModel();
             String filaelemen[]={codigo, descripcion, precio,cant, iva, descuento,importe};
             m.addRow(filaelemen);  
             
             calcula=(Double.parseDouble(importe));
             total=total+calcula;
             
             subtotal=subtotal+x;
             totalFactura.setText("$"+total);
             subtotalFactura.setText("$"+subtotal);
             Insertar(codigo,cant);
           }
        }catch(Exception e ){
            }
    }
    
    public void tablaProducto(){
      try{        
           String titulos[]={"CODIGO", "PRECIO","DESCRIPCION","IVA%",
               "FECHA-INICIO","FECHA-FIN","DESCUENTO%", "PROMOCION"};
            m=new DefaultTableModel(null, titulos);
            
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
           tablaproducto.setModel(m);
        }
   
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }  
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaproducto = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        cantProduct = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        asf = new javax.swing.JLabel();
        nombreFactura = new javax.swing.JTextField();
        apellidoFactura = new javax.swing.JTextField();
        sd = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rfcFactura = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        buscarIdFactura = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        direccionFactura = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        fechaFact = new javax.swing.JTextField();
        nofactura = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        formapagoFact = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablafact = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        totalFactura = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        subtotalFactura = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();

        tablaproducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tablaproducto);

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("CATALOGO DE PRODUCTOS");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel6.setOpaque(true);

        jButton5.setText("Agregar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel7.setText("Cantidad:");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cantProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5)
                                .addGap(254, 254, 254))))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cantProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setPreferredSize(new java.awt.Dimension(950, 550));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Generales"));

        asf.setText("Nombre:");

        nombreFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreFacturaActionPerformed(evt);
            }
        });

        apellidoFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidoFacturaActionPerformed(evt);
            }
        });

        sd.setText("Apellido:");

        jLabel2.setText("RFC");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/firma-pgp-solicitud-icono-8529-32.png"))); // NOI18N
        jLabel1.setText("ID");

        buscarIdFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarIdFacturaActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/Male-user-search-icon.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel11.setText("Direccion:");

        jLabel12.setText("Fecha:");

        nofactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nofacturaActionPerformed(evt);
            }
        });

        jLabel13.setText("No. Factura:");

        formapagoFact.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CREDITO", "DEBITO", "CONTADO" }));

        jLabel10.setText("Formas de Pago:");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/Master-Card-icon.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/Visa-icon.png"))); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/Paypal-icon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buscarIdFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(asf))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(rfcFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nombreFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(sd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(apellidoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)
                                        .addComponent(nofactura, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(18, 18, 18)
                                        .addComponent(fechaFact, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(direccionFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(formapagoFact, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(asf)
                    .addComponent(nombreFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(buscarIdFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sd)
                    .addComponent(apellidoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel10)
                    .addComponent(formapagoFact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(rfcFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(fechaFact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(direccionFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(nofactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(10, 10, 10))
        );

        tablafact.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "$ PRECIO", "CANT", "IVA%", "DESCUENTO%", "TOTAL="
            }
        ));
        jScrollPane1.setViewportView(tablafact);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/Shopping-cart-add-icon.png"))); // NOI18N
        jButton8.setText("Agregar");
        jButton8.setPreferredSize(new java.awt.Dimension(111, 41));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/Shopping-cart-remove-icon .png"))); // NOI18N
        jButton9.setText("Eliminar");
        jButton9.setPreferredSize(new java.awt.Dimension(111, 41));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/Shopping-cart-alert-icon.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/Download-icon.png"))); // NOI18N
        jButton10.setText("Guardar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel15.setText("Total:");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/US-dollar-icon.png"))); // NOI18N

        jLabel16.setText("Subtotal:");

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("LISTADO DE PRECIOS");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel5.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel16)
                                .addGap(29, 29, 29)
                                .addComponent(subtotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(98, 98, 98)
                                .addComponent(jLabel15)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(totalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)))
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(totalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel15)
                                        .addComponent(subtotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel16)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(17, 17, 17))
        );

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/data-apply-icon (2).png"))); // NOI18N
        jButton4.setText("Generar Folio");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/table-add-icon.png"))); // NOI18N
        jButton6.setText("Nueva Factura");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/table-remove-icon.png"))); // NOI18N
        jButton7.setText("EliminarFactura");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/table-redo-icon.png"))); // NOI18N
        jButton3.setText("Consulta Factura");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton3))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/Users-icon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 837, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14))
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombreFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreFacturaActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreFacturaActionPerformed

    private void apellidoFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidoFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellidoFacturaActionPerformed

    private void buscarIdFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarIdFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarIdFacturaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       buscarUsuario();
    }//GEN-LAST:event_jButton1ActionPerformed

    @SuppressWarnings("empty-statement")
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    limpiar();
     totalFactura.setText("");
     subtotalFactura.setText("");
     DefaultTableModel modelo=(DefaultTableModel)   tablafact.getModel();
            int filas=tablafact.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
     jDialog1.setSize(800,300);
     jDialog1.setVisible(true);
     tablaProducto();
     
     
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
     
        try{
            int fsel=tablafact.getSelectedRow();
        
     System.out.println(fsel);
     if(fsel==-1){
         JOptionPane.showConfirmDialog(null, "Seleccione producto a eliminar");
     }else{
          descuento=tablaproducto.getValueAt(fsel,6).toString();
          iva=tablaproducto.getValueAt(fsel,3).toString();
           String calcul=tablafact.getValueAt(fsel,6).toString();
           String pre=tablafact.getValueAt(fsel,2).toString();
           System.out.println("descuento="+descuento+"calcula="+calcul+"iva:"+iva);
           System.out.println("total:"+total); 
           
             descue=((Double.parseDouble(calcul)*Double.parseDouble(descuento))/100);
             ivas=(Double.parseDouble(iva)*(Double.parseDouble(calcul))/100);
             sub=subtotal-Double.parseDouble(pre);
             sub1=total-((Double.parseDouble(precio)-descue)+ivas);
            
             System.out.println("descue:"+descue+"ivas:"+ivas);
             System.out.println("sub:"+sub+"total:"+sub1);
           
             totalFactura.setText("$"+sub1);
             subtotalFactura.setText("$"+sub);
             
             m=(DefaultTableModel)tablafact.getModel();
             m.removeRow(fsel);
         System.out.println("lelele");
     }
    }
    catch(Exception e){
         System.out.println("jskjsdf");
    }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      encabezado obj=new encabezado();
      obj.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
      jPanel1.setVisible(false);
      buscarFolio();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       jPanel1.setVisible(false);
       eliminarFactura();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      jPanel1.setVisible(false);
       buscarFolio();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        seleccionarProducto();
        cantProduct.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void nofacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nofacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nofacturaActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
       ///LLENA EL LA TABLAFORMADE PAGO
       
     flag=0;
    }//GEN-LAST:event_jButton10ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidoFactura;
    private javax.swing.JLabel asf;
    private javax.swing.JTextField buscarIdFactura;
    private javax.swing.JTextField cantProduct;
    private javax.swing.JTextField direccionFactura;
    public javax.swing.JTextField fechaFact;
    private javax.swing.JComboBox formapagoFact;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JTextField nofactura;
    private javax.swing.JTextField nombreFactura;
    private javax.swing.JTextField rfcFactura;
    private javax.swing.JLabel sd;
    private javax.swing.JTextField subtotalFactura;
    public javax.swing.JTable tablafact;
    public javax.swing.JTable tablaproducto;
    private javax.swing.JTextField totalFactura;
    // End of variables declaration//GEN-END:variables
}
