package main;

import clases.ConvocatoriaExamen;
import clases.Enunciado;
import clases.UnidadDidactica;
import controlador.Dao;
import controlador.DaoImplementacion;
import java.time.LocalDate;
import java.util.List;
import utilidades.Utilidades;

public class Main {

    // Instancia del DAO para interactuar con la base de datos
    static Dao dao = new DaoImplementacion();

    public static void main(String[] args) {
        int opcion = 0;

        // Bucle para mostrar el menú principal hasta que el usuario decida salir
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Crear unidad didáctica");
            System.out.println("2. Crear convocatoria");
            System.out.println("3. Crear enunciado de examen");
            System.out.println("4. Consultar enunciados por unidad didáctica");
            System.out.println("5. Consultar convocatorias por enunciado");
            System.out.println("6. Asignar enunciado a convocatoria");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                // Lee la opción seleccionada por el usuario
                opcion = Utilidades.leerInt("Seleccione una opción: ");

                // Ejecuta la opción seleccionada
                switch (opcion) {
                    case 1:
                        crearUnidadDidactica(); // Crear unidad didáctica
                        break;
                    case 2:
                        crearConvocatoria(); // Crear convocatoria
                        break;
                    case 3:
                        crearEnunciadoExamen(); // Crear enunciado de examen
                        break;
                    case 4:
                        consultarEnunciadosPorUnidad(); // Consultar enunciados por unidad didáctica
                        break;
                    case 5:
                        consultarConvocatoriasPorEnunciado(); // Consultar convocatorias por enunciado
                        break;
                    case 6:
                        asignarEnunciadoAConvocatoria(); // Asignar enunciado a convocatoria
                        break;
                    case 0:
                        System.out.println("Adiós"); // Salir del programa
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione otra opción."); // Opción no válida
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe introducir un número válido."); // Manejo de entrada no válida
                opcion = -1; // Para que el bucle continúe
            } catch (Exception e) {
                System.out.println("Se produjo un error inesperado: " + e.getMessage()); // Manejo de excepciones generales
            }
        } while (opcion != 0); // Continúa hasta que el usuario elige salir
    }

    // Método para crear una unidad didáctica
    public static void crearUnidadDidactica() {
        try {
            int id;
            String acronimo;
            String titulo;
            String evaluacion;
            String descripcion;

            System.out.println("Introduce los datos de una unidad didáctica");

            // Solicita un ID único para la unidad didáctica
            do {
                id = Utilidades.leerInt("ID: ");
            } while (existeIdUnidades(id)); // Verifica si el ID ya existe

            // Lee los demás datos de la unidad didáctica
            acronimo = Utilidades.introducirCadena("Acrónimo: ");
            titulo = Utilidades.introducirCadena("Título: ");
            evaluacion = Utilidades.introducirCadena("Evaluación: ");
            descripcion = Utilidades.introducirCadena("Descripción: ");

            // Guarda la unidad didáctica en la base de datos
            dao.createUnidadDidactica(id, acronimo, titulo, evaluacion, descripcion);
            System.out.println("Unidad Didáctica creada exitosamente: " + titulo);
        } catch (Exception e) {
            System.out.println("Error al crear unidad didáctica: " + e.getMessage()); // Manejo de errores
        }
    }

    // Método para crear una convocatoria
    public static void crearConvocatoria() {
        try {
            int id;
            String convocatoria, descripcion, curso;
            LocalDate fecha;

            // Solicita un ID único para la convocatoria
            do {
                id = Utilidades.leerInt("ID: ");
            } while (existeIdUnidades(id)); // Verifica si el ID ya existe

            // Lee los datos de la convocatoria
            convocatoria = Utilidades.introducirCadena("Introduce el nombre de la convocatoria: ");
            descripcion = Utilidades.introducirCadena("Introduce la descripción: ");
            fecha = Utilidades.pidoFechaDMA("Introduce la fecha de la convocatoria (dd-MM-yyyy): ");
            curso = Utilidades.introducirCadena("Introduce el curso (por ejemplo, '2023-2024'): ");

            // Guarda la convocatoria en la base de datos
            dao.createConvocatoriaExamen(id, convocatoria, descripcion, fecha, curso);
            System.out.println("Convocatoria creada exitosamente: " + convocatoria);
        } catch (Exception e) {
            System.out.println("Error al crear convocatoria: " + e.getMessage()); // Manejo de errores
        }
    }

    // Método para crear un enunciado de examen
    public static void crearEnunciadoExamen() {
        try {
            int id;
            // Solicita un ID único para el enunciado
            do {
                id = Utilidades.leerInt("ID: ");
            } while (existeIdUnidades(id)); // Verifica si el ID ya existe

            // Lee los datos del enunciado
            String descripcion = Utilidades.introducirCadena("Introduce la descripción: ");
            String nivel = Utilidades.introducirCadena("Introduce un nivel según su dificultad: (BAJA, MEDIA, ALTA)");
            boolean disponible = Utilidades.leerRespuesta("Introduce si está disponible (true/false): ");
            String ruta = Utilidades.introducirCadena("Introduce la ruta: ");
            int convo = Utilidades.leerInt("Introduce el id de la convocatoria: ");

            // Guarda el enunciado en la base de datos
            dao.createEnunciado(id, descripcion, nivel, disponible, ruta, convo);
            int unidad = Utilidades.leerInt("Introduce la unidad didáctica a la que se está refiriendo: ");
            dao.createTiene(id, unidad); // Relaciona el enunciado con la unidad didáctica

            System.out.println("Enunciado de examen creado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al crear enunciado de examen: " + e.getMessage()); // Manejo de errores
        }
    }

    // Método para consultar enunciados por unidad didáctica
    public static void consultarEnunciadosPorUnidad() {
        try {
            // Obtiene la lista de unidades didácticas
            List<UnidadDidactica> unidades = dao.obtenerUnidades();
            System.out.println("Estas son las unidades disponibles:");
            for (UnidadDidactica unidad : unidades) {
                System.out.println(unidad.toString()); // Muestra cada unidad
            }

            // Solicita el ID de la unidad didáctica
            int idUnidad = Utilidades.leerInt("Introduce el ID de la unidad didáctica de la que quieras ver los enunciados: ");
            List<Enunciado> enunciados = dao.enunciadosUnidad(idUnidad); // Obtiene enunciados asociados

            // Verifica si hay enunciados asociados
            if (enunciados.isEmpty()) {
                System.out.println("No se encontraron enunciados para esta unidad didáctica.");
            } else {
                System.out.println("Enunciados asociados a la unidad didáctica con ID " + idUnidad + ":");
                for (Enunciado enunciado : enunciados) {
                    // Muestra los detalles de cada enunciado
                    System.out.println("ID: " + enunciado.getId());
                    System.out.println("Descripción: " + enunciado.getDescripcion());
                    System.out.println("Nivel: " + enunciado.getNivel());
                    System.out.println("Disponible: " + (enunciado.isDisponible() ? "Sí" : "No"));
                    System.out.println("Ruta: " + enunciado.getRuta());
                    System.out.println("Convocatoria ID: " + enunciado.getConvocatoriaId());
                    System.out.println("---");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar enunciados por unidad: " + e.getMessage()); // Manejo de errores
        }
    }

    // Método para consultar convocatorias por enunciado
    public static void consultarConvocatoriasPorEnunciado() {
        try {
            // Obtiene la lista de enunciados disponibles
            List<Enunciado> enunciados = dao.obtenerEnunciados();
            System.out.println("Estas son las Enunciados disponibles:");
            for (Enunciado enun : enunciados) {
                System.out.println(enun.toString()); // Muestra cada enunciado
            }

            // Solicita el ID del enunciado
            int idEnunciado = Utilidades.leerInt("Introduce el id del enunciado: ");
            List<ConvocatoriaExamen> convocatorias = dao.convocatoriaEnunciado(idEnunciado); // Obtiene convocatorias asociadas

            // Verifica si hay convocatorias asociadas
            if (convocatorias.isEmpty()) {
                System.out.println("No se encontraron convocatorias para este enunciado.");
            } else {
                System.out.println("Convocatorias asociadas al enunciado con ID " + idEnunciado + ":");
                for (ConvocatoriaExamen convocatoria : convocatorias) {
                    System.out.println(convocatoria); // Muestra cada convocatoria
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar convocatorias por enunciado: " + e.getMessage()); // Manejo de errores
        }
    }

    // Método para asignar un enunciado a una convocatoria
    public static void asignarEnunciadoAConvocatoria() {
        try {
            // Obtiene la lista de enunciados disponibles
            List<Enunciado> enunciados = dao.obtenerEnunciados();
            System.out.println("Estas son los Enunciados disponibles:");
            for (Enunciado enun : enunciados) {
                System.out.println(enun.toString()); // Muestra cada enunciado
            }

            // Solicita el ID del enunciado a asignar
            int idEnunciado = Utilidades.leerInt("Introduce el ID del enunciado a asignar: ");
            // Obtiene la lista de convocatorias disponibles
            List<ConvocatoriaExamen> convos = dao.obtenerConvos();
            System.out.println("Estas son las Convocatorias de Examen disponibles:");
            for (ConvocatoriaExamen convo : convos) {
                System.out.println(convo.toString()); // Muestra cada convocatoria
            }

            // Solicita el ID de la convocatoria
            int idConvocatoria = Utilidades.leerInt("Introduce el ID de la convocatoria: ");
            dao.enunciadoConvocatoria(idEnunciado, idConvocatoria); // Asigna el enunciado a la convocatoria
            System.out.println("El enunciado con ID " + idEnunciado + " ha sido asignado a la convocatoria con ID " + idConvocatoria);
        } catch (Exception e) {
            System.out.println("Error al asignar enunciado a convocatoria: " + e.getMessage()); // Manejo de errores
        }
    }

    // Método para verificar si un ID de unidad didáctica ya existe
    public static boolean existeIdUnidades(int id) {
        List<UnidadDidactica> unidades = dao.obtenerUnidades(); // Obtiene todas las unidades didácticas
        boolean existe = false;
        for (UnidadDidactica unidad : unidades) {
            if (id == unidad.getId()) { // Verifica si el ID coincide
                existe = true;
                break; // Sale del bucle si se encuentra
            }
        }
        return existe; // Retorna si el ID existe o no
    }

    // Método para verificar si un ID de enunciado ya existe
    public static boolean existeIdEnunciados(int id) {
        List<Enunciado> enunciados = dao.obtenerEnunciados(); // Obtiene todos los enunciados
        boolean existe = false;
        for (Enunciado enunciado : enunciados) {
            if (id == enunciado.getId()) { // Verifica si el ID coincide
                existe = true;
                break; // Sale del bucle si se encuentra
            }
        }
        return existe; // Retorna si el ID existe o no
    }

    // Método para verificar si un ID de convocatoria ya existe
    public static boolean existeIdConvos(int id) {
        List<ConvocatoriaExamen> convos = dao.obtenerConvos(); // Obtiene todas las convocatorias
        boolean existe = false;
        for (ConvocatoriaExamen convo : convos) {
            if (id == convo.getIdConvo()) { // Verifica si el ID coincide
                existe = true;
                break; // Sale del bucle si se encuentra
            }
        }
        return existe; // Retorna si el ID existe o no
    }
}
