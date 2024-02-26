/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConsultasSQL;


public class QuerysTratamientoProductos { 
    private int id;
    private int id_tratamientos;
    private int id_productos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getId_tratamientos() {
        return id_tratamientos;
    }

    public void setId_tratamientos(int id_tratamientos) {
        this.id_tratamientos = id_tratamientos;
    }
    
    public int getId_productos() {
        return id_productos;
    }

    public void setId_productos(int id_productos) {
        this.id_productos = id_productos;
    }
    
    public static String RegistroTratamiento_productos = "INSERT INTO tratamientos_productos(id_tratamientos, id_productos) VALUES(?,?)";
    
    public static String verPtratamientos ="SELECT\n" +
        "    tp.id,\n" +
        "    tp.id_tratamientos,\n" +
        "    tp.id_productos,\n" +
        "    cp.nombre AS nombre_producto\n" +
        "FROM\n" +
        "    tratamientos_productos tp\n" +
        "JOIN\n" +
        "    catalogo_productos cp ON tp.id_productos = cp.id where tp.id_tratamientos = ?;";
    
    public static String ActualizarTratamientoProductos = "INSERT INTO tratamientos_productos"
            + " (id_tratamientos, id_productos) SELECT id, ? FROM tratamientos WHERE id = ?;";
    
}
