/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import Clases.Cliente;
import java.util.ArrayList;
import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModeloClientes {
    private static Conexion con = new Conexion();
    private static Connection conexion = con.getConexion();
    private static PreparedStatement ps = null;
    public ArrayList<Cliente> getCliente(){
        
        ArrayList<Cliente>listaCliente = new ArrayList<>();
        try (
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from clientes")) {

           while (rs.next()) {                
               
               Cliente cliente = new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"));
               listaCliente.add(cliente);
           }


       } catch (SQLException ex) {
           ex.printStackTrace(); // o log.error("Error al obtener clientes", ex);
       }


        return listaCliente;
    }
}
