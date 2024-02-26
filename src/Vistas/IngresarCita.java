/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import Clases.Cliente;
import Clases.Empleados;
import ConsultasSQL.QuerysCita;
import Controlador.Citas;
import Modelos.ModeloClientes;
import Modelos.ModeloEmpleados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrea
 */

public class IngresarCita extends javax.swing.JFrame {

    /**
     * Creates new form IngresarCita
     */
    
    //para poner publico el nombre del cliente
    static  String clienteSeleccionadoNombre;
    static  String empleadoSeleccionadoNombre;
    public IngresarCita() {
        initComponents();
        this.setLocationRelativeTo(null);
        //para llenar el id y el nombre del cliente al boton de seleccion desplegable
        llenarCliente();
        // Obtener Ia fecha actual
        Date currentDate = new Date();
        // para Ia fecha no sea mayo: a Ia actual y menor Ia actual
        // Fecha . setMaxSe1ectab1eDate (new Date ( ) ) ;
        // Calcular Ia fecha máxima coma mes después de Ia fecha actual
        Calendar maxDate = Calendar.getInstance();
        maxDate.setTime(currentDate);
        maxDate.add(Calendar.MONTH,1);
        jdFecha.setMinSelectableDate(currentDate) ;
        // Configurar Ia fecha máxima en EI üDateChooser
        jdFecha.setMaxSelectableDate(maxDate.getTime()) ;
        jdFecha.setDate(new Date());
        cbxServicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                llenarEmpleado();
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnAgendar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cbxTipoCliente = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbxServicio = new javax.swing.JComboBox<>();
        jdFecha = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbxHora = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaNotas = new javax.swing.JTextArea();
        cbxEmpleados = new Clases.ComboBoxSuggestion();
        jLabel11 = new javax.swing.JLabel();
        cbxCliente = new Clases.ComboBoxSuggestion();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(253, 253, 253));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Agendar Cita");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ListaodologoBarberia.png"))); // NOI18N

        btnAgendar.setBackground(new java.awt.Color(253, 253, 253));
        btnAgendar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cita.png"))); // NOI18N
        btnAgendar.setText("Agendar");
        btnAgendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(253, 253, 253));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancelarr.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(253, 253, 253));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Generalidades de la reserva", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 1, 14), new java.awt.Color(0, 102, 204))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(153, 153, 255));
        jPanel3.setToolTipText("");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Servicio: ");

        cbxTipoCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE--", "Niño", "Adolecente", "Joven", "Adulto", "Adulto mayor" }));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Empleado:");

        cbxServicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE--", "Corte", "Tratamiento", "Manicura y pedicura" }));
        cbxServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxServicioActionPerformed(evt);
            }
        });

        jdFecha.setDateFormatString("y-M-d");
        jdFecha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText(" Fecha :");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Hora :");

        cbxHora.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxHora.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM", "12:00 AM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM" }));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Notas:");

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

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Tipo de cliente:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxTipoCliente, 0, 136, Short.MAX_VALUE)
                            .addComponent(cbxServicio, 0, 1, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jdFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cbxHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1))
                            .addComponent(cbxEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbxEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(cbxHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Nombre del titular de la reserva :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Teléfono del titular:");

        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(btnAgendar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(150, 150, 150)
                .addComponent(jLabel2))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgendar))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxServicioActionPerformed
            
    }//GEN-LAST:event_cbxServicioActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed
    private Set<String> horasOcupadas = new HashSet<>();
    
    private void btnAgendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendarActionPerformed
        Cliente clienteSeleccionado = (Cliente) cbxCliente.getSelectedItem();
        Empleados empleadoSeleccionado = (Empleados) cbxEmpleados.getSelectedItem();
        
        String tipoClienteSeleccionado = cbxTipoCliente.getSelectedItem().toString();
        String telefono = txtTelefono.getText();
        String servicioSeleccionado = cbxServicio.getSelectedItem().toString();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = simpleDateFormat.format(jdFecha.getDate());
        int index = cbxHora.getSelectedIndex();
        String horaCompleta = (String) cbxHora.getModel().getElementAt(index);
        String hora = horaCompleta.split(" ")[0];
        String notas = txtaNotas.getText().trim();
        Date fechaDate = jdFecha.getDate();

        if ("--SELECCIONE--".equals(tipoClienteSeleccionado)) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de cliente", "Error de validación", JOptionPane.INFORMATION_MESSAGE);
            return ;
        }else if(telefono.isEmpty()){
            JOptionPane.showMessageDialog(this, "El teléfono en la cita no puede estar vacío", "Error de validación", JOptionPane.INFORMATION_MESSAGE);
            return ;
        }else if (!telefono.matches("[2389]\\d{7}")) {
            JOptionPane.showMessageDialog(null, "El número de teléfono debe empezar con 2, 3, 8 o 9", "Error al guardar", JOptionPane.ERROR_MESSAGE);
            return ;
        }else if(!telefono.matches("[0-9][0-9 -]+")) {
            JOptionPane.showMessageDialog(null, "Alguno de los caracteres que ingresó en el teléfono no es válido", "Error al guardar", 
                    JOptionPane.WARNING_MESSAGE);
            return ;
        }else   if (fechaDate == null) {
            JOptionPane.showMessageDialog(this, "La fecha de la cita no puede estar vacía", "Error de validación", JOptionPane.INFORMATION_MESSAGE);
            return ;
        }else if ("--SELECCIONE--".equals(servicioSeleccionado)) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de servicio", "Error de validación", JOptionPane.INFORMATION_MESSAGE);
            return ;
        }else if(notas.isEmpty()){
            JOptionPane.showMessageDialog(this, "la notas en la cita no puede estar vacía", "Error de validación", JOptionPane.INFORMATION_MESSAGE);
            return ;
        }else if(!notas.matches("[a-zA-Z0-9áéíóúñÁÉÍÓÚÑ][a-zA-Z0-9 áéíóúñÁÉÍÓÚÑü.:;,-]+")){
            JOptionPane.showMessageDialog(null, "Alguno de los caracteres que ingresó en la nota no es válido", "Error al guardar", JOptionPane.WARNING_MESSAGE);
        } else 
            if (clienteSeleccionado != null && empleadoSeleccionado != null) {
            int clienteSeleccionadoId = clienteSeleccionado.getId();

            // Verificar que empleadoSeleccionado no sea null antes de acceder a sus propiedades
            if (empleadoSeleccionado != null) {
                int empleadoSeleccionadoId = empleadoSeleccionado.getId();

                        if (verificarDisponibilidadBD(clienteSeleccionadoId, servicioSeleccionado, empleadoSeleccionadoId, fecha, hora)) {
                        // Actualizar el conjunto de horas ocupadas
                        horasOcupadas.add(hora);

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

                                // Agregar la cita
                                if (Citas.Guardar(querys)) {
                                    JOptionPane.showMessageDialog(null, "Nueva cita ingresada exitosamente");
                                    dispose();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Algo falló, consulte con el administrador del sistema", "Error al guardar", JOptionPane.OK_OPTION);
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "La hora seleccionada ya está ocupada.", "Error de validación", JOptionPane.INFORMATION_MESSAGE);
                            }

                } else {
                    // Manejar el caso en que empleadoSeleccionado sea null
                    JOptionPane.showMessageDialog(this, "Debe seleccionar un empleado", "Error de validación", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                // Manejar el caso en que clienteSeleccionado sea null
                JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente y un empleado", "Error de validación", JOptionPane.INFORMATION_MESSAGE);
            }
    }//GEN-LAST:event_btnAgendarActionPerformed
    // Método para verificar la disponibilidad de la hora en la base de datos
    private boolean verificarDisponibilidadBD(int nombre, String servicio, int empleado, String fecha, String hora) {
        // Llamar al método en la clase Citas que realiza la verificación en la base de datos
        // Asume que existe un método en la clase Citas con la siguiente firma:
        // public boolean verificarDisponibilidadBD(String nombreCliente, String fecha, String hora)
        return Citas.verificarDisponibilidadBD(nombre ,servicio, empleado, fecha, hora);
    }
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        String telefono = txtTelefono.getText();
        char c = evt.getKeyChar();
         //si los caracteres en el telefono son letras
        if(Character.isLetter(c)){
            evt.consume();
         }
        
        if (telefono.length()>=8){
        evt.consume();
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtaNotasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaNotasKeyTyped
         String notas = txtaNotas.getText();
        if(notas.length()>=250){
       //JOptionPane.showMessageDialog(this, "La direccion no puede contener mas de 60 letras");
       evt.consume();
       }
    }//GEN-LAST:event_txtaNotasKeyTyped

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
            java.util.logging.Logger.getLogger(IngresarCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IngresarCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IngresarCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IngresarCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IngresarCita().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgendar;
    private javax.swing.JButton btnCancelar;
    private Clases.ComboBoxSuggestion cbxCliente;
    private Clases.ComboBoxSuggestion cbxEmpleados;
    private javax.swing.JComboBox cbxHora;
    private javax.swing.JComboBox<String> cbxServicio;
    private javax.swing.JComboBox<String> cbxTipoCliente;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdFecha;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextArea txtaNotas;
    // End of variables declaration//GEN-END:variables
}
