/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;
import Conexion.Conexion;
import ConsultasSQL.QuerysTratamiento;
import ConsultasSQL.QuerysTratamientoProductos;
import com.mysql.cj.jdbc.Blob;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class VerTratamiento extends javax.swing.JFrame {
    private String idCorte;
    private static Conexion con = new Conexion();
    private static Connection conexion = con.getConexion();
    public VerTratamiento(String idCorte) throws IOException {
        initComponents();
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);
        this.idCorte = idCorte;
        // Aquí deberías usar el ID para cargar los datos del corte en los componentes visuales (por ejemplo, lblNombre, lblPrecio, etc.).
        cargarDatosDelTratamiento();
        
        // ocultar la columna 1 id de la tabla tratamento_productos
        int columnIndex = 1; // Índice de la columna que deseas ocultar

        // Oculta la columna
        tblProductosDeTratamiento.getColumnModel().getColumn(columnIndex).setMaxWidth(0);
        tblProductosDeTratamiento.getColumnModel().getColumn(columnIndex).setMinWidth(0);
        tblProductosDeTratamiento.getColumnModel().getColumn(columnIndex).setPreferredWidth(0);
        tblProductosDeTratamiento.getColumnModel().getColumn(columnIndex).setResizable(false);
        
        // ocultar la columna 2 id de la tabla tratamento_productos
        int columnIndex1 = 2; // Índice de la columna que deseas ocultar

        // Oculta la columna
        tblProductosDeTratamiento.getColumnModel().getColumn(columnIndex1).setMaxWidth(0);
        tblProductosDeTratamiento.getColumnModel().getColumn(columnIndex1).setMinWidth(0);
        tblProductosDeTratamiento.getColumnModel().getColumn(columnIndex1).setPreferredWidth(0);
        tblProductosDeTratamiento.getColumnModel().getColumn(columnIndex1).setResizable(false);
        
    }
        
    private VerTratamiento() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void cargarDatosDelTratamiento() {
        try {
            
            // Cargar la consulta SQL desde la variable 'verPtratamientos'.
            String consultaSQL2 = QuerysTratamientoProductos.verPtratamientos;
            
             // Cargar la consulta SQL desde el archivo 'QuerysCortes'.
            String consultaSQL = QuerysTratamiento.VerTratamiento;
        
            // Crear una sentencia preparada con la consulta SQL.
            PreparedStatement ps = conexion.prepareStatement(consultaSQL);
            PreparedStatement ps2 = conexion.prepareStatement(consultaSQL2);
            ps.setString(1, idCorte); 
            ps2.setString(1, idCorte);
        
            // Ejecutar la consulta y obtener el resultado.
            var rs = ps.executeQuery();
            var rs2 = ps2.executeQuery();
        
            if(rs.next()){
                String nombre = rs.getString("Nombre");
                String tipoTratamiento = rs.getString("tipoTratamiento");
                String descripcion = rs.getString("Descripcion");
                Blob Foto1Blob = (Blob) rs.getBlob("Foto1");
                Blob Foto2Blob = (Blob) rs.getBlob("Foto2");
            
                InputStream foto1Stream = Foto1Blob.getBinaryStream();
                InputStream foto2Stream = Foto2Blob.getBinaryStream();
            
                BufferedImage Foto1Image = ImageIO.read(foto1Stream);
                BufferedImage Foto2Image = ImageIO.read(foto2Stream);
            
                String nombreProductos = rs.getString("estado");
                String precio = rs.getString("precio");
            
                txtNombre.setText(nombre);
                tTratamiento.setText(tipoTratamiento);
                txtDescripcion.setText(descripcion);
                txtprecio.setText(precio);
                // Tamaño fijo para las imágenes
                int imagenAncho = 200; // Ancho deseado en píxeles
                int imagenAlto = 200; // Alto deseado en píxeles
            
                if (Foto1Image != null) {
                    Foto1Image = resizeImage(Foto1Image, imagenAncho, imagenAlto);
                    Label_Foto1.setIcon(new ImageIcon(Foto1Image));
                    Label_Foto1.setText(""); // Borra cualquier texto previo
                } else {
                    Label_Foto1.setText("<html><div style='text-align: center;'>No hay imagen<br>disponible</div></html>");
                    Label_Foto1.setForeground(Color.RED);
                }
                if (Foto2Image != null) {
                    Foto2Image = resizeImage(Foto2Image, imagenAncho, imagenAlto);
                    Label_Foto2.setIcon(new ImageIcon(Foto2Image));
                    Label_Foto2.setText(""); // Borra cualquier texto previo
                } else {
                    Label_Foto2.setText("<html><div style='text-align: center;'>No hay imagen<br>disponible</div></html>");
                    Label_Foto2.setForeground(Color.RED);
                }
            
                txtProductos.setText(nombreProductos);
            
                //Metodo para mostrar los productos de la tabla
                // Limpiar el modelo de la tabla antes de agregar nuevos datos.
                DefaultTableModel model = (DefaultTableModel) Vistas.VerTratamiento.tblProductosDeTratamiento.getModel();
                model.setRowCount(0);
                int count = 1;
                
                // Llenar la tabla con los datos de productos.
                while (rs2.next()) {
                    String idProducto = rs2.getString("id");
                    String idTratamiento = rs2.getString("id_tratamientos");
                    String nombreProducto = rs2.getString("nombre_producto");

                    String[] datos = {
                        String.valueOf(count),
                        idProducto,
                        idTratamiento,
                        nombreProducto
                };

                model.addRow(datos);
                count++;
            }
            
            }
        
                // Cierra los recursos (ResultSet, PreparedStatement).
                rs.close();
                ps.close();
            } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Método para redimensionar la imagen a un tamaño específico
    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = resizedImage.createGraphics();
        graphics.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics.dispose();
        return resizedImage;
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
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Label_Foto1 = new javax.swing.JLabel();
        Label_Foto2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tTratamiento = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        txtProductos = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProductosDeTratamiento = new javax.swing.JTable();
        txtprecio = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));

        jLabel1.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Ver tratamiento");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Nombre del tratamiento:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Descripción:");

        txtNombre.setEditable(false);
        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtNombre.setBorder(null);
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(249, 253, 250));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes proyecto/volver.jpg"))); // NOI18N
        btnCancelar.setText("Volver");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Fotos:");

        Label_Foto1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Label_Foto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_Foto1.setText("Foto 1");
        Label_Foto1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Label_Foto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Label_Foto1MouseClicked(evt);
            }
        });

        Label_Foto2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Label_Foto2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_Foto2.setText("Foto 2");
        Label_Foto2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Label_Foto2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Label_Foto2MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("Tipo de tratamiento:");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ListaodologoBarberia.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("Listado:");

        tTratamiento.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tTratamiento.setText("jLabel3");

        txtDescripcion.setEditable(false);
        txtDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(5);
        txtDescripcion.setBorder(null);
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtDescripcion);

        txtProductos.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtProductos.setText("jLabel3");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Estado:");
        jLabel3.setMaximumSize(new java.awt.Dimension(94, 22));
        jLabel3.setMinimumSize(new java.awt.Dimension(94, 22));
        jLabel3.setPreferredSize(new java.awt.Dimension(94, 22));

        //hacer que la tabla no sea editabe
        tblProductosDeTratamiento.setFocusable(false);
        tblProductosDeTratamiento = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblProductosDeTratamiento.setAutoCreateRowSorter(true);
        tblProductosDeTratamiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num", "Id", "Id_tratamiento", "Nombre del tratamiento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductosDeTratamiento.setToolTipText("");
        tblProductosDeTratamiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosDeTratamientoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblProductosDeTratamiento);
        if (tblProductosDeTratamiento.getColumnModel().getColumnCount() > 0) {
            tblProductosDeTratamiento.getColumnModel().getColumn(0).setMinWidth(40);
            tblProductosDeTratamiento.getColumnModel().getColumn(0).setPreferredWidth(40);
            tblProductosDeTratamiento.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        txtprecio.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtprecio.setText("jLabel10");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setText("Precio:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(145, 145, 145)
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(147, 147, 147)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtprecio))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Label_Foto2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(10, 10, 10)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addComponent(jLabel7)
                            .addGap(14, 14, 14)
                            .addComponent(tTratamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(80, 80, 80)
                            .addComponent(jLabel4)
                            .addGap(6, 6, 6)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(30, 30, 30)
                    .addComponent(jLabel6)
                    .addGap(5, 5, 5)
                    .addComponent(Label_Foto1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(349, 349, 349)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(225, 225, 225)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCancelar)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(tTratamiento))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel6)
                    .addComponent(Label_Foto1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(Label_Foto2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtprecio)
                                    .addComponent(jLabel9)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtProductos)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))))
                .addGap(26, 26, 26)
                .addComponent(btnCancelar)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped

    }//GEN-LAST:event_txtNombreKeyTyped

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void Label_Foto1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Label_Foto1MouseClicked

    }//GEN-LAST:event_Label_Foto1MouseClicked

    private void Label_Foto2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Label_Foto2MouseClicked

    }//GEN-LAST:event_Label_Foto2MouseClicked

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped


    }//GEN-LAST:event_txtDescripcionKeyTyped

    private void tblProductosDeTratamientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosDeTratamientoMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            int filaSeleccionada = tblProductosDeTratamiento.getSelectedRow();
            if (filaSeleccionada >= 0) {
                String idTratamiento = (String) tblProductosDeTratamiento.getValueAt(filaSeleccionada, 0); // Supongo que el ID está en la primera columna.
                VerTratamiento verTratamiento = null;
                try {
                    verTratamiento = new VerTratamiento(idTratamiento);
                } catch (IOException ex) {
                    //Logger.getLogger(MostrarCortes.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
                verTratamiento.setVisible(true);
            }
        }
    }//GEN-LAST:event_tblProductosDeTratamientoMouseClicked

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
            java.util.logging.Logger.getLogger(VerTratamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerTratamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerTratamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerTratamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerTratamiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel Label_Foto1;
    public static javax.swing.JLabel Label_Foto2;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JLabel tTratamiento;
    public static javax.swing.JTable tblProductosDeTratamiento;
    public static javax.swing.JTextArea txtDescripcion;
    public static javax.swing.JTextField txtNombre;
    public static javax.swing.JLabel txtProductos;
    public static javax.swing.JLabel txtprecio;
    // End of variables declaration//GEN-END:variables

}
