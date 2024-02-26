/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConsultasSQL;

/**
 *
 * @author Josue
 */
public class QuerysManicura {
    public static String LISTARMANICURAYPEDICURA = "select * from manicuraypedicura ";
    private int id;
    private String estilo;
    private Double precio;
    private String tecnica;           
    private byte[] foto;
    private byte[] foto1;
    private String esmaltado;
    private String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getTecnica() {
        return tecnica;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public byte[] getFoto1() {
        return foto1;
    }

    public void setFoto1(byte[] foto1) {
        this.foto1 = foto1;
    }

    public String getEsmaltado() {
        return esmaltado;
    }

    public void setEsmaltado(String esmaltado) {
        this.esmaltado = esmaltado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
        
    public static String MostraManicura = "Select * from manicuraypedicura";
    
    public static String RegistraManicura = "INSERT INTO manicuraypedicura("
            +"estilo,"+"precio,"+"tecnica,"+"foto1,"+"foto2,"+"esmaltado,"+"descripcion)"
            +"VALUES(?,?,?,?,?,?,?)";
    
    public static String ActualizarManicura = "UPDATE manicuraypedicura SET estilo = ?,"
            + " precio = ?,"+" tecnica = ?,"+" foto1 = ?, "+" foto2 =?,"
            +"  esmaltado =?,"+" descripcion =? WHERE id = ?";
    
       //Consulta para registrar
    public static String TraerManicura = "SELECT * from manicuraypedicura where id=?";
    
    //consulta para el ver de la venta de servicios
    public static String VerManicura = "SELECT estilo, precio, tecnica, foto1, foto2, esmaltado, descripcion  FROM manicuraypedicura WHERE id = ?";

}
