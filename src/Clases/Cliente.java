/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    
    public  Cliente(int id, String nombre, String apellido){   
        this.apellido = apellido;
        this.nombre = nombre;
        this. id = id;
        
    }
    
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
    public String toString(){
        return nombre + " " + apellido;
    }
    
    
}

