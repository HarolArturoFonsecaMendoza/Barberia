/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConsultasSQL;


public class QuerysTratamiento { 
    private int id;
    private String nombre;
    private String tipoTratamiento;
    private String Descripcion;
    private byte[] Foto1;
    private byte[] Foto2;
    private String NomreProductos;
    private String estado;
    private Double precio;

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

    public String getTipoTratamiento() {
        return tipoTratamiento;
    }

    public void setTipoTratamiento(String tipoTratamiento) {
        this.tipoTratamiento = tipoTratamiento;
    }
    
    public byte[] getFoto1() {
        return Foto1;
    }

    public void setFoto1(byte[] Foto1) {
        this.Foto1 = Foto1;
    }

    public byte[] getFoto2() {
        return Foto2;
    }

    public void setFoto2(byte[] Foto2) {
        this.Foto2 = Foto2;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
       public String getNomreProductos() {
        return NomreProductos;
    }

    public void setNomreProductos(String NomreProductos) {
        this.NomreProductos = NomreProductos;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public static String RegistroTratamiento = "INSERT INTO tratamientos("
            +"Nombre,"+"tipoTratamiento,"+"Descripcion,"+"Foto1,"+"Foto2,"+"estado,precio)"
            +"VALUES(?,?,?,?,?,?,?)";
    
    public static String ListarTratamiento = "SELECT * FROM tratamientos";
    
    public static String VerTratamiento = "SELECT Nombre, tipoTratamiento,Descripcion, Foto1, Foto2, estado, precio FROM tratamientos WHERE id = ?";
    
    public static String ActualizarTratamiento = "UPDATE tratamientos SET Nombre = ?, tipoTratamiento = ?, Descripcion = ?, Foto1 = ?, Foto2 = ?, estado = ?, precio = ? WHERE id = ?";
    
    public static String VerFoto = "SELECT Foto1, Foto2 FROM tratamientos WHERE id = ?";
}
