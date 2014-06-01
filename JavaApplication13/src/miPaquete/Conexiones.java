
package miPaquete;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Conexiones {
    int folio=0;
    
    private static Connection conect;
      
      public boolean conectar(String usua, String pass){
          System.out.println("intentando con: "+usua+" y "+pass);
        try{
            String connectionUrl;
            connectionUrl = "jdbc:sqlserver://localhost;"
                      + "databaseName=FACTURA;user="+usua+";password="+pass+";";
            conect = DriverManager.getConnection(connectionUrl);
            System.out.println("Conectado.");
            return true;
        }catch (SQLException ex){
            System.out.println("Error en conectar");
        }
        return false;
      }
      
      
      public Connection conecta2(){
            return conect;
      }
      //eliminar
      public ResultSet generar(String SQL){
          try {  
            Statement estado = conect.createStatement();
            ResultSet resultado;
            resultado = estado.executeQuery(SQL);
            return resultado;
            }
            catch (SQLException e) {
                System.out.println("Error en generar");
               
            }
        return null;
      }
      
      
      public ResultSet Consultar(int id, String SQL){
          try {  
            Statement estado = conect.createStatement();
            ResultSet resultado;
           // System.out.println("INTENTANDO CON: "+SQL);
            resultado = estado.executeQuery(SQL);
            return resultado;
            }
            catch (SQLException e) {
                System.out.println("Error en Consultar");
                //JOptionPane.showMessageDialog(null, "NO EXISTE ESE ID");
            }
        return null;
      }
      
     
      public void Insertar(String SQL){         
        try {
            Statement stmt = conect.createStatement();
            stmt.executeUpdate(SQL);
            System.out.println("Datos Insertados :"+SQL);
        }catch (SQLException e){
            System.out.println("Error en Insertar");
        }
      }
      
      public void Eliminar(int id, String SQL){         
        try {
            Statement stmt = conect.createStatement();
            stmt.executeUpdate(SQL+id);
            System.out.println("Eliminacion exitosa ID ="+id);
        }catch (SQLException e){
            System.out.println("Error en Eliminar");
            JOptionPane.showMessageDialog(null, "NO EXISTE ESE ID");
        }
      }
      
      
      public int Aleatorio(String sql, String s) throws SQLException{ //300 a 399
          System.out.println("ente al randowm");
          Random rnd = new Random();
          int r;
          int id=0;
          
          Statement estado = conect.createStatement();
          ResultSet resultado;
          resultado = estado.executeQuery(sql);
          
          while(resultado.next() || id == 0){
              do{
              id = rnd.nextInt(400);
              }while(id<300);
          }
          return id;
      }
      
      public int Folio(String sql, String s) throws SQLException{ //300 a 399
          Statement estado = conect.createStatement();
          ResultSet resultado;
          resultado = estado.executeQuery(sql);
          
          while(resultado.next() ){
              folio++;
          }
          return folio;
      }
      
      
      
      
      public void Timbre(){
          try {       
            Statement estado = conect.createStatement();
            ResultSet resultado;
            resultado = estado.executeQuery("SELECT * FROM persona");
 
            while (resultado.next()) {
                System.out.println(
                                resultado.getString("NOMBRE") 
                       + "\t" + resultado.getString("APELLIDO_MATERNO")
                       + "\t" + resultado.getString("APELLIDO_PATERNO")
                       + "\t" + resultado.getString("RAZON_SOCIAL")
                       + "\t" + resultado.getString("RDF")
                       + "\t" + resultado.getString("REGIMEN_FISCAL")
                       + "\t" + resultado.getString("ID_PERSONA")
                       + "\t" + resultado.getString("TIPO_PERSONA")+"\n");
            }
            resultado.close();
            estado.close();
            }
            catch (SQLException e) {
                System.out.println("Error en Consultar");
            }
      }
}
