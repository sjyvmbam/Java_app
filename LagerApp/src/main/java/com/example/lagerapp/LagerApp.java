package com.example.lagerapp;


import com.example.lagerapp.Dao.DaoConnectionImp;
import com.example.lagerapp.Service.LagerServiceImp;
import com.example.lagerapp.model.Medikament;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LagerApp {

    public static void main(String[] args) {

        int [] a={1,2,3,4,5};
        String[]b={"hello","sandra"};
        List <String> c= Arrays.stream(b)
                .filter(y -> y.startsWith("s")).collect(Collectors.toList());

        System.out.println(c);

     // new DaoConnectionImp().createTable();
        Medikament medikament=new LagerServiceImp().findOneMedikament(3);
        //new LagerServiceImp().aufstocken(new Medikament(1,50));

        System.out.println(medikament.getPharmazentralnummer());

      // new LagerServiceImp().addMedikament(new Medikament("doliprane",1120001,70));
    }


}