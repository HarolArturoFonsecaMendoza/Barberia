/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.Conexion;
import ConsultasSQL.QuerysClientes;
import ConsultasSQL.QuerysDetalleVentas;
import ConsultasSQL.QuerysInventarioProductos;
import ConsultasSQL.QuerysVentas;
import Vistas.CrearVentasClientes;
import Vistas.CrearVentasProductos;
import Vistas.CrearVentasServiciosClientes;
import Vistas.MostrarVentaServicios;
import Vistas.MostrarVentas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class VentasServicios {
    
    private static Conexion con = new Conexion();
    private static Connection conexion = con.getConexion();
    private static PreparedStatement ps = null;
    private static final int filasxPagina = 5;
    
    
    public static List ids = new ArrayList();


         private static final int filasxPagina2 = 10;
     public static List ids2 = new ArrayList();
     public static List<QuerysClientes> clientes_ventas = new ArrayList();
     
     
     public static int NumeroPagesClientes(String buscar) {
        String sql = "";
        if (buscar.isEmpty()) {
            buscar = "";
            sql = "SELECT count(*) from clientes";

        } else {

            sql = "SELECT count(*) from clientes WHERE "
                    + " concat(nombre,' ',apellido) like concat('%','" + buscar + "','%') ";

        }

        try {
            Statement st = conexion.createStatement();

            ps = conexion.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int totalPages = (int) Math.ceil((double) rs.getInt(1) / filasxPagina2);
                return totalPages;
            } else {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    public static void MostrarClientes(String buscar, int paginaActual, int totalPages) {
        DefaultTableModel model = (DefaultTableModel) CrearVentasServiciosClientes.jtClientesVentas.getModel();
        
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        VentasServicios.ids2.clear();
        VentasServicios.clientes_ventas.clear();
        
        String sql = "";
        if (buscar.equals("")) {
            sql = QuerysClientes.LISTARCLIENTES + " limit " + filasxPagina2 + " offset " + (paginaActual - 1) * filasxPagina2;

        } else {
            sql = "select * from clientes  WHERE "
                    + "concat(nombre,' ',apellido) like concat('%','" + buscar + "','%') limit " + filasxPagina2 + " offset " + (paginaActual - 1) * filasxPagina2;
            //"SELECT * FROM empleados p WHERE UPPER(p.nombreEmpleados) LIKE UPPER('%" + buscar + "%')"
        }

        String datos[] = new String[7];

        try {

            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            int count = 0;
            if (paginaActual == 1) {
                count = 1;
            } else {
                for (int i = 1; i < paginaActual; i++) {
                    count += 5;
                }
                count += 1;
            }
            
            while (rs.next()) {
                QuerysClientes clien = new QuerysClientes();
                datos[0] = count + "";
                datos[1] = rs.getString("nombre")+" "+rs.getString("apellido");
                datos[2] = rs.getString("telefono");
                
                clien.setId(rs.getInt("id"));
                clien.setNombre(rs.getString("nombre"));
                clien.setApellido(rs.getString("apellido"));
                clien.setDireccion(rs.getString("direccion"));
                
                VentasServicios.ids2.add(rs.getInt("id"));
                VentasServicios.clientes_ventas.add(clien);
                model.addRow(datos);

                count++;
            }
            int totalRows = count - 1; // Restamos el encabezado de la tabla
            totalPages = (NumeroPagesClientes(buscar) == 0 && model.getRowCount() > 0) ? 1 : NumeroPagesClientes(buscar);
            paginaActual = model.getRowCount() == 0 ? 0 : paginaActual;
            CrearVentasServiciosClientes.seguimiento1.setText("Página " + paginaActual + " de " + totalPages);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(SwingConstants.RIGHT);
            CrearVentasServiciosClientes.jtClientesVentas.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
    
     public static boolean Guardar(QuerysVentas qp, DefaultTableModel model) {
        String sql = QuerysVentas.InsertarVEntaServicio;
        String sql2 = QuerysDetalleVentas.InsertarDetalleVentaServicio;
        try {
            ps = conexion.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, qp.getCai());
            ps.setString(2, qp.getNumeroFactura());
            ps.setInt(3, qp.getFk_cliente());
            ps.setString(4, qp.getFecha());
            ps.setDouble(5, qp.getTotal());
            ps.setDouble(6, qp.getImpuestos());
            ps.executeUpdate();
            
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idGenerado = generatedKeys.getInt(1);
                
                for (int i = 0; i < model.getRowCount(); i++) {
                        ps = conexion.prepareStatement(sql2);
                        ps.setInt(1, Integer.parseInt(model.getValueAt(i, 3).toString()));
                        ps.setString(2, model.getValueAt(i, 4).toString());
                        ps.setInt(3, idGenerado);
                        ps.setDouble(4, Double.parseDouble(model.getValueAt(i, 2).toString()));
                        ps.setInt(5, 0);
                        ps.executeUpdate();

                }

            }
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
    }
     
     
     
     public static int NumeroPages(String buscar, String textoInicial, String textoFinal) {
        

        
        String sql = "";
        if (buscar.isEmpty()) {
            buscar = "";
            sql = "SELECT count(*) FROM ventas_servicios"
                    + "	INNER JOIN clientes ON clientes.id = ventas_servicios.fk_cliente WHERE "
                    + " ventas_servicios.fecha BETWEEN if(" + textoInicial + " IS NULL,'1980-01-01','" + textoInicial + "') and if(" + textoFinal + " IS NULL,'2900-01-01','" + textoFinal + "') " ;
            
        } else {

            sql = "SELECT count(*) from ventas_servicios"
                    + "	INNER JOIN clientes ON clientes.id = ventas_servicios.fk_cliente WHERE "
                    + "(numeroFactura like concat('%','" + buscar + "','%') "
                    + "and cai like concat('%','" + buscar + "','%') "
                    + "and concat(clientes.nombre,' ',clientes.apellido) like concat('%','" + buscar + "','%')) "
                    + " and ventas_servicios.fecha BETWEEN if(" + textoInicial + " IS NULL,'1980-01-01','" + textoInicial + "') and if(" + textoFinal + " IS NULL,'2900-01-01','" + textoFinal + "') ";

        }
        
       

        try {
            Statement st = conexion.createStatement();

            ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int totalPages = (int) Math.ceil((double) rs.getInt(1) / filasxPagina);
                return totalPages;
            } else {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }
    
    public static void MostrarVentaServicios(String buscar, int paginaActual, int totalPages, String textoInicial, String textoFinal) {
        DefaultTableModel model = (DefaultTableModel) MostrarVentaServicios.jtventas.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
            MostrarVentaServicios.jtventas.getColumnModel().getColumn(0).setMaxWidth(0);
            MostrarVentaServicios.jtventas.getColumnModel().getColumn(0).setMinWidth(0);
            MostrarVentaServicios.jtventas.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
            MostrarVentaServicios.jtventas.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        }

        Clientes.ids.clear();



        String datos[] = new String[7];

        try {
            
            String sql = "";
           
            if (buscar.equals("")) {
                sql = QuerysVentas.LISTARVENTASERVICIOS +" WHERE "
                    + " ventas_servicios.fecha BETWEEN if(" + textoInicial + " IS NULL,'1980-01-01','" + textoInicial + "') and if(" + textoFinal + " IS NULL,'2900-01-01','" + textoFinal + "')" 
                    + " limit " + filasxPagina + " offset " + (paginaActual - 1) * filasxPagina;
                ps = conexion.prepareStatement(sql);   
            } else {
                sql = QuerysVentas.LISTARVENTASERVICIOS + " WHERE "
                        + "(numeroFactura like concat('%',?,'%') "
                        + "or cai like concat('%',?,'%') "
                        + "or concat(clientes.nombre,' ',clientes.apellido) like concat('%',?,'%'))"
                        + " and ventas_servicios.fecha BETWEEN if(" + textoInicial + " IS NULL,'1980-01-01','" + textoInicial + "') and if(" + textoFinal + " IS NULL,'2900-01-01','" + textoFinal + "')" 
                        + "limit " + filasxPagina + " offset " + (paginaActual - 1) * filasxPagina;
              
                ps = conexion.prepareStatement(sql);
                ps.setString(1, buscar);
                ps.setString(2, buscar);
                ps.setString(3, buscar);
            }

            
            ResultSet rs = ps.executeQuery();
            int count = 0;
            if (paginaActual == 1) {
                count = 1;
            } else {
                for (int i = 1; i < paginaActual; i++) {
                    count += 5;
                }
                count += 1;
            }

            while (rs.next()) {
                datos[0] = count + "";
                datos[1] = count + "";
                datos[2] = rs.getString("nombre") + " " + rs.getString("apellido");
                datos[3] = rs.getString("numeroFactura");
                datos[4] = rs.getString("cai");
                datos[5] = rs.getString("fecha");

                Clientes.ids.add(rs.getInt("id"));
                model.addRow(datos);
                count++;
            }
            
            int totalRows = count - 1; // Restamos el encabezado de la tabla
            totalPages = (NumeroPages(buscar, textoInicial, textoFinal) == 0 && model.getRowCount() > 0) ? 1 : NumeroPages(buscar, textoInicial, textoFinal);
            paginaActual = model.getRowCount() == 0 ? 0 : paginaActual;
            
            MostrarVentaServicios.seguimiento.setText("Página " + paginaActual + " de " + totalPages);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(SwingConstants.RIGHT);
            MostrarVentaServicios.jtventas.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(VentasServicios.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
