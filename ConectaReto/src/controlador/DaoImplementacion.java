/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import clases.ConvocatoriaExamen;
import clases.Enunciado;
import clases.UnidadDidactica;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoImplementacion implements Dao {

    private Connection con;
    private PreparedStatement stmt;
    private ResourceBundle fichConf;
    private ResultSet rs;
    private String url, usuario, pass;

    public void DaoImplementacion() {
        fichConf = ResourceBundle.getBundle("modelo.config");
        url = fichConf.getString("url");
        usuario = fichConf.getString("usuario");
        pass = fichConf.getString("pass");
    }

    private final String CREATEUNIDAD = "INSERT INTO UnidadDidactica VALUES(?, ?, ?, ?, ?);";
    private final String CREATECONVOCATORIA = "INSERT INTO ConvocatoriaExamen VALUES(?, ?, ?, ?, ?);";
    private final String INSERT_ENUNCIADO = "INSERT INTO Enunciado VALUES (?, ?, ?, ?, ?, ?)";
    private final String CREATETIENE = "INSERT INTO Tiene VALUES (?, ?)";
    private final String ENUNCIADOS = "SELECT * FROM enunciado WHERE idEnunciado IN (SELECT idEnun FROM tiene WHERE idUni = ?)";
    private final String CONVOCATORIAS_ENUNCIADO = "SELECT * FROM ConvocatoriaExamen WHERE idConvo IN (SELECT idConvo FROM Enunciado WHERE idEnunciado = ?)";
    private final String ENUNCIADO_A_CONVOCATORIA = "UPDATE Enunciado SET idConvo = ? WHERE idEnunciado = ?";
    private final String TODOS_ENUNCIADOS = "SELECT * FROM ENUNCIADO";
    private final String TODOS_UNIDADES = "SELECT * FROM UNIDADDIDACTICA";
    private final String TODOS_CONVO = "SELECT * FROM CONVOCATORIAEXAMEN";

    //abrir la conexion con la base de datos
    private void openConnection() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examendb?serverTimezone=Europe/Madrid&useSSL=false", "root",
                    "abcd*1234");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Este metodo cierra la conexion con la base de datos
     *
     * @throws SQLException
     */
    private void closeConnection() throws SQLException {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void createConvocatoriaExamen(int id, String convocatoria, String descripcion, LocalDate fecha, String curso) {
        ConvocatoriaExamen conv = new ConvocatoriaExamen();
        this.openConnection();
        try (PreparedStatement stmt = con.prepareStatement(CREATECONVOCATORIA)) {
            stmt.setInt(1, id);
            stmt.setString(2, convocatoria);
            stmt.setString(3, descripcion);
            stmt.setDate(4, java.sql.Date.valueOf(fecha));
            stmt.setString(5, curso);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cierra la conexión en el bloque finally
            try {
                if (con != null && !con.isClosed()) {
                    con.close();  // Asegura el cierre de la conexión
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoImplementacion.class.getName()).log(Level.SEVERE, "Error al cerrar la conexión", ex);
            }
        }
    }

    @Override
    public void createEnunciado(int id, String descripcion, String nivel, Boolean disponible, String ruta, int convo) {
        Enunciado enun = new Enunciado();
        this.openConnection();
        try (PreparedStatement stmt = con.prepareStatement(INSERT_ENUNCIADO)) {
            // Inserta los valores en la base de datos
            stmt.setInt(1, id);
            stmt.setString(2, descripcion);
            stmt.setString(3, nivel);
            stmt.setBoolean(4, disponible);
            stmt.setString(5, ruta);
            stmt.setInt(6, convo);

            // Ejecuta la actualización
            stmt.executeUpdate();

        } catch (SQLException e) {
            // Manejo de excepciones
            Logger.getLogger(DaoImplementacion.class.getName()).log(Level.SEVERE, "Error al crear el enunciado");
        } finally {
            // Cierra la conexión
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(DaoImplementacion.class.getName()).log(Level.SEVERE, "Error al cerrar la conexión");

            }
        }
    }

    @Override
    public List<ConvocatoriaExamen> convocatoriaEnunciado(int idEnunciado) {
        List<ConvocatoriaExamen> convocatorias = new ArrayList<>();

        this.openConnection();
        try (PreparedStatement stmt = con.prepareStatement(CONVOCATORIAS_ENUNCIADO)) {
            stmt.setInt(1, idEnunciado);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idConvo");
                String nombre = rs.getString("convocatoria");
                String descripcion = rs.getString("descripcion");
                Date fecha = rs.getDate("fecha");
                String curso = rs.getString("curso");

                ConvocatoriaExamen conv = new ConvocatoriaExamen(id, nombre, descripcion, fecha, curso);
                convocatorias.add(conv);
            }
        } catch (SQLException e) {
            Logger.getLogger(DaoImplementacion.class.getName()).log(Level.SEVERE, "Error al consultar convocatorias", e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(DaoImplementacion.class.getName()).log(Level.SEVERE, "Error al cerrar la conexión", e);
            }
        }
        return convocatorias;
    }

    // Implementación del método para crear una unidad didáctica
    @Override
    public void createUnidadDidactica(int id, String acronimo, String titulo, String evaluacion, String descripcion) {
        UnidadDidactica did = new UnidadDidactica();  // Inicialización de la unidad didáctica si es necesaria

        this.openConnection();  // Abre la conexión a la base de datos

        // Usa try-with-resources para asegurar que stmt se cierre correctamente
        try (PreparedStatement stmt = con.prepareStatement(CREATEUNIDAD)) {
            // Prepara la sentencia SQL con los parámetros
            stmt.setInt(1, id);  // Asigna el ID
            stmt.setString(2, acronimo);  // Asigna el acrónimo
            stmt.setString(3, titulo);  // Asigna el título
            stmt.setString(4, evaluacion);  // Asigna la evaluación
            stmt.setString(5, descripcion);  // Asigna la descripción

            // Ejecuta la sentencia SQL
            stmt.executeUpdate();

        } catch (SQLException ex) {
            // Registro de la excepción en caso de error
            Logger.getLogger(DaoImplementacion.class.getName()).log(Level.SEVERE, "Error al crear la Unidad Didáctica", ex);
        } finally {
            // Cierra la conexión en el bloque finally
            try {
                if (con != null && !con.isClosed()) {
                    con.close();  // Asegura el cierre de la conexión
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoImplementacion.class.getName()).log(Level.SEVERE, "Error al cerrar la conexión", ex);
            }
        }
    }

    @Override
    public void createTiene(int idEnun, int idUni) {
        this.openConnection();

        // Usa try-with-resources para asegurar que stmt se cierre correctamente
        try (PreparedStatement stmt = con.prepareStatement(CREATETIENE)) {
            // Prepara la sentencia SQL con los parámetros
            stmt.setInt(1, idEnun);  // Asigna el Enunciado
            stmt.setInt(2, idUni);  // Asigna la Unidad

            // Ejecuta la sentencia SQL
            stmt.executeUpdate();

        } catch (SQLException ex) {
            // Registro de la excepción en caso de error
            Logger.getLogger(DaoImplementacion.class.getName()).log(Level.SEVERE, "Error al crear la Unidad Didáctica", ex);
        } finally {
            // Cierra la conexión en el bloque finally
            try {
                if (con != null && !con.isClosed()) {
                    con.close();  // Asegura el cierre de la conexión
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoImplementacion.class.getName()).log(Level.SEVERE, "Error al cerrar la conexión", ex);
            }
        }
    }

    @Override
    public List<Enunciado> enunciadosUnidad(int idUnidadDidactica) {
        this.openConnection(); // Abre la conexión
        List<Enunciado> enunciados = new ArrayList<>(); // Lista para almacenar los enunciados

        try (PreparedStatement stmt = con.prepareStatement(ENUNCIADOS)) {
            stmt.setInt(1, idUnidadDidactica); // Seteamos el valor de idUnidadDidactica en la consulta

            try (ResultSet rs = stmt.executeQuery()) {
                // Itera a través del resultado de la consulta
                while (rs.next()) {
                    // Creamos un nuevo objeto Enunciado y lo rellenamos con los datos del ResultSet
                    Enunciado enun = new Enunciado();
                    enun.setId(rs.getInt("idEnunciado"));
                    enun.setDescripcion(rs.getString("descripcion"));
                    enun.setNivelFromString(rs.getString("nivel")); // Conversión del string a enum usando el método personalizado
                    enun.setDisponible(rs.getBoolean("disponible"));
                    enun.setRuta(rs.getString("ruta"));
                    enun.setConvocatoriaId(rs.getInt("idConvo"));

                    // Añadimos el enunciado a la lista
                    enunciados.add(enun);
                }
            }
        } catch (SQLException e) {
            // Manejo de errores
            Logger.getLogger(DaoImplementacion.class.getName()).log(Level.SEVERE, "Error al consultar enunciados", e);
        } finally {
            // Cierra la conexión en el bloque finally
            try {
                if (con != null && !con.isClosed()) {
                    con.close(); // Asegura el cierre de la conexión
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoImplementacion.class.getName()).log(Level.SEVERE, "Error al cerrar la conexión", ex);
            }
        }

        return enunciados; // Devolvemos la lista de enunciados filtrados por la unidad didáctica
    }

    @Override
    public void enunciadoConvocatoria(int idEnunciado, int idConvocatoria) {
        this.openConnection();

        try (PreparedStatement stmt = con.prepareStatement(ENUNCIADO_A_CONVOCATORIA)) {
            // Setear los valores de la convocatoria y el enunciado
            stmt.setInt(1, idConvocatoria);
            stmt.setInt(2, idEnunciado);

            // Ejecutar la actualización
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DaoImplementacion.class.getName()).log(Level.SEVERE, "Error al asignar la convocatoria al enunciado", e);
        } finally {
            // Cerrar la conexión
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(DaoImplementacion.class.getName()).log(Level.SEVERE, "Error al cerrar la conexión", e);
            }
        }
    }
    
    @Override
    public List<Enunciado> obtenerEnunciados(){
        this.openConnection(); // Abre la conexión
        List<Enunciado> enunciados = new ArrayList<>(); // Lista para almacenar los enunciados

        try (PreparedStatement stmt = con.prepareStatement(TODOS_ENUNCIADOS)) {
            try (ResultSet rs = stmt.executeQuery()) {
                // Itera a través del resultado de la consulta
                while (rs.next()) {
                    // Creamos un nuevo objeto Enunciado y lo rellenamos con los datos del ResultSet
                    Enunciado enun = new Enunciado();
                    enun.setId(rs.getInt("idEnunciado"));
                    enun.setDescripcion(rs.getString("descripcion"));
                    enun.setNivelFromString(rs.getString("nivel")); // Conversión del string a enum usando el método personalizado
                    enun.setDisponible(rs.getBoolean("disponible"));
                    enun.setRuta(rs.getString("ruta"));
                    enun.setConvocatoriaId(rs.getInt("idConvo"));

                    // Añadimos el enunciado a la lista
                    enunciados.add(enun);
                }
            }
        } catch (SQLException e) {
            // Manejo de errores
            Logger.getLogger(DaoImplementacion.class.getName()).log(Level.SEVERE, "Error al consultar enunciados", e);
        } finally {
            // Cierra la conexión en el bloque finally
            try {
                if (con != null && !con.isClosed()) {
                    con.close(); // Asegura el cierre de la conexión
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoImplementacion.class.getName()).log(Level.SEVERE, "Error al cerrar la conexión", ex);
            }
        }

        return enunciados; // Devolvemos la lista de enunciados filtrados por la unidad didáctica
    }
    
    @Override
    public List<UnidadDidactica> obtenerUnidades(){
        this.openConnection(); // Abre la conexión
        List<UnidadDidactica> unidades = new ArrayList<>(); // Lista para almacenar los enunciados

        try (PreparedStatement stmt = con.prepareStatement(TODOS_UNIDADES)) {
            try (ResultSet rs = stmt.executeQuery()) {
                // Itera a través del resultado de la consulta
                while (rs.next()) {
                    // Creamos un nuevo objeto Enunciado y lo rellenamos con los datos del ResultSet
                    UnidadDidactica uni = new UnidadDidactica();
                    uni.setId(rs.getInt("idUnidad"));
                    uni.setAcronimo(rs.getString("acronimo"));
                    uni.setTitulo(rs.getString("titulo")); // Conversión del string a enum usando el método personalizado
                    uni.setEvaluacion(rs.getString("evaluacion"));
                    uni.setDescripcion(rs.getString("descripcion"));

                    // Añadimos el enunciado a la lista
                    unidades.add(uni);
                }
            }
        } catch (SQLException e) {
            // Manejo de errores
            Logger.getLogger(DaoImplementacion.class.getName()).log(Level.SEVERE, "Error al consultar enunciados", e);
        } finally {
            // Cierra la conexión en el bloque finally
            try {
                if (con != null && !con.isClosed()) {
                    con.close(); // Asegura el cierre de la conexión
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoImplementacion.class.getName()).log(Level.SEVERE, "Error al cerrar la conexión", ex);
            }
        }

        return unidades; // Devolvemos la lista de enunciados filtrados por la unidad didáctica
    }
    
    
    @Override
    public List<ConvocatoriaExamen> obtenerConvos(){
        this.openConnection(); // Abre la conexión
        List<ConvocatoriaExamen> convocatorias = new ArrayList<>(); // Lista para almacenar los enunciados

        try (PreparedStatement stmt = con.prepareStatement(TODOS_CONVO)) {
            try (ResultSet rs = stmt.executeQuery()) {
                // Itera a través del resultado de la consulta
                while (rs.next()) {
                    // Creamos un nuevo objeto Enunciado y lo rellenamos con los datos del ResultSet
                    ConvocatoriaExamen convo = new ConvocatoriaExamen();
                    convo.setIdConvo(rs.getInt("idUnidad"));
                    convo.setConvocatoria(rs.getString("Convocatoria"));
                    convo.setDescripcion(rs.getString("titulo")); // Conversión del string a enum usando el método personalizado
                    convo.setFecha(rs.getDate("fecha"));
                    convo.setCurso(rs.getString("curso"));

                    // Añadimos el enunciado a la lista
                    convocatorias.add(convo);
                }
            }
        } catch (SQLException e) {
            // Manejo de errores
            Logger.getLogger(DaoImplementacion.class.getName()).log(Level.SEVERE, "Error al consultar enunciados", e);
        } finally {
            // Cierra la conexión en el bloque finally
            try {
                if (con != null && !con.isClosed()) {
                    con.close(); // Asegura el cierre de la conexión
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoImplementacion.class.getName()).log(Level.SEVERE, "Error al cerrar la conexión", ex);
            }
        }

        return convocatorias; // Devolvemos la lista de enunciados filtrados por la unidad didáctica
    }
}
