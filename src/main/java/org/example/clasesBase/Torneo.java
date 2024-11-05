package org.example.clasesBase;

import java.util.ArrayList;

public class Torneo {
    private int id;
    private String nombre;
    private char codRegion;
    private float PuntosVictoria;
    private int puntos = (int)(Math.random() * 51) + 50;  // Los ptos que ganas
    private String nombreAdmin;
    private String passAdmin;
    private ArrayList <Entrenador>entrenadores = new ArrayList<>();  //Lo hacemos con arraylist para no tener problema con el numero de entrenadores, ni combates.
    private ArrayList <Combate>combates = new ArrayList<>();

    public Torneo(int id, String nombre, String nombreAdmin, String password, int i) {
    }


    //Constructor

    public Torneo(int id, String nombre, char codRegion,
                  ArrayList<Entrenador> entrenadores, ArrayList<Combate> combates) {

        this.id = id;
        this.nombre = nombre;
        this.codRegion = codRegion;
        this.entrenadores = entrenadores;
        this.combates = combates;
    }

    public Torneo(int id, String nombre, char codRegion, String nombreAdmin,String passAdmin) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.codRegion = codRegion;
        this.nombreAdmin=nombreAdmin;
        this.passAdmin=passAdmin;
    }

    public Torneo(int id,String nombre,char codRegion,String nombreAdmin,int puntosVictoria) {
        this.id = id;
        this.nombre = nombre;
        this.codRegion=codRegion;
        this.nombreAdmin=nombreAdmin;
        this.puntos=puntosVictoria;
    }

    //Getters Setters


    public String getNombreAdmin() {
        return nombreAdmin;
    }

    public void setNombreAdmin(String nombreAdmin) {
        this.nombreAdmin = nombreAdmin;
    }

    public String getPassAdmin() {
        return passAdmin;
    }

    public void setPassAdmin(String passAdmin) {
        this.passAdmin = passAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getCodRegion() {
        return codRegion;
    }

    public void setCodRegion(char codRegion) {
        this.codRegion = codRegion;
    }

    public float getPuntosVictoria() {
        return PuntosVictoria;
    }

    public void setPuntosVictoria(float puntosVictoria) {
        PuntosVictoria = puntosVictoria;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public ArrayList<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    public void setEntrenadores(ArrayList<Entrenador> entrenadores) {
        this.entrenadores = entrenadores;
    }

    public ArrayList<Combate> getCombates() {
        return combates;
    }

    public void setCombates(ArrayList<Combate> combates) {
        this.combates = combates;
    }

}