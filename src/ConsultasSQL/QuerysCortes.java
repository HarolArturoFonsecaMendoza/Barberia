/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConsultasSQL;

import com.mysql.cj.jdbc.Blob;
import java.awt.Image;

/**
 *
 * @author Josue
 */
public class QuerysCortes { 
    private int id;
    private String nombre_cortr;
    private String genero;
    private byte[] corte;
    private byte[] corte2;
    private byte[] corte3;
    private Double precio;
    private String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_cortr() {
        return nombre_cortr;
    }

    public void setNombre_cortr(String nombre_cortr) {
        this.nombre_cortr = nombre_cortr;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public byte[] getCorte() {
        return corte;
    }

    public void setCorte(byte[] corte) {
        this.corte = corte;
    }

    public byte[] getCorte2() {
        return corte2;
    }

    public void setCorte2(byte[] corte2) {
        this.corte2 = corte2;
    }

    public byte[] getCorte3() {
        return corte3;
    }

    public void setCorte3(byte[] corte3) {
        this.corte3 = corte3;
    }
    
    
    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public static String RegistraCortes = "INSERT INTO catalogo_cortes("
            +"nombre_corte,"+"genero,"+"corte,"+"cote2,"+"corte3,"+"precio,"+"descripcion)"
            +"VALUES(?,?,?,?,?,?,?)";
    
    public static String LISTARCORTES = "SELECT * FROM catalogo_cortes";
    
     public static String VerCortes = "SELECT nombre_corte, genero, corte, cote2, corte3, precio, descripcion FROM catalogo_cortes WHERE id = ?";
}
