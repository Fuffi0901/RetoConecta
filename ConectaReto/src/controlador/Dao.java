/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import clases.ConvocatoriaExamen;
import clases.Dificultad;
import clases.Enunciado;
import clases.UnidadDidactica;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 2dam
 */
public interface Dao {
    
    public void createUnidadDidactica(int id, String acronimo, String titulo, String evaluacion, String descripcion);
    
    public void createConvocatoriaExamen(int id, String convocatoria, String descripcion, LocalDate fecha, String curso);
    
    public List<Enunciado> enunciadosUnidad(int idUnidadDidactica);
    
    public List<ConvocatoriaExamen> convocatoriaEnunciado(int idEnunciado);
        
    public void createEnunciado(int id, String descripcion, String nivel, Boolean disponible, String ruta, int convo);
    
    public void createTiene (int idEnun, int idUni);
    
    public void enunciadoConvocatoria(int idEnunciado, int idConvocatoria);
    
    public List<Enunciado> obtenerEnunciados();    
    
    public List<UnidadDidactica> obtenerUnidades();    

    public List<ConvocatoriaExamen> obtenerConvos();    

}
