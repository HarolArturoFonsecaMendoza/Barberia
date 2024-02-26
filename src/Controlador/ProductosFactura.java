/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.Conexion;
import java.util.logging.Level;
import java.util.logging.Logger;
import ConsultasSQL.QuerysProductosCuidados;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import Vistas.ProductoParaCompra;
import java.sql.SQLException;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class ProductosFactura {
    // Establecer la conexión a la base de datos
    private static Conexion con = new Conexion();
    private static Connection conexion = con.getConexion();
    private static PreparedStatement ps = null;
    
    public static void ProductoParaCompra(String buscar){
        DefaultTableModel model = (DefaultTableModel)ProductoParaCompra.tblProductosParafactura.getModel();
        while (model.getRowCount() > 0 ){
                model.removeRow(0);
                ProductoParaCompra.tblProductosParafactura.getColumnModel().getColumn(4).setMaxWidth(0);
                ProductoParaCompra.tblProductosParafactura.getColumnModel().getColumn(4).setMinWidth(0);
                ProductoParaCompra.tblProductosParafactura.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
                ProductoParaCompra.tblProductosParafactura.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
        }
        
         String sql = "";
            if(buscar.equals("")){
                sql = QuerysProductosCuidados.ListarProductos;
                
            }

            
            String datos[] = new String[5];
        
        try{
        
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
           
            int count = 1;
            while(rs.next()){
                datos[0] = count+"";
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("marca");
                datos[3] = rs.getString("tamano");
                datos[4] = rs.getString( "Id");
                model.addRow(datos);
                
                DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
                tcr.setHorizontalAlignment(SwingConstants.RIGHT);
                ProductoParaCompra.tblProductosParafactura.setModel(model);
                //MostrarProductos.tblMostrarProductos.getColumnModel().getColumn(4).setCellRenderer(tcr);
                count++;
              
                
            }
           // MOSTRARCARGO.tblCa.setModel(modelo);//la tabla se actualiza. HacerCalculos(r);
        }catch (SQLException ex){
           Logger.getLogger(ProductoParaCompra.class.getName()).log(Level.SEVERE,null, ex);
            
        }
    }
}
