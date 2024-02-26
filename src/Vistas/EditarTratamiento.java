/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import Conexion.Conexion;
import ConsultasSQL.QuerysTratamiento;
import ConsultasSQL.QuerysTratamientoProductos;
import Controlador.Tratamiento;
import static Controlador.Tratamiento.MostrarTratamientos;
import static Vistas.MostrarTratamientos.tblMostrarTratamiento;
import com.mysql.cj.jdbc.Blob;
import com.sun.jdi.connect.spi.Connection;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class EditarTratamiento extends javax.swing.JFrame {
    private FileInputStream fis;
    private int longitudBytes;
    private static Conexion con = new Conexion();
    private static java.sql.Connection conexion = con.getConexion();
    private static PreparedStatement ps = null;
    private String idTratamiento;
    public static BufferedImage Foto1Image;
    public static BufferedImage Foto2Image;

    /**
     * Creates new form EditarTratamiento
     */
    public EditarTratamiento(String idTratamiento) throws IOException {
        initComponents();
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);
        this.idTratamiento = idTratamiento;
        // Aquí deberías usar el ID para cargar los datos del corte en los componentes visuales (por ejemplo, lblNombre, lblPrecio, etc.).
        cargarDatosDelTratamiento();
        DefaultTableModel model = (DefaultTableModel) tblProductosDeTratamiento.getModel();
        // Después de cargar o actualizar los datos de la tabla, selecciona la primera fila
        if (tblProductosDeTratamiento.getRowCount() > 0) {
            tblProductosDeTratamiento.setRowSelectionInterval(0, 0);
        }

        //ocultar el txtId
        txtId.setVisible(false);
        
    }
    
    private EditarTratamiento(){
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
            ps.setString(1, idTratamiento); 
            ps2.setString(1, idTratamiento);
        
            // Ejecutar la consulta y obtener el resultado.
            var rs = ps.executeQuery();
            var rs2 = ps2.executeQuery();
        
            if(rs.next()){
                String nombre = rs.getString("Nombre");
                String tipoTratamiento = rs.getString("tipoTratamiento");
                String descripcion = rs.getString("Descripcion");
                String precio = rs.getString("precio");
                Blob Foto1Blob = (Blob) rs.getBlob("Foto1");
                Blob Foto2Blob = (Blob) rs.getBlob("Foto2");
            
                InputStream foto1Stream = Foto1Blob.getBinaryStream();
                InputStream foto2Stream = Foto2Blob.getBinaryStream();
            
                BufferedImage Foto1Image = ImageIO.read(foto1Stream);
                BufferedImage Foto2Image = ImageIO.read(foto2Stream);
            
                //String nombreProductos = rs.getString("estado");
            
                txtNombre.setText(nombre);
                cbxTipoTratamiento.setSelectedItem(tipoTratamiento);
                txtDescripcion.setText(descripcion);
                txtPrecio.setText(precio);
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
            
                //txtProductos.setText(nombreProductos);
            
                //Metodo para mostrar los productos de la tabla
                // Limpiar el modelo de la tabla antes de agregar nuevos datos.
                DefaultTableModel model = (DefaultTableModel) Vistas.EditarTratamiento.tblProductosDeTratamiento.getModel();
                model.setRowCount(0);
                //int count = 1;
                
                // Llenar la tabla con los datos de productos.
                while (rs2.next()) {
                    
                    String nombreProducto = rs2.getString("nombre_producto");
                    String idProducto = rs2.getString("id_productos");
                    String idTratamiento = rs2.getString("id_tratamientos");
                    String id = rs2.getString("id");
                    String[] datos = {
                        //String.valueOf(count),
                        nombreProducto,
                        idProducto,
                        idTratamiento,
                        id
                };

                model.addRow(datos);
                //count++;
            }
            
            }
        
                // Cierra los recursos (ResultSet, PreparedStatement).
                rs.close();
                ps.close();
            } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public BufferedImage getFoto1Image() {
        return Foto1Image;
    }
    
    public BufferedImage getFoto2Image() {
        return Foto2Image;
    }
    
    // Método para redimensionar la imagen a un tamaño específico
    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = resizedImage.createGraphics();
        graphics.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics.dispose();
        return resizedImage;
    }
    
    // Método para obtener el próximo ID disponible desde la base de datos
    private int obtenerProximoId() {
        int maxId = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            

            // Crear la declaración SQL para obtener el máximo ID
            String sql = "SELECT MAX(id) FROM tratamientos";
            statement = conexion.createStatement();

            // Ejecutar la consulta y obtener el resultado
            resultSet = statement.executeQuery(sql);

            // Obtener el máximo ID
            if (resultSet.next()) {
                maxId = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejo adecuado de las excepciones en un entorno de producción
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                // No cerramos la conexión aquí para que pueda ser reutilizada
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Incrementar el máximo ID para obtener el próximo ID disponible
        return maxId + 1;
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
        btnEditar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        Label_Foto1 = new javax.swing.JLabel();
        Label_Foto2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbxTipoTratamiento = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        BtnAgregarListado = new javax.swing.JButton();
        BtnLimpiarListado = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtId = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProductosDeTratamiento = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dubai", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Editar tratamiento");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 21, 230, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Nombre del tratamiento:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 101, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Descripción:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, -1, -1));

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 101, 230, -1));

        btnEditar.setBackground(new java.awt.Color(253, 253, 255));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(0, 0, 204));
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/editarN.jpg"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 573, 110, -1));

        btnCancelar.setBackground(new java.awt.Color(253, 253, 255));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(0, 0, 204));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(739, 575, 110, 50));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Fotos:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(498, 81, -1, -1));

        txtDescripcion.setColumns(20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(5);
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtDescripcion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 183, 230, 100));

        Label_Foto1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Label_Foto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_Foto1.setText("Foto 1");
        Label_Foto1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Label_Foto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Label_Foto1MouseClicked(evt);
            }
        });
        jPanel1.add(Label_Foto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(549, 81, 190, 180));

        Label_Foto2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Label_Foto2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_Foto2.setText("Foto 2");
        Label_Foto2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Label_Foto2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Label_Foto2MouseClicked(evt);
            }
        });
        jPanel1.add(Label_Foto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(549, 292, 190, 180));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("Tipo de tratamiento:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 141, -1, -1));

        cbxTipoTratamiento.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbxTipoTratamiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Capilares ", "Faciales" }));
        cbxTipoTratamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoTratamientoActionPerformed(evt);
            }
        });
        jPanel1.add(cbxTipoTratamiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 143, 230, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ListaodologoBarberia.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(759, 1, 120, 100));

        BtnAgregarListado.setBackground(new java.awt.Color(253, 253, 255));
        BtnAgregarListado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnAgregarListado.setForeground(new java.awt.Color(0, 0, 204));
        BtnAgregarListado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/agregar productoN.jpg"))); // NOI18N
        BtnAgregarListado.setText("Agregar");
        BtnAgregarListado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));
        BtnAgregarListado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarListadoActionPerformed(evt);
            }
        });
        jPanel1.add(BtnAgregarListado, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 510, 110, -1));

        BtnLimpiarListado.setBackground(new java.awt.Color(253, 253, 255));
        BtnLimpiarListado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BtnLimpiarListado.setForeground(new java.awt.Color(0, 0, 204));
        BtnLimpiarListado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancelarr.png"))); // NOI18N
        BtnLimpiarListado.setText("Limpiar");
        BtnLimpiarListado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));
        BtnLimpiarListado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarListadoActionPerformed(evt);
            }
        });
        jPanel1.add(BtnLimpiarListado, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 510, 90, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("Precio:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, -1, -1));

        txtId.setText("jLabel3");
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 617, -1, -1));

        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });
        jPanel1.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, 120, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setText("Listado de productos:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, -1));

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
                "Nombre del tratamiento", "id_productos", "id_tratamiento", "Id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
        jScrollPane3.setViewportView(tblProductosDeTratamiento);
        if (tblProductosDeTratamiento.getColumnModel().getColumnCount() > 0) {
            tblProductosDeTratamiento.getColumnModel().getColumn(1).setMinWidth(0);
            tblProductosDeTratamiento.getColumnModel().getColumn(1).setPreferredWidth(0);
            tblProductosDeTratamiento.getColumnModel().getColumn(1).setMaxWidth(0);
            tblProductosDeTratamiento.getColumnModel().getColumn(2).setMinWidth(0);
            tblProductosDeTratamiento.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblProductosDeTratamiento.getColumnModel().getColumn(2).setMaxWidth(0);
            tblProductosDeTratamiento.getColumnModel().getColumn(3).setMinWidth(0);
            tblProductosDeTratamiento.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblProductosDeTratamiento.getColumnModel().getColumn(3).setMaxWidth(0);
        }

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 341, 230, 160));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        txtNombre.setText(txtNombre.getText().replaceAll("( )+", " "));
        if (txtNombre.getText().length() == 0 && evt.getKeyChar() == ' ') {
            evt.consume();
        }

        // Limitar la longitud a 30 caracteres
        if (txtNombre.getText().length() >= 30) {
            evt.consume();
        }

        char c = evt.getKeyChar();
        if(Character.isDigit(c)){
            getToolkit().beep();
            evt.consume();
        }

        // No permitir iniciar con espacios ni caracteres especiales
        if (txtNombre.getText().isEmpty() && (c == ' ' || !Character.isLetter(c))) {
            evt.consume();
            return;
        }

        // Permitir solo letras, espacios y borrar
        if (!Character.isLetter(c) && c != ' ' && c != '\b') {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private byte[] obtenerBytesDeImagen(String rutaImagen) {
   
    File archivoImagen = new File(rutaImagen);
    
    // Verificar si el archivo de imagen existe
   if (!archivoImagen.exists()) {
        JOptionPane.showMessageDialog(null, "Debe selecionar una imagen", "Error de validación", JOptionPane.WARNING_MESSAGE);
        return null; // Retorna null si el archivo no existe
    }

    // Continuar con la carga de la imagen
    try {
        FileInputStream fis = new FileInputStream(archivoImagen);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }
        return bos.toByteArray();
    } catch (IOException e) {
        e.printStackTrace();
        return null; // Retorna null si hay un error al cargar la imagen
    }
}
    
     private byte[] obtenerBytesDeImage(String rutaImagen) {
   
    File archivoImagen = new File(rutaImagen);
    
    // Verificar si el archivo de imagen existe
   if (!archivoImagen.exists()) {
        JOptionPane.showMessageDialog(null, "Debe selecionar una imagen", "Error de validación", JOptionPane.WARNING_MESSAGE);
        return null; // Retorna null si el archivo no existe
    }

    // Continuar con la carga de la imagen
    try {
        FileInputStream fis = new FileInputStream(archivoImagen);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }
        return bos.toByteArray();
    } catch (IOException e) {
        e.printStackTrace();
        return null; // Retorna null si hay un error al cargar la imagen
    }
}
     private byte[] obtenerByteDeImagen (String rutaImagen) {
   
    File archivoImagen = new File(rutaImagen);
    
    // Verificar si el archivo de imagen existe
   if (!archivoImagen.exists()) {
        JOptionPane.showMessageDialog(null, "Debe selecionar una imagen", "Error de validación", JOptionPane.WARNING_MESSAGE);
        return null; // Retorna null si el archivo no existe
    }

    // Continuar con la carga de la imagen
    try {
        FileInputStream fis = new FileInputStream(archivoImagen);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }
        return bos.toByteArray();
    } catch (IOException e) {
        e.printStackTrace();
        return null; // Retorna null si hay un error al cargar la imagen
    }
}
    
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        String nuevoNombre = txtNombre.getText().trim();
        String TipoTratamiento = cbxTipoTratamiento.getSelectedItem().toString();
        String direccion = txtDescripcion.getText().trim();
        
        String foto = Label_Foto1.getText().trim();
        String foto1 = Label_Foto2.getText().trim();
        
        int idtxt = Integer.parseInt(txtId.getText().trim());
        String texto = txtPrecio.getText();

        // Obtener el ID del tratamiento que se va a actualizar
        int fila = tblMostrarTratamiento.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "No se seleccionó ningún tratamiento", "Error al actualizar", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si no se seleccionó ningún tratamiento
        }
        String idTratamiento2 = (String) tblMostrarTratamiento.getValueAt(fila, 1);
        
        // Validar que el nuevo nombre no esté vacío
        if (nuevoNombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del tratamiento no puede estar vacío", "Error de validación", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si el nuevo nombre está vacío
        }
        //Validacion combobox
        if(TipoTratamiento.equals("seleccione un tratamiento")){
            JOptionPane.showMessageDialog(this, "No ha seleccionado ningun tratamiento", "Error de validación", JOptionPane.INFORMATION_MESSAGE);
            return; // Salir del método si el campo está vacío
        }
        
        //Validacion descripcion
        if (direccion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La descripción no puede estar vacía", "Error de validación", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si el campo está vacío
        }
        
        DefaultTableModel model = (DefaultTableModel) tblProductosDeTratamiento.getModel();
        // Verificar si la tabla está vacía
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "La tabla de productos está vacía", "Error de validación", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si la tabla está vacía
        }
        
        

        // Obtener el próximo ID disponible
        int idTratamiento = obtenerProximoId();

        String estado="habilitado";
        QuerysTratamiento querysT = new QuerysTratamiento();
        
        querysT.setId(idtxt);
        querysT.setNombre(nuevoNombre);
        querysT.setDescripcion(direccion);
        querysT.setTipoTratamiento(TipoTratamiento);
        querysT.setEstado(estado);
        if (!texto.isEmpty()) {
            // Paso 2: Intentar convertir la cadena a un valor double
            try {
                double precio = Double.parseDouble(texto);
                querysT.setPrecio(precio);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El precio no es un valor double válido.", "Error de validación", JOptionPane.WARNING_MESSAGE);
                return; // Salir del método si la tabla está vacía
                
            }
        } else {
            JOptionPane.showMessageDialog(null, "El precio está vacía.", "Error de validación", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si la tabla está vacía
        }
        
        
        // Verificar y cargar la primera imagen si es necesario
        if (!foto.isEmpty()) {
            byte[] imagenBytes = obtenerBytesDeImagen(foto);
            if (imagenBytes != null) {
                querysT.setFoto1(imagenBytes);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo cargar la primera imagen", "Error de validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        // Verificar y cargar la segunda imagen si es necesario
        if (!foto1.isEmpty()) {
            byte[] imagenBytes1 = obtenerBytesDeImagen(foto1);
            if (imagenBytes1 != null) {
                querysT.setFoto2(imagenBytes1);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo cargar la segunda imagen", "Error de validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        
        if (Tratamiento.ActualizarTratamiento(querysT)) {
            // Guardar nuevos productos
            for (int i = 0; i < model.getRowCount(); i++) {
                int idProducto = Integer.parseInt(model.getValueAt(i, 1).toString());
                int id_tratamientos = Integer.parseInt(model.getValueAt(i, 2).toString());
                 System.out.println("A: " + id_tratamientos);
                 System.out.println("B: " + idProducto);
    
                // Verificar si el producto es nuevo (no está en la base de datos)
                if (!Tratamiento.ProductoAsociadoATratamiento(id_tratamientos, idProducto)) {
                    // Guardar la nueva asociación de producto
                    QuerysTratamientoProductos querysTP = new QuerysTratamientoProductos();
                    querysTP.setId_tratamientos(querysT.getId());
                    querysTP.setId_productos(idProducto);
                    Tratamiento.GuardarTP(querysTP);

                }
            }
            JOptionPane.showMessageDialog(null, "Tratamiento editado exitosamente");
            this.dispose();
            MostrarTratamientos();
        } else {
            JOptionPane.showMessageDialog(null, "Algo falló, consulte con el administrador del sistema", "Error al guardar", JOptionPane.OK_OPTION);
        }


    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        txtDescripcion.setText(txtDescripcion.getText().replaceAll("( )+", " "));
        if (txtNombre.getText().length() == 0 && evt.getKeyChar() == ' ') {
                    evt.consume();
        }      
        
        // Limitar la longitud a 180 caracteres
        if (txtDescripcion.getText().length() >= 180) {
            evt.consume();
        }
        
        char c = evt.getKeyChar();
        if(Character.isDigit(c)){
            getToolkit().beep();
            evt.consume();
        }
        
        // No permitir iniciar con espacios ni caracteres especiales
        if (txtDescripcion.getText().isEmpty() && (c == ' ' || !Character.isLetter(c))) {
            evt.consume();
            return;
        }
        
        // Permitir solo letras, espacios y borrar
        if (!Character.isLetter(c) && c != ' ' && c != '\b') {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtDescripcionKeyTyped

    private void Label_Foto1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Label_Foto1MouseClicked

        JFileChooser se = new JFileChooser();

        // Agregar un filtro para seleccionar solo archivos PNG
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes PNG", "jpg");
        se.setFileFilter(filter);

        se.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int estado = se.showOpenDialog(null);

        if (estado == JFileChooser.APPROVE_OPTION) {
            try {
                File archivoSeleccionado = se.getSelectedFile();

                if (archivoSeleccionado != null) {
                    String ruaImagen = archivoSeleccionado.getAbsolutePath();
                    byte[] imagenBytes = obtenerBytesDeImagen(ruaImagen);

                    if (imagenBytes != null) {
                        ImageIcon icono = new ImageIcon(imagenBytes);
                        Image imagen = icono.getImage();

                        // Redimensionar la imagen para ajustar al tamaño del Label_Foto
                        int ancho = Label_Foto1.getWidth();
                        int alto = Label_Foto1.getHeight();
                        Image imagenRedimensionada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

                        // Crear un nuevo ImageIcon con la imagen redimensionada
                        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);

                        // Mostrar la imagen en el componente Label_Foto
                        Label_Foto1.setIcon(iconoRedimensionado);
                        Label_Foto1.setText(ruaImagen); // Actualiza el texto del Label con la ruta
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo cargar la imagen", "Error de validación", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_Label_Foto1MouseClicked

    private void Label_Foto2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Label_Foto2MouseClicked

        JFileChooser se = new JFileChooser();

        // Agregar un filtro para seleccionar solo archivos PNG
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes PNG", "jpg");
        se.setFileFilter(filter);

        se.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int estado = se.showOpenDialog(null);

        if (estado == JFileChooser.APPROVE_OPTION) {
            try {
                File archivoSeleccionado = se.getSelectedFile();

                if (archivoSeleccionado != null) {
                    String rutImagen = archivoSeleccionado.getAbsolutePath();
                    byte[] imageBytes = obtenerBytesDeImagen(rutImagen);

                    if (imageBytes != null) {
                        ImageIcon icono = new ImageIcon(imageBytes);
                        Image imagen = icono.getImage();

                        // Redimensionar la imagen para ajustar al tamaño del Label_Foto
                        int ancho = Label_Foto2.getWidth();
                        int alto = Label_Foto2.getHeight();
                        Image imagenRedimensionada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

                        // Crear un nuevo ImageIcon con la imagen redimensionada
                        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);

                        // Mostrar la imagen en el componente Label_Foto
                        Label_Foto2.setIcon(iconoRedimensionado);
                        Label_Foto2.setText(rutImagen); // Actualiza el texto del Label con la ruta
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo cargar la imagen", "Error de validación", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_Label_Foto2MouseClicked

    private void cbxTipoTratamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoTratamientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoTratamientoActionPerformed

    private void BtnAgregarListadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarListadoActionPerformed
        ProductoPEtratamiento prod = new ProductoPEtratamiento();
        prod.setVisible(true);
        prod.setLocationRelativeTo(null);
    }//GEN-LAST:event_BtnAgregarListadoActionPerformed

    private void BtnLimpiarListadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarListadoActionPerformed
        try {
            int filaSeleccionada = tblProductosDeTratamiento.getSelectedRow();

            // Si no hay fila seleccionada, preguntar si quiere borrar toda la tabla
            if (filaSeleccionada == -1) {
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea borrar toda la tabla?", "Confirmar", JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    // Borrar todas las filas de la tabla
                    DefaultTableModel modeloDestino = (DefaultTableModel) tblProductosDeTratamiento.getModel();
                    modeloDestino.setRowCount(0);
                    JOptionPane.showMessageDialog(null, "Se borraron todas las filas de la tabla.");
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una fila o confirme para borrar toda la tabla.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                // Si hay una fila seleccionada, borrar esa fila
                DefaultTableModel modeloDestino = (DefaultTableModel) tblProductosDeTratamiento.getModel();
                String idTratamiento2 = (String) tblProductosDeTratamiento.getValueAt(filaSeleccionada, 3);
                    
                // Eliminar productos asociados al tratamiento actual
                Tratamiento.EliminarProductosDeTratamiento(idTratamiento2);
                int respuesta = JOptionPane.showConfirmDialog(null, "Deseas eliminar "
                    + "este producto?", "Advertencia", JOptionPane.YES_NO_OPTION);
                if(respuesta == JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(null, "Se borró el producto seleccionado.");
                    modeloDestino.removeRow(filaSeleccionada);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_BtnLimpiarListadoActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

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
            java.util.logging.Logger.getLogger(EditarTratamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarTratamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarTratamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarTratamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditarTratamiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregarListado;
    private javax.swing.JButton BtnLimpiarListado;
    private javax.swing.JLabel Label_Foto1;
    private javax.swing.JLabel Label_Foto2;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    public static javax.swing.JComboBox<String> cbxTipoTratamiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTable tblProductosDeTratamiento;
    public static javax.swing.JTextArea txtDescripcion;
    public static javax.swing.JLabel txtId;
    public static javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
