/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package miPaquete;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Persona extends javax.swing.JInternalFrame {

    //public PopupImprovisado pop = new PopupImprovisado();
    public String identificador;
    public int flag = 0;
    Conexiones con = new Conexiones();
    Connection conect;
    public int idpersona, idtelefono, idpagina, idlogotipo, iddomicilio;
    public int idpago, folio, codigo, cantidad;
    
    public Persona() {
        initComponents();
        inhabilitar();
        btnActualizar.setVisible(false);
        conect = con.conecta2();
    }
    
    void limpiar(){
        nombrePersona.setText("");
        estadoPersona.setText("");
        coloniaPersona.setText("");
        paternoPersona.setText("");
        ciudadPersona.setText("");
        numeroPersona.setText("");
        postalPersona.setText("");
        maternoPersona.setText("");
        callePersona.setText("");
        externaPersona.setText("");
        interiorPersona.setText("");
        dominioPersona.setText("");
        localizacionPersona.setText("");
        regimenPersona.setText("");
        rsocialPersona.setText("");
        rfcPersona.setText("");
    }
    
    void inhabilitar(){
        nombrePersona.setEditable(false);
        nombrePersona.setText("");
        estadoPersona.setEditable(false);
        estadoPersona.setText("");
        coloniaPersona.setEditable(false);
        coloniaPersona.setText("");
        paternoPersona.setEditable(false); 
        paternoPersona.setText("");
        ciudadPersona.setEditable(false); 
        ciudadPersona.setText("");
        numeroPersona.setEditable(false);
        numeroPersona.setText("");
        postalPersona.setEditable(false);
        postalPersona.setText("");
        maternoPersona.setEditable(false); 
        maternoPersona.setText("");
        callePersona.setEditable(false);
        callePersona.setText("");
        externaPersona.setEditable(false); 
        externaPersona.setText("");
        interiorPersona.setEditable(false);
        interiorPersona.setText("");
        tipoPersona.setEditable(false); 
        descripcionPersona.setEditable(false); 
        dominioPersona.setEditable(false); 
        dominioPersona.setText("");
        localizacionPersona.setEditable(false);
        localizacionPersona.setText("");
        regimenPersona.setEditable(false); 
        regimenPersona.setText("");
        rsocialPersona.setEditable(false); 
        rsocialPersona.setText("");
        rfcPersona.setEditable(false);
        rfcPersona.setText("");
    }
    
    void habilitar(){
        nombrePersona.setEditable(true);
        estadoPersona.setEditable(true);
        coloniaPersona.setEditable(true);
        paternoPersona.setEditable(true);
        ciudadPersona.setEditable(true);
        numeroPersona.setEditable(true);
        postalPersona.setEditable(true);
        maternoPersona.setEditable(true);
        callePersona.setEditable(true);
        externaPersona.setEditable(true);
        interiorPersona.setEditable(true);
        tipoPersona.setEditable(true);
        descripcionPersona.setEditable(true);
        dominioPersona.setEditable(true);
        localizacionPersona.setEditable(true);
        regimenPersona.setEditable(true);
        rsocialPersona.setEditable(true);
        rfcPersona.setEditable(true);
    }
       
   
    public void insertar() throws SQLException{
        if(nombrePersona.getText().equals("") || 
            paternoPersona.getText().equals("") ||
            maternoPersona.getText().equals("") ||
            estadoPersona.getText().equals("") ||
            ciudadPersona.getText().equals("") ||
            callePersona.getText().equals("") ||
            coloniaPersona.getText().equals("") ||
            interiorPersona.getText().equals("") ||
            externaPersona.getText().equals("") ||
            postalPersona.getText().equals("") ||
            numeroPersona.getText().equals("") ||
            dominioPersona.getText().equals("") ||
            localizacionPersona.getText().equals("") ||
            regimenPersona.getText().equals("") ||
            rsocialPersona.getText().equals("") ||
            rfcPersona.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "FALTA LLENAR DATOS");   
        }
        else{

        String SQL;
        String Query,s;
        
        Query = "SELECT ID_PERSONA FROM PERSONA";
        s = "ID_PERSONA";
        idpersona = con.Aleatorio(Query, s);
        System.out.println("El id de persona : "+idpersona);
        
        Query = "SELECT ID_TELEFONO FROM TELEFONO";
        s = "ID_TELEFONO";
        idtelefono = con.Aleatorio(Query, s);
        System.out.println("El id de telefono : "+idtelefono);
        
        Query = "SELECT ID_PAGINA FROM PAGINA_WEB";
        s = "ID_PAGINA";
        idpagina = con.Aleatorio(Query, s);
        System.out.println("El id de persona : "+idpagina);
        
        Query = "SELECT ID_LOGOTIPO FROM LOGOTIPO";
        s = "ID_LOGOTIPO";
        idlogotipo = con.Aleatorio(Query, s);
        System.out.println("El id de LOGOTIPO : "+idlogotipo);
        
        Query = "SELECT ID_DOMICILIO FROM DOMICILIO";
        s = "ID_DOMICILIO";
        iddomicilio = con.Aleatorio(Query, s);
        System.out.println("El id de domicilio : "+iddomicilio);
        
        ////Llenar PERSONA
        int tipopersona = (tipoPersona.getSelectedItem() == "moral") ? 1 : 0;
        SQL = "INSERT PERSONA VALUES "
                + "('"+nombrePersona.getText()+"',"
                + "'"+maternoPersona.getText()+"',"
                + "'"+paternoPersona.getText()+"',"
                + "'"+rsocialPersona.getText()+"',"
                + "'"+rfcPersona.getText()+"',"
                + "'"+regimenPersona.getText()+"',"
                + ""+idpersona+","
                + ""+tipopersona+")";
        con.Insertar(SQL);
        
        ////Llenar DOMICILIO
        SQL = "INSERT DOMICILIO VALUES "
                + "("+iddomicilio+","
                + "'"+ciudadPersona.getText()+"',"
                + "'"+coloniaPersona.getText()+"',"
                + "'"+callePersona.getText()+"',"
                + ""+externaPersona.getText()+","
                + ""+interiorPersona.getText()+","
                + ""+postalPersona.getText()+","
                + "'"+estadoPersona.getText()+"')";
        con.Insertar(SQL);
        
        //Llenar TELEFONO
        SQL = "INSERT TELEFONO VALUES "
                + "("+idtelefono+","
                + ""+numeroPersona.getText()+","
                + "'"+descripcionPersona.getSelectedItem()+"')";       
        con.Insertar(SQL);
        
        //Llenar Pagina WEB
        SQL = "INSERT PAGINA_WEB VALUES "
                + "("+idpagina+","
                + "'"+dominioPersona.getText()+"')";
        con.Insertar(SQL);
        
        //Llenar Logotipo
        SQL = "INSERT LOGOTIPO VALUES "
                + "("+idlogotipo+","
                + "'"+localizacionPersona.getText()+"')";
        con.Insertar(SQL);
        
        ////Llenar PERSONA_LOGOTIPO
        SQL = "INSERT PERSONA_LOGOTIPO VALUES "
                + "("+idlogotipo+","
                + ""+idpersona+")";
        con.Insertar(SQL);
        
        ////Llenar PERSONA_PAGINA
        SQL = "INSERT PERSONA_PAGINA VALUES "
                + "("+idpagina+","
                + ""+idpersona+")";
        con.Insertar(SQL);
        
        ////Llenar PERSONA_TELEFONO
        SQL = "INSERT PERSONA_TELEFONO VALUES "
                + "("+idtelefono+","
                + ""+idpersona+")";
        con.Insertar(SQL);
        
        ////Llenar PERSONA_DOMICILIO
        SQL = "INSERT PERSONA_DOMICILIO VALUES "
                + "("+idpersona+","
                + ""+iddomicilio+")";
        con.Insertar(SQL);
        
        idPersona.setText(Integer.toString(idpersona));
        JOptionPane.showMessageDialog(this, "Tu ID de USUARIO es: "
                + ""+idPersona.getText());
        
        limpiar();
      }
    }
    
    public void buscar () throws SQLException{
        int found = 0 ;
        int idbuscar = Integer.parseInt( JOptionPane.showInputDialog(
        null,"Introduzca el ID a buscar",
        "BUSCADOR",
        JOptionPane.QUESTION_MESSAGE) );
        
        try{
        ResultSet resultado;
        String SQL;
                
        //SQL = "SELECT * FROM PERSONA INNER JOIN PERSONA_DOMICILIO ON PERSONA_DOMICILIO.ID_PERSONA = PERSONA.ID_PERSONA INNER JOIN DOMICILIO ON PERSONA_DOMICILIO.ID_DOMICILIO = DOMICILIO.ID_DOMICILIO INNER JOIN PERSONA_TELEFONO ON PERSONA_TELEFONO.ID_PERSONA = PERSONA.ID_PERSONA INNER JOIN TELEFONO ON PERSONA_TELEFONO.ID_TELEFONO = TELEFONO.ID_TELEFONO INNER JOIN PERSONA_PAGINA ON PERSONA_PAGINA.ID_PERSONA = PERSONA.ID_PERSONA INNER JOIN PAGINA_WEB ON PERSONA_PAGINA.ID_PAGINA = PAGINA_WEB.ID_PAGINA INNER JOIN PERSONA_LOGOTIPO ON PERSONA_LOGOTIPO.ID_PERSONA = PERSONA.ID_PERSONA INNER JOIN LOGOTIPO ON PERSONA_LOGOTIPO.ID_LOGOTIPO = LOGOTIPO.ID_LOGOTIPO WHERE PERSONA.ID_PERSONA = "+idbuscar+"";
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
                + "WHERE PERSONA.ID_PERSONA = "+idbuscar+"";
        
        resultado = con.Consultar(idbuscar,SQL);
        
         while (resultado.next()) {
             //Persona
             nombrePersona.setText(resultado.getString("NOMBRE") );
             maternoPersona.setText(resultado.getString("APELLIDO_MATERNO"));
             paternoPersona.setText(resultado.getString("APELLIDO_PATERNO"));
             rsocialPersona.setText(resultado.getString("RAZON_SOCIAL"));
             rfcPersona.setText(resultado.getString("RFC"));
             regimenPersona.setText(resultado.getString("REGIMEN_FISCAL"));
             idPersona.setText(resultado.getString("ID_PERSONA"));
             tipoPersona.setSelectedItem(resultado.getString("TIPO_PERSONA"));
             
             //Telefono
             numeroPersona.setText(resultado.getString("NUMERO") );
             descripcionPersona.setSelectedItem(resultado.getString("DESCRIPCION"));
             
             //Pagina Web
             dominioPersona.setText(resultado.getString("DOMINIO") );
             
             //Logotipo
             localizacionPersona.setText(resultado.getString("LOCALIZACION") );
             
             //Domicilio
             ciudadPersona.setText(resultado.getString("CIUDAD"));               
             coloniaPersona.setText(resultado.getString("COLONIA"));
             callePersona.setText(resultado.getString("CALLE"));
             externaPersona.setText(resultado.getString("NUMERO_EXT"));
             interiorPersona.setText(resultado.getString("NUMERO_INT"));
             postalPersona.setText(resultado.getString("CP"));
             estadoPersona.setText(resultado.getString("ESTADO"));
             
             //ASIGNACION DE ID's globales
             idpersona = Integer.parseInt(resultado.getString("ID_PERSONA"));
             idtelefono = Integer.parseInt(resultado.getString("ID_TELEFONO"));
             idpagina = Integer.parseInt(resultado.getString("ID_PAGINA"));
             idlogotipo = Integer.parseInt(resultado.getString("ID_LOGOTIPO"));
             iddomicilio = Integer.parseInt(resultado.getString("ID_DOMICILIO"));
             found = 1;
         }
         
         if(found == 0){
             JOptionPane.showMessageDialog(null, "NO EXISTE ID..!!!");
         }
         else{
            flag = 1;
            btnActualizar.setVisible(true);
            btnActualizar.setText("MODIFICAR");
            habilitar();
         }
        
        resultado.close();
        }
        catch (SQLException e) {
            System.out.println("Error en Consultar");
            JOptionPane.showMessageDialog(null, "No hay datos");
        }
    }
    
    public void modificar (){
         Conexiones mod = new Conexiones();
        int tipo = (tipoPersona.getSelectedItem() == "moral") ? 1 : 0;
        
        String SQL;
        SQL = "UPDATE PERSONA set "
                + "NOMBRE = '"+nombrePersona.getText()+"', "
                + "APELLIDO_MATERNO = '"+maternoPersona.getText()+"', "
                + "APELLIDO_PATERNO = '"+paternoPersona.getText()+"',"
                + "RAZON_SOCIAL = '"+rsocialPersona.getText()+"', "
                + "RFC = '"+rfcPersona.getText()+"', "
                + "REGIMEN_FISCAL = '"+regimenPersona.getText()+"', "
                + "TIPO_PERSONA = "+tipo+" "
                + "where ID_PERSONA = "+idpersona+"";
        mod.Insertar(SQL);
        SQL = "UPDATE TELEFONO SET "
                + "NUMERO = "+numeroPersona.getText()+", "
                + "DESCRIPCION = '"+descripcionPersona.getSelectedItem()+"' "
                + "WHERE ID_TELEFONO = "+idtelefono+"";
        mod.Insertar(SQL);
        SQL = "UPDATE PAGINA_WEB SET "
                + "DOMINIO = '"+dominioPersona.getText()+"' "
                + "WHERE ID_PAGINA = "+idpagina+"";
        mod.Insertar(SQL);
        SQL = "UPDATE DOMICILIO SET "
                + "CIUDAD = '"+ciudadPersona.getText()+"', "
                + "COLONIA = '"+coloniaPersona.getText()+"', "
                + "CALLE = '"+callePersona.getText()+"', "
                + "NUMERO_EXT = "+externaPersona.getText()+", "
                + "NUMERO_INT = "+interiorPersona.getText()+", "
                + "CP = "+postalPersona.getText()+", "
                + "ESTADO = '"+estadoPersona.getText()+"' "
                + "WHERE ID_DOMICILIO = "+iddomicilio+"";
        mod.Insertar(SQL);
        JOptionPane.showMessageDialog(null, "Datos modificados Exitosamente");
    }
    
    
    public void eliminar(){
        int found=0;
        int ideliminar = Integer.parseInt( JOptionPane.showInputDialog(
        null,"Introduzca el ID a ELIMINAR",
        "BUSCADOR",
        JOptionPane.QUESTION_MESSAGE) );
        
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
                + "WHERE PERSONA.ID_PERSONA = "+ideliminar+"";
        resultado = con.Consultar(ideliminar,SQL);
        
         while (resultado.next()){
             //ASIGNACION DE ID's
             idpersona = Integer.parseInt(resultado.getString("ID_PERSONA"));
             idtelefono = Integer.parseInt(resultado.getString("ID_TELEFONO"));
             idpagina = Integer.parseInt(resultado.getString("ID_PAGINA"));
             idlogotipo = Integer.parseInt(resultado.getString("ID_LOGOTIPO"));
             iddomicilio = Integer.parseInt(resultado.getString("ID_DOMICILIO"));
             found = 1;
         }    
         
         resultado.close();
        }
        catch (SQLException e) {
            System.out.println("Error en Consultar para Eliminar");
            JOptionPane.showMessageDialog(null, "No hay datos");
        }
        
        if(found == 0){
            JOptionPane.showMessageDialog(null, "NO EXISTE ID..!!!");
        }
        else{
        String SQL;
        
        SQL = "DELETE PERSONA_DOMICILIO WHERE ID_PERSONA =";
        con.Eliminar(idpersona,SQL);
        SQL = "DELETE PERSONA_LOGOTIPO WHERE ID_PERSONA =";
        con.Eliminar(idpersona,SQL);
        SQL = "DELETE PERSONA_PAGINA WHERE ID_PERSONA =";
        con.Eliminar(idpersona,SQL);
        SQL = "DELETE PERSONA_TELEFONO WHERE ID_PERSONA =";
        con.Eliminar(idpersona,SQL);
        
        SQL = "DELETE FACTURA WHERE ID_PERSONA =";
        con.Eliminar(idpersona,SQL);
        
        SQL = "DELETE PERSONA WHERE ID_PERSONA =";
        con.Eliminar(idpersona,SQL);
        SQL = "DELETE TELEFONO WHERE ID_TELEFONO = ";
        con.Eliminar(idtelefono,SQL);
        SQL = "DELETE PAGINA_WEB WHERE ID_PAGINA = ";
        con.Eliminar(idpagina,SQL);
        SQL = "DELETE LOGOTIPO WHERE ID_LOGOTIPO = ";
        con.Eliminar(idlogotipo,SQL);
        SQL = "DELETE DOMICILIO WHERE ID_DOMICILIO = ";
        con.Eliminar(iddomicilio,SQL);   
        
        
        JOptionPane.showMessageDialog(null, "Eliminacion Exitosa");
        limpiar();
        }
       }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel22 = new javax.swing.JLabel();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        nombrePersona = new javax.swing.JTextField();
        paternoPersona = new javax.swing.JTextField();
        maternoPersona = new javax.swing.JTextField();
        estadoPersona = new javax.swing.JTextField();
        ciudadPersona = new javax.swing.JTextField();
        coloniaPersona = new javax.swing.JTextField();
        interiorPersona = new javax.swing.JTextField();
        externaPersona = new javax.swing.JTextField();
        postalPersona = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        callePersona = new javax.swing.JTextField();
        tipoPersona = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        idPersona = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        numeroPersona = new javax.swing.JTextField();
        dominioPersona = new javax.swing.JTextField();
        localizacionPersona = new javax.swing.JTextField();
        descripcionPersona = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        regimenPersona = new javax.swing.JTextField();
        rsocialPersona = new javax.swing.JTextField();
        rfcPersona = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        jLabel22.setText("jLabel22");

        setPreferredSize(new java.awt.Dimension(950, 550));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel19.setText("NUMERO EXT");

        jLabel20.setText("CODIGO POSTAL");

        interiorPersona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                interiorPersonaKeyTyped(evt);
            }
        });

        externaPersona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                externaPersonaKeyTyped(evt);
            }
        });

        postalPersona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                postalPersonaKeyTyped(evt);
            }
        });

        jLabel14.setText("ESTADO");

        jLabel15.setText("CIUDAD");

        jLabel16.setText("COLONIA");

        jLabel1.setText("NOMBRE");

        jLabel2.setText("APELLIDO PATERNO");

        jLabel3.setText("APELLIDO MATERNO");

        jLabel7.setText("TIPO PERSONA");

        jLabel17.setText("CALLE");

        jLabel18.setText("NUMERO INT");

        tipoPersona.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "moral", "fisica" }));

        jLabel23.setText("ID_PERSONA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel16)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(estadoPersona)
                            .addComponent(coloniaPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombrePersona, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(idPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20)))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(postalPersona)
                    .addComponent(paternoPersona, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(ciudadPersona, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(interiorPersona, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(externaPersona, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(callePersona, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(maternoPersona, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tipoPersona, 0, 170, Short.MAX_VALUE))
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(paternoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(maternoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(ciudadPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(callePersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(interiorPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(externaPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(nombrePersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(estadoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(coloniaPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jLabel7)
                            .addComponent(postalPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tipoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(idPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        jLabel8.setText("DATOS GENERALES");

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel10.setText("LOCALIZACION(LOGO)");

        jLabel11.setText("NUMERO");

        jLabel12.setText("DESCRIPCION(TEL)");

        jLabel13.setText("DOMINIO");

        numeroPersona.setText("12345678910");
        numeroPersona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numeroPersonaKeyTyped(evt);
            }
        });

        descripcionPersona.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "casa", "oficina", "fax", "celular" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(numeroPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(27, 27, 27)
                        .addComponent(descripcionPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dominioPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(localizacionPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(numeroPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(descripcionPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(dominioPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(localizacionPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel9.setText("CONTACTO");

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setText("RAZON SOCIAL");

        jLabel5.setText("RFC");

        jLabel6.setText("REGIMEN FISCAL");

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/percent-icon.png"))); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(rsocialPersona, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addComponent(regimenPersona, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rfcPersona))
                .addGap(49, 49, 49)
                .addComponent(btnActualizar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnActualizar)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(regimenPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(rsocialPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(rfcPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel21.setText("DATOS FISCALES");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/new-icon.png"))); // NOI18N
        jButton1.setText("NUEVO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/search-icon.png"))); // NOI18N
        jButton2.setText("BUSCAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miPaquete/trash-icon.png"))); // NOI18N
        jButton3.setText("ELIMINAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jLabel21))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)))
                .addGap(33, 33, 33))
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(237, 237, 237)
                .addComponent(jButton3)
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel8)
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      habilitar();
      btnActualizar.setVisible(true);
      btnActualizar.setText("ACTUALIZAR");
      System.out.println(tipoPersona.getSelectedItem());
      limpiar();
      flag = 0;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            buscar();
        } catch (SQLException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        eliminar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        
        if(flag == 1){  
            modificar();
        }
        else{           
            System.out.println("LLAMANDO INSERTAR");
            try {
                insertar();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error, verifica tus datos");
            }
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void interiorPersonaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_interiorPersonaKeyTyped
        char c = evt.getKeyChar();
        if(c < '0' || c > '9'){
            evt.consume();
        }
    }//GEN-LAST:event_interiorPersonaKeyTyped

    private void externaPersonaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_externaPersonaKeyTyped
        char c = evt.getKeyChar();
        if(c < '0' || c > '9'){
            evt.consume();
        }
    }//GEN-LAST:event_externaPersonaKeyTyped

    private void postalPersonaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_postalPersonaKeyTyped
        char c = evt.getKeyChar();
        if(c < '0' || c > '9'){
            evt.consume();
        }
    }//GEN-LAST:event_postalPersonaKeyTyped

    private void numeroPersonaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numeroPersonaKeyTyped
        char c = evt.getKeyChar();
        if(c < '0' || c > '9'){
            evt.consume();
        }
    }//GEN-LAST:event_numeroPersonaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JTextField callePersona;
    private javax.swing.JTextField ciudadPersona;
    private javax.swing.JTextField coloniaPersona;
    private javax.swing.JComboBox descripcionPersona;
    private javax.swing.JTextField dominioPersona;
    private javax.swing.JTextField estadoPersona;
    private javax.swing.JTextField externaPersona;
    private javax.swing.JTextField idPersona;
    private javax.swing.JTextField interiorPersona;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
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
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField localizacionPersona;
    private javax.swing.JTextField maternoPersona;
    private javax.swing.JTextField nombrePersona;
    private javax.swing.JTextField numeroPersona;
    private javax.swing.JTextField paternoPersona;
    private javax.swing.JTextField postalPersona;
    private javax.swing.JTextField regimenPersona;
    private javax.swing.JTextField rfcPersona;
    private javax.swing.JTextField rsocialPersona;
    private javax.swing.JComboBox tipoPersona;
    // End of variables declaration//GEN-END:variables
}
