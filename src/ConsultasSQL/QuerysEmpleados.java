/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConsultasSQL;

/**
 *
 * @author Josue
 */
public class QuerysEmpleados {
     private int id;
    private String nombre;
    private String apellido;
    private String identidad;
    private String telefono;
    private String cargo;
    private String estado;
    private String direccion;
    private String nombreEmergencia;
    private String telefonoemergencia;
    
    
    

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

    public String getIdentidad() {
        return identidad;
    }

    public void setIdentidad(String identidad) {
        this.identidad = identidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getNombreEmergencia() {
        return nombreEmergencia;
    }

    public void setNombreEmergencia(String nombreEmergencia) {
        this.nombreEmergencia = nombreEmergencia;
    }

    public String getTelefonoemergencia() {
        return telefonoemergencia;
    }

    public void setTelefonoemergencia(String telefonoEmergencia) {
        this.telefonoemergencia = telefonoEmergencia;
    }
    

     public static String RegistraEmpleado = "INSERT INTO empleados("
            +"nombre,"+"apellido,"+"identidad,"+"telefono,"+"cargo,"+"estado,"+"direccion,"+"nombreEmergencia,"+"telefonoemergencia)"
            +"VALUES(?,?,?,?,?,?,?,?,?)";
     
     // Consulta SQL para listar todos los empleados de la base de datos
    public static String LISTAREMPLEADOS = "SELECT * FROM empleados where Estado = 'Activo'";
    
    public String Busqueda(String textBusqueda) {
        String query = "SELECT * FROM empleados p WHERE p.nombre LIKE '%" + textBusqueda + "%'";
        return query;
    }
    
    // Consulta SQL para actualizar un empleado en la base de datos
    public static String ACTUALIZEmpleado = "UPDATE empleados SET nombre = ?, apellido = ?, identidad = ?, telefono = ?," +"cargo = ?,"
            + " estado = ?, direccion = ?, nombreEmergencia = ?, telefonoemergencia = ? WHERE id = ?";

//    public static String ACTUALIZEmpleado = "UPDATE empleados SET nombre = ?,"
//            + " apellido = ?, telefono = ?,estado = ? WHERE id = ?";

}
    

