package com.example.lagerapp.model;

import java.util.List;

public class Verkaufen {
    private int id;
    private int produktVerkauft;
    private List<Medikament> medikament;

    public Verkaufen(){

    }

    public Verkaufen(int id, Medikament medikament){
        this.id=id;
        this.medikament = (List<Medikament>) medikament;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Medikament getMedikament() {

        return (Medikament) medikament;
    }

    public int getProduktVerkauft() {
        return produktVerkauft;
    }

    public void setProduktVerkauft(int produktVerkauft) {
        this.produktVerkauft = produktVerkauft;
    }

    public void setMedikament(Medikament medikament) {
        this.medikament = (List<Medikament>) medikament;
    }




}

