/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.Conexion;
import static Controlador.ComprasProducto.NumeroPages;
import Vistas.MostrarCompras;
import Vistas.VerCompras;
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
public class VerCompra {
    
    private static Conexion con = new Conexion();
    private static Connection conexion = con.getConexion();
    private static PreparedStatement ps = null;
    
    
    public static void VerCompras(String id){
        DefaultTableModel model = (DefaultTableModel)VerCompras.tblDetalle.getModel();
           while(model.getRowCount() > 0 ){
            model.removeRow(0);
        }
        String sql = "";
               /* sql = "select nombre, categoria, cantidad, precio from detallecompra as dd\n" +
                        "inner join catalogo_productos as cp on dd.producto = cp.id where dd.idCompra= ' "+1+ "'"; */
           sql = "select producto, cantidad, tipoProducto, precio from detalleCompra where idCompra = "+id;
        String datos[] = new String[6];
        
        try{
           
            PreparedStatement st = conexion.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            int count =1;
            float total =0;
            while(rs.next()){
                
                datos[1] = rs.getString("producto");
                datos[2] = rs.getString("cantidad");
                datos[3] = rs.getString("tipoProducto");
                datos[4] = rs.getString("precio");
                datos[5] = String.valueOf(Integer.parseInt(rs.getString("cantidad"))* Double.parseDouble(rs.getString("precio")));
                total = total + (float) (Integer.parseInt(rs.getString("cantidad"))* Double.parseDouble(rs.getString("precio")));
                datos[0] = String.valueOf(count);
                count++; 
                model.addRow(datos);
            }
            VerCompras.lbltotal.setText("Total: "+total);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
                tcr.setHorizontalAlignment(SwingConstants.RIGHT);
                VerCompras.tblDetalle.setModel(model);
           
        }catch (SQLException ex){
            Logger.getLogger(ComprasProducto.class.getName()).log(Level.SEVERE, null, ex);
            
        }
            
    }
}
