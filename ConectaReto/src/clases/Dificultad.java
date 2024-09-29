/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 * El enum {@code Dificultad} representa los niveles de dificultad que pueden
 * asignarse a un enunciado, examen o cualquier actividad.
 * 
 * Los niveles de dificultad disponibles son:
 * <ul>
 *   <li>{@link #ALTA}: Nivel de dificultad alto.</li>
 *   <li>{@link #MEDIA}: Nivel de dificultad medio.</li>
 *   <li>{@link #BAJA}: Nivel de dificultad bajo.</li>
 * </ul>
 * 
 * Este enum puede ser utilizado para clasificar actividades en función de su
 * complejidad.
 * 
 * @author 2dam
 */
public enum Dificultad {
    
    /**
     * Nivel de dificultad alto.
     */
    ALTA,
    
    /**
     * Nivel de dificultad medio.
     */
    MEDIA,
    
    /**
     * Nivel de dificultad bajo.
     */
    BAJA;
}
