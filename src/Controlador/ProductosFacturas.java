/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//Controlador para la tabla dentro de la factura de ingresar compras

package Controlador;

import Conexion.Conexion;
import java.util.logging.Level;
import java.util.logging.Logger;
import ConsultasSQL.QuerysProductosCuidados;
import Vistas.IngresarCompra;
//import Vistas.IngresarCompra;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import Vistas.ProductoParaCompra;
import java.sql.SQLException;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class ProductosFacturas {
    //Establecer la conexion a la base de datos en este controlador
    private static Conexion con = new Conexion();
    private static Connection conexion = con.getConexion();
    private static PreparedStatement ps = null;
    
    public static void IngresarCompra(String buscar){
        String[] titulos = {"<html><b>Nombre del producto</b></html>","<html><b>Cantidad</b></html>",
            "<html><b>Precio unitario</b></html>","<html><b>precio Venta</b></html>","<html><b>Total</b></html>"};
        DefaultTableModel model = new DefaultTableModel(null,titulos);
        
        while(model.getRowCount() > 0 ){
            model.removeRow(0);
        }
        
         String sql = "";
            if(buscar.equals("")){
                sql = QuerysProductosCuidados.ListarProductos;
                
            }

            
            String datos[] = new String[4];
        
        try{
        
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
           
//            
            while(rs.next()){
                
                datos[0] = rs.getString("Nombre del Producto");
                datos[1] = rs.getString("Cantidad");
                datos[2] = rs.getString("precio");
                datos[3] = rs.getString("precio venta");
                datos[4] = rs.getString( "Total");
                model.addRow(datos);
                
                DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
                tcr.setHorizontalAlignment(SwingConstants.RIGHT);
               //IngresarCompra.tblProductosCompras.setModel(model);
                IngresarCompra.tblProductosCompras.setModel(model);
                //MostrarProductos.tblMostrarProductos.getColumnModel().getColumn(4).setCellRenderer(tcr);
                
              
                
            }
           // MOSTRARCARGO.tblCa.setModel(modelo);//la tabla se actualiza. HacerCalculos(r);
        }catch (SQLException ex){
           Logger.getLogger(ProductoParaCompra.class.getName()).log(Level.SEVERE,null, ex);
            
        }
    }
    
}
