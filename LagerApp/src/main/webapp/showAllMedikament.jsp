<%@ page import="java.util.List" %>
<%@ page import="com.example.lagerapp.model.Medikament" %>
<%@ page import="jakarta.servlet.RequestDispatcher" %><%--
  Created by IntelliJ IDEA.
  User: Staige
  Date: 14.08.2022
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
<html>
<head>
    <title>Title hier</title>
    <link rel="stylesheet" href="index.css">
    <link rel="stylesheet" href="webjars/bootstrap/5.2.0/css/bootstrap.css">

</head>
<body>
<%
  List<Medikament> result= (List<Medikament>) request.getAttribute("result");
%>


<h1>"Hello page all medikament!"</h1>

<div class="container mt-4">
<table class="table table-hover table-bordered table-responsive tablecenterCSS" style="width: 100%">
    <tr bgcolor="#f9f9f9">
        <th style="width:20%">Name</th>
        <th style="width:20%">Referenz</th>
        <th style="width:20%">Stueckzahl</th>
        <th>StueckErhoehen</th>
        <th>Verkaufen</th>
    </tr>
    <%
        for (Medikament medikament : result) {
    %>
    <%
        int id = medikament.getId();
    %>
    <%
        String name = medikament.getName();
    %>
    <%
        int stueckzahl = medikament.getStueckzahl();
    %>
    <%
        int phamazentralnummer = medikament.getPharmazentralnummer();
    %>
    <tr>
        <td>
            <%=name%>
        </td>


        <td>
            <%=phamazentralnummer%>
        </td>

        <td>
            <%=stueckzahl%>
        </td>

        <td>
            <a href="erhoehen?id=<%=id%>">
                erhoehen
            </a>
        </td>

        <td>
            <div class="btn-danger">
                <a href="sell?id=<%=id%>" aria-hidden="true" style="color:blue">
                    sell</a>
            </div>

        </td>
    </tr>
    <%}%>
</table>
</div>

</body>
</html>
