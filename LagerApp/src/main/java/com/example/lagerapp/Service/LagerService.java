package com.example.lagerapp.Service;

import com.example.lagerapp.model.Medikament;

import java.util.List;

public interface LagerService {


    String addMedikament(Medikament medikament);

    String verkaufenMedikament(Medikament medikament);

    void erstellenFileToCsv();

    List<Medikament> findAllMedikament();
    public Medikament findOneMedikament(int a);


        void erstellungPdf();
    String aufstocken(Medikament medikamentList);

}
