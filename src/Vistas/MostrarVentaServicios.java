/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vistas;

import Controlador.Clientes;
import Controlador.VentasServicios;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author PC
 */
public class MostrarVentaServicios extends javax.swing.JPanel {
    private int paginaActual = 1;
    public static int totalPages = Clientes.NumeroPages("");
    
    /**
     * Creates new form MostrarVentaServicios
     */
    public MostrarVentaServicios() {
        initComponents();
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String textoInicial;
        String textoFinal;
               try {
                    textoFinal = formato.format(jDateChooser1.getDate());
               } catch (Exception e) {
                    textoFinal = null;
               }
                try {
                    textoInicial = formato.format(jDateChooser2.getDateEditor().getDate());
               } catch (Exception e) {
                    textoInicial = null;
               }
       
        VentasServicios.MostrarVentaServicios("", paginaActual, totalPages, textoInicial, textoFinal);
        PromptSupport.setPrompt("Buscar por cliente, CAI.", txtbusqueda);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        seguimiento = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtventas = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        previo = new javax.swing.JButton();
        siguiente = new javax.swing.JButton();
        txtbusqueda = new javax.swing.JTextField();
        Buscar = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        seguimiento.setText("Pagina:");

        btnNuevo.setBackground(new java.awt.Color(253, 253, 255));
        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/agregarN.jpg"))); // NOI18N
        btnNuevo.setText("Agregar");
        btnNuevo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        jDateChooser2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser2PropertyChange(evt);
            }
        });

        jtventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "Núm", "Cliente", "Num Factura", "CAI", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtventasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtventas);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("Buscar por fecha");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Listado de ventas de servicios");

        previo.setBackground(new java.awt.Color(253, 253, 255));
        previo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes proyecto/atras.jpg"))); // NOI18N
        previo.setBorder(null);
        previo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previoActionPerformed(evt);
            }
        });

        siguiente.setBackground(new java.awt.Color(253, 253, 255));
        siguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes proyecto/adelante.jpg"))); // NOI18N
        siguiente.setBorder(null);
        siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteActionPerformed(evt);
            }
        });

        txtbusqueda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));
        txtbusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusquedaKeyReleased(evt);
            }
        });

        Buscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Buscar.setForeground(new java.awt.Color(0, 0, 204));
        Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lupa.jpg"))); // NOI18N
        Buscar.setText("Buscar");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ListaodologoBarberia.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 821, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(50, 50, 50))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(previo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(599, 599, 599)
                .addComponent(seguimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(Buscar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(previo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(seguimiento))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        CrearVentaServicio ventasServicio = new CrearVentaServicio();
        ventasServicio.setVisible(true);
        ventasServicio.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        if ("date".equals(evt.getPropertyName())) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String textoBusqueda = txtbusqueda.getText();
            String textoInicial;
            String textoFinal;
            try {
                textoFinal = formato.format(jDateChooser1.getDate());
            } catch (Exception e) {
                textoFinal = null;
            }
            try {
                textoInicial = formato.format(jDateChooser2.getDateEditor().getDate());
            } catch (Exception e) {
                textoInicial = null;
            }
            System.out.println(textoInicial);
            System.out.println(textoFinal);

            paginaActual = 1;
            totalPages = VentasServicios.NumeroPages(textoBusqueda,textoInicial,textoFinal);
            VentasServicios.MostrarVentaServicios(textoBusqueda, paginaActual, totalPages,textoInicial,textoFinal);
        }
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser2PropertyChange
        if ("date".equals(evt.getPropertyName())) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String textoBusqueda = txtbusqueda.getText();
            String textoInicial;
            String textoFinal;
            try {
                textoFinal = formato.format(jDateChooser1.getDate());
            } catch (Exception e) {
                textoFinal = null;
            }
            try {
                textoInicial = formato.format(jDateChooser2.getDateEditor().getDate());
            } catch (Exception e) {
                textoInicial = null;
            }
            System.out.println(textoInicial);
            System.out.println(textoFinal);

            paginaActual = 1;
            totalPages = VentasServicios.NumeroPages(textoBusqueda,textoInicial,textoFinal);
            VentasServicios.MostrarVentaServicios(textoBusqueda, paginaActual, totalPages,textoInicial,textoFinal);
        }
    }//GEN-LAST:event_jDateChooser2PropertyChange

    private void siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteActionPerformed
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String textoBusqueda = txtbusqueda.getText();
        String textoInicial;
        String textoFinal;
        try {
            textoFinal = formato.format(jDateChooser1.getDate());
        } catch (Exception e) {
            textoFinal = null;
        }
        try {
            textoInicial = formato.format(jDateChooser2.getDateEditor().getDate());
        } catch (Exception e) {
            textoInicial = null;
        }
        totalPages = VentasServicios.NumeroPages(txtbusqueda.getText(), textoInicial, textoFinal);
        if (paginaActual < totalPages) {
            paginaActual++;

            VentasServicios.MostrarVentaServicios(textoBusqueda, paginaActual, totalPages, textoInicial, textoFinal);
        }
    }//GEN-LAST:event_siguienteActionPerformed

    private void previoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previoActionPerformed
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String textoBusqueda = txtbusqueda.getText();
        String textoInicial;
        String textoFinal;
        try {
            textoFinal = formato.format(jDateChooser1.getDate());
        } catch (Exception e) {
            textoFinal = null;
        }
        try {
            textoInicial = formato.format(jDateChooser2.getDateEditor().getDate());
        } catch (Exception e) {
            textoInicial = null;
        }
        if (paginaActual > 1) {
            paginaActual--;

            VentasServicios.MostrarVentaServicios(textoBusqueda, paginaActual, totalPages, textoInicial, textoFinal);
        }
    }//GEN-LAST:event_previoActionPerformed

    private void jtventasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtventasMouseClicked

        if(evt.getClickCount() == 2){
            int fila = jtventas.getSelectedRow();
            VerVenta verVenta = new VerVenta(jtventas.getValueAt(fila, 0).toString());
            JOptionPane.showMessageDialog(null, jtventas.getValueAt(fila, 7));
            verVenta.lblfactura.setText(jtventas.getValueAt(fila, 4).toString());
            verVenta.lblCai.setText(jtventas.getValueAt(fila, 3).toString());
            verVenta.lblcliente.setText(jtventas.getValueAt(fila, 2).toString());
            verVenta.lblfecha.setText(jtventas.getValueAt(fila, 5).toString());
            verVenta.setVisible(true);

        }  // TODO add your handling code here:
    }//GEN-LAST:event_jtventasMouseClicked

    private void txtbusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusquedaKeyReleased
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String textoBusqueda = txtbusqueda.getText();
        String textoInicial;
        String textoFinal;
        try {
            textoFinal = formato.format(jDateChooser1.getDate());
        } catch (Exception e) {
            textoFinal = null;
        }
        try {
            textoInicial = formato.format(jDateChooser2.getDateEditor().getDate());
        } catch (Exception e) {
            textoInicial = null;
        }
        paginaActual = 1;
        totalPages = VentasServicios.NumeroPages(textoBusqueda, textoInicial, textoFinal);
        VentasServicios.MostrarVentaServicios(textoBusqueda, paginaActual, totalPages, textoInicial, textoFinal);        // TODO add your handling code here:
    }//GEN-LAST:event_txtbusquedaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Buscar;
    private javax.swing.JButton btnNuevo;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jtventas;
    private javax.swing.JButton previo;
    public static javax.swing.JLabel seguimiento;
    private javax.swing.JButton siguiente;
    private javax.swing.JTextField txtbusqueda;
    // End of variables declaration//GEN-END:variables
}
