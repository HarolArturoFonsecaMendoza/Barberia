/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConsultasSQL;

/**
 * 
 *
 * @author Josue
 */
public class QuerysProveedores {
    private int id;
    private String nombre;
    private String empresa;
    private String rtn;
    private String telefono;
    private String direccion;

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

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getRtn() {
        return rtn;
    }

    public void setRtn(String rtn) {
        this.rtn = rtn;
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
    
    
     public static String RegistraProveedor = "INSERT INTO provedores("
            +"nombre,"+"empresa,"+"rtn,"+"telefono,"+"direccion)"
            +"VALUES(?,?,?,?,?)";
    
    
    
    public static String LISTARPROVEEDORES = "SELECT * FROM proveedores";
    
     public static String ACTUALIZARPROVEEDOR = "UPDATE provedores SET nombre = ?,"
            + " empresa = ?,"+" rtn = ?,"+" telefono = ?, "+" direccion =? WHERE id = ?";
     
    public String Buscar(String textBusqueda){
        String query= "SELECT * FROM provedores p WHERE p.nombre or p.empresa LIKE '%" + textBusqueda+ "%";
        return query;
    }
    
}
