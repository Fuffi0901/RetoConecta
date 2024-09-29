/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 * La clase {@code UnidadDidactica} representa una unidad didáctica dentro de un curso o programa educativo.
 * Contiene información como el identificador, acrónimo, título, forma de evaluación y una descripción.
 * 
 * @author 2dam
 */
public class UnidadDidactica {
    
    /**
     * Identificador único de la unidad didáctica.
     */
    private int id;
    
    /**
     * Acronimo que representa de forma abreviada el título de la unidad didáctica.
     */
    private String acronimo;
    
    /**
     * Título completo de la unidad didáctica.
     */
    private String titulo;
    
    /**
     * Descripción del método o tipo de evaluación para esta unidad didáctica.
     */
    private String evaluacion;
    
    /**
     * Descripción adicional sobre el contenido o temas tratados en la unidad didáctica.
     */
    private String descripcion;
    
    /**
     * Constructor por defecto que no inicializa los atributos.
     */
    public UnidadDidactica() {
        
    }
    
    /**
     * Constructor que inicializa todos los atributos de la clase.
     * 
     * @param id El identificador de la unidad didáctica.
     * @param acronimo El acrónimo que representa la unidad didáctica.
     * @param titulo El título completo de la unidad didáctica.
     * @param evaluacion El tipo o método de evaluación para la unidad didáctica.
     * @param descripcion La descripción del contenido de la unidad didáctica.
     */
    public UnidadDidactica(int id, String acronimo, String titulo, String evaluacion, String descripcion) {
        this.id = id;
        this.acronimo = acronimo;
        this.titulo = titulo;
        this.evaluacion = evaluacion;
        this.descripcion = descripcion;
    }
    
    // Getter y Setter
    
    /**
     * Devuelve el identificador de la unidad didáctica.
     * 
     * @return El identificador de la unidad didáctica.
     */
    public int getId() {
        return id;
    }

    /**
     * Asigna un valor al identificador de la unidad didáctica.
     * 
     * @param id El nuevo identificador de la unidad didáctica.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el acrónimo de la unidad didáctica.
     * 
     * @return El acrónimo de la unidad didáctica.
     */
    public String getAcronimo() {
        return acronimo;
    }

    /**
     * Asigna un valor al acrónimo de la unidad didáctica.
     * 
     * @param acronimo El nuevo acrónimo de la unidad didáctica.
     */
    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    /**
     * Devuelve el título completo de la unidad didáctica.
     * 
     * @return El título completo de la unidad didáctica.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Asigna un valor al título de la unidad didáctica.
     * 
     * @param titulo El nuevo título de la unidad didáctica.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Devuelve el tipo o método de evaluación de la unidad didáctica.
     * 
     * @return El tipo de evaluación de la unidad didáctica.
     */
    public String getEvaluacion() {
        return evaluacion;
    }

    /**
     * Asigna un valor al método de evaluación de la unidad didáctica.
     * 
     * @param evaluacion El nuevo tipo de evaluación.
     */
    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    /**
     * Devuelve la descripción de la unidad didáctica.
     * 
     * @return La descripción de la unidad didáctica.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Asigna un valor a la descripción de la unidad didáctica.
     * 
     * @param descripcion La nueva descripción de la unidad didáctica.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * Método que permite asignar todos los atributos de la unidad didáctica simultáneamente.
     * 
     * @param id El identificador de la unidad didáctica.
     * @param acronimo El acrónimo de la unidad didáctica.
     * @param titulo El título completo de la unidad didáctica.
     * @param evaluacion El tipo o método de evaluación de la unidad didáctica.
     * @param descripcion La descripción del contenido de la unidad didáctica.
     */
    public void setDatos(int id, String acronimo, String titulo, String evaluacion, String descripcion) {
        this.id = id;
        this.acronimo = acronimo;
        this.titulo = titulo;
        this.evaluacion = evaluacion;
        this.descripcion = descripcion;
    }
    
    /**
     * Devuelve una representación en cadena del objeto {@code UnidadDidactica}, 
     * mostrando los valores de los atributos {@code id}, {@code acronimo}, 
     * {@code titulo}, {@code evaluacion} y {@code descripcion}.
     * 
     * @return Una cadena que representa la unidad didáctica.
     */
    @Override
    public String toString() {
        return "id: " + id + ", acronimo: " + acronimo + ", titulo: " + titulo + 
               ", evaluacion: " + evaluacion + ", descripción: " + descripcion;
    }
}
