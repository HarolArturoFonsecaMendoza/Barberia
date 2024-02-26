/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Conexion.Conexion;
import static Controlador.Ventas.NumeroPages;
import Vistas.MostrarVentas;
import Vistas.VerCompras;
import Vistas.VerVenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class VerVentas {
     private static Conexion con = new Conexion();
    private static Connection conexion = con.getConexion();
    private static PreparedStatement ps = null;
    
     public static void VerVenta(String id){
        DefaultTableModel model = (DefaultTableModel)VerVenta.tblDetalle.getModel();
           while(model.getRowCount() > 0 ){
            model.removeRow(0);
               
        }
    String sql = "";
                sql = "select producto, dd.cantidad as cantida, dd.precio as precios from detalle_ventas as dd\n" +
                        "inner join inventarioproductos as cp on dd.fk_producto = cp.id where dd.fk_ventas= ' "+id+ "'"; 
        
        String datos[] = new String[8];
        
        try{
        
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int count =1;
            float total =0;
            while(rs.next()){
                
                datos[1] = rs.getString("producto");
                datos[2] = rs.getString("cantida");
                datos[3] = rs.getString("precios");
                datos[4] = String.valueOf(Integer.parseInt(rs.getString("cantida"))* Double.parseDouble(rs.getString("precios")));
                total = total + (float) (Integer.parseInt(rs.getString("cantida"))* Double.parseDouble(rs.getString("precios")));
                datos[0] = String.valueOf(count);
                count++; 
                model.addRow(datos);
            }
            
            VerVenta.lbltotal2.setText(String.format("Sub total:  %.2f", (total-(total*0.15))));
            VerVenta.lbImpuestos.setText(String.format("Impuestos: %.2f", (total*0.15)));
            VerVenta.lbltotal.setText(String.format("Total:  %.2f", total));
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
                tcr.setHorizontalAlignment(SwingConstants.RIGHT);
                VerVenta.tblDetalle.setModel(model);
           
        }catch (SQLException ex){
            Logger.getLogger(ComprasProducto.class.getName()).log(Level.SEVERE, null, ex);
            
        }
            
    }

}
