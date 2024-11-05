package org.example.controlFicheros;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscrituraFicheros {


    //Constructor de la clase para escritura

    public EscrituraFicheros() {
    }

    /*
      Metodo para poder escribir archivos con formato XML,
       comprobamos si la ruta que nos pasan esta vacia, de ser asi, ponemos directamente src/main/Files/
     */

    public void escribirXML(String extension, String nombre, String ruta, String contenido) {
        // Crear la ruta completa del archivo
        if (ruta.isEmpty()) {
            ruta = "src/main/Files/";
        }
        String rutaCompleta = ruta + "/" + nombre + extension;
        File f = new File(rutaCompleta);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
            writer.write(contenido);
            System.out.println("Archivo XML creado en: " + rutaCompleta);
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    /*
      Metodo creado para meter en el documento credenciales.txt los nuevos ususarios que creemos,
      asi como los datos necesarios
     */

    public void insertarCredenciales(String nombre, String password, String tipo, int id) {
        String linea = nombre + "  " + password + "  " + tipo + "  " + id;
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("src/main/Files/Credenciales.txt", true));
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}






