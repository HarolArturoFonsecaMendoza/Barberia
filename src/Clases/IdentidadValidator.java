/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.time.LocalDate;

/**
 *
 * @author carlo
 */
public class IdentidadValidator {
    public int cantidadDepartamentos = 18; // Obtener este valor de una fuente confiable.
    public int[] cantidadMunicipiosPorDepartamento = {8, 16, 10, 21, 23, 12, 19, 30, 5, 16, 4, 19, 28, 16, 23, 28, 14, 18};
    // Obtener estos valores de una fuente confiable o calcularlos programáticamente.

    public boolean validarIdentidad(String identidad) {
        // Verificar el formato y la longitud de la identidad
        // (Implementa aquí la lógica para asegurarte de que cumpla con los requisitos).

        // Obtener los números de departamento y municipio desde la identidad
        int numeroDepartamento = Integer.parseInt(identidad.substring(0, 2));
        int numeroMunicipio = Integer.parseInt(identidad.substring(2, 4));
        // Obtener los números del año desde la identidad
        int numeroAnio = Integer.parseInt(identidad.substring(5, 9));

        // Verificar que los números de departamento y municipio estén dentro del rango válido
        // (Implementa aquí la lógica existente).

        if (numeroAnio == 0) {
            return false; // El año es inválido.
        }

        // Obtener el año actual
        int anioActual = LocalDate.now().getYear();

        if (numeroAnio > anioActual) {
            return false; // El año no puede ser mayor al año actual.
        }
        
        // Verificar que los números de departamento y municipio estén dentro del rango válido
        if (numeroDepartamento < 1 || numeroDepartamento > cantidadDepartamentos) {
            return false; // El número de departamento es inválido.
        }

        int indiceDepartamento = numeroDepartamento - 1;
        int cantidadMunicipiosEnDepartamento = cantidadMunicipiosPorDepartamento[indiceDepartamento];

        if (numeroMunicipio < 1 || numeroMunicipio > cantidadMunicipiosEnDepartamento) {
            return false; // El número de municipio es inválido para este departamento.
        }

        // La identidad es válida
        return true;
    }
    
    

}


