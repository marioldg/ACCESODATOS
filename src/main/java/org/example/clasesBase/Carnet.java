package org.example.clasesBase;

import java.time.LocalDate;
import java.util.Date;

public class Carnet {
    private long idEntrenador;
    private LocalDate fechaExpedicion;
    private float puntos;
    private int numVictorias;


    public Carnet(long idEntrenador, float puntos, int numVictorias) {

        this.idEntrenador = idEntrenador;
        this.fechaExpedicion = LocalDate.now();
        this.puntos = puntos;
        this.numVictorias = numVictorias;
    }

    public Carnet() {
        this.puntos=0;
        this.numVictorias=0;
    }

    public Carnet(int id, int numVictorias, int ptos) {
        this.idEntrenador=id;
        this.fechaExpedicion = LocalDate.now();
        this.puntos = ptos;
        this.numVictorias = numVictorias;

    }


    public long getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(long idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public int getNumVictorias() {
        return numVictorias;
    }

    public void setNumVictorias(int numVictorias) {
        this.numVictorias = numVictorias;
    }

    public LocalDate getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(LocalDate fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public float getPuntos() {
        return puntos;
    }

    public void setPuntos(float puntos) {
        this.puntos = puntos;
    }



}

