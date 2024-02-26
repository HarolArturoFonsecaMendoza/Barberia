/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.Conexion;
import ConsultasSQL.QuerysCortes;
import java.sql.ResultSet;
import Vistas.MostrarCortes;
import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Blob;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Josue
 */
public class TiposCortes {
     private static Conexion con = new Conexion();
    private static Connection conexion = con.getConexion();
    private static PreparedStatement ps = null;
    
    // Declarar una variable para establecer el tamaño de la página (número de elementos a mostrar por página)
    public static final int PAGE_SIZE = 20;
    // Declarar una variable para almacenar el número de página actual
    public static int currentPage = 1;
    // Declarar una variable para almacenar el número total de páginas
    public static int totalPages = 1;
    
    
    public static boolean Guardar(QuerysCortes qp){
       String sql = QuerysCortes.RegistraCortes;

    try {
        ps = conexion.prepareStatement(sql);
        ps.setString(1, qp.getNombre_cortr());
        ps.setString(2, qp.getGenero());

        Blob blob1 = conexion.createBlob();
        blob1.setBytes(1, qp.getCorte());
        ps.setBlob(3, blob1);

        Blob blob2 = conexion.createBlob();
        blob2.setBytes(1, qp.getCorte2());
        ps.setBlob(4, blob2);

        Blob blob3 = conexion.createBlob();
        blob3.setBytes(1, qp.getCorte3());
        ps.setBlob(5, blob3);

        ps.setDouble(6, qp.getPrecio());
        ps.setString(7, qp.getDescripcion());

        ps.executeUpdate();
        return true;
    } catch (SQLException ex) {
        return false;
        // Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
    } 
    
         
    }
    
    //jtextfild para el cuadro de busqueda
    private JTextField txtBusqueda;
    //jlabel para la informacion de la paginacion
    // Declarar el JLabel
    private JLabel lblPageInfo1;
    
    
    public void setup() {
        // Configurar el JLabel y su texto inicial
        lblPageInfo1 = new JLabel("Página 1 de " + totalPages);
        
        MostrarCortes.lblPageInfo1.setText(lblPageInfo1.getText());
        
        // Otros códigos de inicialización
        txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscar();
            }
        
            @Override
            public void removeUpdate(DocumentEvent e) {
                buscar();
            }
        
            @Override
            public void changedUpdate(DocumentEvent e) {
                buscar();
            }
        });
    }
    
    public void buscar() {
        String textoBusqueda = txtBusqueda.getText().trim();
        MostrarCortes("");
    }
    
    public static void MostrarCortes(String buscar) {
        String[] titulos = {"<html><b>Número</b></html>", "<html><b>Nombre del corte</b></html>", "<html><b>Género</b></html>",
            "<html><b>Precio</b></html>","<html><b>Descripción</b></html>"};
        DefaultTableModel model = new DefaultTableModel(null, titulos);
        
        
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
    
        String sql = QuerysCortes.LISTARCORTES;
        if (!buscar.equals("")) {
            sql += " WHERE nombre_corte LIKE '%" + buscar + "%' OR genero LIKE '%" + buscar + "%'";
        }
    
        String datos[] = new String[6]; // Incrementado a 6 para incluir el número de orden
    
        try {
            java.sql.Statement st = (java.sql.Statement) Conexion.getConection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            

            int count = 0;
            int startIndex = (currentPage - 1) * PAGE_SIZE;
            int endIndex = currentPage * PAGE_SIZE;
           
            while (rs.next()) {
                if (count >= startIndex && count < endIndex) {
                    datos[0] = String.valueOf(count + 1); // Número de orden
                    datos[1] = rs.getString("nombre_corte");
                    datos[2] = rs.getString("genero");
                    datos[3] = rs.getString("precio");
                    datos[4] = rs.getString("descripcion");
                    model.addRow(datos);
                }
                count++;
            }

            MostrarCortes.tblMostrarCortes.setModel(model);

            // Calcular el número total de páginas
            int totalRows = count;
            totalPages = (int) Math.ceil((double) totalRows / PAGE_SIZE);
            
            // Actualizar el JLabel con la información de la página actual y el número total de páginas
            MostrarCortes.lblPageInfo1.setText("Página " + currentPage + " de " + totalPages);
            
        } catch (Exception e) {
            e.printStackTrace();
        }         
    }
    
    public void  AdminCortes(){
        mostrarGenero(0,null);
    }
    //Metodo para buscar por generos
    public static void mostrarGenero(int opbuscar, String valor){
        String[] titulos = {"<html><b>Número</b></html>", "<html><b>Nombre del corte</b></html>", "<html><b>Género</b></html>",
            "<html><b>Precio</b></html>","<html><b>Descripción</b></html>"};
        DefaultTableModel model = new DefaultTableModel(null, titulos);
        
        
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
    
        String sql;
    
        String datos[] = new String[6]; // Incrementado a 6 para incluir el número de orden
        
        if(opbuscar == 0 && valor == null){
            sql = "SELECT * FROM catalogo_cortes";
        }
        else{
            if(opbuscar == 1 && valor != null){
                sql= "SELECT * FROM catalogo_cortes WHERE genero = 'Hombre' OR nombre_corte LIKE '%"+opbuscar+"%'";
            }
            else{
                if(opbuscar == 2 && valor != null){
                    sql= "SELECT * FROM catalogo_cortes WHERE genero = 'Mujer' OR nombre_corte LIKE '%"+opbuscar+"%'";
                }
                else{
                   sql = "SELECT * FROM catalogo_cortes"; 
                }
            }
        }
    
        try {
            java.sql.Statement st = (java.sql.Statement) Conexion.getConection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            

            int count = 0;
            int startIndex = (currentPage - 1) * PAGE_SIZE;
            int endIndex = currentPage * PAGE_SIZE;
           
            while (rs.next()) {
                if (count >= startIndex && count < endIndex) {
                    datos[0] = String.valueOf(count + 1); // Número de orden
                    datos[1] = rs.getString("nombre_corte");
                    datos[2] = rs.getString("genero");
                    datos[3] = rs.getString("precio");
                    datos[4] = rs.getString("descripcion");
                    model.addRow(datos);
                }
                count++;
            }

            MostrarCortes.tblMostrarCortes.setModel(model);

            // Calcular el número total de páginas
            int totalRows = count;
            totalPages = (int) Math.ceil((double) totalRows / PAGE_SIZE);
            
            // Actualizar el JLabel con la información de la página actual y el número total de páginas
            MostrarCortes.lblPageInfo1.setText("Página " + currentPage + " de " + totalPages);
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
    }

    // Método para actualizar la tabla cuando se realiza la paginación 
    public static void updateTable(String buscar) {
        String[] titulos = {"<html><b>Número</b></html>", "<html><b>Nombre del corte</b></html>", "<html><b>Género</b></html>",
            "<html><b>Precio</b></html>","<html><b>Descripción</b></html>"};
        DefaultTableModel model = new DefaultTableModel(null, titulos);
    
      
        
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        
        String sql = QuerysCortes.LISTARCORTES;
        if (!buscar.equals("")) {
            sql += " WHERE nombre_corte LIKE '%" + buscar + "%' OR genero LIKE '%" + buscar + "%'";
        }
        
        String datos[] = new String[5]; // Incrementado a 5 para incluir el número de orden

        try {
            java.sql.Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            int count = 0;
            int startIndex = (currentPage - 1) * PAGE_SIZE;
            int endIndex = currentPage * PAGE_SIZE;
            
            while (rs.next()) {
                if (count >= startIndex && count < endIndex) {
                    datos[0] = String.valueOf(count + 1); // Número de orden
                    datos[1] = rs.getString("nombre_corte");
                    datos[2] = rs.getString("genero");
                    datos[3] = rs.getString("precio");
                    datos[4] = rs.getString("descripcion");
                    model.addRow(datos);
                }
                count++;
            }

            MostrarCortes.tblMostrarCortes.setModel(model);

            int totalRows = count;
            totalPages = (int) Math.ceil((double) totalRows / PAGE_SIZE);
            
            // Actualizar el JLabel con la información de la página actual y el número total de páginas
            MostrarCortes.lblPageInfo1.setText("Página " + currentPage + " de " + totalPages);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Metodo para eliminar datos de una fila:
    public void eliminarCorte(){
        int fila = MostrarCortes.tblMostrarCortes.getSelectedRow();
        //String valor = MostrarCortes.tblMostrarCortes.getValueAt(fila,0)
    }
    
     //Metodo para eliminar datos de la tabla de cortes de cabello
    public static void eliminarCorte(String buscar){
        int fila = MostrarCortes.tblMostrarCortes.getSelectedRow();
        String valor = MostrarCortes.tblMostrarCortes.getValueAt(fila, 1).toString();
        
        try {
            //codigo para eliminar de la db
            PreparedStatement eliminar = conexion.prepareStatement("DELETE FROM catalogo_cortes WHERE nombre_corte = "
                    + "'"+valor+"'");
            eliminar.executeUpdate();
            MostrarCortes(valor);
            String[] titulos = {"<html><b>Número</b></html>", "<html><b>Nombre del corte</b></html>", "<html><b>Género</b></html>",
            "<html><b>Precio</b></html>","<html><b>Descripción</b></html>"};
            DefaultTableModel model = new DefaultTableModel(null, titulos);
    
      
        
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }
        
            String sql = QuerysCortes.LISTARCORTES;
            if (!buscar.equals("")) {
                sql += " WHERE nombre_corte LIKE '%" + buscar + "%'";
            }
        
            String datos[] = new String[5]; // Incrementado a 5 para incluir el número de orden

            try {
                java.sql.Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery(sql);

                int count = 0;
                int startIndex = (currentPage - 1) * PAGE_SIZE;
                int endIndex = currentPage * PAGE_SIZE;
            
                while (rs.next()) {
                    if (count >= startIndex && count < endIndex) {
                        datos[0] = String.valueOf(count + 1); // Número de orden
                        datos[1] = rs.getString("nombre_corte");
                        datos[2] = rs.getString("genero");
                        datos[3] = rs.getString("precio");
                        datos[4] = rs.getString("descripcion");
                        model.addRow(datos);
                    }
                    count++;
            }

            MostrarCortes.tblMostrarCortes.setModel(model);

            int totalRows = count;
            totalPages = (int) Math.ceil((double) totalRows / PAGE_SIZE);
            
            // Actualizar el JLabel con la información de la página actual y el número total de páginas
            MostrarCortes.lblPageInfo1.setText("Página " + currentPage + " de " + totalPages);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
            
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + "No se logro eliminar el registro");
        }
            
    }
}


