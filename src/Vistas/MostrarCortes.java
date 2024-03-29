/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vistas;

import Controlador.TiposCortes;
import static Controlador.TiposCortes.currentPage;
import static Controlador.TiposCortes.eliminarCorte;
import static Controlador.TiposCortes.updateTable;
import static Controlador.TiposCortes.totalPages;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.prompt.PromptSupport;


/**
 *
 * @author PC
 */
public class MostrarCortes extends javax.swing.JPanel {
    

    /**
     * Creates new form MostrarCortes
     */
    public MostrarCortes() {
        initComponents();
        tblMostrarCortes.getTableHeader().setReorderingAllowed(false);
        Controlador.TiposCortes.MostrarCortes("");
        
        //placeholder del cuadro de busqueda
        PromptSupport.setPrompt("Buscar por nombre del corte de cabello ",txtBusqueda1);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnPrvious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMostrarCortes = new javax.swing.JTable();
        btnCrear1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtBusqueda1 = new javax.swing.JTextField();
        lblPageInfo1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        generos = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 254));

        jButton4.setBackground(new java.awt.Color(255, 253, 252));
        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        jButton4.setText("Eliminar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lupa.jpg"))); // NOI18N
        jLabel5.setText("Buscar");

        btnPrvious.setBackground(new java.awt.Color(253, 253, 252));
        btnPrvious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes proyecto/atras.jpg"))); // NOI18N
        btnPrvious.setBorder(null);
        btnPrvious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrviousActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(253, 253, 252));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes proyecto/adelante.jpg"))); // NOI18N
        btnNext.setBorder(null);
        btnNext.setBorderPainted(false);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        //hacer que la tabla no sea editabe
        tblMostrarCortes.setFocusable(false);
        tblMostrarCortes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblMostrarCortes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblMostrarCortes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMostrarCortesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblMostrarCortes);

        btnCrear1.setBackground(new java.awt.Color(255, 253, 252));
        btnCrear1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCrear1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/agregarN.jpg"))); // NOI18N
        btnCrear1.setText("Agregar ");
        btnCrear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrear1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 153));
        jLabel6.setText("Listado de tipos de cortes de cabellos ");

        txtBusqueda1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBusqueda1ActionPerformed(evt);
            }
        });
        txtBusqueda1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusqueda1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusqueda1KeyTyped(evt);
            }
        });

        lblPageInfo1.setText("Pagina:");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ListaodologoBarberia (1).png"))); // NOI18N

        generos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Hombre", "Mujer" }));
        generos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusqueda1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addContainerGap(799, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnPrvious)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnNext)
                                    .addGap(526, 526, 526)
                                    .addComponent(lblPageInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(generos, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(12, 12, 12)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnCrear1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(193, 193, 193)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 227, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusqueda1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnCrear1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPrvious)
                    .addComponent(btnNext)
                    .addComponent(lblPageInfo1)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrviousActionPerformed
        // TODO add your handling code here:
        if (currentPage > 1) {
            currentPage--;
            updateTable("");
            int opcion = generos.getSelectedIndex();
            String valorBus = txtBusqueda1.getText();
            TiposCortes.mostrarGenero(opcion, valorBus);
            
        }

    }//GEN-LAST:event_btnPrviousActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        if (currentPage < totalPages) {
            currentPage++;
            updateTable("");
            int opcion = generos.getSelectedIndex();
            String valorBus = txtBusqueda1.getText();
            TiposCortes.mostrarGenero(opcion, valorBus);
            
        }

    }//GEN-LAST:event_btnNextActionPerformed

    private void btnCrear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrear1ActionPerformed
        IngresarCorte ingresarCorte = new IngresarCorte();
        ingresarCorte.setVisible(true);
        
    }//GEN-LAST:event_btnCrear1ActionPerformed

    private void txtBusqueda1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusqueda1ActionPerformed
        int opcion = generos.getSelectedIndex();
        String valorBus = txtBusqueda1.getText();
        TiposCortes.mostrarGenero(opcion, valorBus);
    }//GEN-LAST:event_txtBusqueda1ActionPerformed

    private void txtBusqueda1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusqueda1KeyReleased
        // TODO add your handling code here:
        String textoBusqueda = txtBusqueda1.getText();
        Controlador.TiposCortes.MostrarCortes(textoBusqueda);
    }//GEN-LAST:event_txtBusqueda1KeyReleased

    private void txtBusqueda1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusqueda1KeyTyped

    }//GEN-LAST:event_txtBusqueda1KeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        //variable para seleccionar el producto de la lista de productos
        int fila = tblMostrarCortes.getSelectedRow();
        
        //try catch para ejecutar las validaciones
        try {
            if (fila < 0) {
                JOptionPane.showMessageDialog(null, "No se seleccionó ningún corte de cabello",
                    "Error al eliminar", JOptionPane.WARNING_MESSAGE);
                return; // Salir del método si no se seleccionó ningún producto
            }
            else{
                int respuesta = JOptionPane.showConfirmDialog(null,"Deseas eliminar "
                        + "este registro?","Advertencia", JOptionPane.YES_NO_OPTION);
                
                if(respuesta == JOptionPane.YES_OPTION){
                    //llamar el metodo eliminarCorte para eliminar registros de la tabla
                eliminarCorte("");
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void generosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generosActionPerformed
        // TODO add your handling code here:
        updateTable("");
        int opcion = generos.getSelectedIndex();
        String valorBus = txtBusqueda1.getText();
        TiposCortes.mostrarGenero(opcion, valorBus);
    }//GEN-LAST:event_generosActionPerformed

    private void tblMostrarCortesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMostrarCortesMouseClicked
              if (evt.getClickCount() == 2) {
            int filaSeleccionada = tblMostrarCortes.getSelectedRow();
            if (filaSeleccionada >= 0) {
                String idCorte = (String) tblMostrarCortes.getValueAt(filaSeleccionada, 0); // Supongo que el ID está en la primera columna.
                VerCortes verCortes = null;
                try {
                    verCortes = new VerCortes(idCorte);
                } catch (IOException ex) {
                    Logger.getLogger(MostrarCortes.class.getName()).log(Level.SEVERE, null, ex);
                }
                verCortes.setVisible(true);
            }
        }
    }//GEN-LAST:event_tblMostrarCortesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrear1;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrvious;
    public static javax.swing.JComboBox<String> generos;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JLabel lblPageInfo1;
    public static javax.swing.JTable tblMostrarCortes;
    public static javax.swing.JTextField txtBusqueda1;
    // End of variables declaration//GEN-END:variables
}
