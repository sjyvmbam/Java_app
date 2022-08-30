package com.example.lagerapp.model;

import com.example.lagerapp.Dao.DaoConnectionImp;

import java.util.ArrayList;
import java.util.List;

public class Lager {
    private int id;
    private List<Medikament> medikament=new ArrayList<>();


    public Lager(){

    }
    public Lager(int id, List<Medikament> medikament){
        this.id=id;
        this.medikament=medikament;
    }



    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<Medikament> getMedikament() {
        return medikament;
    }

    public void setMedikament(List<Medikament> medikament) {
        this.medikament = medikament;
    }


}


