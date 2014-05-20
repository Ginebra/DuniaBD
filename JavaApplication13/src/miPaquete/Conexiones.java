package miPaquete;

import java.sql.*;
import java.util.*;

public class Conexiones {
    
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
      
      
      public Connection conectar2(){
          String usua = "sa";
          String pass = "123";
          System.out.println("intentando con: "+usua+" y "+pass);
        try{
            String connectionUrl;
            connectionUrl = "jdbc:sqlserver://localhost;"
                      + "databaseName=FACTURA;user="+usua+";password="+pass+";";
            conect = DriverManager.getConnection(connectionUrl);
            System.out.println("Conectado.");
            return conect;
        }catch (SQLException ex){
            System.out.println("Error en conectar");
        }
        return null;
      }
      
      
      public ResultSet ConsultarPersona(int id, String SQL){
          System.out.println("intentando buscar id "+id);
          try {  
            Statement estado = conect.createStatement();
            ResultSet resultado;
            resultado = estado.executeQuery(SQL+id);
            return resultado;
            /*while (resultado.next()) {
                System.out.println(
                                resultado.getString("NOMBRE") 
                       + "\t" + resultado.getString("APELLIDO_MATERNO")
                       + "\t" + resultado.getString("APELLIDO_PATERNO")
                       + "\t" + resultado.getString("RAZON_SOCIAL")
                       + "\t" + resultado.getString("RFC")
                       + "\t" + resultado.getString("REGIMEN_FISCAL")
                       + "\t" + resultado.getString("ID_PERSONA")
                       + "\t" + resultado.getString("TIPO_PERSONA")+"\n");
            }
            
            resultado.close();
            estado.close();
            */
            }
            catch (SQLException e) {
                System.out.println("Error en Consultar");
            }
        return null;
      }
      
      public void Modifica() throws SQLException{
      String SQL = "UPDATE PERSONA set nombre = 'NEKO' where ID_PERSONA = 333";
            
        try {
            Statement stmt = conect.createStatement();
            stmt.executeUpdate(SQL);
            System.out.println("Modificacion exitosa");
        }catch (SQLException e){
            System.out.println("Error en Modificar");
        }
      }
      
      public void Insertar(String SQL){
      //String SQL = "INSERT PERSONA VALUES ('JJJD','sdfg','sdffa','rz','rdf','regimen',9,1)" ;
            
        try {
            Statement stmt = conect.createStatement();
            stmt.executeUpdate(SQL);
            System.out.println("Modificacion exitosa");
        }catch (SQLException e){
            System.out.println("Error en Insertar");
        }
      }
      
      public int Aleatorio(){ //300 a 399
          Random rnd = new Random();
          int r, id;
          
          do{
          id = rnd.nextInt(400);
          r = (id >= 300 && id < 400) ? 1 : 2;  //False/True .. ??? 
          }while(r != 1);
          return id;
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
