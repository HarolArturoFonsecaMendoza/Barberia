/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.Conexion;
import ConsultasSQL.QuerysClientes;
import ConsultasSQL.QuerysManicura;
import static Controlador.Clientes.NumeroPages;
import Vistas.MostrarClientes;
import Vistas.Mostrarmanicuraypedicura;
import com.mysql.cj.jdbc.Blob;
//import Conexion.Conexion;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Josue
 */
public class Manicura {
    private static Conexion con = new Conexion();
    private static Connection conexion = con.getConexion();
    private static PreparedStatement ps = null;
    private static final int filasxPagina = 20;
    
    public static List ids = new ArrayList();
    
     public static List<QuerysManicura> MostrarManicura() {
        List<QuerysManicura> lista = new ArrayList<>();
        try {

            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(QuerysManicura.MostraManicura);
               QuerysManicura  manicura = new QuerysManicura();
            while (rs.next()) {
                manicura.setEstilo(rs.getString("estilo"));
                manicura.setEsmaltado(rs.getString("esmaltado"));
                manicura.setId(rs.getInt("id"));
                manicura.setPrecio(rs.getDouble("precio"));
                manicura.setTecnica(rs.getString("tecnica"));
                manicura.setDescripcion(rs.getString("descripcion"));
                manicura.setFoto(rs.getBytes("foto1"));
                manicura.setFoto1(rs.getBytes("foto2"));
                lista.add(manicura);
            }
         
            return lista;
            
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            lista = new ArrayList<>();
            return lista;
        }

    }
    
    public static boolean Guardar(QuerysManicura qp) {
        String sql = QuerysManicura.RegistraManicura;

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, qp.getEstilo());
            ps.setDouble(2, qp.getPrecio());
            ps.setString(3, qp.getTecnica());
            
            Blob blob1 = (Blob) conexion.createBlob();       
            blob1.setBytes(1, qp.getFoto());
            ps.setBlob(4, blob1);
            
            Blob blob2 = (Blob) conexion.createBlob();
            blob2.setBytes(1, qp.getFoto1());
            ps.setBlob(5, blob2);
            
            ps.setString(6, qp.getEsmaltado());
            ps.setString(7, qp.getDescripcion());

            int rowsAffected = ps.executeUpdate();
            
            // Verifica si se insertaron registros con éxito
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime la excepción para depuración
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Imprime la excepción para depuración
            }
        }
    }

 public static void Mostrarmanicuraypedicura(String buscar, int paginaActual, int totalPages) {
        DefaultTableModel model = (DefaultTableModel) Mostrarmanicuraypedicura.tblMostrarmanicuraypedicura.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
            Mostrarmanicuraypedicura.tblMostrarmanicuraypedicura.getColumnModel().getColumn(4).setMaxWidth(0);
           Mostrarmanicuraypedicura.tblMostrarmanicuraypedicura.getColumnModel().getColumn(4).setMinWidth(0);
            Mostrarmanicuraypedicura.tblMostrarmanicuraypedicura.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
            Mostrarmanicuraypedicura.tblMostrarmanicuraypedicura.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
             Mostrarmanicuraypedicura.tblMostrarmanicuraypedicura.getColumnModel().getColumn(5).setMaxWidth(0);
           Mostrarmanicuraypedicura.tblMostrarmanicuraypedicura.getColumnModel().getColumn(5).setMinWidth(0);
            Mostrarmanicuraypedicura.tblMostrarmanicuraypedicura.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
            Mostrarmanicuraypedicura.tblMostrarmanicuraypedicura.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
            
             Mostrarmanicuraypedicura.tblMostrarmanicuraypedicura.getColumnModel().getColumn(8).setMaxWidth(0);
           Mostrarmanicuraypedicura.tblMostrarmanicuraypedicura.getColumnModel().getColumn(8).setMinWidth(0);
            Mostrarmanicuraypedicura.tblMostrarmanicuraypedicura.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
            Mostrarmanicuraypedicura.tblMostrarmanicuraypedicura.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
            
            
                        
        }

        Manicura.ids.clear();

        String sql = "";
        if (buscar.equals("")) {
            sql = QuerysManicura.LISTARMANICURAYPEDICURA + " limit " + filasxPagina + " offset " + (paginaActual - 1) * filasxPagina;

        } else {
            sql = QuerysManicura.LISTARMANICURAYPEDICURA + " WHERE estilo like '%" + buscar + "%' or esmaltado like '%" + buscar + "%' or diseno like '%" + buscar + "%' or "
                    + "tecnica like '%" + buscar + "%' limit " + filasxPagina + " offset " + (paginaActual - 1) * filasxPagina;
        }

        String datos[] = new String[9];

        try {

            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            int count = 0;
            if (paginaActual == 1) {
                count = 1;
            } else {
                for (int i = 1; i < paginaActual; i++) {
                    count += 20;
                }
                count += 1;
            }

            while (rs.next()) {
                datos[0] = count + "";
                datos[1] = rs.getString("estilo");
                datos[2] = rs.getString("precio");
                datos[3] = rs.getString("tecnica");
                datos[4] = rs.getString("foto1");
                datos[5] = rs.getString("foto2");
                datos[6] = rs.getString("esmaltado");
                datos[7] = rs.getString("descripcion");
                datos[8] = rs.getString("id");
                Manicura.ids.add(rs.getInt("id"));
               

                model.addRow(datos);

                count++;

            }
            int totalRows = count - 1; // Restamos el encabezado de la tabla
            totalPages = (NumeroPages(buscar) == 0 && model.getRowCount() > 0) ? 1 : NumeroPages(buscar);
            paginaActual = model.getRowCount() == 0 ? 0 : paginaActual;
            Mostrarmanicuraypedicura.seguimiento.setText("Página " + paginaActual + " de " + totalPages);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(SwingConstants.RIGHT);
            Mostrarmanicuraypedicura.tblMostrarmanicuraypedicura.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

  public static QuerysManicura TraerManicura(int id) {

        try {
            Statement st = conexion.createStatement();

            ps = conexion.prepareStatement(QuerysManicura.TraerManicura);
            ps.setInt(1, id);
            QuerysManicura manicura = new QuerysManicura();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                
               
                manicura.setEstilo(rs.getString("estilo"));
                manicura.setPrecio(rs.getDouble("precio"));
                manicura.setTecnica(rs.getString("tecnica"));
                manicura.setFoto(rs.getBytes("foto1"));
                manicura.setFoto1(rs.getBytes("foto2"));
                 manicura.setEsmaltado(rs.getString("esmaltado"));
                manicura.setDescripcion(rs.getString("descripcion"));
                
                manicura.setId(rs.getInt("id"));
            }
            return manicura;
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            return new QuerysManicura();
        }

    }


    public static int NumeroPages(String string) {
         String sql = "";
        if (string.isEmpty()) {
            string = "";
            sql = "SELECT count(*) from manicuraypedicura";

        } else {

            sql = "SELECT count(*) from manicuraypedicura WHERE estilo like '%" + string + "%' or esmaltado like '%" + string + "%' or diseno like '%" + string + "%' or "
                    + "tecnica like '%" + string + "%' ";

        }

        try {
            Statement st = conexion.createStatement();

            ps = conexion.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int totalPages = (int) Math.ceil((double) rs.getInt(1) / filasxPagina);
                return totalPages;
            } else {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public static class ids {

        public ids() {
        }
    }


    public static boolean Editar(QuerysManicura qp) {
     String sql = QuerysManicura.ActualizarManicura;

    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
        java.sql.Blob blob1 = null;
        java.sql.Blob blob2 = null;

        try {
            blob1 = conexion.createBlob();
            blob2 = conexion.createBlob();

            ps.setString(1, qp.getEstilo());
            ps.setDouble(2, qp.getPrecio());
            ps.setString(3, qp.getTecnica());

            java.sql.Blob foto1Actual = obtenerFotoActualDesdeBD(qp.getId(), "foto1");
        java.sql.Blob foto2Actual = obtenerFotoActualDesdeBD(qp.getId(), "foto2");

        // Manejar la primera imagen
        if (qp.getFoto() != null) {
            blob1.setBytes(1, qp.getFoto());
            ps.setBlob(4, blob1);
        } else {
            // Si no hay una nueva imagen, mantener la imagen actual
            ps.setBlob(4, foto1Actual);
        }

        // Manejar la segunda imagen
        if (qp.getFoto1() != null) {
            blob2.setBytes(1, qp.getFoto1());
            ps.setBlob(5, blob2);
        } else {
            // Si no hay una nueva imagen, mantener la imagen actual
            ps.setBlob(5, foto2Actual);
        }
          

            ps.setString(6, qp.getEsmaltado());
            ps.setString(7, qp.getDescripcion());
            ps.setInt(8, qp.getId());

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar en la base de datos: " + ex.getMessage(), "Error al guardar", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            if (blob1 != null) {
                blob1.free();
            }
            if (blob2 != null) {
                blob2.free();
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al guardar en la base de datos: " + ex.getMessage(), "Error al guardar", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}

// Método para verificar si una imagen ha cambiado
private static java.sql.Blob obtenerFotoActualDesdeBD(int id, String columna) throws SQLException {
    String sql = "SELECT " + columna + " FROM manicuraypedicura WHERE id = ?";
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
}

