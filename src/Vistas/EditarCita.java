/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import Clases.Cliente;
import Clases.Empleados;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.Calendar;
import Controlador.Citas;

import ConsultasSQL.QuerysCita;
import Modelos.ModeloClientes;
import Modelos.ModeloEmpleados;
import static Vistas.MostrarCitas.Listadoempleados;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
/**
 *
 * @author Bucardo
 */
public class EditarCita extends javax.swing.JFrame {
    private int paginaActual = 1;
    public static int totalPages;
    static  String clienteSeleccionadoNombre;
    static  String empleadoSeleccionadoNombre;
     
    public EditarCita() {
        
        initComponents();
        
       cbxCliente.setEditable(false);
       cbxServicio.setEditable(false);
       cbxTipoCliente.setEditable(false);
       
        
        txtid.setVisible(false);
       this.setLocationRelativeTo(null);
        setResizable(false);
     
        // Obtener la fecha actual
        Date currentDate = new Date();
        
        // Calcular la fecha máxima como 1 mes después de la fecha actual
        Calendar maxDate = Calendar.getInstance();
        maxDate.setTime(currentDate);
        maxDate.add(Calendar.MONTH, 1);
        Fecha.setMinSelectableDate(currentDate);
        // Configurar la fecha máxima en el JDateChooser
        Fecha.setMaxSelectableDate(maxDate.getTime());
        llenarCliente();
        agregarEventoEmpleado();
        agregarEventoCliente();
        cbxServicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                llenarEmpleado();
            }
        });
    }
    
    // Método para agregar el evento al JTextField
    private void agregarEventoCliente() {
            txtNombre.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarComboBox();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarComboBox();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarComboBox();
            }

            private void actualizarComboBox() {
                String textoIngresado = txtNombre.getText().trim();
                if (!textoIngresado.isEmpty()) {
                    for (int i = 0; i < cbxCliente.getItemCount(); i++) {
                        Cliente cliente = (Cliente) cbxCliente.getItemAt(i);
                        if (cliente.getNombre().equalsIgnoreCase(textoIngresado)) {
                            cbxCliente.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            }
        });
    }
   

    private void agregarEventoEmpleado() {
            txtEmpleado.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarComboBox();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarComboBox();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarComboBox();
            }

            private void actualizarComboBox() {
                String textoIngresado = txtEmpleado.getText().trim();
                if (!textoIngresado.isEmpty()) {
                    for (int i = 0; i < cbxEmpleados.getItemCount(); i++) {
                        Empleados empleados = (Empleados) cbxEmpleados.getItemAt(i);
                        if (empleados.getNombre().equalsIgnoreCase(textoIngresado)) {
                            cbxEmpleados.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            }
        });
    }

    
          //metodo para poder traer el id y el nombre del proveedor
    public void llenarCliente(){
        ModeloClientes modCli = new ModeloClientes();
        ArrayList<Cliente> listaCliente = modCli.getCliente();
        int clienteSeleccionadoId = 0;
        clienteSeleccionadoNombre = "---";
        
        cbxCliente.removeAllItems();
        
        for(int i = 0; i < listaCliente.size(); i++ ){
            cbxCliente.addItem(new Cliente(listaCliente.get(i).getId(),
                    listaCliente.get(i).getNombre(),listaCliente.get(i).getApellido()));
            
            int indiceSeleccionado = cbxCliente.getSelectedIndex();
            
            if (indiceSeleccionado == i) {
                // Guardar el ID del proveedor seleccionado
                clienteSeleccionadoId = listaCliente.get(i).getId();
                clienteSeleccionadoNombre = listaCliente.get(i).getNombre();
            }
        }    
    }
    public void llenarEmpleado() {
        ModeloEmpleados modEmp = new ModeloEmpleados();

        // Obtener el servicio seleccionado en el JComboBox
        String servicioSeleccionado = cbxServicio.getSelectedItem().toString();
        
        // Llenar el ComboBox de empleados con los empleados activos del servicio seleccionado
        ArrayList<Empleados> listaEmpleados = modEmp.getEmpleadosActivosPorServicio(servicioSeleccionado);
        //System.out.println("Servicios seleccionados:" + servicioSeleccionado);
        int empleadoSeleccionadoId = 0;
        empleadoSeleccionadoNombre = "---";

        cbxEmpleados.removeAllItems();

        for (int i = 0; i < listaEmpleados.size(); i++) {
            cbxEmpleados.addItem(new Empleados(listaEmpleados.get(i).getId(),listaEmpleados.get(i).getNombre(), listaEmpleados.get(i).getApellido(),listaEmpleados.get(i).getCargo()));

            int indiceSeleccionado = cbxEmpleados.getSelectedIndex();

            if (indiceSeleccionado == i) {
                // Guardar el ID y el Nombre del empleado seleccionado
                empleadoSeleccionadoId = listaEmpleados.get(i).getId();
                empleadoSeleccionadoNombre = listaEmpleados.get(i).getNombre();
            }
        }
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
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbxTipoCliente = new javax.swing.JComboBox<>();
        cbxServicio = new javax.swing.JComboBox<>();
        Fecha = new com.toedter.calendar.JDateChooser();
        cbxEmpleados = new Clases.ComboBoxSuggestion();
        cbxHora = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaNotas = new javax.swing.JTextArea();
        btnActualizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        cbxCliente = new Clases.ComboBoxSuggestion();
        txtTelefono = new javax.swing.JTextField();
        txtid = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtEmpleado = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Editar Cita");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ListaodologoBarberia.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Nombre del titular de la reserva :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Teléfono del titular:");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Generalidades de la reserva"));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Tipo de cliente:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Servicio: ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Empleado:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText(" Fecha :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Notas:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Hora :");

        cbxTipoCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE--", "Niño", "Adolecente", "Joven", "Adulto", "Adulto mayor" }));
        cbxTipoCliente.setEnabled(false);

        cbxServicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE--", "Corte", "Tratamiento", "Manicura y pedicura" }));
        cbxServicio.setEnabled(false);
        cbxServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxServicioActionPerformed(evt);
            }
        });

        Fecha.setDateFormatString("yyyy-MM-dd");
        Fecha.setMaxSelectableDate(new java.util.Date(253370790113000L));
        Fecha.setMinSelectableDate(new java.util.Date(-1483203487000L));

        cbxEmpleados.setEditable(false);

        cbxHora.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxHora.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM" }));

        txtaNotas.setColumns(20);
        txtaNotas.setLineWrap(true);
        txtaNotas.setRows(5);
        txtaNotas.setWrapStyleWord(true);
        txtaNotas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtaNotasKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtaNotas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbxHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1)))))
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cbxTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbxEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(cbxHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        cbxCliente.setEditable(false);
        cbxCliente.setEnabled(false);

        txtTelefono.setEditable(false);

        txtid.setEditable(false);
        txtid.setText("id");
        txtid.setEnabled(false);

        txtNombre.setEditable(false);
        txtNombre.setEnabled(false);
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtEmpleado.setEditable(false);
        txtEmpleado.setEnabled(false);
        txtEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(188, 188, 188))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(144, 144, 144)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(btnActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addGap(139, 139, 139))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(btnCancelar))
                .addGap(15, 15, 15))
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxServicioActionPerformed

    }//GEN-LAST:event_cbxServicioActionPerformed

    private void txtaNotasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaNotasKeyTyped
        // TODO add your handling code here:
        txtaNotas.setText(txtaNotas.getText().replaceAll("( )+", " "));

        // Eliminamos la verificación de longitud cero
        // if (nota.getText().length() == 0 && evt.getKeyChar() == ' ') {
        if (evt.getKeyChar() == ' ') {
            evt.consume();
        }

        int tam = txtaNotas.getText().length();
        if (tam >= 250) {
            evt.consume();
        }

        char c = evt.getKeyChar();
        // Verificamos si el carácter no es una letra
        if (!Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();
        }

    }//GEN-LAST:event_txtaNotasKeyTyped

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        int id = Integer.parseInt(txtid.getText());
        Cliente clienteSeleccionado = (Cliente) cbxCliente.getSelectedItem();
        Empleados empleadoSeleccionado = (Empleados) cbxEmpleados.getSelectedItem();
        
        String tipoClienteSeleccionado = cbxTipoCliente.getSelectedItem().toString();
        String telefono = txtTelefono.getText();
        String servicioSeleccionado = cbxServicio.getSelectedItem().toString();
        
        int index = cbxHora.getSelectedIndex();
        String horaCompleta = (String) cbxHora.getModel().getElementAt(index);
        String hora = horaCompleta.split(" ")[0];
        String notas = txtaNotas.getText().trim();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = simpleDateFormat.format(Fecha.getDate());
         String fechas =((JTextField)Fecha.getDateEditor().getUiComponent()).getText();
         
        if(fechas.isEmpty()){
            JOptionPane.showMessageDialog(null, "La fecha esta vacia","Error al guardar",
                    JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si el campo está vacío
        }
           
        if(hora.equals("seleccione una hora")){
         JOptionPane.showMessageDialog(this, "No ha seleccionado ninguna hora", "Error de validación", JOptionPane.INFORMATION_MESSAGE);
            return; // Salir del método si el campo está vacío
        }
       
        
          String descripcion =  txtaNotas.getText().trim(); // Eliminar espacios en blanco al inicio y al final
        if (descripcion.isEmpty()) {
              JOptionPane.showMessageDialog(this, "La nota de la cita  no puede estar vacía", "Error de validación", JOptionPane.INFORMATION_MESSAGE);
           
            return; // Salir del método si el campo está vacío
        }
        int empleadoSeleccionadoId = empleadoSeleccionado.getId();
        int clienteSeleccionadoId = clienteSeleccionado.getId();
        // Lógica para agregar la cita
            QuerysCita querys = new QuerysCita();
            

            querys.setFk_nombre(clienteSeleccionadoId);
            querys.setTelefono(telefono);
            querys.setTipo_cliente(tipoClienteSeleccionado);
            querys.setServicios(servicioSeleccionado);
            querys.setFk_Empleado(empleadoSeleccionadoId);
            querys.setFecha(fecha);
            querys.setHora(hora);
            querys.setNotas(notas);

            querys.setId(Integer.parseInt(txtid.getText()));
            
            if (Citas.Editar(querys)){
             JOptionPane.showMessageDialog(null, "Se ha  modificado la cita exitosamente","Editado con éxito",
                     JOptionPane.INFORMATION_MESSAGE);
                 
                
          
          Controlador.Citas.MostrarCitas("",paginaActual, totalPages,Listadoempleados.getModel().getSelectedItem().toString());
          dispose();
         }else {
                JOptionPane.showMessageDialog(null, "Algo falló, consulte con el administrador de sistema", "Error al editar", 
                    JOptionPane.OK_OPTION);
         }
        
        
       
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpleadoActionPerformed

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
            java.util.logging.Logger.getLogger(EditarCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditarCita().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static com.toedter.calendar.JDateChooser Fecha;
    public static javax.swing.JButton btnActualizar;
    public static javax.swing.JButton btnCancelar;
    public static Clases.ComboBoxSuggestion cbxCliente;
    public static Clases.ComboBoxSuggestion cbxEmpleados;
    public static javax.swing.JComboBox cbxHora;
    public static javax.swing.JComboBox<String> cbxServicio;
    public static javax.swing.JComboBox<String> cbxTipoCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextField txtEmpleado;
    public static javax.swing.JTextField txtNombre;
    public static javax.swing.JTextField txtTelefono;
    public static javax.swing.JTextArea txtaNotas;
    public static javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}
