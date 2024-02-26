/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.Conexion;
import Vistas.MostrarDevoluciones;
import Vistas.VerDevoluciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Devoluciones {
    
    private static Conexion con = new Conexion();
    private static Connection conexion = con.getConexion();
    private static PreparedStatement ps = null;
    
    private static final int filasxPagina = 20;
    
        public static int NumeroPages(String buscar, String textoInicial, String textoFinal) {
        String sql = "";

        if (textoInicial == null) {
            textoInicial = "1980-01-01";
        }
        if (textoFinal == null) {
            textoFinal = "2900-01-01";
        }

        if (buscar.isEmpty()) {
            sql = "SELECT count(*) FROM devoluciones as d "
                    + "INNER JOIN barberia.catalogo_productos as p ON d.producto_devolver = p.id "
                    + "INNER JOIN barberia.ventas as v ON d.id_ventas = v.id "
                    + "INNER JOIN barberia.clientes as c ON v.fk_cliente = c.id "
                    + "WHERE DATE(d.fecha) BETWEEN COALESCE(?, '1980-01-01') AND COALESCE(?, '2900-01-01')";
        } else {
            sql = "SELECT count(*) FROM devoluciones as d "
                    + "INNER JOIN barberia.catalogo_productos as p ON d.producto_devolver = p.id "
                    + "INNER JOIN barberia.ventas as v ON d.id_ventas = v.id "
                    + "INNER JOIN barberia.clientes as c ON v.fk_cliente = c.id "
                    + "WHERE (v.numeroFactura LIKE ? "
                    + "OR d.descripcion LIKE ? "
                    + "OR d.monto_a_devolver LIKE ?) "
                    + "AND DATE(d.fecha) BETWEEN COALESCE(?, '1980-01-01') AND COALESCE(?, '2900-01-01')";
        }

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);

            if (!buscar.isEmpty()) {
                ps.setString(1, "%" + buscar + "%");
                ps.setString(2, "%" + buscar + "%");
                ps.setString(3, "%" + buscar + "%");
                ps.setString(4, textoInicial);
                ps.setString(5, textoFinal);
            } else {
                ps.setString(1, textoInicial);
                ps.setString(2, textoFinal);
            }

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int totalPages = (int) Math.ceil((double) rs.getInt(1) / filasxPagina);
                return totalPages;
            } else {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Devoluciones.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    
    public static void MostrarDevoluciones(String buscar, int paginaActual, int totalPages, String textoInicial, String textoFinal){
        DefaultTableModel model = (DefaultTableModel)MostrarDevoluciones.tblMostrarDevoluciones.getModel();
           while(model.getRowCount() > 0 ){
            model.removeRow(0);
            }
           // Verificar que textoInicial y textoFinal no sean null
            if (textoInicial == null) {
                textoInicial = "1980-01-01";  
            }
            if (textoFinal == null) {
                textoFinal = "2900-01-01";   
            }
            
            System.out.println(textoInicial);
            System.out.println(textoFinal);
            String sql = "";
                if (buscar.isEmpty()) {
                    sql = "SELECT d.id, d.id_ventas, v.numeroFactura, concat(c.nombre,' ',c.apellido) AS nombreCliente, p.nombre AS nombreProducto, d.descripcion, d.fecha, d.cantidad, d.precio, d.subtotal, d.monto_a_devolver FROM barberia.devoluciones AS d INNER JOIN barberia.ventas AS v ON d.id_ventas = v.id INNER JOIN barberia.clientes AS c ON v.fk_cliente = c.id INNER JOIN barberia.catalogo_productos AS p ON d.producto_devolver = p.id WHERE "  
                           + "d.fecha BETWEEN if(" + textoInicial + " IS NULL,'1980-01-01','" + textoInicial + "') and if(" + textoFinal + " IS NULL,'2900-01-01','" + textoFinal + "') "
                           + "LIMIT " + filasxPagina + " OFFSET " + (paginaActual - 1) * filasxPagina;
                } else {
                   sql = "SELECT d.id, d.id_ventas, v.numeroFactura, concat(c.nombre,' ',c.apellido) AS nombreCliente, p.nombre AS nombreProducto, d.descripcion, d.fecha, d.cantidad, d.precio, d.subtotal, d.monto_a_devolver FROM barberia.devoluciones AS d INNER JOIN barberia.ventas AS v ON d.id_ventas = v.id INNER JOIN barberia.clientes AS c ON v.fk_cliente = c.id INNER JOIN barberia.catalogo_productos AS p ON d.producto_devolver = p.id "
                            + "WHERE (v.numeroFactura LIKE '%" + buscar + "%' "
                            + "OR d.descripcion LIKE '%" + buscar + "%' "
                            + "OR d.monto_a_devolver LIKE '%" + buscar + "%') "
                            + "AND d.fecha BETWEEN if(" + textoInicial + " IS NULL,'1980-01-01','" + textoInicial + "') and if(" + textoFinal + " IS NULL,'2900-01-01','" + textoFinal + "') "
                            + "LIMIT " + filasxPagina + " OFFSET " + (paginaActual - 1) * filasxPagina;
                
                }

 
        
        String datos[] = new String[10];
        
        try{
        
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
             int count = 0;
            if(paginaActual ==1) {
                count =1;
            }else{
                for(int i =1;i < paginaActual ; i++){
                    count +=20;
                }
                count +=1;
            }
            while(rs.next()){
                datos[0] = count +"";
                datos[1] = rs.getString("v.numeroFactura");
                datos[2] = rs.getString("d.descripcion");
                datos[3] = rs.getString("d.fecha");
                datos[4] = rs.getString("d.cantidad");
                datos[5] = rs.getString("d.precio");
                datos[6] = rs.getString("d.subtotal");
                datos[7] = rs.getString("d.monto_a_devolver");
                datos[8] = rs.getString("d.id");
                datos[9] = rs.getString("d.id_ventas");
                count++;
                model.addRow(datos);
            }
             
                totalPages = (NumeroPages(buscar, textoInicial, textoFinal) == 0 && model.getRowCount() > 0) ? 1 : NumeroPages(buscar, textoInicial, textoFinal);
                paginaActual = model.getRowCount() == 0 ? 0 : paginaActual;
            
                MostrarDevoluciones.seguimiento.setText("Página " + paginaActual + " de " + totalPages);
                DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
                tcr.setHorizontalAlignment(SwingConstants.RIGHT);
                MostrarDevoluciones.tblMostrarDevoluciones.setModel(model);
             
        }catch (SQLException ex){
            Logger.getLogger(ComprasProducto.class.getName()).log(Level.SEVERE, null, ex);
            
        }
            
    }
    
    
    public static void Devoluciones(String id){
        DefaultTableModel model = (DefaultTableModel)Vistas.Devoluciones.tblDetalle.getModel();
           while(model.getRowCount() > 0 ){
            model.removeRow(0);
        }
           

        String sql = "";
           sql = "SELECT cp.id, cp.nombre, dv.precio, dv.cantidad FROM barberia.detalle_ventas AS dv INNER JOIN barberia.catalogo_productos AS cp ON dv.fk_producto = cp.id WHERE dv.fk_ventas = "+id;
        String datos[] = new String[6];
        
        try{
           
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            int count =1;
            float total =0;
            while(rs.next()){
                
                datos[1] = rs.getString("cp.nombre");
                datos[2] = rs.getString("dv.precio");
                datos[3] = rs.getString("dv.cantidad");
                datos[4] = String.valueOf(Integer.parseInt(rs.getString("dv.cantidad"))* Double.parseDouble(rs.getString("dv.precio")));
                total = total + (float) (Integer.parseInt(rs.getString("dv.cantidad"))* Double.parseDouble(rs.getString("dv.precio")));
                 datos[5] = rs.getString("cp.id");
                datos[0] = String.valueOf(count);
                count++; 
                model.addRow(datos);
                
            }
            Vistas.Devoluciones.lbltotal.setText("Monto a devolver: "+total);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
                tcr.setHorizontalAlignment(SwingConstants.RIGHT);
                Vistas.Devoluciones.tblDetalle.setModel(model);
           
        }catch (SQLException ex){
            Logger.getLogger(ComprasProducto.class.getName()).log(Level.SEVERE, null, ex);
            
        }
            
    }
    
        public static void VerDevolucionesYVentas(String idVentas, String monto_a_devolver) {
        DefaultTableModel model = (DefaultTableModel) VerDevoluciones.tblDetalle.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        String devolucionesSQL = "SELECT d.id, d.id_ventas, p.nombre, d.descripcion, d.fecha, d.cantidad, d.precio, d.subtotal, d.monto_a_devolver FROM barberia.devoluciones as d inner join barberia.catalogo_productos as p on d.producto_devolver = p.id where d.id_ventas = '" + idVentas + "' and d.monto_a_devolver = '" + monto_a_devolver + "'";

        String ventasSQL = "SELECT v.numeroFactura, v.cai, concat(c.nombre,' ',c.apellido) AS nombreCliente, v.fecha FROM barberia.ventas AS v INNER JOIN barberia.clientes AS c ON v.fk_cliente = c.id where v.id = '" + idVentas + "'";

        try {
            Statement st = conexion.createStatement();

            // Consulta de Devoluciones
            ResultSet rsDevoluciones = st.executeQuery(devolucionesSQL);

            // Procesar resultados de Devoluciones
            int count = 1;
            float totalDevoluciones = 0;
            while (rsDevoluciones.next()) {
                String[] datos = new String[6];
                datos[0] = String.valueOf(count);
                datos[1] = rsDevoluciones.getString("p.nombre");
                datos[2] = rsDevoluciones.getString("d.precio");
                datos[3] = rsDevoluciones.getString("d.cantidad");
                datos[4] = rsDevoluciones.getString("d.subtotal");
                totalDevoluciones += Float.parseFloat(rsDevoluciones.getString("d.subtotal"));
                count++;
                model.addRow(datos);
            }

            // Consulta de Ventas
            ResultSet rsVentas = st.executeQuery(ventasSQL);

            // Procesar resultados de Ventas
            if (rsVentas.next()) {
                String Factura = rsVentas.getString("v.numeroFactura");
                String Cai = rsVentas.getString("v.cai");
                String Cliente = rsVentas.getString("nombreCliente");
                String Fecha = rsVentas.getString("v.fecha");

                VerDevoluciones.lblFactura.setText(Factura);
                VerDevoluciones.lblCai.setText(Cai);
                VerDevoluciones.lblCliente.setText(Cliente);
                VerDevoluciones.lblFecha.setText(Fecha);

                DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
                tcr.setHorizontalAlignment(SwingConstants.RIGHT);
                VerDevoluciones.tblDetalle.setModel(model);

                VerDevoluciones.lbltotal.setText(String.format("Total Devoluciones: %.2f", totalDevoluciones));
            } else {
                // No hay resultados de Ventas, puedes manejar esto según tus necesidades
                System.out.println("No se encontraron resultados de Ventas");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ComprasProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
