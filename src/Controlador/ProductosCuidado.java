/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;


import Conexion.Conexion;
import ConsultasSQL.QuerysProductosCuidados;
import Vistas.MostrarProductosCuidado;
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
 * @author Admins
 */
public class ProductosCuidado {
    private static Conexion con = new Conexion();
    private static Connection conexion = con.getConexion();
    private static PreparedStatement ps = null;
    
    private static final int filasxPagina = 20;   
    
        public static int NumeroPages(String buscar, int paginaActual,String categoria){
                
            String textoCategoria = "";
            if(categoria.equals("Todas")){
                textoCategoria = "";
            }else if(categoria.equals("Cuidado corporal")){
                textoCategoria = " categoria = 'Cuidado corporal' and ";
                
            }else if(categoria.equals("Cuidado de cabello")){
                 textoCategoria = " categoria = 'Cuidado de cabello' and ";
                 
            }else if(categoria.equals("Cuidado facial")){
                 textoCategoria = " categoria = 'Cuidado facial' and ";
            }
            String sql = ""; 
                sql = "SELECT count(*) from catalogo_productos WHERE "+textoCategoria+" (nombre like concat('%','"+buscar+"','%') or "
                        + "marca like '%"+buscar+"%')";

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
                Logger.getLogger(ProductosCuidado.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }

        }
        
         public static void MostrarProductosCuidado(String buscar, int paginaActual, int totalPages, String categoria){
        DefaultTableModel model = (DefaultTableModel)MostrarProductosCuidado.tblMostrarProductos.getModel();
           String textoCategoria = "";
            if(categoria.equals("Todas")){
                textoCategoria = "";
            }else if(categoria.equals("Cuidado corporal")){
                textoCategoria = " categoria = 'Cuidado corporal' and ";
                
            }else if(categoria.equals("Cuidado de cabello")){
                 textoCategoria = " categoria = 'Cuidado de cabello' and ";
                 
            }else if(categoria.equals("Cuidado facial")){
                 textoCategoria = " categoria = 'Cuidado facial' and ";
            }     
        
        while(model.getRowCount() > 0 ){
               
            MostrarProductosCuidado.tblMostrarProductos.getColumnModel().getColumn(5).setMaxWidth(0);
            MostrarProductosCuidado.tblMostrarProductos.getColumnModel().getColumn(5).setMinWidth(0);
            MostrarProductosCuidado.tblMostrarProductos.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
            MostrarProductosCuidado.tblMostrarProductos.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);

            model.removeRow(0);
        }
           String sql = "";
           
            if (!buscar.isEmpty()) {
                sql = "select * from catalogo_productos  WHERE "+textoCategoria+" (nombre like concat('%','"+buscar+"','%') or "
                + "marca like '%"+buscar+"%') limit " + filasxPagina + " offset " + (paginaActual - 1) * filasxPagina;
            
                MostrarProductosCuidado.siguiente.setVisible(true);
                MostrarProductosCuidado.Previo.setVisible(true);
                MostrarProductosCuidado.seguimiento.setVisible(true);
            } else {
                sql = "select * from catalogo_productos WHERE "+textoCategoria+" (nombre like concat('%','"+buscar+"','%') or "
                + "marca like '%"+buscar+"%')";
            
                MostrarProductosCuidado.siguiente.setVisible(true);
                MostrarProductosCuidado.Previo.setVisible(true);
                MostrarProductosCuidado.seguimiento.setVisible(true);
            
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
                datos[2] = rs.getString("marca");
                datos[3] = rs.getString("tamano");
                datos[4] = rs.getString("categoria");
                datos[5] = rs.getString("descripcion");
                datos[6] = rs.getString("id");
                
                model.addRow(datos);
                count++;
                totalPages = NumeroPages(buscar,paginaActual,categoria);
                
                MostrarProductosCuidado.seguimiento.setText("Página " + paginaActual + " de " + totalPages);
                DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
                tcr.setHorizontalAlignment(SwingConstants.RIGHT);
                MostrarProductosCuidado.tblMostrarProductos.setModel(model);
                
            }
           
        }catch (SQLException ex){
            Logger.getLogger(MostrarProductosCuidado.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
         
    public static boolean Guardar(QuerysProductosCuidados qp) {
    String sql = QuerysProductosCuidados.RegistraProducto; 

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, qp.getNombre());
            ps.setString(2, qp.getMarca());
            ps.setString(3, qp.getTamano());
            ps.setString(4, qp.getCategoria());
            ps.setString(5, qp.getDescripcion());

            ps.executeUpdate();
            return true; 
        } catch (SQLException ex) {
            Logger.getLogger(ProductosCuidado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
//            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
