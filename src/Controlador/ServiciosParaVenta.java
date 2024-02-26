/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.Conexion;
import ConsultasSQL.QuerysCortes;
import ConsultasSQL.QuerysManicura;
import ConsultasSQL.QuerysTratamiento;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import Vistas.ServicioCorte;
import Vistas.ServicioManicurayPedicura;
import Vistas.ServicoTratamiento;

public class ServiciosParaVenta {
    // Establecer la conexión a la base de datos
    private static Conexion con = new Conexion();
    private static Connection conexion = con.getConexion();
    private static PreparedStatement ps = null;
    
    public static void ServicioDeCorte(String buscar){
        DefaultTableModel model = (DefaultTableModel)ServicioCorte.tblCortes.getModel();
        while (model.getRowCount() > 0 ){
                model.removeRow(0);
        }
        
         String sql = "";
            if(buscar.equals("")){
                sql = QuerysCortes.LISTARCORTES;
                
            }else{
                sql = "select * from catalogo_cortes  WHERE nombre_corte like '%" + buscar + "%' ";
                
            }

            String datos[] = new String[6];
        
        try{
        
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
           
            int count = 1;
            while(rs.next()){
                datos[0] = count+"";
                datos[1] = rs.getString("nombre_corte");
                datos[2] = rs.getString("precio");
                datos[3] = rs.getString("id");
                datos[4] = "Corte";
                model.addRow(datos);
                
                DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
                tcr.setHorizontalAlignment(SwingConstants.RIGHT);
                ServicioCorte.tblCortes.setModel(model);
                count++;
              
                
            }
           // MOSTRARCARGO.tblCa.setModel(modelo);//la tabla se actualiza. HacerCalculos(r);
        }catch (SQLException ex){
           Logger.getLogger(ServicioCorte.class.getName()).log(Level.SEVERE,null, ex);
            
        }
    }
    
    public static void ServicioDeManicura(String buscar){
        DefaultTableModel model = (DefaultTableModel)ServicioManicurayPedicura.tblManicura.getModel();
        while (model.getRowCount() > 0 ){
                model.removeRow(0);
        }
        
         String sql = "";
            if(buscar.equals("")){
                sql = QuerysManicura.LISTARMANICURAYPEDICURA;
                
            }else{
                sql = "select * from manicuraypedicura  WHERE estilo like '%" + buscar + "%' ";
                
            }

            String datos[] = new String[6];
        
        try{
        
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
           
            int count = 1;
            while(rs.next()){
                datos[0] = count+"";
                datos[1] = rs.getString("estilo");
                datos[2] = rs.getString("precio");
                datos[3] = rs.getString("id");
                datos[4] = "Manicura";
                model.addRow(datos);
                
                DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
                tcr.setHorizontalAlignment(SwingConstants.RIGHT);
                ServicioManicurayPedicura.tblManicura.setModel(model);
                count++;
              
                
            }
           // MOSTRARCARGO.tblCa.setModel(modelo);//la tabla se actualiza. HacerCalculos(r);
        }catch (SQLException ex){
           Logger.getLogger(ServicioManicurayPedicura.class.getName()).log(Level.SEVERE,null, ex);
            
        }
    }
     public static void ServicioDeTratamiento(String buscar){
        DefaultTableModel model = (DefaultTableModel)ServicoTratamiento.tblTratamiento.getModel();
        while (model.getRowCount() > 0 ){
                model.removeRow(0);
        }
        
         String sql = "";
            if(buscar.equals("")){
                sql = QuerysTratamiento.ListarTratamiento;
                
            }else{
                sql = "select * from tratamientos  WHERE nombre like '%" + buscar + "%' ";
                
            }

            String datos[] = new String[6];
        
        try{
        
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
           
            int count = 1;
            while(rs.next()){
                datos[0] = count+"";
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("precio");
                datos[3] = rs.getString("tipoTratamiento");

                datos[4] = rs.getString("id");
                 datos[5] = "tratamiento";
                model.addRow(datos);
                
                DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
                tcr.setHorizontalAlignment(SwingConstants.RIGHT);
                ServicoTratamiento.tblTratamiento.setModel(model);
                count++;
              
                
            }
           // MOSTRARCARGO.tblCa.setModel(modelo);//la tabla se actualiza. HacerCalculos(r);
        }catch (SQLException ex){
           Logger.getLogger( ServicoTratamiento.class.getName()).log(Level.SEVERE,null, ex);
            
        }
    }  
    //public static String VerManicura = "SELECT estilo, precio, tecnica, foto1, foto2, esmaltado, descripcion,diseno FROM catalogo_cortes WHERE id = ?";

}
