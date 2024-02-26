/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConsultasSQL;

/**
 *
 * @author Bucardo
 */
public class QuerysMobiliarioyEquipo {
    
    
    private int id;
   
    private String nombre;
    private String cantidad;
    private String estado;
    private String descripcion;
    private String fechadeadquisicion;

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

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechadeadquisicion() {
        return fechadeadquisicion;
    }

    public void setFechadeadquisicion(String fechadeadquisicion) {
        this.fechadeadquisicion = fechadeadquisicion;
    }
    // arregar la cantidad en el creates y el updates 
    public static String List = "SELECT * FROM mobiliarioyequipo";
    public static String CREATES = "INSERT INTO mobiliarioyequipo(" + "nombre," + "cantidad,"+"estado," + "descripcion,"+"fechadeadquisicion)"+ "VALUES(?,?,?,?,?)";
    public static String UPDATES = "UPDATE mobiliarioyequipo SET nombre = ?, cantidad = ?, estado=?, descripcion = ?, fechadeadquisicion=? WHERE id = ?";
    
    
}
