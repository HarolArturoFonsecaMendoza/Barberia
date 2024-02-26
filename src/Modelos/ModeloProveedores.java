/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.util.ArrayList;
import Clases.Proveedor;
import Conexion.Conexion;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author PC
 */
public class ModeloProveedores {
    public ArrayList<Proveedor> getProveedor(){
        Connection con = Conexion.getConection();
        Statement stmt;
        ResultSet rs;
        ArrayList<Proveedor>listaProveedor = new ArrayList<>();
        
        try {
            stmt = con.createStatement();
            rs= stmt.executeQuery("Select * from provedores");
            
            while (rs.next()) {                
                Proveedor proveedor = new Proveedor(0,"Nose");
                proveedor.setId(rs.getInt("id"));
                proveedor.setNombre(rs.getString("nombre"));
                listaProveedor.add(proveedor);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ModeloProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProveedor;
    }
}
