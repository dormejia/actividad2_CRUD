
package Modelo;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Modelo.clsConexion;
import java.awt.HeadlessException;
// para hacer consultas
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;



public class clsCliente extends clsPersona{
    clsConexion cn;
    
    private String id;
    
    private String Nit;
    
    public clsCliente(String Nit) {
        this.Nit = Nit;
    }
    //contructores
    public clsCliente(){}

    public clsCliente(String id, String Nit, String Nombre, String Apellido, String Direccion, String Telefono, String Fecha_na) {
        super(Nombre, Apellido, Direccion, Telefono, Fecha_na);
        this.id = id;
        this.Nit = Nit;
    }

    public clsCliente(String id, String Nit) {
        this.id = id;
        this.Nit = Nit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

 
    public String getNit() {
        return Nit;
    }

    public void setNit(String Nit) {
        this.Nit = Nit;
    }
    // insertar a sql
     public void crear(){
         try{
           PreparedStatement parametro;
           String query = "INSERT INTO clientes(nit,nombres,apellidos,direccion,telefono,fecha_nacimiento)VALUES(?,?,?,?,?,?);";
           cn = new clsConexion();
           cn.abrir();
           parametro =( PreparedStatement)cn.cnn.prepareStatement(query);
           parametro.setString(1,this.getNit());
           parametro.setString(2,this.getNombre());
           parametro.setString(3,this.getApellido());
           parametro.setString(4,this.getDireccion());
           parametro.setString(5,this.getTelefono());
           parametro.setString(6,this.getFecha_na());
           
           int ejecutar = 0;
                  ejecutar = parametro.executeUpdate();
           cn.cerrar();
           JOptionPane.showMessageDialog(null,Integer.toString(ejecutar)+ "registro ingresado");
           
         }catch(HeadlessException  | SQLException ex){
             System.out.println("error"+ ex.getMessage());
         }
     
     }

     // leer
     public DefaultTableModel leer(){
     DefaultTableModel tabla = new DefaultTableModel();
     
      try{
            cn = new clsConexion();
           cn.abrir();
           PreparedStatement parametro;
           String query = "SELECT idclientes as id,nit,nombres,apellidos,direccion,telefono,fecha_nacimiento from clientes;";
           ResultSet consulta= cn.cnn.createStatement().executeQuery(query);
           String  encabezado[]={"ID","NIT","NOMBRES","APELLIDOS","DIRECCION","TELEFONO","FCH_NACIMIENTO"};
           tabla.setColumnIdentifiers(encabezado);
           
           String datos[]= new String[7];
           
           while(consulta.next()){
           datos[0]= consulta.getString("id");
           datos[1]= consulta.getString("nit");
           datos[2]= consulta.getString("nombres");
           datos[3]= consulta.getString("apellidos");
           datos[4]= consulta.getString("direccion");
           datos[5]= consulta.getString("telefono");
           datos[6]= consulta.getString("fecha_nacimiento");
            tabla.addRow(datos);
           }
          
           
           cn.cerrar();
         }catch(SQLException ex){
             System.out.println("error"+ ex.getMessage());
         }
    
     return tabla;
     
     }
     // burcar par amodificar
     public String[] buscar(){
         String datos[]= new String[7];
     try{
            cn = new clsConexion();
           cn.abrir();
           PreparedStatement parametro;
           String query = "SELECT idclientes as id,nit,nombres,apellidos,direccion,telefono,fecha_nacimiento from clientes where idclientes = "+this.getId()+";";
           ResultSet consulta= cn.cnn.createStatement().executeQuery(query);

           
           
            while(consulta.next()){
          
           datos[0]= consulta.getString("id");
           datos[1]= consulta.getString("nit");
           datos[2]= consulta.getString("nombres");
           datos[3]= consulta.getString("apellidos");
           datos[4]= consulta.getString("direccion");
           datos[5]= consulta.getString("telefono");
           datos[6]= consulta.getString("fecha_nacimiento");
        
            }
       
           
           cn.cerrar();
         }catch(SQLException ex){
             System.out.println("error"+ ex.getMessage());
         }
     
     return datos;
     
     }
     // actualizar
     
      public void actualizar(){
         try{
           PreparedStatement parametro;
           String query = "UPDATE clientes SET nit = ?,nombres = ?, apellidos = ?, direccion = ?, telefono = ?, fecha_nacimiento = ? WHERE (idclientes = "+this.getId()+");";
           cn = new clsConexion();
           cn.abrir();
           parametro =( PreparedStatement)cn.cnn.prepareStatement(query);
           parametro.setString(1,this.getNit());
           parametro.setString(2,this.getNombre());
           parametro.setString(3,this.getApellido());
           parametro.setString(4,this.getDireccion());
           parametro.setString(5,this.getTelefono());
           parametro.setString(6,this.getFecha_na());
           
           int ejecutar = 0;
                  ejecutar = parametro.executeUpdate();
           cn.cerrar();
           JOptionPane.showMessageDialog(null,Integer.toString(ejecutar)+ "registro Actualizado");
           
         }catch(HeadlessException  | SQLException ex){
             System.out.println("error"+ ex.getMessage());
         }
     
     }
// eliminar DELETE FROM people WHERE Idpeople = 3;
  public void eliminar(){
         try{
           PreparedStatement parametro;
           String query = "DELETE FROM clientes WHERE idclientes = "+this.getId()+" and nit =?;";
           cn = new clsConexion();
           cn.abrir();
           parametro =( PreparedStatement)cn.cnn.prepareStatement(query);
           parametro.setString(1,this.getNit());

           
           int ejecutar = 0;
                  ejecutar = parametro.executeUpdate();
           cn.cerrar();
           JOptionPane.showMessageDialog(null,Integer.toString(ejecutar)+ "registro eliminado");
           
         }catch(HeadlessException  | SQLException ex){
             System.out.println("error"+ ex.getMessage());
         }
     
     }

    
}
