package org.example.clasesBase;

import java.util.ArrayList;
import java.util.Date;

public class Combate {

    private Date fecha;
    private long id;
    private ArrayList <Entrenador> entrenadores = new ArrayList<>();

    /**
     * Constructor
     * @param entrenadores
     * @param id
     * @param fecha
     */
    public Combate(ArrayList<Entrenador> entrenadores, long id, Date fecha) {
        this.entrenadores = entrenadores;
        this.id = id;
        this.fecha = fecha;
    }

    public Combate(Date fecha, long id) {
        this.fecha = fecha;
        this.id = id;
    }



    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    public void setEntrenadores(ArrayList<Entrenador> entrenadores) {
        this.entrenadores = entrenadores;
    }
}

