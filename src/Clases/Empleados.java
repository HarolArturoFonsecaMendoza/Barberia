/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author PC
 */

public class Empleados {
    
   private int id;
   private String nombre;
   private String apellido;
   private String cargo;

    public Empleados(int id, String nombre, String apellido, String cargo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
    }

//   public  Empleados(int id, String nombre){   
//        this. id = id;
//       this.nombre = nombre;
//           
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }    
    
    public String getCargo(){
        return cargo;
    }
    
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String toString(){
        return nombre +" "+ apellido;
    }
}
