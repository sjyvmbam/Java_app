package com.example.lagerapp.controller;

import com.example.lagerapp.Dao.DaoConnection;
import com.example.lagerapp.Dao.DaoConnectionImp;
import com.example.lagerapp.Service.LagerService;
import com.example.lagerapp.Service.LagerServiceImp;
import com.example.lagerapp.model.Medikament;
import com.google.gson.Gson;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "MedikamentController", value = "/")
public class MedikamentController extends HttpServlet {
    private static  final Long serialVersionUID=1L;
    private LagerService lagerService;
    public void init() throws ServletException{
        super.init();
        lagerService=new LagerServiceImp();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getServletPath();
        PrintWriter out= response.getWriter();
        out.println(action);
        System.out.println(action);
        switch (action){
            case "/add_medicament":
                addMedikament(request,response);
                break;
            case "/home":
                home(request,response);

            case "/stueck_erhoehen":
                stueckErhoehen(request,response);

            case "/erhoehen":
                  showEditForm(request,response);
            default:
                listMedikament(request,response);
                break;
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         this.doGet(request,response);
    }

    public void addMedikament(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name =request.getParameter("name");
        int phamazentralnummer=Integer.parseInt(request.getParameter("phamazentralnummer"));
        int stueckzahl=Integer.parseInt(request.getParameter("stueckzahl"));
        Medikament medikament= new Medikament(name,phamazentralnummer,stueckzahl);
        lagerService.addMedikament(medikament);
        response.sendRedirect("MedikamentController");
        //RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
        //rd.forward(request,response);

    }

    public void listMedikament(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Medikament>medikamentList=lagerService.findAllMedikament();
        PrintWriter out= response.getWriter();
        response.setContentType("application/json");
        Gson  gson=new Gson();
        List<Medikament> result= new ArrayList<>();
        if (medikamentList.size()>0){
            result=medikamentList;
            String jsonData= gson.toJson(medikamentList);
            request.setAttribute("result",result);
            //out.print(jsonData);
        }
        RequestDispatcher rd=request.getRequestDispatcher("showAllMedikament.jsp");
        rd.forward(request,response);
        //out.close();
    }

    public  void  home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd=request.getRequestDispatcher("HomePage.jsp");
        rd.forward(request,response);
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        //Medikament m=new Medikament();
        Medikament medikament=lagerService.findOneMedikament(id);
        request.setAttribute("medikament",medikament);
        RequestDispatcher rd=request.getRequestDispatcher("user-form.jsp");
        rd.forward(request,response);
    }

    public void stueckErhoehen(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id= Integer.parseInt((request.getParameter("id")));
        int stueck= Integer.parseInt((request.getParameter("stueckzahl")));
        Medikament medikament=new Medikament(id,stueck);
        lagerService.aufstocken(medikament);
        RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
        rd.forward(request,response);
    }
}

