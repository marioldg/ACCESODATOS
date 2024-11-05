
package org.example;

import org.example.clasesBase.*;
import org.example.controlFicheros.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public Scanner scanner = new Scanner(System.in);
    public LecturaFicheros lecturaFicheros = new LecturaFicheros();
    public static SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    public EscrituraFicheros escrituraFicheros = new EscrituraFicheros();
    //public final  String file = "src/main/Files/Credenciales.txt";
    public boolean acceso;
    private ArrayList<Torneo> torneos = new ArrayList<>();
    private ArrayList<Entrenador> entrenadores = new ArrayList<>();
    public static void main(String[] args) throws ParseException {
        Main main = new Main();


        /*ArrayList<Entrenador> entrenadores = new ArrayList<>();
        entrenadores.add(new Entrenador(11, "Pablo", "Esp"));
        entrenadores.add(new Entrenador(12, "Laura", "Arg"));
        entrenadores.add(new Entrenador(13, "Diego", "Mex"));
        entrenadores.add(new Entrenador(14, "Sofía", "Col"));
        ArrayList<Combate> combates = new ArrayList<>();
        combates.add(new Combate(formato.parse("11-11-1111"), 13));
        Entrenador Pablo = new Entrenador(11, "Pablo", "esp");
        Torneo t = new Torneo(12, "Torneo", 'a', entrenadores, combates);
        ArrayList<Torneo> tp = new ArrayList<>();
        tp.add(t);
        Pablo.setTorneosEntrenadores(tp);
        Pablo.exportarXML("src/main/Files");*/


        /*METODO PARA LEER UN .DAT
        try (DataInputStream dis = new DataInputStream(new FileInputStream("src/main/Files/Torneos.dat"))) {
            while (dis.available() > 0) {  // Mientras haya datos disponibles
                int numero = dis.readInt();
                double decimal = dis.readDouble();
                String texto = dis.readUTF();

                System.out.println("Número: " + numero);
                System.out.println("Decimal: " + decimal);
                System.out.println("Texto: " + texto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }





    public Main() {
        /**
         * Cuando empezamos saltamos al metodo login, para meter credenciales
         * Si no son exitosas, nos saltan opciones
         */

        leerTorneos();
        login();



    }

    /**
     * En el metodo login, metemos credenciales, y nos asignamos rol
     */


    public void login() {
        while (true) {
            System.out.print("Ingrese su nombre de usuario: ");
            String username = scanner.nextLine();

            System.out.print("Ingrese su contraseña: ");
            String password = scanner.nextLine();

            acceso = lecturaFicheros.controlLogIn(username, password);

            if (!acceso) {
                System.out.println("Credenciales incorrectas. \n" +
                        "Si desea volver a intentarlo pulse 1. \n" +
                        "Si quieres ser invitado pulse 2.\n" +
                        "Si desea salir pulse 0.");
                int cod = Integer.parseInt(scanner.nextLine());

                switch (cod) {
                    case 0:
                        System.out.println("Saliendo del programa...");
                        return;
                    case 2:
                        menuInvitado();
                        break;
                    case 1:
                        System.out.println("Reintentando LogIn...");
                        break;
                    default:
                        System.out.println("Opción no válida. Reintentando LogIn...");
                        break;
                }
            } else {
                menu(lecturaFicheros.getRol(), username);
                break;
            }
        }
    }

    /**
     * Menu generico, que nos permite movernos
     * entre las diferenctes opciones de los distintos usuarios
     *
     * @param rol
     * @param nombre
     */
    public void menu(String rol, String nombre) {
        switch (rol) {
            case "Admin":
                menuAdmin();
                break;
            case "Entrenador":
                menuEntrenador(null); //como no se guarda la info de los entrenadores no se mete la info aun
                break;
            case "Invi":
                login();
                break;
            default:
                System.out.println("Opcion no valida");
        }
    }

    /**
     * Metodo para desplegar por consola el menu de admin ( no admin torneo)
     */

    public void menuAdmin() {
        System.out.println("Eres el Admin las opciones son esas :" +
                "\n 1- Nuevo Torneo" +
                "\n 0-Salir");
        int opcion = Integer.parseInt(scanner.nextLine());
        switch (opcion) {
            case 1:
                nuevoTorneo();
                break;
            case 0:
                login();
                break;

            default:
                System.out.println("Error en el programa");
                break;
        }
    }

    /**
     * Metodo para desplegar por consola el menu de entrenador
     */

    public void menuEntrenador(Entrenador e) {
        System.out.println("Eres el Entrenador las opciones son esas :" +
                "\n 0- Volver al login"+
                "\n 1- Ver Carnet" +
                "\n 2- Exportar Carnet");

        Entrenador entrenador = new Entrenador();
        int opcion = Integer.parseInt(scanner.nextLine());
        switch (opcion) {

            case 2:
                entrenador.exportarXML("src/main/Files/");
                break;
            case 1:


                break;
            case 0:
                login();
                break;
            default:
                break;
        }
    }

    /**
     * Metodo para desplegar por consola el menu de invitado
     */
    public void menuInvitado() {
        System.out.println("Eres el Invitado las opciones son esas :" +
                "\n 1-Nuevo entrenador " +
                "\n 2-Logear" +
                "\n 3-Salir");
        int opcion = Integer.parseInt(scanner.nextLine());
        switch (opcion) {
            case 1:
                nuevoEntrenador();
                break;
            case 2:
                login();
                break;
            default:
                System.out.println("Saliendo");
                break;
        }
    }

    /**
     * Metodo para crear nuevo entrenador
     * Controlamos que el Entrenador no Exista
     * Si no existe Comprobar que el code de nacioaldiad Existe
     * Si ambos existen creamos, y metemos en credenciales.txt
     */
    public void nuevoEntrenador() {
        System.out.println("Ingrese el nombre del entrenador");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la nacionalidad del entrenador");
        String nacionalidad = scanner.nextLine();
        System.out.println("Ingrese el id del entrenador");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Introduce el idTorneo del torneo que quiera introducir");

        for(Torneo torneos : torneos){
            System.out.println(torneos.getNombre() + "  " + torneos.getId());
        }
        int idTorneo = Integer.parseInt(scanner.nextLine());
        boolean torneoCorrecto=false;
        Torneo torneoElegido = null;
        for(Torneo torneos: torneos){
            if(idTorneo == (torneos.getId())){
                torneoCorrecto=true;
                torneoElegido=torneos;
            }
        }

        /*
        Se tienen que cumplior las dos, que el code de nacionalidad este dentro del xml de nacionalidad
        Y que el id del torneo exista
         */
        if (lecturaFicheros.leerPaises(nacionalidad) && torneoCorrecto) {

            Entrenador nuevoEntrenador = new Entrenador(id, nombre, nacionalidad);

            System.out.println("El torneo si existe");

            nuevoEntrenador.getTorneosEntrenadores().add(torneoElegido);          //me añade al array de los torneos que participa el torneo que escoge el nuevo entrenador
            entrenadores.add(nuevoEntrenador);                                   //añade el nuevo entrenador al array de entrenadores del main
            torneos.get(idTorneo).getEntrenadores().add(nuevoEntrenador);       //añade el nuevo entrenador al array de de entrenadores del torneo escogido
            crearCredenciales(nombre, id, "Entrenador");
            menuEntrenador(nuevoEntrenador);
        } else {
            System.out.println("Error:el code del pais, o el torneo, no esta, no se crea");
        }


    }

        /*
        Metodo creado para incuir un ADMIN TORNEO,
        en la lista de credenciales del txt
        */

    public void nuevoAdminTorneo() {

    }

     // Comprobamos que no existen credenciales dentro de credencuales.txt


    public void crearCredenciales(String nombre, int id, String tipo) {
        if (lecturaFicheros.comprobarNuevo(nombre)) {
            System.out.println("Usuario repetido");

        } else {
            System.out.println("Inserte contraseña");
            String pass = scanner.nextLine();
            escrituraFicheros.insertarCredenciales(nombre, pass, tipo, id);
        }
    }


     // Metodo diseñado para crear nuevo torneo


    public void nuevoTorneo() {

        System.out.println("Comenzamos la creacion del nuevo admin");
        String pass = null;
        System.out.println("Introduce el nombre del Admin del Torneo");
        String nombre = scanner.nextLine();

        if (!lecturaFicheros.comprobarNuevo(nombre)) {
            System.out.println("El usuario no existe");
            System.out.println("Introduce pass");
            pass = scanner.nextLine();

        } else {
            System.out.println("El id metido, ya existe.Intentandolo de nuevo..");
            nuevoTorneo();
        }

            System.out.println("Ingrese el nombre del torneo");
            String nombreTorneo = scanner.nextLine();
            System.out.println("Ingrese la región del torneo (una letra)");
            char region = scanner.nextLine().charAt(0); // Leer el primer carácter como región

            /*
            Este metodo al que llamamos, nos da true si el torneo existe, y false si no
            Tenemos que negar el metodo para seguir dentro
             */
           boolean existeTorneo = false;
           for(Torneo t : torneos) {
               if (t.getNombre().equals(nombreTorneo)) {
                   System.out.println("El torneo ya existe");
                   existeTorneo = true;
               }

        }
           if(!existeTorneo) {
            Torneo torneo1 = new Torneo(torneos.size(), nombreTorneo,region,nombre,pass);
            torneos.add(torneo1);
            System.out.println("El torneo ha sido creado correctamente.");
            escrituraFicheros.insertarCredenciales(nombre, pass, "Admin Torneo",torneo1.getId());

            //Almacena los datos de torneo en el .dat

               try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/main/Files/Torneos.dat", true))) {
                   dos.writeInt(torneo1.getId());
                   dos.writeUTF(torneo1.getNombre());
                   dos.writeChar(torneo1.getCodRegion());
                   dos.writeUTF(nombre);
                   dos.writeInt(torneo1.getPuntos());

               } catch (IOException e) {
                   System.out.println("Error al guardar el torneo en archivo.");
                   e.printStackTrace();
               }


           }
        menuAdmin();


        }

    //Esta funcion te lee el .dat

    public void leerTorneos() {
        File f = new File("src/main/Files/Torneos.dat");
        if(f.exists()&&f.length()>0) {
            try (DataInputStream dis = new DataInputStream(new FileInputStream(f))) {
                while (dis.available() > 0) { // Mientras haya datos disponibles en el archivo
                    int id = dis.readInt();                     // Lee el ID del torneo
                    String nombre = dis.readUTF();              // Lee el nombre del torneo
                    char region = dis.readChar();               // Lee la región del torneo
                    String adminNombre = dis.readUTF();         // Lee el nombre del administrador
                    int puntos = dis.readInt();           // Lee la contraseña del administrador

                    // Crear un nuevo objeto Torneo con los datos leídos
                    Torneo torneo = new Torneo(id, nombre, region, adminNombre, puntos);
                    torneos.add(torneo); // Añadir el torneo a la lista
                }

            } catch (IOException e) {
                System.out.println("Error al leer el archivo de torneos.");
                e.printStackTrace();
            }


        }

    }

    }

