/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.time.LocalDate;
import java.util.Date;

/**
 * La clase {@code ConvocatoriaExamen} representa una convocatoria de examen.
 * Contiene información sobre la convocatoria, descripción, fecha y curso al que pertenece.
 * 
 * @author 2dam
 */
public class ConvocatoriaExamen {
    private int idConvo;
 
    private String convocatoria;
 
    private String descripcion;
   
    private Date fecha;
   
    private String curso;

    public ConvocatoriaExamen() {
    
    }
    
    /**
     * Constructor que inicializa todos los atributos de la clase.
     * 
     * @param id El id de convocatoria.
     * @param convocatoria El nombre o tipo de convocatoria.
     * @param descripcion La descripción de la convocatoria.
     * @param fecha La fecha de la convocatoria de examen.
     * @param curso El curso académico de la convocatoria.
     */
    public ConvocatoriaExamen(int id, String convocatoria, String descripcion, Date fecha, String curso) {
        this.idConvo = id;
        this.convocatoria = convocatoria;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.curso = curso;
    }

    public int getIdConvo() {
        return idConvo;
    }

    public String getConvocatoria() {
        return convocatoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getCurso() {
        return curso;
    }

    public void setIdConvo(int idConvo) {
        this.idConvo = idConvo;
    }

    public void setConvocatoria(String convocatoria) {
        this.convocatoria = convocatoria;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
    
    
    public void setDatos(String convocatoria, String descripcion, Date fecha, String curso) {
        this.convocatoria = convocatoria;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.curso = curso;
    }
    
    /**
     * Devuelve una representación en cadena del objeto {@code ConvocatoriaExamen}, 
     * mostrando los valores de los atributos {@code convocatoria}, {@code descripcion}, 
     * {@code fecha} y {@code curso}.
     * 
     * @return Una cadena que representa el objeto {@code ConvocatoriaExamen}.
     */
    @Override
    public String toString() {
        return "convocatoria: " + convocatoria + ", descripcion: " + descripcion + 
               ", fecha: " + fecha + ", curso: " + curso;
    }
}
