/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.Conexion;
import ConsultasSQL.QuerysEmpleados;
import ConsultasSQL.QuerysEmpleados;
import Vistas.MostrarEmpleados;
import Vistas.MostrarEmpleados;
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
 * @author Josue
 */
public class Empleados {
    private static Conexion con = new Conexion();
    private static Connection conexion = con.getConexion();
    private static PreparedStatement ps = null;
    
    private static final int filasxPagina = 20;   
    
    
    public static int NumeroPages(String buscar, int paginaActual,String estado){
        
        String textoEstado = "";
            if(estado.equals("Todos")){
                textoEstado = "";
            }
            if(estado.equals("Activos")){
                textoEstado = " estado = 'Activo' and ";
            }
            if(estado.equals("Inactivos")){
                 textoEstado = " estado = 'Inactivo' and ";
            }
            String sql = ""; 
            sql = "SELECT count(*) from empleados WHERE "+textoEstado+" (nombre like concat('%','"+buscar+"','%') or "
                + "apellido like '%"+buscar+"%')";
        
        try{
             Statement st = conexion.createStatement();
            
            ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int totalPages = (int) Math.ceil((double) rs.getInt(1) / filasxPagina);
                return totalPages;
            } else {
                return 0;
            }
        }catch (SQLException ex){
            Logger.getLogger(Proveedores.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        
    }
   
    public static void MostrarEmpleados(String buscar, int paginaActual, int totalPages,String estado){
        DefaultTableModel model = (DefaultTableModel)MostrarEmpleados.tblMostrarEmpleados.getModel();
         String textoEstado = "";
            if(estado.equals("Todos")){
                textoEstado = "";
            }
            if(estado.equals("Activos")){
                textoEstado = " estado = 'Activo' and ";
            }
            if(estado.equals("Inactivos")){
                 textoEstado = " estado = 'Inactivo' and ";
            }
            while (model.getRowCount() > 0 ){
                model.removeRow(0);  
            }
            
        String sql;

        if (buscar.isEmpty()) {
            sql = "select * from empleados  WHERE "+textoEstado+" (nombre like concat('%','"+buscar+"','%') or "
            + "apellido like '%"+buscar+"%') limit " + filasxPagina + " offset " + (paginaActual - 1) * filasxPagina;
            MostrarEmpleados.siguiente.setVisible(true);
            MostrarEmpleados.Previo.setVisible(true);
            MostrarEmpleados.seguimiento.setVisible(true);
        } else {
            sql = "select * from empleados WHERE "+textoEstado+" (nombre like concat('%','"+buscar+"','%') or "
            + "apellido like '%"+buscar+"%')";
            MostrarEmpleados.siguiente.setVisible(true);
            MostrarEmpleados.Previo.setVisible(true);
            MostrarEmpleados.seguimiento.setVisible(true);
        }
            
        String datos[] = new String[11];
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
                datos[0] = count+"";
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("apellido");
                datos[3] = rs.getString("identidad");
                datos[4] = rs.getString("telefono");
                datos[5] = rs.getString("cargo");
                datos[6] = rs.getString("estado");
                datos[7] = rs.getString("direccion");
                datos[8] = rs.getString("nombreEmergencia");
                datos[9] = rs.getString("telefonoemergencia");
                datos[10] = rs.getString("id");
                model.addRow(datos);
                //MostrarEmpleados.tblMostrarEmpleados.getColumnModel().getColumn(4).setCellRenderer(tcr);
                count++;
            }
            int totalRows = count - 1; // Restamos el encabezado de la tabla
            totalPages = NumeroPages(buscar,paginaActual,estado);
            MostrarEmpleados.seguimiento.setText("Página " + paginaActual + " de " + totalPages);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(SwingConstants.RIGHT);
            MostrarEmpleados.tblMostrarEmpleados.setModel(model);
           
           // MOSTRARCARGO.tblCa.setModel(modelo);//la tabla se actualiza. HacerCalculos(r);
        }catch (SQLException ex){
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean ValidarTelefon(String buscar){
            String sql = ""; 
            sql = "SELECT * FROM empleados WHERE telefono=?";
        
        try{
             Statement st = conexion.createStatement();
            
            ps = conexion.prepareStatement(sql);
            ps.setString(1, buscar);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        }catch (SQLException ex){
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public static boolean ValidarTelefonoEditar(String buscar, int idEditar) {
    String sql = "SELECT * FROM empleados WHERE telefono=? AND id<>?";
        
    try {
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, buscar);
        ps.setInt(2, idEditar);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            return true; // Existe otro registro con el mismo número de teléfono (excluyendo el registro en edición)
        } else {
            return false; // No se encontraron otros registros con el mismo número de teléfono
        }
    } catch (SQLException ex) {
        Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}

    
    public static boolean ValidarIdentidad(String buscar){
            String sql = ""; 
            sql = "SELECT * FROM empleados WHERE identidad=?";
        
        try{
             Statement st = conexion.createStatement();
            
            ps = conexion.prepareStatement(sql);
            ps.setString(1, buscar);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        }catch (SQLException ex){
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
     public static boolean ValidarIdentidadEditar(String buscar, int idEditar) {
    String sql = "SELECT * FROM empleados WHERE identidad=? AND id<>?";
        
    try {
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, buscar);
        ps.setInt(2, idEditar);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            return true; // Existe otro registro con la misma identidad (excluyendo el registro en edición)
        } else {
            return false; // No se encontraron otros registros con la misma identidad
        }
    } catch (SQLException ex) {
        Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}
   
 
    public static boolean Guardar(QuerysEmpleados qp){
        String sql = QuerysEmpleados.RegistraEmpleado;
        
        try{
        ps = conexion.prepareStatement(sql);
        ps.setString(1, qp.getNombre());
        ps.setString(2, qp.getApellido());
        ps.setString(3, qp.getIdentidad());
        ps.setString(4, qp.getTelefono());
        ps.setString(5, qp.getCargo());
        ps.setString(6, qp.getEstado());
        ps.setString(7, qp.getDireccion());
        ps.setString(8, qp.getNombreEmergencia());
        ps.setString(9, qp.getTelefonoemergencia());
        
        
        
        ps.executeUpdate();
        return true;
        } catch (SQLException ex){
            return false;
//            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public static boolean Editar(QuerysEmpleados qp){
        String sql = QuerysEmpleados.ACTUALIZEmpleado;
        
        try{
        ps = conexion.prepareStatement(sql);
        ps.setString(1, qp.getNombre());
        ps.setString(2, qp.getApellido());
        ps.setString(3, qp.getIdentidad());
        ps.setString(4, qp.getTelefono());
        ps.setString(5, qp.getCargo());
        ps.setString(6, qp.getEstado());
        ps.setString(7, qp.getDireccion());
        ps.setString(8, qp.getNombreEmergencia());
        ps.setString(9, qp.getTelefonoemergencia());
        ps.setInt(10, qp.getId());
        
        ps.executeUpdate();
        return true;
        } catch (SQLException ex){
            return false;
//            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
