/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.Conexion;
import ConsultasSQL.QuerysCita;
import Vistas.MostrarCitas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Andrea
 */
public class Citas {
    private static Conexion con = new Conexion();
    private static Connection conexion = con.getConexion();
    private static PreparedStatement ps = null;
    
    private static final int filasxPagina = 20;
    
    public static boolean Guardar(QuerysCita qp){
        String sql= QuerysCita.RegistrarCitas;
        try{
            ps = conexion.prepareStatement(sql);
           ps.setInt(1,qp.getFk_nombre());
           ps.setString(2,qp.getTelefono());
           ps.setString(3,qp.getTipo_cliente());
           ps.setString(4,qp.getServicios());
           ps.setInt(5,qp.getFk_Empleado());
           ps.setString(6,qp.getFecha());
           ps.setString(7, qp.getHora());
           ps.setString(8, qp.getNotas());
           ps.executeUpdate();
           return true;



        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
    }  
     public static boolean Editar(QuerysCita qp){
        String sql= QuerysCita.EditarCitas;
        try{
            ps = conexion.prepareStatement(sql);
           ps.setInt(2,qp.getFk_nombre());
           ps.setString(3,qp.getTelefono());
           ps.setString(4,qp.getTipo_cliente());
           ps.setString(5,qp.getServicios());
           ps.setInt(6,qp.getFk_Empleado());
           ps.setString(7,qp.getFecha());
           ps.setString(8, qp.getHora());
           ps.setString(9, qp.getNotas());
           ps.setInt(1, qp.getId());
           ps.executeUpdate();
           return true;



        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
    } 
  
     public static boolean verificarDisponibilidadBD(int nombre, String servicio, int empleado, String fecha, String hora) {
        try {
            String sql = "SELECT COUNT(*) FROM citas WHERE fecha = ? AND hora = ? AND servicios = ? AND empleado = ? AND nombre = ?";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, fecha);
            ps.setString(2, hora);
            ps.setString(3, servicio);
            ps.setInt(4, empleado);
            ps.setInt(5, nombre);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0; // Si count es 0, la hora está disponible
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return false; // En caso de error, asumimos que la hora está ocupada
    }
    
    public static int NumeroPages(String buscar, int paginaActual,String estado){
        
        String textoEstado = "";
        if(estado.equals("Todos")){
            textoEstado = "";
        }
        if(estado.equals("Corte")){
            textoEstado = " servicios = 'Corte' and ";
        }
        if(estado.equals("Tratamiento")){
            textoEstado = " servicios = 'Tratamiento' and ";
        }
        if(estado.equals("Manicura y pedicura")){
            textoEstado = " servicios = 'Manicura y pedicura' and ";
        }
        String sql = ""; 
        sql = "SELECT count(*) from citas WHERE "+textoEstado+" (nombre like concat('%','"+buscar+"','%') or "
        + "tipo_cliente like '%"+buscar+"%' or " + "servicios like '%"+buscar+"%' or " + "fecha like '%"+buscar+"%'"
            + "or " + "hora like '%"+buscar+"%' or " + "telefono like '%"+buscar+"%')";

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
    public static String convertirFormatoHora(String hora24) {
    try {
        SimpleDateFormat formato24 = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat formato12 = new SimpleDateFormat("h:mm a", Locale.ENGLISH);

        Date horaDate = formato24.parse(hora24);
        return formato12.format(horaDate);
    } catch (ParseException ex) {
        ex.printStackTrace();
        return hora24; // Devuelve la hora sin cambios en caso de error
    }
}


    public static void MostrarCitas(String buscar, int paginaActual, int totalPages,String estado){
        DefaultTableModel model = (DefaultTableModel)MostrarCitas.tblMostraCitas.getModel();
        String textoEstado = "";
        
        if(estado.equals("Todos")){
            textoEstado = "";
        }
        if(estado.equals("Corte")){
            textoEstado = " servicios = 'Corte' and ";
        }
        if(estado.equals("Tratamiento")){
            textoEstado = " servicios = 'Tratamiento' and ";
        }
        if(estado.equals("Manicura y pedicura")){
            textoEstado = " servicios = 'Manicura y pedicura' and ";
        }

        while (model.getRowCount() > 0 ){
            model.removeRow(0);  
        }
    
        String sql = "select citas.id,(clientes.nombre) as nombre, citas.tipo_cliente,citas.telefono, citas.servicios,\n" +
        " (empleados.nombre)as nombre_empleado,citas.fecha, citas.hora, citas.notas \n" +
        " from citas join clientes on citas.nombre = clientes.id join empleados on citas.empleado = empleados.id;";

        if (buscar.isEmpty()) {
            sql = "select citas.id, (clientes.nombre) as nombre, citas.tipo_cliente, citas.telefono, citas.servicios, (empleados.nombre)as nombre_empleado, \n" +
            "citas.fecha, citas.hora, citas.notas from citas join clientes on citas.nombre = clientes.id join empleados on citas.empleado = empleados.id  WHERE "+textoEstado+" (clientes.nombre like concat('%','"+buscar+"','%') or "
            + "(empleados.nombre) like '%"+buscar+"%' or " + "servicios like '%"+buscar+"%'or " + "fecha like '%"+buscar+"%'"
            + "or " + "hora like '%"+buscar+"%' or " + "citas.telefono like '%"+buscar+"%') limit " + filasxPagina + " offset " + (paginaActual - 1) * filasxPagina;
            MostrarCitas.siguiente.setVisible(true);
            MostrarCitas.Previo.setVisible(true);
            MostrarCitas.seguimiento.setVisible(true);
        } else {
            sql = "select citas.id, (clientes.nombre) as nombre, citas.tipo_cliente, citas.telefono, citas.servicios, (empleados.nombre)as nombre_empleado, \n" +
            "citas.fecha, citas.hora, citas.notas from citas join clientes on citas.nombre = clientes.id join empleados on citas.empleado = empleados.id  WHERE "+textoEstado+" (clientes.nombre like concat('%','"+buscar+"','%') or "
            + "(empleados.nombre) like '%"+buscar+"%' or " + "servicios like '%"+buscar+"%'or " + "fecha like '%"+buscar+"%'"
            + "or " + "hora like '%"+buscar+"%' or " + "citas.telefono like '%"+buscar+"%')";
            MostrarCitas.siguiente.setVisible(true);
            MostrarCitas.Previo.setVisible(true);
            MostrarCitas.seguimiento.setVisible(true);
        }
    
        String datos[] = new String[10];
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
                datos[1] = rs.getString("id");
                datos[2] = rs.getString("nombre");
                datos[3] = rs.getString("tipo_cliente");
                datos[4] = rs.getString("telefono");
                datos[5] = rs.getString("servicios");
                datos[6] = rs.getString("nombre_empleado");
                datos[7] = rs.getString("fecha");
                datos[8] = rs.getString("hora");
                datos[9] = rs.getString("notas");
                model.addRow(datos);
                //MostrarEmpleados.tblMostrarEmpleados.getColumnModel().getColumn(4).setCellRenderer(tcr);
                count++;
            }
            int totalRows = count; // Restamos el encabezado de la tabla
            totalPages = NumeroPages(buscar,paginaActual,estado);
            totalPages = (int) Math.ceil((double) totalRows / filasxPagina);
            MostrarCitas.seguimiento.setText("Página " + paginaActual + " de " + totalPages);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            MostrarCitas.tblMostraCitas.setModel(model);     
        }catch (SQLException ex){
            java.util.logging.Logger.getLogger(Citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    //Metodo para eliminar el corte
    public static void eliminarCita(){
        int fila = MostrarCitas.tblMostraCitas.getSelectedRow();
        String valor = MostrarCitas.tblMostraCitas.getValueAt(fila, 1).toString();
        
        try {
            PreparedStatement eliminar = conexion.prepareStatement("DELETE FROM citas WHERE"
                    + " id = '"+valor+"'");
            eliminar.executeUpdate();
            MostrarCitas(valor, fila, fila, valor);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + "No se logro eliminar el corte");
        }
    }


    public static int obtenerPaginaActual() {
        // Implementa la lógica para obtener la página actual de tu aplicación
        // Puede depender de cómo estás manejando la paginación en tu interfaz de usuario
        // Devuelve la página actual como un entero
        return 1; // Cambia esto según tu implementación
    }
}
