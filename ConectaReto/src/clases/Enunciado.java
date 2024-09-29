/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 * La clase {@code Enunciado} representa un enunciado de algún tipo de problema o ejercicio.
 * Incluye un identificador, una descripción, el nivel de dificultad, si está disponible 
 * y la ruta a un recurso asociado.
 * 
 * @author 2dam
 */
public class Enunciado {
    
    /**
     * Identificador único del enunciado.
     */
    private Integer id;
    
    /**
     * Descripción del enunciado.
     */
    private String descripcion;
    
    /**
     * Nivel de dificultad del enunciado.
     * Puede tomar valores de la clase {@code Dificultad}.
     */
    private Dificultad nivel;
    
    /**
     * Indica si el enunciado está disponible.
     */
    private boolean disponible;
    
    /**
     * Ruta a un archivo o recurso asociado al enunciado.
     */
    private String ruta;
    
    private int convocatoriaId;

    /**
     * Constructor por defecto que no inicializa los atributos.
     */
    public Enunciado() {
    }

    /**
     * Constructor que permite inicializar todos los atributos del enunciado.
     * 
     * @param id El identificador único del enunciado.
     * @param descripcion La descripción del enunciado.
     * @param nivel El nivel de dificultad del enunciado.
     * @param disponible Indica si el enunciado está disponible.
     * @param ruta La ruta al recurso asociado al enunciado.
     */
    public Enunciado(Integer id, String descripcion, Dificultad nivel, boolean disponible, String ruta) {
        this.id = id;
        this.descripcion = descripcion;
        this.nivel = nivel;
        this.disponible = disponible;
        this.ruta = ruta;
    }
    
    // Getters y Setters
    
    /**
     * Devuelve el identificador único del enunciado.
     * 
     * @return El identificador del enunciado.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Asigna un valor al identificador del enunciado.
     * 
     * @param id El nuevo identificador del enunciado.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Devuelve la descripción del enunciado.
     * 
     * @return La descripción del enunciado.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Asigna una nueva descripción al enunciado.
     * 
     * @param descripcion La nueva descripción del enunciado.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve el nivel de dificultad del enunciado.
     * 
     * @return El nivel de dificultad del enunciado.
     */
    public Dificultad getNivel() {
        return nivel;
    }

    /**
     * Asigna un nuevo nivel de dificultad al enunciado.
     * 
     * @param nivel El nuevo nivel de dificultad.
     */
    public void setNivel(Dificultad nivel) {
        this.nivel = nivel;
    }

    /**
     * Indica si el enunciado está disponible.
     * 
     * @return {@code true} si el enunciado está disponible, de lo contrario {@code false}.
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Cambia el estado de disponibilidad del enunciado.
     * 
     * @param disponible El nuevo estado de disponibilidad.
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Devuelve la ruta al recurso asociado al enunciado.
     * 
     * @return La ruta al recurso del enunciado.
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * Asigna una nueva ruta al enunciado.
     * 
     * @param ruta La nueva ruta del enunciado.
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getConvocatoriaId() {
        return convocatoriaId;
    }

    public void setConvocatoriaId(int convocatoriaId) {
        this.convocatoriaId = convocatoriaId;
    }

    /**
     * Devuelve una representación en forma de cadena del objeto {@code Enunciado}, 
     * incluyendo su identificador, descripción, nivel de dificultad, disponibilidad y ruta.
     * 
     * @return Una cadena que representa el objeto {@code Enunciado}.
     */
    @Override
    public String toString() {
        return "id: " + id + ", descripcion: " + descripcion + ", nivel: " + nivel + 
               ", disponible: " + disponible + ", ruta: " + ruta;
    }
    
      public void setNivelFromString(String nivelStr) {
        try {
            this.nivel = nivel.valueOf(nivelStr.toUpperCase()); // Conversión a enum
        } catch (IllegalArgumentException e) {
            System.out.println("Error: El nivel '" + nivelStr + "' no es válido.");
        }
    }
}
