/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConsultasSQL;

public class QuerysComprasProducto {

    public static Object getItemAt(int selectedIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private int id;
    private String numeroFactura;
    private String cai;
    private String tipoDeCompra;
    private int fk_proveedor;
    private String fecha;
    private int total;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getCai() {
        return cai;
    }

    public void setCai(String cai) {
        this.cai = cai;
    }
    

    public String getTipoCompra() {
        return tipoDeCompra;
    }

    public void setTipoCompra(String tipoDeCompra) {
        this.tipoDeCompra = tipoDeCompra;
    }

    public int getFk_proveedor() {
        return fk_proveedor;
    }

    public void setFk_proveedor(int fk_proveedor) {
        this.fk_proveedor = fk_proveedor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

   
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    public static String LISTARCOMPRAS = "SELECT * FROM comprasproductos";
}
