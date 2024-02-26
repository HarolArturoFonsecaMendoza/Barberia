/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConsultasSQL;

/**
 *
 * @author Admin
 */
public class QuerysProductosCuidados {
    private int id;
    private String nombre;
    private String marca;
    private String tamano;
    private String categoria;
    private String descripcion;
    private String precio;

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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
    
    public static String RegistraProducto = "INSERT INTO catalogo_productos("
            + "nombre," + "marca," + "tamano," + "categoria," + "descripcion)"
            + "VALUES(?,?,?,?,?)";
    
    public static String ListarProductos = "SELECT * FROM catalogo_productos";
    
    public static String ACTUALIZAR = "UPDATE catalogo_productos SET nombre = ?, marca = ?,  categoria=?,tamano = ?, descripcion=? WHERE id = ?";

}
