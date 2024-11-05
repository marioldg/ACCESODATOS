package org.example.controlFicheros;

import org.example.Main;
import org.example.clasesBase.Torneo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.Scanner;

public class LecturaFicheros {
    private final String rutaCredenciales = "src/main/Files/Credenciales.txt";
    private String rol;
    private Scanner t = new Scanner(System.in);
    private EscrituraFicheros escrituraFicheros = new EscrituraFicheros();


    public String getRol() {
        return rol;
    }

    // Constructor vacio de la clase

    public LecturaFicheros() {
    }


    //Metodo creado para controlar el LogIn de un usuario, asignandole un rol.


    public boolean controlLogIn(String username, String password) {
        boolean sol = false;
        try (BufferedReader br = new BufferedReader(new FileReader(rutaCredenciales))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("  "); //divide la linea con comas
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    //si las credenciales coinciden
                    rol = parts[2];
                    System.out.println("Login exitoso. \n Bienvenido " + username + ". Rol: " + parts[2]);
                    sol = true;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return sol;
    }


    //Metodo creado para leer cualquier archivo XML, de tal forma que nos da un String con el contenido

    public String leerFicheroXML(String rutaPasada) {
        StringBuilder contenido = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaPasada))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append(System.lineSeparator());
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Archivo no encontrado: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Error de lectura: " + ex.getMessage());
        }

        return contenido.toString().trim(); // Elimina el último salto de línea
    }


    //Metodo para leer el XML de paises

    public boolean leerPaises(String code) {
        try {
            File archivo = new File("src/main/Files/Paises.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);
            doc.getDocumentElement().normalize();

            // Obtener la lista de todos los elementos <id> dentro de <pais>
            NodeList listaIds = doc.getElementsByTagName("id");

            for (int i = 0; i < listaIds.getLength(); i++) {
                Element idElemento = (Element) listaIds.item(i);
                String idPais = idElemento.getTextContent();

                // Comparar el ID buscado con el ID del país actual
                if (idPais.equals(code)) {
                    return true; // ID encontrado
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // ID no encontrado
    }


    //Metodo creado para ver si el idUsuario que metemos existe o no


    public boolean comprobarNuevo(String usuario) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/Files/Credenciales.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("  ");
                String idUsuario = parts[0]; // primer elemento es el nombre
                if (idUsuario.equals(usuario)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}










