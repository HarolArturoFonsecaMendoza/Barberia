/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConsultasSQL;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class QuerysDetalleVentas {
    
    private int id;
    private int fk_producto;
    private int fk_ventas;
    private double precio;
    private int cantidad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_producto() {
        return fk_producto;
    }

    public void setFk_producto(int fk_producto) {
        this.fk_producto = fk_producto;
    }

    public int getFk_ventas() {
        return fk_ventas;
    }

    public void setFk_ventas(int fk_ventas) {
        this.fk_ventas = fk_ventas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public QuerysInventarioProductos getDatosProducto() {
        QuerysInventarioProductos inventarioProductos = new QuerysInventarioProductos();
        try {
           
            Conexion con = new Conexion();
            Connection conexion = con.getConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM inventarioproductos WHERE id = ? ");
            ps.setInt(1, getFk_producto());
            
            
            ResultSet resultset = ps.executeQuery();
            
            if (resultset.next()) {
                inventarioProductos.setId(resultset.getInt("id"));
                inventarioProductos.setProducto(resultset.getString("producto"));
                inventarioProductos.setTipo_producto(resultset.getString("tipo_producto"));
                inventarioProductos.setCantidad(resultset.getInt("cantidad"));
                inventarioProductos.setPrecio(resultset.getDouble("precio"));
                inventarioProductos.setPrecio_venta(resultset.getDouble("precio_venta"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QuerysDetalleVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inventarioProductos;
    }
    
    public static String InsertarDetalle = "INSERT INTO `detalle_ventas` (`fk_producto`, `fk_ventas`, `precio`, `cantidad`) VALUES (?, ?, ?, ?);";
    public static String ActualizarDetalle = "UPDATE inventarioproductos SET cantidad = cantidad - ? WHERE  id=?;";
    
      public static String InsertarDetalleVentaServicio = "INSERT INTO `detalle_ventas_servicios` (`fk_producto`,`tipo_servicio`, `fk_ventas`, `precio`, `cantidad`) VALUES (?, ?, ?, ?, ?);"; 

}
