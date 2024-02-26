/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConsultasSQL;

/**
 *
 * @author Josue
 */
public class QuerysInventarioProductos {
     private int id;
    private String producto;
    private String tipo_producto;
    private int cantidad;
    private double precio; 
    private double precio_venta;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getTipo_producto() {
        return tipo_producto;
    }

    public void setTipo_producto(String tipo_producto) {
        this.tipo_producto = tipo_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }
    
     public static String LISTARINVENTARIO = "SELECT * FROM inventarioproductos";
     
     public String Busqueda(String textBusqueda) {
       String query = "SELECT * FROM inventarioproductos p WHERE p.producto LIKE '%" + textBusqueda + "%' OR " +
                   "p.marca LIKE '%" + textBusqueda + "%";
       return query;
     }
     
    
}
