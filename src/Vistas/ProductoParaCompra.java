/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import Conexion.Conexion;
import ConsultasSQL.QuerysProductosCuidados;
import Controlador.ProductosFactura;
import static Vistas.IngresarCompra.tblProductosCompras;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.prompt.PromptSupport;
//import static Vistas.IngresarCompra.tblProductosCompras;


/**
 *
 * @author PC
 */
public class ProductoParaCompra extends javax.swing.JFrame {
    
      
    
    //Variables para calcular el total de la factura
    static double totalFactura, suma;
    
    /**
     * Creates new form ProductoParaCompra
     */
    public ProductoParaCompra() {
        initComponents();
        this.setLocationRelativeTo(null);
        PromptSupport.setPrompt("Buscar por nombre del producto o por su marca", CuadroBuscarProducto);
        
        //Inicializando la variable para calcular el total de la factura
        totalFactura = 0;
        suma = 0;
        
        tblProductosParafactura.getTableHeader().setReorderingAllowed(false);
        Controlador.ProductosFactura.ProductoParaCompra("");
        
            tblProductosParafactura.addMouseListener(new MouseAdapter() {
            public void MouseClicked(MouseEvent e){
                int fila = tblProductosParafactura.getSelectedRow();
            }
        });   
    }
    
//    public static void calcular(){
//        suma = 0;
//        for(int i = 0; i< tblProductosCompras.getRowCount(); i++){
//            float renglon;
//            renglon = (float) Float.parseFloat(tblProductosCompras.getValueAt(i, 3).toString());
//            
//            suma = suma + renglon;
//        }
//    }
    
    
    
    public void filtrarDatosProductos(String valor){
        String[] titulos = {"<html>Num.</html>","<html>nombre</html>","<html>marca</html>","<html>categoria</html>"};
        String[] registrosP = new String[4];
        
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        String SQL = "select * from catalogo_productos WHERE "
                + "nombre like '%"+valor+"%' or marca like '%"+valor+"%' or tamano like '%"+valor+"%'";
        
        
        
        try {
            Statement st=(Statement) Conexion.getConection().createStatement();
            ResultSet rs= st.executeQuery(SQL);
            
            while(rs.next()){
                registrosP[0]=rs.getString("id");
                registrosP[1]=rs.getString("nombre");
                registrosP[2]=rs.getString("marca");
                registrosP[3]=rs.getString("tamano");
                
                modelo.addRow(registrosP);
                
            }
            
            ProductoParaCompra.tblProductosParafactura.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error"+e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        cantidad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        bntAgregarProducto = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProductosParafactura = new javax.swing.JTable();
        CuadroBuscarProducto = new javax.swing.JTextField();
        bntAgregarProd = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPventa = new javax.swing.JTextField();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ingresar productos a la factura");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Agregar Producto a la Factura");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 270, -1));

        cantidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadActionPerformed(evt);
            }
        });
        cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantidadKeyTyped(evt);
            }
        });
        getContentPane().add(cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 440, 90, 33));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Cantidad :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, -1, -1));

        bntAgregarProducto.setBackground(new java.awt.Color(253, 253, 253));
        bntAgregarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/añadir.png"))); // NOI18N
        bntAgregarProducto.setText("Agregar Producto");
        bntAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAgregarProductoActionPerformed(evt);
            }
        });
        getContentPane().add(bntAgregarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 480, -1, 31));

        cancelar.setBackground(new java.awt.Color(253, 253, 253));
        cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancelarr.png"))); // NOI18N
        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        getContentPane().add(cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 480, 130, -1));

        tblProductosParafactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Num.", "Nombre del Producto", "Marca", "Presentación", "Id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductosParafactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosParafacturaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblProductosParafactura);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 150, 802, 275));

        CuadroBuscarProducto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                CuadroBuscarProductoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                CuadroBuscarProductoFocusLost(evt);
            }
        });
        CuadroBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CuadroBuscarProductoActionPerformed(evt);
            }
        });
        CuadroBuscarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CuadroBuscarProductoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CuadroBuscarProductoKeyTyped(evt);
            }
        });
        getContentPane().add(CuadroBuscarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(491, 96, 287, 30));

        bntAgregarProd.setBackground(new java.awt.Color(253, 253, 253));
        bntAgregarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/añadir.png"))); // NOI18N
        bntAgregarProd.setText("Agregar producto al catalogo");
        bntAgregarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAgregarProdActionPerformed(evt);
            }
        });
        getContentPane().add(bntAgregarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, 29));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lupa.jpg"))); // NOI18N
        jLabel2.setText("Buscar");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(392, 96, 70, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Precio de compra :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 450, -1, -1));

        txtValor.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorActionPerformed(evt);
            }
        });
        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorKeyTyped(evt);
            }
        });
        getContentPane().add(txtValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 440, 120, 33));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ListaodologoBarberia.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 210, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Precio de venta:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 450, -1, -1));

        txtPventa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPventa.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPventaKeyTyped(evt);
            }
        });
        getContentPane().add(txtPventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 440, 100, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAgregarProductoActionPerformed
        int fila = tblProductosParafactura.getSelectedRow();

    try {
        String nombreProd, marca, presen, cantid, precio, totalP, ventas;
        double tot = 0.0, calcula = 0.0;

        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "No se seleccionó ningún producto",
                    "Error de validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (cantidad.getText().isEmpty() || cantidad.getText().startsWith("0")) {
            JOptionPane.showMessageDialog(null, "Ingrese cantidades mayores a cero(0)",
                    "Error de validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (txtValor.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El precio no puede estar vacío",
                    "Error de validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (txtPventa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El precio de venta no puede estar vacío",
                    "Error de validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) tblProductosParafactura.getModel();
        nombreProd = tblProductosParafactura.getValueAt(fila, 1).toString();
        marca = tblProductosParafactura.getValueAt(fila, 2).toString();
        presen = tblProductosParafactura.getValueAt(fila, 3).toString();
        precio = txtValor.getText();
        cantid = cantidad.getText();
        ventas = txtPventa.getText();

        tot = Double.parseDouble(precio) * Integer.parseInt(cantid);
        totalP = String.valueOf(tot);

        modelo = (DefaultTableModel) tblProductosCompras.getModel();

        int rowCount = modelo.getRowCount();
        boolean productoEncontrado = false;

        for (int i = 0; i < rowCount; i++) {
            String productoExistente = modelo.getValueAt(i, 0).toString();

            if (productoExistente.equals(nombreProd)) {
                String cantidadExistente = modelo.getValueAt(i, 3).toString();
                int nuevaCantidad = Integer.parseInt(cantidadExistente) + Integer.parseInt(cantid);
                modelo.setValueAt(String.valueOf(nuevaCantidad), i, 3);

                String precioExistente = modelo.getValueAt(i, 4).toString();
                double precioUnitario = Double.parseDouble(precio);
                double nuevoPrecio = Math.max(precioUnitario, Double.parseDouble(precioExistente));
                modelo.setValueAt(String.valueOf(nuevoPrecio), i, 4);

                String ventaExistente = modelo.getValueAt(i, 6).toString();
                double precioVenta = Double.parseDouble(ventas);

                // Comparar y actualizar precio de venta
                if (precioVenta > Double.parseDouble(ventaExistente)) {
                    modelo.setValueAt(String.valueOf(precioVenta), i, 6);

                    double nuevoTotal = nuevoPrecio * nuevaCantidad;
                    modelo.setValueAt(String.valueOf(nuevoTotal), i, 5);
                } else {
                    // Mostrar un mensaje si el precio de venta es menor que el precio
                    JOptionPane.showMessageDialog(null, "El precio de venta no puede ser menor que el precio de compra.",
                            "Error de validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                productoEncontrado = true;
                break;
            }
        }

        if (!productoEncontrado) {
            // El producto no existe en la tabla, agregarlo como una nueva fila
            if (Double.parseDouble(ventas) >= Double.parseDouble(precio)) {
                tot = Double.parseDouble(precio) * Integer.parseInt(cantid);
                totalP = String.valueOf(tot);

                String filaElemento[] = {nombreProd, marca, presen, cantid, precio, totalP, ventas};
                modelo.addRow(filaElemento);
            } else {
                // Mostrar un mensaje si el precio de venta es menor que el precio
                JOptionPane.showMessageDialog(null, "El precio de venta no puede ser menor que el precio de compra.",
                        "Error de validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        calcula = Double.parseDouble(precio) * Integer.parseInt(cantidad.getText());
        totalFactura += calcula;

        cantidad.setText("");
        txtValor.setText("");
        txtPventa.setText("");
        JOptionPane.showMessageDialog(null, "Se agregó el producto!");

    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_bntAgregarProductoActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
         this.dispose();
    }//GEN-LAST:event_cancelarActionPerformed

    private void CuadroBuscarProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CuadroBuscarProductoKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_CuadroBuscarProductoKeyTyped

    private void CuadroBuscarProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CuadroBuscarProductoKeyReleased
        // TODO add your handling code here:
        filtrarDatosProductos(CuadroBuscarProducto.getText());
    }//GEN-LAST:event_CuadroBuscarProductoKeyReleased

    private void CuadroBuscarProductoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CuadroBuscarProductoFocusGained
        // TODO add your handling code here:
        //placeholder para notificar como realizar la busqueda
        JTextField textField = (JTextField) evt.getSource();
        String placeholder = "Buscar por nombre de producto y tipo de inventario";

        if (textField.getText().equals(placeholder)) {
            textField.setText("");
            textField.setForeground(Color.BLACK); // Establece el color de fuente adecuado
        }
    }//GEN-LAST:event_CuadroBuscarProductoFocusGained

    private void CuadroBuscarProductoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CuadroBuscarProductoFocusLost
        // TODO add your handling code here:
        //placeholder para notificar como realizar la busqueda
          JTextField textField = (JTextField) evt.getSource();
          String placeholder = "Buscar por nombre de producto y tipo de inventario";

          if (textField.getText().isEmpty()) {
            textField.setText(placeholder);
            textField.setForeground(Color.GRAY); // Establece el color de fuente del placeholder
          }
    }//GEN-LAST:event_CuadroBuscarProductoFocusLost

    private void CuadroBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CuadroBuscarProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CuadroBuscarProductoActionPerformed

    private void tblProductosParafacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosParafacturaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblProductosParafacturaMouseClicked

    private void cantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadKeyTyped
        // TODO add your handling code here:
        //validacion para que no acepte letras
        char validar= evt.getKeyChar();
        if(Character.isLetter(validar)){
            getToolkit().beep();
            evt.consume();
        }
        //validacion de que no permita espacios o caracteres especiales
        if(evt.getKeyChar() >=30 && evt.getKeyChar() <= 47 || evt.getKeyChar() >=58 && evt.getKeyChar() <= 97){
            getToolkit().beep();
            evt.consume();
        }
        //Validar que solo se puedan ingresar una cantidad de 3 numeros
        if(cantidad.getText().length() >= 4){
            getToolkit().beep();
            evt.consume();
        }
        //validar que no se ingrese primero un cero
        if (cantidad.getText().startsWith("0")) {
            // Emitir un sonido de error
            getToolkit().beep();
            // Consumir el evento para evitar que se ingrese el cero
            evt.consume();
        }     
    }//GEN-LAST:event_cantidadKeyTyped

    private void cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadActionPerformed

    private void bntAgregarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAgregarProdActionPerformed
         CrearProductosCuidado crearproducto = new CrearProductosCuidado();
        crearproducto.setVisible(true); 
    }//GEN-LAST:event_bntAgregarProdActionPerformed

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorActionPerformed

    private void txtValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyTyped
        // Obtener el texto actual en el campo txtValor
    String currentText = txtValor.getText();

    // Obtener el carácter que se intenta agregar
    char newChar = evt.getKeyChar();

    // Si el campo está vacío y se intenta ingresar '0', no permitirlo
    if (currentText.isEmpty() && newChar == '0') {
        evt.consume(); // No permitir el ingreso del carácter
        return;
    }

    // Si el campo está vacío y se intenta ingresar '.', no permitirlo
    if (currentText.isEmpty() && newChar == '.') {
        evt.consume(); // No permitir el ingreso del carácter
        return;
    }

    // Si el nuevo carácter es un '.', verificar que no se haya ingresado otro previamente
    if (newChar == '.') {
        if (currentText.contains(".")) {
            evt.consume(); // No permitir el ingreso de otro punto
            return;
        }
    }

    // Comprobar si el nuevo carácter es un dígito
    if (!Character.isDigit(newChar) && newChar != '.') {
        getToolkit().beep();
        evt.consume(); // No permitir el ingreso del carácter
    }

    // Comprobar si la longitud del texto supera el límite de 10 caracteres
    if (currentText.length() >= 4) {
        evt.consume(); // No permitir seguir escribiendo en el campo
    }
      
         

    }//GEN-LAST:event_txtValorKeyTyped

    private void txtPventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPventaKeyTyped
          // Obtener el texto actual en el campo txtValor
    String currentText = txtPventa.getText();

    // Obtener el carácter que se intenta agregar
    char newChar = evt.getKeyChar();

    // Si el campo está vacío y se intenta ingresar '0', no permitirlo
    if (currentText.isEmpty() && newChar == '0') {
        evt.consume(); // No permitir el ingreso del carácter
        return;
    }

    // Si el campo está vacío y se intenta ingresar '.', no permitirlo
    if (currentText.isEmpty() && newChar == '.') {
        evt.consume(); // No permitir el ingreso del carácter
        return;
    }

    // Si el nuevo carácter es un '.', verificar que no se haya ingresado otro previamente
    if (newChar == '.') {
        if (currentText.contains(".")) {
            evt.consume(); // No permitir el ingreso de otro punto
            return;
        }
    }

    // Comprobar si el nuevo carácter es un dígito
    if (!Character.isDigit(newChar) && newChar != '.') {
        getToolkit().beep();
        evt.consume(); // No permitir el ingreso del carácter
    }

    // Comprobar si la longitud del texto supera el límite de 10 caracteres
    if (currentText.length() >= 4) {
        evt.consume(); // No permitir seguir escribiendo en el campo
    }
      
         

    }//GEN-LAST:event_txtPventaKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProductoParaCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductoParaCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductoParaCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductoParaCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductoParaCompra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CuadroBuscarProducto;
    public static javax.swing.JButton bntAgregarProd;
    public static javax.swing.JButton bntAgregarProducto;
    private javax.swing.JButton cancelar;
    private javax.swing.JTextField cantidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTable tblProductosParafactura;
    private javax.swing.JTextField txtPventa;
    public static javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
