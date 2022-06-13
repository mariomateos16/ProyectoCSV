package es.mariomateos.proyectocsv;

import java.io.BufferedWriter;
import java.io.FileWriter;


public class Exportar {
    public static void exportarContenido(float media, String seleccionPais){
        String nombreFichero = "export/media_atentados.csv";
        String textoEncabezado = "Pais,Media de atentados ";
        String textoDatos = seleccionPais+","+media;
        
        //Creación del BufferedWriter
        BufferedWriter bw = null;
        
        try {
        //Crear un objeto BufferedWriter. Si ya existe el fichero, 
        //  se borra automáticamente su contenido anterior.
            bw = new BufferedWriter(new FileWriter(nombreFichero));
        //Escribir en el fichero el texto con un salto de línea
            bw.write(textoEncabezado + "\n");
            bw.write(textoDatos + "\n");
        }
        // Comprobar si se ha producido algún error
        catch(Exception ex) {
            System.out.println("Error de escritura del fichero");
            ex.printStackTrace();
        }
        // Asegurar el cierre del fichero en cualquier caso
        finally {
            try {
                // Cerrar el fichero si se ha podido abrir
                if(bw != null)
                    bw.close();
            }
            catch (Exception ex) {
                System.out.println("Error al cerrar el fichero");
                ex.printStackTrace();
            }
        }
    }
}
