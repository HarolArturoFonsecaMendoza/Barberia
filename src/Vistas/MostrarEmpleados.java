/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vistas;

import com.mysql.cj.x.protobuf.MysqlxExpr;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.text.PlainDocument;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author Andrea
 */
public class MostrarEmpleados extends javax.swing.JPanel {
        
    private int paginaActual = 1;
    public static int totalPages;
    /**
     * Creates new form MostrarEmpleados
     */
    public MostrarEmpleados() {
        initComponents();
        tblMostrarEmpleados.getTableHeader().setReorderingAllowed(false);
        totalPages = Controlador.Empleados.NumeroPages("", paginaActual,Listadoempleados.getModel().getSelectedItem().toString());
        Controlador.Empleados.MostrarEmpleados("", paginaActual, totalPages,Listadoempleados.getModel().getSelectedItem().toString());
        
         PromptSupport.setPrompt("Buscar por nombre y apellido", txtBusqueda);
        
        
        
        tblMostrarEmpleados.addMouseListener(new MouseAdapter(){
            
            public void mouseClicked(MouseEvent e){
                if (e.getClickCount()==2){
                    int fila= tblMostrarEmpleados.getSelectedRow();
                    VerEmpleado verEmpleado = new VerEmpleado();
                    
                    verEmpleado.lblNombre.setText(tblMostrarEmpleados.getValueAt(fila, 1).toString());
                    verEmpleado.lblApellido.setText(tblMostrarEmpleados.getValueAt(fila, 2).toString());
                    verEmpleado.lblnumeroidentidad.setText(tblMostrarEmpleados.getValueAt(fila, 3).toString());
                    verEmpleado.lblTeléfono.setText(tblMostrarEmpleados.getValueAt(fila, 4).toString());
                    verEmpleado.lblcargo.setText(tblMostrarEmpleados.getValueAt( fila,5).toString());
                    verEmpleado.lblEstado.setText(tblMostrarEmpleados.getValueAt(fila, 6).toString());
                    verEmpleado.txtADireccion.setText(tblMostrarEmpleados.getValueAt(fila, 7).toString());
                    verEmpleado.lblnombreemergencia.setText(tblMostrarEmpleados.getValueAt(fila, 8).toString());
                    verEmpleado.lblteléfonoemergencia.setText(tblMostrarEmpleados.getValueAt(fila, 9).toString());
                    
                     verEmpleado.setVisible(true);
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
        tblMostrarEmpleados = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        siguiente = new javax.swing.JButton();
        Previo = new javax.swing.JButton();
        seguimiento = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Listadoempleados = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        tblMostrarEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Num", "Nombre", "Apellido", "Identidad", "Teléfono", "Cargo", "Estado", "Direccion", "Emergencia", "Telefono Emergencia", "id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblMostrarEmpleados);
        if (tblMostrarEmpleados.getColumnModel().getColumnCount() > 0) {
            tblMostrarEmpleados.getColumnModel().getColumn(0).setMinWidth(50);
            tblMostrarEmpleados.getColumnModel().getColumn(0).setMaxWidth(50);
            tblMostrarEmpleados.getColumnModel().getColumn(4).setMinWidth(0);
            tblMostrarEmpleados.getColumnModel().getColumn(4).setMaxWidth(0);
            tblMostrarEmpleados.getColumnModel().getColumn(7).setMinWidth(0);
            tblMostrarEmpleados.getColumnModel().getColumn(7).setMaxWidth(0);
            tblMostrarEmpleados.getColumnModel().getColumn(8).setMinWidth(0);
            tblMostrarEmpleados.getColumnModel().getColumn(8).setMaxWidth(0);
            tblMostrarEmpleados.getColumnModel().getColumn(9).setMinWidth(0);
            tblMostrarEmpleados.getColumnModel().getColumn(9).setMaxWidth(0);
        }

        jLabel1.setBackground(new java.awt.Color(204, 255, 204));
        jLabel1.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("                           Listado de Empleados");

        txtBusqueda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBusquedaActionPerformed(evt);
            }
        });
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(253, 253, 252));
        btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/agregarN.jpg"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEditar.setBackground(new java.awt.Color(253, 253, 252));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/editarN.jpg"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logobarberiapequeno.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        siguiente.setBackground(new java.awt.Color(253, 253, 252));
        siguiente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        siguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes proyecto/adelante.jpg"))); // NOI18N
        siguiente.setBorder(null);
        siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteActionPerformed(evt);
            }
        });

        Previo.setBackground(new java.awt.Color(253, 253, 252));
        Previo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Previo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes proyecto/atras.jpg"))); // NOI18N
        Previo.setBorder(null);
        Previo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrevioActionPerformed(evt);
            }
        });

        seguimiento.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        seguimiento.setText("Página: ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lupa.jpg"))); // NOI18N
        jLabel4.setText("Buscar ");

        Listadoempleados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Activos", "Inactivos" }));
        Listadoempleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListadoempleadosActionPerformed(evt);
            }
        });

        jLabel3.setText("Seleccionar Listado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Listadoempleados, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 848, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Previo, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(seguimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(239, 239, 239))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(182, 182, 182)))
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Listadoempleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(btnNuevo)
                        .addGap(56, 56, 56)
                        .addComponent(btnEditar)
                        .addGap(238, 238, 238))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seguimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Previo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
      EmpleadosStore empleados = new EmpleadosStore();
      empleados.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaActionPerformed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
         String textoBusqueda = txtBusqueda.getText();
          Controlador.Empleados.MostrarEmpleados(textoBusqueda, paginaActual, totalPages,Listadoempleados.getModel().getSelectedItem().toString());
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        EditarEmpleados editarEmpleados = new EditarEmpleados();
                
        int fila = tblMostrarEmpleados.getSelectedRow();
        if(fila>=0){
        EditarEmpleados.txt_nombre.setText(tblMostrarEmpleados.getValueAt( fila,1).toString());
        EditarEmpleados.txt_apellido.setText(tblMostrarEmpleados.getValueAt( fila,2).toString());
        EditarEmpleados.fmt_identidad.setText(tblMostrarEmpleados.getValueAt( fila,3).toString());
        EditarEmpleados.fmt_telefono.setText(tblMostrarEmpleados.getValueAt( fila,4).toString());
        EditarEmpleados.cbxCargo.setSelectedItem(tblMostrarEmpleados.getValueAt( fila,5).toString());
        EditarEmpleados.cbxEstado.setSelectedItem(tblMostrarEmpleados.getValueAt( fila,6).toString());
        EditarEmpleados.tex_direccion.setText(tblMostrarEmpleados.getValueAt( fila,7).toString());
        EditarEmpleados.txt_NomEme.setText(tblMostrarEmpleados.getValueAt( fila,8).toString());
        EditarEmpleados.fmt_telefonoEmergencia.setText(tblMostrarEmpleados.getValueAt( fila,9).toString());
        EditarEmpleados.txt_Id.setText(tblMostrarEmpleados.getValueAt( fila,10).toString());
        
        
        
        
        }
        
        
        
        
        editarEmpleados.setVisible(true);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void PrevioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrevioActionPerformed
     
        if (paginaActual > 1) {
            paginaActual--;
            Controlador.Empleados.MostrarEmpleados(txtBusqueda.getText(), paginaActual, totalPages,Listadoempleados.getModel().getSelectedItem().toString());
        }
    }//GEN-LAST:event_PrevioActionPerformed

    private void siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteActionPerformed
        totalPages = Controlador.Empleados.NumeroPages(txtBusqueda.getText(), paginaActual,Listadoempleados.getModel().getSelectedItem().toString());
        if (paginaActual < totalPages) {
            paginaActual++;
            Controlador.Empleados.MostrarEmpleados(txtBusqueda.getText(), paginaActual, totalPages,Listadoempleados.getModel().getSelectedItem().toString());
        }
    }//GEN-LAST:event_siguienteActionPerformed

    private void ListadoempleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListadoempleadosActionPerformed
        String  valor= Listadoempleados.getModel().getSelectedItem().toString();
    
        Controlador.Empleados.MostrarEmpleados(txtBusqueda.getText(), paginaActual, totalPages,valor);

    }//GEN-LAST:event_ListadoempleadosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Listadoempleados;
    public static javax.swing.JButton Previo;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel seguimiento;
    public static javax.swing.JButton siguiente;
    public static javax.swing.JTable tblMostrarEmpleados;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
