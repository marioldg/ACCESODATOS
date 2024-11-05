package org.example.clasesBase;

import org.example.controlFicheros.EscrituraFicheros;
import org.example.controlFicheros.LecturaFicheros;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Entrenador {
    private long id;
    private String nombre, nacionalidad;
    private LecturaFicheros lecturaFicheros = new LecturaFicheros();
    private ArrayList<Torneo> torneosEntrenadores = new ArrayList<>();
    private LocalDate fechaHoy = LocalDate.now();
    private Carnet carnet; //
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public ArrayList<Torneo> getTorneosEntrenadores() {
        return torneosEntrenadores;
    }
    //Constructor de entrenadores

    public Entrenador(String nombre, long id) {
        this.id = id;
        this.nombre = nombre;
    }

    public Entrenador(long id, String nombre, String nacionalidad, Carnet carnet) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.carnet = new Carnet(id, 0, 0);
    }

    public Entrenador() {
        this.carnet = new Carnet(id, 0, 0);
    }


    public Entrenador(long id, String nombre, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.carnet = new Carnet(id, 0, 0);
    }


    //Getters setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Carnet getCarnet() {
        return carnet;
    }

    public void setCarnet(Carnet carnet) {
        this.carnet = carnet;
    }

    public void setTorneosEntrenadores(ArrayList<Torneo> torneosEntrenadores) {
        this.torneosEntrenadores = torneosEntrenadores;
    }

    @Override
    public String toString() {
        String carnetV = "Entrenador" +
                "\nnombre='" + nombre + '\'' +
                "\nnacionalidad='" + nacionalidad + '\'' +
                "\nfechaHoy=" + fechaHoy +
                "\nid=" + id +
                "\npuntos=" + carnet.getPuntos()
                + "\ntorneos:\n";

        for (Torneo torneos : torneosEntrenadores) {
            carnetV += "nombre" + torneos.getNombre() + " region: " + torneos.getCodRegion();
        }
        return carnetV;
    }




    /*
     Metodo creado para que un Entrenador pueda exportar sus datos en un formato XML
     Para eelo tenemos que pasarle una ruta, si no obtenemos ruta, o esta vacia, le asignaremos una directamente.
     En nuestro caso ponemos "src/main/Files/"
     */

    public void exportarXML(String ruta) {
        String baseXML = lecturaFicheros.leerFicheroXML("src/main/Files/baseCarnet.xml");
        baseXML = baseXML.replaceAll("replace.idEntrenador", String.valueOf(id))
                .replaceAll("replace.fehaEXP", carnet.getFechaExpedicion().format(formato))
                .replaceAll("replace.nombre", nombre)
                .replaceAll("replace.nacionaldiad", nacionalidad)
                .replaceAll("replace.fechaHOY", fechaHoy.format(formato))
                .replaceAll("replace.ptos", String.valueOf(carnet.getPuntos()));

        String torneoXML = lecturaFicheros.leerFicheroXML("src/main/Files/baseTorneos.xml");
        String combateXML = lecturaFicheros.leerFicheroXML("src/main/Files/baseCombates.xml");


        for (Torneo torneo : torneosEntrenadores) {
            String torneoActual = torneoXML.replaceAll("replace.nombreTorneo", torneo.getNombre())
                    .replaceAll("replace.region", String.valueOf(torneo.getCodRegion()))
                    .replaceAll("replace.puntos", String.valueOf(torneo.getPuntos()));

            baseXML += torneoActual;

            for (Combate combate : torneo.getCombates()) {
                String combateActual = combateXML.replaceAll("replace.idCombate", String.valueOf(combate.getId()))
                        .replaceAll("replace.fechaCombate", String.valueOf(combate.getFecha()))
                        .replaceAll("replace.victoria", String.valueOf(carnet.getNumVictorias()));

                baseXML += combateActual;
            }

            baseXML += "\n</combates>"; // Cierra la etiqueta de combates
            baseXML += "\n</torneo>"; // Cierra la etiqueta de torneo
        }

        baseXML += "\n</torneos>";
        baseXML += "\n</carnet>";

        System.out.println(baseXML);
        EscrituraFicheros out = new EscrituraFicheros();
        out.escribirXML(".xml", getNombre(), ruta, baseXML);
    }

}

