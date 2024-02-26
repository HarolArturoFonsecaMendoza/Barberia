/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import Controlador.Citas;


public class Cita {
    private int nombreId;
    private String fecha;
    private String hora;
    private String servicio;

    public Cita(int nombreId, String fecha, String hora) {
        this.nombreId = nombreId;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getNombreId() {
        return nombreId;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }
    
    public String getServicio() {
        return servicio;
    }

    // Método para verificar la disponibilidad de la hora en la base de datos
    public static boolean verificarDisponibilidadBD(int nombreCliente, String servicio, int empleado, String fecha, String hora) {
        return Citas.verificarDisponibilidadBD(nombreCliente, servicio, empleado, fecha, hora);
    }
 
}

