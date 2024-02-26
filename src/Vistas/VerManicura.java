/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import ConsultasSQL.QuerysManicura;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Josue
 */


public class VerManicura extends javax.swing.JFrame {

    /**
     * Creates new form CrearCliente
     */
    public VerManicura(QuerysManicura manicura) {
        initComponents();
        this.setLocationRelativeTo(null);
        
        jlEsmaltado.setText(manicura.getEsmaltado());
        jlEstilo.setText(manicura.getEstilo());
        jlPrecio.setText(manicura.getPrecio().toString());
        jlTecnica.setText(manicura.getTecnica());
        jADescripcion.setText(manicura.getDescripcion());
        
        ImageIcon imageIcon = new ImageIcon(manicura.getFoto());
        
        Image image = imageIcon.getImage();

                    int width = Label_Foto1.getWidth();
                    int height =  Label_Foto1.getHeight();
                    BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2 = scaledImage.createGraphics();
                    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g2.drawImage(image, 0, 0, width, height, null);
                    g2.dispose();
                 
        Label_Foto1.setIcon(new ImageIcon(scaledImage));
        
        ImageIcon imageIcon2 = new ImageIcon(manicura.getFoto1());
        
        Image image2 = imageIcon2.getImage();

                    int width2 = Label_Foto2.getWidth();
                    int height2 =  Label_Foto2.getHeight();
                    BufferedImage scaledImage2 = new BufferedImage(width2, height2, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g22 = scaledImage2.createGraphics();
                    g22.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g22.drawImage(image2, 0, 0, width2, height2, null);
                    g22.dispose();
        Label_Foto2.setIcon(new ImageIcon(scaledImage2));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlEsmaltado = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jADescripcion = new javax.swing.JTextArea();
        Label_Foto1 = new javax.swing.JLabel();
        Label_Foto2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jlPrecio = new javax.swing.JLabel();
        jlEstilo = new javax.swing.JLabel();
        jlTecnica = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(250, 248, 248));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Ver manicura o pedicura");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Estilo:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));

        jlEsmaltado.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlEsmaltado.setText("Precio:");
        jPanel1.add(jlEsmaltado, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, 260, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Descripción:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, -1, -1));

        btnCancelar.setBackground(new java.awt.Color(249, 253, 250));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(0, 0, 153));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes proyecto/volver.jpg"))); // NOI18N
        btnCancelar.setText("Volver");
        btnCancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 550, 130, 60));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Diseño:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, -1, -1));

        jADescripcion.setEditable(false);
        jADescripcion.setBackground(new java.awt.Color(255, 248, 248));
        jADescripcion.setColumns(20);
        jADescripcion.setLineWrap(true);
        jADescripcion.setRows(5);
        jADescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jADescripcionKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jADescripcion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, 280, 60));

        Label_Foto1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Label_Foto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_Foto1.setText("Foto 1");
        Label_Foto1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Label_Foto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Label_Foto1MouseClicked(evt);
            }
        });
        jPanel1.add(Label_Foto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, 190, 180));

        Label_Foto2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Label_Foto2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_Foto2.setText("Foto 2");
        Label_Foto2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Label_Foto2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Label_Foto2MouseClicked(evt);
            }
        });
        jPanel1.add(Label_Foto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 360, 180, 180));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Técnica:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, -1, -1));
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, 100, 120));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Esmaltado:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Precio:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, -1, -1));

        jlPrecio.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlPrecio.setText("Precio:");
        jPanel1.add(jlPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 260, -1));

        jlEstilo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlEstilo.setText("Precio:");
        jPanel1.add(jlEstilo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 260, -1));

        jlTecnica.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlTecnica.setText("Precio:");
        jPanel1.add(jlTecnica, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 260, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ListaodologoBarberia (1).png"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, 110, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed
  

    private void jADescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jADescripcionKeyTyped
        
      
           
    }//GEN-LAST:event_jADescripcionKeyTyped

    private void Label_Foto2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Label_Foto2MouseClicked
  
    }//GEN-LAST:event_Label_Foto2MouseClicked

    private void Label_Foto1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Label_Foto1MouseClicked

      
    }//GEN-LAST:event_Label_Foto1MouseClicked



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label_Foto1;
    private javax.swing.JLabel Label_Foto2;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JTextArea jADescripcion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlEsmaltado;
    private javax.swing.JLabel jlEstilo;
    private javax.swing.JLabel jlPrecio;
    private javax.swing.JLabel jlTecnica;
    // End of variables declaration//GEN-END:variables


    
}
