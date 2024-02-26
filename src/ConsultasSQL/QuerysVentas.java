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
public class QuerysVentas {
    public static String LISTARVENTAS = "SELECT * FROM ventas INNER JOIN clientes ON clientes.id = ventas.fk_cliente";
    
    public static String LISTARVENTASERVICIOS = "SELECT * FROM ventas_servicios INNER JOIN clientes ON clientes.id = ventas_servicios.fk_cliente";
    
    private int id;
    private String cai;
    private String numeroFactura;
    private int fk_cliente;
    private String fecha;
    private Double total;
    private Double impuestos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCai() {
        return cai;
    }

    public void setCai(String cai) {
        this.cai = cai;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public int getFk_cliente() {
        return fk_cliente;
    }

    public void setFk_cliente(int fk_cliente) {
        this.fk_cliente = fk_cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(Double impuestos) {
        this.impuestos = impuestos;
    }
    
    public QuerysClientes getDatosClientes() {
        QuerysClientes clientes = new QuerysClientes();
        try {
           
            Conexion con = new Conexion();
            Connection conexion = con.getConexion();
            PreparedStatement ps = conexion.prepareStatement(QuerysVentas.TraerCliente);
            ps.setInt(1, getFk_cliente());
            
            
            ResultSet resultset = ps.executeQuery();
            
            if (resultset.next()) {
                clientes.setId(resultset.getInt("id"));
                clientes.setNombre(resultset.getString("nombre"));
                clientes.setApellido(resultset.getString("apellido"));
                clientes.setFechaNacimiento(resultset.getString("fechaNacimiento"));
                clientes.setDireccion(resultset.getString("direccion"));
                clientes.setTelefono(resultset.getString("telefono"));
                clientes.setFechaRegistro(resultset.getString("fechaRegistro"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QuerysVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }
    
/*public QuerysInventarioProductos getDatosProductos() {
        QuerysClientes clientes = new QuerysClientes();
        try {
           
            Conexion con = new Conexion();
            Connection conexion = con.getConexion();
            PreparedStatement ps = conexion.prepareStatement(QuerysDetallesVentas.TraerCliente);
            ps.setInt(1, getFk_cliente());
            
            
            ResultSet resultset = ps.executeQuery();
            
            if (resultset.next()) {
                clientes.setId(resultset.getInt("id"));
                clientes.setNombre(resultset.getString("nombre"));
                clientes.setApellido(resultset.getString("apellido"));
                clientes.setFechaNacimiento(resultset.getString("fechaNacimiento"));
                clientes.setDireccion(resultset.getString("direccion"));
                clientes.setTelefono(resultset.getString("telefono"));
                clientes.setFechaRegistro(resultset.getString("fechaRegistro"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QuerysDetallesVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }*/

    //Consulta para registrar
    public static String TraerCliente = "SELECT * from clientes where clientes.id=?";
    
    public static String InsertarVEnta = "INSERT INTO `ventas` (`cai`, `numeroFactura`, `fk_cliente`, `fecha`, `total`, `impuestos`) VALUES (?, ?, ?, ?, ?, ?);";
    
    public static String InsertarVEntaServicio = "INSERT INTO `ventas_servicios` (`cai`, `numeroFactura`, `fk_cliente`, `fecha`, `total`, `impuestos`) VALUES (?, ?, ?, ?, ?, ?);";
    


}
