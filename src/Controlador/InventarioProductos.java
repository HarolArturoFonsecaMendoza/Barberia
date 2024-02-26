/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.Conexion;
import ConsultasSQL.QuerysInventarioProductos;
import Vistas.MostrarInventario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

public class InventarioProductos {
    private static Conexion con = new Conexion();
    private static Connection conexion = con.getConexion();
    private static PreparedStatement ps = null;
    public static final int filasxPagina = 20;

    public static int NumeroPages() {
        String sql = "SELECT count(*) FROM inventarioproductos";
        try {
            Statement st = conexion.createStatement();
            ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int totalPages = (int) Math.ceil((double) rs.getInt(1) / filasxPagina);
                return totalPages;
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MostrarInventario.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public static void MostrarProductos(String buscar, int paginaActual) {
        DefaultTableModel model = (DefaultTableModel) MostrarInventario.tblMostrarInventario.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        // Calcular el rango de filas a recuperar para la página actual
        int inicio = (paginaActual - 1) * filasxPagina;
        int fin = inicio + filasxPagina;

        String sql = "SELECT * FROM inventarioproductos WHERE producto LIKE ? OR marca LIKE ? LIMIT ?, ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, "%" + buscar + "%");
            ps.setString(2, "%" + buscar + "%");
            ps.setInt(3, inicio);
            ps.setInt(4, filasxPagina);

            ResultSet rs = ps.executeQuery();

            int count = 1;
            while (rs.next()) {
                String datos[] = new String[7];
                datos[0] = count + "";
                datos[1] = rs.getString("producto");
                datos[2] = rs.getString("marca");
                datos[3] = rs.getString("cantidad");
                datos[4] = rs.getString("precio");
                datos[5] = rs.getString("id");
                datos[6] = rs.getString("precio_venta");
                model.addRow(datos);
                count++;
            }

            // Calcula el número total de páginas
            int totalPages = NumeroPages();

            MostrarInventario.seguimiento.setText("Página " + paginaActual + " de " + totalPages);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(SwingConstants.RIGHT);
            MostrarInventario.tblMostrarInventario.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(InventarioProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

