/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.Conexion;
import java.util.logging.Level;
import java.util.logging.Logger;
import ConsultasSQL.QuerysProductosCuidados;
//import static Controlador.ProductosTratamiento.ProductoParaEditarTratamiento;
//import Vistas.ProductoParaEditarTratamiento;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import Vistas.ProductoParaTratamiento;
import java.sql.SQLException;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import Vistas.ProductoPEtratamiento;

public class ProductosTratamiento {
    // Establecer la conexión a la base de datos
    private static Conexion con = new Conexion();
    private static Connection conexion = con.getConexion();
    private static PreparedStatement ps = null;
    
    public static void ProductoParaTratamiento(String buscar){
        DefaultTableModel model = (DefaultTableModel)ProductoParaTratamiento.tblProductosParaTratamiento.getModel();
        while (model.getRowCount() > 0 ){
                model.removeRow(0);
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
                ProductoParaTratamiento.tblProductosParaTratamiento.setModel(model);
                //MostrarProductos.tblMostrarProductos.getColumnModel().getColumn(4).setCellRenderer(tcr);
                count++;
              
                
            }
           // MOSTRARCARGO.tblCa.setModel(modelo);//la tabla se actualiza. HacerCalculos(r);
        }catch (SQLException ex){
           Logger.getLogger(ProductoParaTratamiento.class.getName()).log(Level.SEVERE,null, ex);
            
        }
    }
    
    public static void ProductoPETratamiento(String buscar){
        DefaultTableModel model = (DefaultTableModel)ProductoPEtratamiento.tblProductosPETratamiento.getModel();
        while (model.getRowCount() > 0 ){
                model.removeRow(0);
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
                ProductoPEtratamiento.tblProductosPETratamiento.setModel(model);
                //MostrarProductos.tblMostrarProductos.getColumnModel().getColumn(4).setCellRenderer(tcr);
                count++;
              
                
            }
           // MOSTRARCARGO.tblCa.setModel(modelo);//la tabla se actualiza. HacerCalculos(r);
        }catch (SQLException ex){
           Logger.getLogger(ProductoPEtratamiento.class.getName()).log(Level.SEVERE,null, ex);
            
        }
    }
}
