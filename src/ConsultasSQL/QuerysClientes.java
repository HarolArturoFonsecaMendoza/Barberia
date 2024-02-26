/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConsultasSQL;

/**
 *
 * @author Alejandra Suárez
 */
public class QuerysClientes {

    public static String LISTARCLIENTES = "select * from clientes ";
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String fechaNacimiento;
    private String fechaRegistro;

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;

    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String FechaRegistro) {
        this.fechaRegistro = FechaRegistro;
    }

    //Consulta para registrar
    public static String RegistrarClientes = "INSERT INTO clientes (" + "nombre," + "apellido," + "telefono," + "direccion," + "fechaNacimiento," + "fechaRegistro"
            + ")" + "VALUES(?,?,?,?,?,?)";

    public String Busqueda(String textBusqueda) {
        String query = "SELECT * FROM clientes p WHERE p.nombre LIKE '%" + textBusqueda + "%'";
        return query;
    }

    //Consulta para registrar
    public static String ActualizarClientes = "UPDATE clientes set nombre=?, apellido=?, telefono=?, direccion=?, fechaNacimiento=?,"
            + "fechaRegistro=? where clientes.id=?";

    //Consulta para registrar
    public static String TraerCliente = "SELECT * from clientes where clientes.id=?";

}
