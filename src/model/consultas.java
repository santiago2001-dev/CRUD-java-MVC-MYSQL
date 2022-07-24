
package model;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Persona;


public class consultas {
    conexion conectar  = new conexion();
     Connection con;
     PreparedStatement ps;
     ResultSet rs;
     
     
  //traer todos los registros
     public ArrayList Listar(){
    
//    creo un arreglo de tipo persona el cual va a guardar todos mis resultados
     ArrayList<Persona>datos = new ArrayList<>();
    String query ="SELECT * FROM users";
    try{
        con = conectar.getConenection();
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();
        
        while(rs.next()){
    //instancio la clase persona para traer los atributos de esta clase y darles el valor con el metodo set
             
            Persona restults = new Persona();
            restults.setId(rs.getInt("ID"));
            restults.setName(rs.getString("nombre"));
            restults.setEmail(rs.getString("correo"));
            restults.setNumber(rs.getString("telefono"));
            
//           por ultimo agrego a mi arreglo tipo persona los atributos con el valor ya dato de la consulta
            datos.add(restults);
           
 
        }
        
    } catch(Exception e){
        e.printStackTrace();
    }
    
   return datos;
    
    }
     public int agregar(Persona P){
     String query ="INSERT INTO users(nombre,correo,telefono)values (?,?,?)";
     
         try {
             con = conectar.getConenection();
             ps = con.prepareStatement(query);
             
             ps.setString(1, P.getName());
             ps.setString(2, P.getEmail());
             ps.setString(3, P.getNumber());
             ps.executeUpdate();
             
         } catch (Exception e) {
               e.printStackTrace();
         }
     
     return 1;
     }
     
     
     
     public int editar(Persona P){
         
         String query = "UPDATE  users SET nombre = ? ,correo = ? , telefono = ? where ID = ?";
         try {
             
         con = conectar.getConenection();
         ps = con.prepareStatement(query);
        
         ps.setString(1, P.getName() );
         ps.setString(2, P.getEmail());
         ps.setString(3, P.getNumber());
         ps.setInt(4,P.getId() );
         ps.executeUpdate();
         
         
         
         } catch (Exception e) {
             e.printStackTrace();
         }
         return 1;
     }
     
     
     public void eliminar (int id){
     String query ="DELETE FROM users WHERE ID ="+id ;
     
         try {
             
            con =  conectar.getConenection();
            ps =  con.prepareStatement(query);
           ps.executeUpdate();
            
             
         } catch (Exception e) {
             e.printStackTrace();
         }
     
     ;
     }
    
}
