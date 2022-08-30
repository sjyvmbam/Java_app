package com.example.lagerapp.model;

public class Medikament {

    private int id;

    private String name;

    private int pharmazentralnummer;
    private int stueckzahl;

    public Medikament(){
    }

    public Medikament(int id, String name , int pharmazentralnummer, int stueckzahl){
        this.id = id;
        this.name=name;
        this.pharmazentralnummer=pharmazentralnummer;
        this.stueckzahl=stueckzahl;
    }
    public Medikament( int id){
        this.id=id;
    }

    public Medikament( int id, int stueckzahl){
        this.id=id;
        this.stueckzahl=stueckzahl;
    }

    public Medikament(String name, int pharmazentralnummer, int stueckzahl){
        this.name=name;
        this.pharmazentralnummer=pharmazentralnummer;
        this.stueckzahl=stueckzahl;
    }

    public Medikament(int id, int pharmazentralnummer, int stueckzahl){
        this.pharmazentralnummer=pharmazentralnummer;
        this.stueckzahl=stueckzahl;
        this.id=id;
    }
    public int getStueckzahl() {
        return stueckzahl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStueckzahl(int stueckzahl) {

        this.stueckzahl = stueckzahl;
    }

    public int getPharmazentralnummer() {
        return pharmazentralnummer;
    }

    public void setPharmazentralnummer(int pharmazentralnummer) {
        this.pharmazentralnummer = pharmazentralnummer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}


