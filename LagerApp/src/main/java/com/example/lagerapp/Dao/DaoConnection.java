package com.example.lagerapp.Dao;

import com.example.lagerapp.model.Medikament;

import java.sql.Connection;
import java.util.List;

public interface DaoConnection {
    void createTable();

    void addNewMedikament(Medikament medikament);

    List<Medikament> findAll();

    Medikament findOne(int a);

    void reduktieren(Medikament medikament);
    void erhoehen(Medikament medikament);
    void erstellenCsv();

    void erstellenPdf();
}