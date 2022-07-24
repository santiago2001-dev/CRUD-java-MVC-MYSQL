
package controller;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS;
import javax.swing.table.DefaultTableModel;
import model.consultas;
import model.Persona;
import vista.vista;


public class controlador implements ActionListener {
    consultas consult = new consultas();
    Persona p = new Persona();
    vista vis = new vista();
    DefaultTableModel  modelo = new DefaultTableModel(); 
    
    
    public controlador (vista v){
    this.vis = v;
    this.vis.listar.addActionListener(this);
    this.vis.Enviar.addActionListener(this);
    this.vis.editar.addActionListener(this);
    this.vis.ok.addActionListener(this);
    this.vis.eliminar.addActionListener(this);
    listar(vis.Table);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== vis.listar){
            listar(vis.Table);
        }
     
        if(e.getSource() == vis.Enviar){
            agregar();
             listar(vis.Table);
        }
        
        
       if(e.getSource() == vis.editar){
         int fila = vis.Table.getSelectedRow();
         if(fila == -1){
             JOptionPane.showMessageDialog(vis, "DEBES SELECCIONAR UNA FILA");
         }else{
             
             //sacar informacion de la tabla al dar click en un registro
             int id = Integer.parseInt((String)vis.Table.getValueAt(fila,0).toString());
             String nombre = (String)vis.Table.getValueAt( fila,1);
             String correo = (String)vis.Table.getValueAt( fila,2);
             String telefono = (String)vis.Table.getValueAt( fila,3);
             
             //envio de valores a las cajas de texto
             vis.txtId.setText(""+id);
             vis.txtName.setText(nombre);
             vis.txtEmail.setText(correo);
             vis.txtCel.setText(telefono);
             
             
         }
       }
       
       //eliminar registro
       if(e.getSource() == vis.eliminar){
       
       int fila = vis.Table.getSelectedRow();
     
         if(fila == -1){
             JOptionPane.showMessageDialog(vis, "DEBES SELECCIONAR UNA FILA");
         }else{
            int id = Integer.parseInt((String)vis.Table.getValueAt(fila,0).toString());
            consult.eliminar(id);
            JOptionPane.showMessageDialog(vis, "Usuario eliminado correctamente");
            listar(vis.Table); 
             
         }
       
       }
       
       
       if(e.getSource() == vis.ok){
           actualzar();
            listar(vis.Table);
                   
       }else{
           System.out.println("error");
       }
    }
    
    
    public void eliminar(){
    
    
    }
    
    public void actualzar(){
         int id = Integer.parseInt(vis.txtId.getText());
         String nom =  vis.txtName.getText(), correo = vis.txtEmail.getText() ,tel = vis.txtCel.getText() ;
        //damos el valor capturado de las cajas de texto a nuestro modelo de la clase persona a cada atributo 
        p.setId(id);
        p.setName(nom);
        p.setEmail(correo);
        p.setNumber(tel);
         
        int r = consult.editar(p);
           
           
        if(r ==1){

            JOptionPane.showMessageDialog(vis, "Usuario actualizado correctamente!!");
        }else{
            
            
            
            JOptionPane.showMessageDialog(vis,"a ocurrudo un error insesperado");
        }
    
    
    }
    
    
    
    
    public void agregar(){
        
        String nom =  vis.txtName.getText(), correo = vis.txtEmail.getText() ,tel = vis.txtCel.getText() ;
        
        
        //damos el valor capturado de las cajas de texto a nuestro modelo de la clase persona a cada atributo 
        p.setName(nom);
        p.setEmail(correo);
        p.setNumber(tel);
        
        int r = consult.agregar(p);
        if(r ==1){

            JOptionPane.showMessageDialog(vis, "Usuario agregado correctamente");
        }else{
            
            
            
            JOptionPane.showMessageDialog(vis,"a ocurrudo un error insesperado");
        }
    
    }
    
    public void listar(JTable Table){
        
        modelo = (DefaultTableModel)Table.getModel();
        modelo.setRowCount(0);
        
        ArrayList<Persona>lista =consult.Listar();
        Object [] object = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object [0] = lista.get(i).getId();
            object [1] = lista.get(i).getName();
            object [2] = lista.get(i).getEmail();
            object [3] = lista.get(i).getNumber();
            modelo.addRow(object);
   }
        for (int i = 0; i < object.length; i++) {
            Object object1 = object[i];
            System.out.println(object1);
        }
        
        
        vis.Table.setModel(modelo);
       


    }
    
}
