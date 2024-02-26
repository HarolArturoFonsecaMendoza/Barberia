/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import Clases.Empleados;
import Conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ModeloEmpleados {
    public ArrayList<Empleados> getEmpleadosActivosPorServicio(String servicioSeleccionado) {
        Connection con = Conexion.getConection();
        Statement stmt;
        ResultSet rs;
        ArrayList<Empleados> listaEmpleados = new ArrayList<>();
        
        // Determinar el tipo de cargo según el servicio seleccionado
        String tipoCargo = "";    
        switch (servicioSeleccionado.toLowerCase()) {
                case "corte":
                    tipoCargo = "barbero";
                    break;
                case "tratamiento":
                    tipoCargo = "cuidado personal";
                    break;
                case "manicura y pedicura":
                    tipoCargo = "manicurista y pedicurista";
                    break;
                // Puedes agregar más casos según tus necesidades
            }
        try {
            stmt = con.createStatement();
            
            // Filtrar por estado activo y tipo de cargo específico para el servicio
            String query = "SELECT * FROM empleados WHERE estado = 'activo' AND cargo = '" + tipoCargo + "'";
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {                
                Empleados empleados = new Empleados(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("cargo"));
                listaEmpleados.add(empleados);
            }            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaEmpleados;
    }
}
