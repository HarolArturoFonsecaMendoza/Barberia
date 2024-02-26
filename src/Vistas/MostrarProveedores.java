/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vistas;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author Josue
 */
public class MostrarProveedores extends javax.swing.JPanel {
    private int paginaActual = 1;
    public static int totalPages = Controlador.Proveedores.NumeroPages();
   
    public MostrarProveedores() {
        initComponents();
        tblMostrarProveedores.getTableHeader().setReorderingAllowed(false);
        Controlador.Proveedores.MostrarProvedores("", paginaActual);
         PromptSupport.setPrompt("Buscar por nombre del representante o por su empresa", txtBusqueda);
      
      tblMostrarProveedores.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent e){
            
            if (e.getClickCount() == 2) {
                int fila = tblMostrarProveedores.getSelectedRow();
                VerProvedor verProvedor = new VerProvedor();
               /** verProvedor.lbId.setText(tblMostrarProveedores.getValueAt(fila, 0).toString());*/
                verProvedor.LbNom.setText(tblMostrarProveedores.getValueAt(fila, 1).toString());
                verProvedor.LbEmp.setText(tblMostrarProveedores.getValueAt(fila,2).toString());
                verProvedor.LbRTN.setText(tblMostrarProveedores.getValueAt(fila, 3).toString()); 
                verProvedor.LbTel.setText(tblMostrarProveedores.getValueAt(fila, 4).toString());
                verProvedor.LbDire.setText(tblMostrarProveedores.getValueAt(fila, 5
                ).toString());
                
                verProvedor.setVisible(true);
            }
        }
             });
 
 }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblMostrarProveedores = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        txtBusqueda = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        btnAdelante = new javax.swing.JButton();
        seguimiento = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(204, 204, 204));
        setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblMostrarProveedores = new JTable(){
            public boolean isCellEditable(int row, int column){
                for(int i=0; i< tblMostrarProveedores.getRowCount(); i++){
                    if(row == i){
                        return false;
                    }
                }
                return true ;
            }
        };
        tblMostrarProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "N°", "Nombre", "Empresa", "RTN", "Teléfono", "Dirección", "Id"
            }
        ));
        tblMostrarProveedores.setSelectionForeground(new java.awt.Color(153, 255, 255));
        tblMostrarProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMostrarProveedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMostrarProveedores);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 680, 320));

        btnNuevo.setBackground(new java.awt.Color(253, 253, 255));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/agregarN.jpg"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 200, 130, 60));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Listado de provedores");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 270, -1));

        btnEditar.setBackground(new java.awt.Color(253, 253, 255));
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/editarN.jpg"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 280, 130, 60));

        txtBusqueda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBusquedaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBusquedaFocusLost(evt);
            }
        });
        txtBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBusquedaActionPerformed(evt);
            }
        });
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyTyped(evt);
            }
        });
        add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 320, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ListaodologoBarberia.png"))); // NOI18N
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lupa.jpg"))); // NOI18N
        jLabel2.setText("Buscar");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 70, -1));

        btnAtras.setBackground(new java.awt.Color(253, 253, 255));
        btnAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes proyecto/atras.jpg"))); // NOI18N
        btnAtras.setBorder(null);
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });
        add(btnAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, -1, -1));

        btnAdelante.setBackground(new java.awt.Color(253, 253, 255));
        btnAdelante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes proyecto/adelante.jpg"))); // NOI18N
        btnAdelante.setBorder(null);
        btnAdelante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdelanteActionPerformed(evt);
            }
        });
        add(btnAdelante, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, -1, -1));

        seguimiento.setText("Pagina: ");
        add(seguimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 470, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
       ProvedoresStore proveedores = new ProvedoresStore();
       proveedores.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed
        
    private void tblMostrarProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMostrarProveedoresMouseClicked
       
    }//GEN-LAST:event_tblMostrarProveedoresMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
      EditarProvedores editarProvedor = new EditarProvedores();       
        int fila = tblMostrarProveedores.getSelectedRow();  
        
       if (fila>=0){
        
        editarProvedor.txt_nombre.setText(tblMostrarProveedores.getValueAt(fila, 1).toString());
       editarProvedor.txt_empresa.setText(tblMostrarProveedores.getValueAt(fila, 2).toString());
        editarProvedor.fmt_rtn.setText(tblMostrarProveedores.getValueAt(fila, 3).toString());
        editarProvedor.fmt_telefono.setText(tblMostrarProveedores.getValueAt(fila, 4).toString());
        editarProvedor.txta_direccion.setText(tblMostrarProveedores.getValueAt(fila, 5).toString());
      editarProvedor.txtID.setText(tblMostrarProveedores.getValueAt(fila, 0).toString());
       editarProvedor.setVisible(true);
        } else {
        JOptionPane.showMessageDialog(null,  "Debes seleccionar una fila ");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void txtBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyTyped
        
    }//GEN-LAST:event_txtBusquedaKeyTyped

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaActionPerformed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        String textoBusqueda = txtBusqueda.getText();
        String placeholder = "Buscar por nombre";
        Controlador.Proveedores.MostrarProvedores(txtBusqueda.getText(),paginaActual);
    
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void txtBusquedaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBusquedaFocusGained
        
       
    }//GEN-LAST:event_txtBusquedaFocusGained

    private void txtBusquedaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBusquedaFocusLost
        // TODO add your handling code here:
        JTextField texField = (JTextField) evt.getSource();
        String placeholder = "Bucar por nombre";
        
        if(texField.getText().isEmpty()){
            texField.setText(placeholder);
            texField.setForeground(Color.gray);
        }
    }//GEN-LAST:event_txtBusquedaFocusLost

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
     if (paginaActual > 1) {
            paginaActual--;
           // Controlador.InventarioProductos.MostrarProductos(txtBusqueda.getText(), paginaActual, totalPages);
            Controlador.InventarioProductos.MostrarProductos(txtBusqueda.getText(), paginaActual);
        }
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnAdelanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdelanteActionPerformed
         if (paginaActual < totalPages) {
            paginaActual++;
            Controlador.Proveedores.MostrarProvedores(txtBusqueda.getText(), paginaActual);
        }
    }//GEN-LAST:event_btnAdelanteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdelante;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel seguimiento;
    public static javax.swing.JTable tblMostrarProveedores;
    public static javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables

   
    
}