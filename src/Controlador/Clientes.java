/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.Conexion;
import ConsultasSQL.QuerysClientes;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import ConsultasSQL.QuerysClientes;
import static Controlador.Clientes.MostrarClientes;
import Vistas.MostrarClientes;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alejandra Suárez
 */
public class Clientes {

    private static Conexion con = new Conexion();
    private static Connection conexion = con.getConexion();
    private static PreparedStatement ps = null;
    private static final int filasxPagina = 20;
    
    
    public static List ids = new ArrayList();


    public static int NumeroPages(String buscar) {
        String sql = "";
        if (buscar.isEmpty()) {
            buscar = "";
            sql = "SELECT count(*) from clientes";

        } else {

            sql = "SELECT count(*) from clientes WHERE "
                    + "(nombre like concat('%','" + buscar + "','%') and apellido like concat('%','" + buscar + "','%')) ";

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

    public static void MostrarClientes(String buscar, int paginaActual, int totalPages) {
        DefaultTableModel model = (DefaultTableModel) MostrarClientes.tblMostrarClientes.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
            MostrarClientes.tblMostrarClientes.getColumnModel().getColumn(4).setMaxWidth(0);
            MostrarClientes.tblMostrarClientes.getColumnModel().getColumn(4).setMinWidth(0);
            MostrarClientes.tblMostrarClientes.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
            MostrarClientes.tblMostrarClientes.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
        }

        Clientes.ids.clear();

        String sql = "";
        if (buscar.equals("")) {
            sql = QuerysClientes.LISTARCLIENTES + " limit " + filasxPagina + " offset " + (paginaActual - 1) * filasxPagina;

        } else {
            sql = "select * from clientes  WHERE nombre like '%" + buscar + "%' or "
                    + "apellido like '%" + buscar + "%' limit " + filasxPagina + " offset " + (paginaActual - 1) * filasxPagina;
            //"SELECT * FROM empleados p WHERE UPPER(p.nombreEmpleados) LIKE UPPER('%" + buscar + "%')"
        }

        String datos[] = new String[7];

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
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("apellido");
                datos[3] = rs.getString("telefono");
                datos[4] = rs.getString("direccion");
                datos[5] = rs.getString("fechaNacimiento");
                datos[6] = rs.getString("fechaRegistro");

                Clientes.ids.add(rs.getInt("id"));

                model.addRow(datos);

                count++;

            }
            int totalRows = count - 1; // Restamos el encabezado de la tabla
            totalPages = (NumeroPages(buscar) == 0 && model.getRowCount() > 0) ? 1 : NumeroPages(buscar);
            paginaActual = model.getRowCount() == 0 ? 0 : paginaActual;
            MostrarClientes.seguimiento.setText("Página " + paginaActual + " de " + totalPages);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(SwingConstants.RIGHT);
            MostrarClientes.tblMostrarClientes.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public static boolean Guardar(QuerysClientes qp) {
        String sql = QuerysClientes.RegistrarClientes;
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, qp.getNombre());
            ps.setString(2, qp.getApellido());
            ps.setString(3, qp.getTelefono());
            ps.setString(4, qp.getDireccion());
            ps.setString(5, qp.getFechaNacimiento());
            ps.setString(6, qp.getFechaRegistro());

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
    }

    public static boolean Actualizar(QuerysClientes qp) {
        String sql = QuerysClientes.ActualizarClientes;
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, qp.getNombre());
            ps.setString(2, qp.getApellido());
            ps.setString(3, qp.getTelefono());
            ps.setString(4, qp.getDireccion());
            ps.setString(5, qp.getFechaNacimiento());
            ps.setString(6, qp.getFechaRegistro());
            ps.setInt(7, qp.getId());

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
    }

    public static QuerysClientes TraerCliente(int id) {

        try {
            Statement st = conexion.createStatement();

            ps = conexion.prepareStatement(QuerysClientes.TraerCliente);
            ps.setInt(1, id);
            QuerysClientes clientes = new QuerysClientes();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                clientes.setId(rs.getInt("id"));
                clientes.setNombre(rs.getString("nombre"));
                clientes.setApellido(rs.getString("apellido"));
                clientes.setTelefono(rs.getString("telefono"));
                clientes.setDireccion(rs.getString("direccion"));
                clientes.setFechaNacimiento(rs.getString("fechaNacimiento"));
                clientes.setFechaRegistro(rs.getString("fechaRegistro"));

            }
            return clientes;
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            return new QuerysClientes();
        }

    }

}
