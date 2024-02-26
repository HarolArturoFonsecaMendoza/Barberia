/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import Conexion.Conexion;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTextField;
import static Vistas.EditarTratamiento.tblProductosDeTratamiento;

/**
 *
 * @author PC
 */
public class ProductoPEtratamiento extends javax.swing.JFrame {
    
   
    

    /**
     * Creates new form ProductoParaCompra
     */
    public ProductoPEtratamiento() {
        initComponents();
        this.setLocationRelativeTo(null);
       
        
//tblProductosParafactura.getTableHeader().setReorderingAllowed(false);
        Controlador.ProductosTratamiento.ProductoPETratamiento("");
        
            tblProductosPETratamiento.addMouseListener(new MouseAdapter() {
            public void MouseClicked(MouseEvent e){
            }
        });   
      
    }
    
    
    
    public void filtrarDatosProductos(String valor){
        String[] titulos = {"<html>Num.</html>","<html>Nombre del Producto</html>"};
        String[] registrosP = new String[4];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        String SQL = "select * from catalogo_productos WHERE nombre like '%" + valor + "'";

        
        
        try {
            Statement st=(Statement) Conexion.getConection().createStatement();
            ResultSet rs= st.executeQuery(SQL);
            
            while(rs.next()){
                registrosP[0]=rs.getString("id");
                registrosP[1]=rs.getString("nombre");
                
                modelo.addRow(registrosP);
                
            }
            
            ProductoPEtratamiento.tblProductosPETratamiento.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error"+e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnAgregarProducto = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProductosPETratamiento = new javax.swing.JTable();
        CuadroBuscarProducto = new javax.swing.JTextField();
        bntAgregarProd = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Refrescar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Agregar Producto");

        btnAgregarProducto.setBackground(new java.awt.Color(253, 253, 253));
        btnAgregarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/añadir.png"))); // NOI18N
        btnAgregarProducto.setText("Agregar Producto");
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        cancelar.setBackground(new java.awt.Color(253, 253, 253));
        cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancelarr.png"))); // NOI18N
        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        tblProductosPETratamiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Num.", "Id", "Nombre del Producto", "Marca", "Presentación"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductosPETratamiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosPETratamientoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblProductosPETratamiento);
        if (tblProductosPETratamiento.getColumnModel().getColumnCount() > 0) {
            tblProductosPETratamiento.getColumnModel().getColumn(0).setMinWidth(40);
            tblProductosPETratamiento.getColumnModel().getColumn(0).setPreferredWidth(40);
            tblProductosPETratamiento.getColumnModel().getColumn(0).setMaxWidth(40);
            tblProductosPETratamiento.getColumnModel().getColumn(1).setMinWidth(0);
            tblProductosPETratamiento.getColumnModel().getColumn(1).setPreferredWidth(0);
            tblProductosPETratamiento.getColumnModel().getColumn(1).setMaxWidth(0);
            tblProductosPETratamiento.getColumnModel().getColumn(3).setMinWidth(0);
            tblProductosPETratamiento.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblProductosPETratamiento.getColumnModel().getColumn(3).setMaxWidth(0);
            tblProductosPETratamiento.getColumnModel().getColumn(4).setMinWidth(0);
            tblProductosPETratamiento.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblProductosPETratamiento.getColumnModel().getColumn(4).setMaxWidth(0);
        }

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

        bntAgregarProd.setBackground(new java.awt.Color(253, 253, 253));
        bntAgregarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/añadir.png"))); // NOI18N
        bntAgregarProd.setText("Agregar producto al catalogo");
        bntAgregarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAgregarProdActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lupa.jpg"))); // NOI18N

        Refrescar.setText("Refrescar");
        Refrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefrescarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CuadroBuscarProducto)
                        .addGap(2, 2, 2))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAgregarProducto)
                        .addGap(78, 78, 78)
                        .addComponent(cancelar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bntAgregarProd)
                        .addGap(18, 18, 18)
                        .addComponent(Refrescar)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CuadroBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Refrescar, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(bntAgregarProd, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelar))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
int fila = tblProductosPETratamiento.getSelectedRow();

try {
    // Variables para capturar los campos de la tabla de productos
    String nombreProd, id;

    if (fila < 0) {
        JOptionPane.showMessageDialog(null, "No se seleccionó ningún producto",
                "Error al agregar producto", JOptionPane.WARNING_MESSAGE);
        return; // Salir del método si no se seleccionó ningún producto
    }

    DefaultTableModel modelo = (DefaultTableModel) tblProductosPETratamiento.getModel();
    id = tblProductosPETratamiento.getValueAt(fila, 1).toString();
    nombreProd = tblProductosPETratamiento.getValueAt(fila, 0).toString();

    // Obtener el valor de id_tratamiento (puedes ajustar esto según tu lógica)
    String idTratamiento = obtenerValorIdTratamiento();

    // Enviar los campos seleccionados de la tabla de productos a la tabla de factura de compras
    modelo = (DefaultTableModel) tblProductosDeTratamiento.getModel();

    // Buscar el producto en la tabla tblProductosDeTratamiento
    boolean productoEncontrado = false;
    for (int i = 0; i < modelo.getRowCount(); i++) {
        String idEnTabla = modelo.getValueAt(i, 0).toString();
        if (idEnTabla.equals(id)) {
            productoEncontrado = true;
            break;
        }
    }

    if (productoEncontrado) {
        JOptionPane.showMessageDialog(null, "El producto ya existe en la tabla", "Duplicado", JOptionPane.WARNING_MESSAGE);
    } else {
        // El producto no existe en la tabla, agregarlo como una nueva fila
        String filaElemento[] = {id, nombreProd,idTratamiento};
        modelo.addRow(filaElemento);
        // JOptionPane.showMessageDialog(null, "Se agregó el producto!");
    }
} catch (Exception e) {
    e.printStackTrace();
}

    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    // Método ficticio para obtener el valor de id_tratamiento (ajusta esto según tu lógica)
private String obtenerValorIdTratamiento() {
    DefaultTableModel tabla = (DefaultTableModel) tblProductosDeTratamiento.getModel();

    // Obtener la fila seleccionada (si es aplicable)
    int filaSeleccionada = tblProductosDeTratamiento.getSelectedRow();

    // Verificar si hay una fila seleccionada
    if (filaSeleccionada != -1) {
        // Obtener el valor de la columna 2 (indice 1 en base 0)
        Object valorColumna2 = tabla.getValueAt(filaSeleccionada, 2);

        // Verificar si el valor no es nulo antes de convertirlo a String
        if (valorColumna2 != null) {
            return valorColumna2.toString();
        }
    }

    // Manejar el caso donde no se puede obtener el valor
    return "";
}

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cancelarActionPerformed

    private void tblProductosPETratamientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosPETratamientoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblProductosPETratamientoMouseClicked

    private void CuadroBuscarProductoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CuadroBuscarProductoFocusGained
        // TODO add your handling code here:
        //placeholder para notificar como realizar la busqueda
        JTextField textField = (JTextField) evt.getSource();
        String placeholder = "Buscar por nombre de producto";

        if (textField.getText().equals(placeholder)) {
            textField.setText("");
            textField.setForeground(Color.BLACK); // Establece el color de fuente adecuado
        }
    }//GEN-LAST:event_CuadroBuscarProductoFocusGained

    private void CuadroBuscarProductoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CuadroBuscarProductoFocusLost
        // TODO add your handling code here:
        //placeholder para notificar como realizar la busqueda
        JTextField textField = (JTextField) evt.getSource();
        String placeholder = "Buscar por nombre de producto";

        if (textField.getText().isEmpty()) {
            textField.setText(placeholder);
            textField.setForeground(Color.GRAY); // Establece el color de fuente del placeholder
        }
    }//GEN-LAST:event_CuadroBuscarProductoFocusLost

    private void CuadroBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CuadroBuscarProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CuadroBuscarProductoActionPerformed

    private void CuadroBuscarProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CuadroBuscarProductoKeyReleased
        // TODO add your handling code here:
        filtrarDatosProductos(CuadroBuscarProducto.getText());
    }//GEN-LAST:event_CuadroBuscarProductoKeyReleased

    private void CuadroBuscarProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CuadroBuscarProductoKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_CuadroBuscarProductoKeyTyped

    private void bntAgregarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAgregarProdActionPerformed
        CrearProductosCuidado crearproducto = new CrearProductosCuidado();
        crearproducto.setVisible(true);
    }//GEN-LAST:event_bntAgregarProdActionPerformed

    private void RefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefrescarActionPerformed
        Controlador.ProductosTratamiento.ProductoParaTratamiento("");
    }//GEN-LAST:event_RefrescarActionPerformed

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
            java.util.logging.Logger.getLogger(ProductoPEtratamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductoPEtratamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductoPEtratamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductoPEtratamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductoPEtratamiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CuadroBuscarProducto;
    private javax.swing.JButton Refrescar;
    public static javax.swing.JButton bntAgregarProd;
    public static javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton cancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTable tblProductosPETratamiento;
    // End of variables declaration//GEN-END:variables
}
