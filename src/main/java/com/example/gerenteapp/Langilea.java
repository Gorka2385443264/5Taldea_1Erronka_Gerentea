package com.example.gerenteapp;

public class Langilea {
    private int id;
    private String izena;
    private String abizena;
    private String pasahitza;
    private String email;
    private int nivelPermisos;

    // Constructor
    public Langilea(int id, String izena, String abizena, String pasahitza, String email, int nivelPermisos) {
        this.id = id;
        this.izena = izena;
        this.abizena = abizena;
        this.pasahitza = pasahitza;
        this.email = email;
        this.nivelPermisos = nivelPermisos;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public String getAbizena() {
        return abizena;
    }

    public void setAbizena(String abizena) {
        this.abizena = abizena;
    }

    public String getPasahitza() {
        return pasahitza;
    }

    public void setPasahitza(String pasahitza) {
        this.pasahitza = pasahitza;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNivelPermisos() {
        return nivelPermisos;
    }

    public void setNivelPermisos(int nivelPermisos) {
        this.nivelPermisos = nivelPermisos;
    }

    @Override
    public String toString() {
        return "Langilea{" +
                "id=" + id +
                ", izena='" + izena + '\'' +
                ", abizena='" + abizena + '\'' +
                ", pasahitza='" + pasahitza + '\'' +
                ", email='" + email + '\'' +
                ", nivelPermisos=" + nivelPermisos +
                '}';
    }
}
