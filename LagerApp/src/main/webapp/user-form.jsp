<%@ page import="com.example.lagerapp.model.Medikament" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html >
<head>
    <link href="webjars/bootstrap/5.2.0/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="index.css">

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light ">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Apotheke App</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse " id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link active" aria-current="page" href="MedikamentController">Show-All-Medikament</a>
                <a class="nav-link" href="<%request.getServletPath();%>addMedikament">Add_Medikament</a>
                <a class="nav-link" href="#">Pricing</a>
                <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
            </div>
        </div>
    </div>
</nav>

<% Medikament result= (Medikament) request.getAttribute("medikament");%>



<div class="container mt-3">

    <div class="d-flex justify-content-center">
        <div class="text-center border border-primary bg-info shadow-lg rounded-pill">
            <h1>Du kannst den Stueck hier erhoehen</h1>
        </div>
    </div>
</div>



<div class="container d-flex justify-content-center mt-5">
    <form action="<%request.getServletPath();
          System.out.println(request.getServletPath());%> stueck_erhoehen" method="post">


        <input type="text"name="id" readonly  value="<%=result.getId()%>" placeholder="id" class="form-control" aria-label="Text input with checkbox">
        <div class="input-group mb-3 mt-2 col-md-6">
            <div class="input-group-text ">
                <label>name</label>
            </div>
            <input type="text"name="name" readonly  value="<%=result.getName()%>" placeholder="name" class="form-control" aria-label="Text input with checkbox">
        </div>
        <div class="input-group mb-3 mt-2 col-md-6">
            <div class="input-group-text ">
                <label>phamazentralnummer</label>
            </div>
            <input type="text" name="phamazentralnummer" readonly value="<%=result.getPharmazentralnummer()%>" placeholder="phamazentralnummer" class="form-control" aria-label="Text input with checkbox">
        </div>

        <div class="input-group col-md-6">
            <div class="input-group-text">
                <label>stueckzahl</label>
            </div>
            <input type="text" name="stueckzahl"  value="<%=result.getStueckzahl()%>" class="form-control" placeholder="stueckzahl" aria-label="Text input with radio button">
        </div>
        <button class="btn-success mt-2" type="submit">Erhoehen</button>
    </form>
</div>


</body>
</html>
