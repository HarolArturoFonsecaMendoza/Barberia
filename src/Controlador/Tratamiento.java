/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.Conexion;
import ConsultasSQL.QuerysTratamiento;
import ConsultasSQL.QuerysTratamientoProductos;
import Vistas.EditarTratamiento;
import Vistas.MostrarTratamientos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Josue
 */
public class Tratamiento {
    private static Conexion con = new Conexion();
    private static Connection conexion = con.getConexion();
    private static PreparedStatement ps = null;
    Statement stmt = null;
    ResultSet rs = null;

    
    public static boolean ProductoAsociadoATratamiento(int idTratamiento, int idProducto) {
        
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM tratamientos_productos WHERE id_tratamientos = ? AND id_productos = ?";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, idTratamiento);
            statement.setInt(2, idProducto);
            resultSet = statement.executeQuery();

            return resultSet.next(); // Devuelve true si existe la asociación, false si no
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    // Declarar una variable para establecer el tamaño de la página (número de elementos a mostrar por página)
    public static final int PAGE_SIZE = 5;
    // Declarar una variable para almacenar el número de página actual
    public static int currentPage = 1;
    // Declarar una variable para almacenar el número total de páginas
    public static int totalPages = 1;


    public static boolean GuardarT(QuerysTratamiento qp) {
        String sqlT = QuerysTratamiento.RegistroTratamiento;

        try {
            // Preparar la declaración para sqlT
            PreparedStatement psT = conexion.prepareStatement(sqlT);
            psT.setString(1, qp.getNombre());
            psT.setString(2, qp.getTipoTratamiento());
            psT.setString(3, qp.getDescripcion());

            // Crear blobs para las imágenes
            Blob blob1 = conexion.createBlob();
            blob1.setBytes(1, qp.getFoto1());
            Blob blob2 = conexion.createBlob();
            blob2.setBytes(1, qp.getFoto2());

            // Establecer los blobs en la declaración para sqlT
            psT.setBlob(4, blob1);
            psT.setBlob(5, blob2);
            
            psT.setString(6, qp.getEstado());
            psT.setDouble(7, qp.getPrecio());
            // Ejecutar la declaración para sqlT
            psT.executeUpdate();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } 
    }
    public static boolean GuardarTP(QuerysTratamientoProductos qpp) {
        String sqlTP = QuerysTratamientoProductos.RegistroTratamiento_productos;

        try {
            // Preparar la declaración para sqlTP
            PreparedStatement psTP = conexion.prepareStatement(sqlTP);

            // Establecer los valores en la declaración para sqlTP

            psTP.setInt(1, qpp.getId_tratamientos());
            psTP.setInt(2, qpp.getId_productos());
            

            // Ejecutar la declaración para sqlTP
            psTP.executeUpdate();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } 
    }
     
    public static void verPt() {
        DefaultTableModel model = (DefaultTableModel) Vistas.VerTratamiento.tblProductosDeTratamiento.getModel();
        model.setRowCount(0);
    
        String sql = QuerysTratamientoProductos.verPtratamientos;
        String datos[] = new String[4];  // Cambiado de 5 a 4
    
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery()) {

            int count = 1;

            while (rs.next()) {
                datos[0] = count+"";
                datos[1] = rs.getString("id");
                datos[2] = rs.getString("id_tratamientos");
                datos[3] = rs.getString("nombre_producto");
                model.addRow(datos);
                count++;
            }

            // No es necesario establecer el modelo de la tabla aquí
            // Vistas.VerTratamiento.tblProductosDeTratamiento.setModel(model);

        } catch (SQLException ex) {
            Logger.getLogger(Tratamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void verPtPE() {
        DefaultTableModel model = (DefaultTableModel) Vistas.EditarTratamiento.tblProductosDeTratamiento.getModel();
        model.setRowCount(0);
    
        String sql = QuerysTratamientoProductos.verPtratamientos;
        String datos[] = new String[2];  // Cambiado de 5 a 4
    
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery()) {

            int count = 1;

            while (rs.next()) {
                //datos[0] = count+"";
                //datos[2] = rs.getString("id_tratamientos");
                datos[0] = rs.getString("nombre_producto");
                datos[1] = rs.getString("id");
                datos[2] = rs.getString("id_tratamientos");
                
                model.addRow(datos);
                count++;
            }

            // No es necesario establecer el modelo de la tabla aquí
            // Vistas.VerTratamiento.tblProductosDeTratamiento.setModel(model);

        } catch (SQLException ex) {
            Logger.getLogger(Tratamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void MostrarTratamientos(){
        DefaultTableModel model = (DefaultTableModel)MostrarTratamientos.tblMostrarTratamiento.getModel();
        model.setRowCount(0);
            
        String sql = QuerysTratamiento.ListarTratamiento;

       
            
        String datos[] = new String[9];
        try{
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            int count = 1;
            
            
           
            while(rs.next()){
                datos[0] = count+"";
                datos[1] = rs.getString("id");
                datos[2] = rs.getString("Nombre");
                datos[3] = rs.getString("tipoTratamiento");
                datos[4] = rs.getString("Descripcion");
                datos[5] = rs.getString("Foto1");
                datos[6] = rs.getString("Foto2");
                datos[7] = rs.getString("estado");
                datos[8] = rs.getString("precio");
                model.addRow(datos);
                //MostrarEmpleados.tblMostrarEmpleados.getColumnModel().getColumn(4).setCellRenderer(tcr);
                count++;
            }
            MostrarTratamientos.tblMostrarTratamiento.setModel(model);
            
                
        }catch (SQLException ex){
            Logger.getLogger(Tratamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean ActualizarTratamiento(QuerysTratamiento qp){
        String sql = QuerysTratamiento.ActualizarTratamiento;
        try(PreparedStatement ps = conexion.prepareStatement(sql)) {
            Blob blob1 = null;
            Blob blob2 = null;
            
            try {
                blob1 = conexion.createBlob();
                blob2 = conexion.createBlob();
                
                ps.setString(1, qp.getNombre());
                ps.setString(2, qp.getTipoTratamiento());
                ps.setString(3, qp.getDescripcion());
                Blob foto1Actual = obtenerFotoActualDesdeBD(qp.getId(), "Foto1");
                Blob foto2Actual = obtenerFotoActualDesdeBD(qp.getId(), "Foto2");
                
                // Manejar la primera imagen
                if (qp.getFoto1() != null) {
                    blob1.setBytes(1, qp.getFoto1());
                    ps.setBlob(4, blob1);
                } else {
                    // Si no hay una nueva imagen, mantener la imagen actual
                    ps.setBlob(4, foto1Actual);
                }
                
                // Manejar la segunda imagen
                if (qp.getFoto2() != null) {
                    blob2.setBytes(1, qp.getFoto2());
                    ps.setBlob(5, blob2);
                } else {
                    // Si no hay una nueva imagen, mantener la imagen actual
                    ps.setBlob(5, foto2Actual);
                }
                
                ps.setString(6, qp.getEstado());
                ps.setDouble(7, qp.getPrecio());
                ps.setInt(8, qp.getId());
                
                int rowsAffected = ps.executeUpdate();
                
                return rowsAffected > 0;
                
                
                
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al editar en la base de datos: " + ex.getMessage(), "Error al guardar", JOptionPane.ERROR_MESSAGE);
                 return false;
            }
            
            finally {
                if (blob1 != null) {
                    blob1.free();
                }
                if (blob2 != null) {
                    blob2.free();
                }
            }   
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al editar en la base de datos: " + ex.getMessage(), "Error al guardar", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    private static Blob obtenerFotoActualDesdeBD(int id, String columna) throws SQLException {
        String sql = "SELECT " + columna + " FROM  tratamientos WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBlob(columna);
                }
            }
        }
        return null;
    }
    
    public static boolean ActualizarTratamientoProductos(QuerysTratamientoProductos qpp) {
        String sqlTP = QuerysTratamientoProductos.ActualizarTratamientoProductos;

        try {
            // Preparar la declaración para sqlTP
            PreparedStatement psTP = conexion.prepareStatement(sqlTP);

            // Establecer los valores en la declaración para sqlTP
            psTP.setInt(1, qpp.getId_tratamientos());
            psTP.setInt(2, qpp.getId_productos());
            //psTP.setInt(3, qpp.getId()); // Asumo que el ID está disponible en el objeto QuerysTratamientoProductos

            // Ejecutar la declaración para sqlTP
            psTP.executeUpdate();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    
    // Método para eliminar productos asociados a un tratamiento
    public static void EliminarProductosDeTratamiento(String idTratamiento) {
        Conexion con = new Conexion();
        Connection conexion = con.getConexion();
        PreparedStatement ps = null;
        System.out.println(idTratamiento);
        String sql = "DELETE FROM tratamientos_productos WHERE id = ?";

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, idTratamiento);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Tratamiento.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar recursos
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(Tratamiento.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

}


