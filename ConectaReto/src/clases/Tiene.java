/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 * La clase {@code Tiene} representa una relación entre dos identificadores, 
 * {@code idEnum} y {@code idUni}, que podrían referirse a una enumeración y 
 * una unidad, respectivamente.
 * 
 * Esta clase contiene métodos para obtener y modificar estos valores, 
 * así como un método para configurar ambos simultáneamente.
 * 
 * @author 2dam
 */
public class Tiene {
    /**
     * Identificador de una enumeración o categoría (posiblemente de otra clase o recurso).
     */
    private int idEnum;

    /**
     * Identificador de una unidad o entidad asociada.
     */
    private int idUni;

    /**
     * Constructor por defecto que no inicializa los atributos.
     */
    public Tiene() {
    }

    /**
     * Constructor que permite inicializar los atributos {@code idEnum} e {@code idUni}.
     *
     * @param idEnun El identificador de la enumeración.
     * @param idUni El identificador de la unidad.
     */
    public Tiene(int idEnun, int idUni) {
        this.idEnum = idEnun;
        this.idUni = idUni;
    }

    /**
     * Devuelve el valor del atributo {@code idEnum}.
     *
     * @return El identificador de la enumeración.
     */
    public int getIdEnun() {
        return idEnum;
    }

    /**
     * Asigna un nuevo valor al atributo {@code idEnum}.
     *
     * @param idEnun El nuevo identificador de la enumeración.
     */
    public void setIdEnun(int idEnun) {
        this.idEnum = idEnun;
    }

    /**
     * Devuelve el valor del atributo {@code idUni}.
     *
     * @return El identificador de la unidad.
     */
    public int getIdUni() {
        return idUni;
    }

    /**
     * Asigna un nuevo valor al atributo {@code idUni}.
     *
     * @param idUni El nuevo identificador de la unidad.
     */
    public void setIdUni(int idUni) {
        this.idUni = idUni;
    }

    /**
     * Método para asignar valores simultáneamente a los atributos {@code idEnum} e {@code idUni}.
     *
     * @param idEnun El nuevo identificador de la enumeración.
     * @param idUni El nuevo identificador de la unidad.
     */
    public void setDatos(int idEnun, int idUni) {
        this.idEnum = idEnun;
        this.idUni = idUni;
    }

    /**
     * Devuelve una representación en cadena del objeto {@code Tiene}, 
     * mostrando los valores de los atributos {@code idEnum} e {@code idUni}.
     *
     * @return Una cadena que representa el objeto con los valores de {@code idEnum} e {@code idUni}.
     */
    @Override
    public String toString() {
        return "idEnum: " + idEnum + ", idUni: " + idUni;
    }
}
