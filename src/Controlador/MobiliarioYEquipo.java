/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Conexion.Conexion;
import ConsultasSQL.QuerysMobiliarioyEquipo;
import java.sql.Connection;
import Vistas.MostrarMobiliarioyEquipo;
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
 * @author Bucardo
 */
public class MobiliarioYEquipo {
    private static Conexion con = new Conexion();
    private static Connection conexion = con.getConexion();
    private static PreparedStatement ps = null;
    
    private static final int filasxPagina = 20;
    
    public static int NumeroPages(String buscar, int paginaActual, String estado){
        
        String textoEstado = "";
            if(estado.equals("Todos")){
                textoEstado = "";
            }if(estado.equals("Nuevo")){
                 textoEstado = " estado = 'nuevo' and ";
            }
            if(estado.equals("Usado")){
                 textoEstado = " estado = 'usado' and ";
            }
            if(estado.equals("Mal estado")){
                 textoEstado = " estado = 'mal estado' and ";
            }
            if(estado.equals("Inhabilitado")){
                 textoEstado = " estado = 'inhabilitado' and ";
            }
            String sql = "";
            sql = "SELECT count(*) from mobiliarioyequipo WHERE "+textoEstado+" (nombre like '%"+buscar+"%')";
            
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
    
    public static void MostrarMobiliarioyEquipo (String buscar, int paginaActual, int totalPages,String estado){
        DefaultTableModel model  = (DefaultTableModel)MostrarMobiliarioyEquipo.tblMostrarMobiliarioyEquipo.getModel();
         String textoEstado = "";
            if(estado.equals("")){
                textoEstado = "";
            }
            if(estado.equals("Nuevo")){
                textoEstado = " estado = 'nuevo' and ";
            }
            if(estado.equals("Usado")){
                 textoEstado = " estado = 'usado' and ";
            }
            if(estado.equals("Mal estado")){
                 textoEstado = " estado = 'mal estado' and ";
            }
            if(estado.equals("Inhabilitado")){
                 textoEstado = " estado = 'inhabilitado' and ";
            }
            while (model.getRowCount()> 0){
              model.removeRow(0);    
            }
            
        String sql= "";
         
        if (buscar.isEmpty()){
            sql = "select * from mobiliarioyequipo WHERE "+textoEstado+" nombre like ('%" + buscar + "%') limit " +
                    filasxPagina + " offset " + (paginaActual - 1) * filasxPagina;
            MostrarMobiliarioyEquipo.siguiente.setVisible(true);
            MostrarMobiliarioyEquipo.Previo.setVisible(true);
            MostrarMobiliarioyEquipo.seguimiento.setVisible(true);
        } else {
            sql = "select * from mobiliarioyequipo WHERE "+textoEstado+" nombre like '%" + buscar + "%'";
            
        }
        
        String datos[] = new String[7];
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
                datos[2] = rs.getString("cantidad");
                datos[3] = rs.getString("estado");
                datos[4] = rs.getString("descripcion");
                datos[5] = rs.getString("fechadeadquisicion");
                datos[6] = rs.getString("id");
                model.addRow(datos);
                count++;
            }
             int totalRows = count - 1; // Restamos el encabezado de la tabla
             totalPages = NumeroPages(buscar,paginaActual,estado);
             MostrarMobiliarioyEquipo.seguimiento.setText("Página " + paginaActual + " de " + totalPages);
             DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
             tcr.setHorizontalAlignment(SwingConstants.RIGHT);
             MostrarMobiliarioyEquipo.tblMostrarMobiliarioyEquipo.setModel(model);
          
        }catch (SQLException ex){
            Logger.getLogger(MobiliarioYEquipo.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    
    //guardar departamento
    public static boolean Guardar(QuerysMobiliarioyEquipo qp){
        String sql = QuerysMobiliarioyEquipo.CREATES;
        
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1,qp.getNombre());
             ps.setString(2,qp.getCantidad());
            ps.setString(3,qp.getEstado());
            ps.setString(4,qp.getDescripcion());
            ps.setString(5,qp.getFechadeadquisicion());
            
            
            ps.executeUpdate(); // Ejecutar la consulta de inserción en la base de datos
            return true;
        } catch (SQLException ex) {
            return false; // Indicar que ocurrió un error durante el guardado
//            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static boolean Editar(QuerysMobiliarioyEquipo qp) {
     
    String sql = QuerysMobiliarioyEquipo.UPDATES; // Obtener la consulta SQL para modificar un desde QuerysProductos
        try {
            ps = conexion.prepareStatement(sql); // Preparar la sentencia SQL
            
            ps.setString(1,qp.getNombre());
            ps.setString(2,qp.getCantidad());
            ps.setString(3,qp.getEstado());
            ps.setString(4,qp.getDescripcion());
            ps.setString(5, qp.getFechadeadquisicion());
            ps.setInt(6,qp.getId());//   se agrego aqui tembien                            Establecer id en la consulta  

            ps.executeUpdate(); // Ejecutar la consulta de inserción en la base de datos* actualiza la información de la base de datos
            return true; // Indicar que el guardado fue exitoso
        } catch (SQLException ex) {
            return false; // Indicar que ocurrió un error durante el guardado
//         Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

