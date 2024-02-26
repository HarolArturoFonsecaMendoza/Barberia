/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConsultasSQL;

import com.sun.jdi.connect.spi.Connection;
/**
 *
 * @author Andrea
 */
public class QuerysCita {
     private int id;
     private int fk_nombre;
     private String tipo_cliente;
     private String telefono;
     private String servicios;
     private int fk_empleado;
     private String fecha;
     private String hora;
     private String notas;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getFk_nombre(){
        return fk_nombre;
    }
    
    public void setFk_nombre(int fk_nombre){
        this.fk_nombre = fk_nombre; 
    }
    
    public String getTipo_cliente(){
        return tipo_cliente;
    }
    
    public void setTipo_cliente(String tipo_cliente){
        this.tipo_cliente = tipo_cliente; 
    }
    
    public String getTelefono(){
        return telefono;
    }
    
    public void setTelefono(String telefono){
        this.telefono = telefono; 
    }
    
    public String getServicios(){
        return servicios;
    }
    
    public void setServicios(String servicios){
        this.servicios = servicios; 
    }
    
    
    public int getFk_Empleado(){
        return fk_empleado;
    }
    
    public void setFk_Empleado(int fk_empleado){
        this.fk_empleado = fk_empleado; 
    }
    
    public String getFecha(){
        return fecha;
    }
    
    public void setFecha(String fecha){
        this.fecha = fecha; 
    }
    
    public String getHora(){
        return hora;
    }
    
    public void setHora(String hora){
        this.hora = hora; 
    }
    
    public String getNotas(){
        return notas;
    }
    
    public void setNotas(String notas){
        this.notas = notas; 
    }
    
    //agendar cita
    public static String RegistrarCitas= "INSERT INTO citas("+"nombre,"+"telefono,"+"tipo_cliente,"+"servicios,"+"empleado,"+"fecha,"+"hora,"+"notas"
            +")"+"VALUES(?,?,?,?,?,?,?,?)";
     public static String EditarCitas="UPDATE citas SET nombre=?, telefono=?, tipo_cliente=?, servicios=?, empleado=?, fecha=?, hora=?, notas=? WHERE id = ?";
    public static String ListarCitas = "select citas.id,(clientes.nombre) as nombre, citas.tipo_cliente,citas.telefono, citas.servicios,\n" +
        " (empleados.nombre)as nombre_empleado,citas.fecha, citas.hora, citas.notas \n" +
        " from citas join clientes on citas.nombre = clientes.id join empleados on citas.empleado = empleados.id;"; 
}
