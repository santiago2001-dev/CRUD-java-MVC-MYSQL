
package model;


import java.sql.Connection;
import java.sql.DriverManager;


public class conexion {
    //variable tipo conection 
    Connection con;
    String user = "root", password = "root", url = "jdbc:mysql://localhost:3308/crudmvc";
    public Connection getConenection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("conectado");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
    
}
