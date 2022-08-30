<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
<html>
<head>
    <title>Title hier</title>
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
                <a class="nav-link active" aria-current="page" href="MedikamentController">Show_All_Medikament</a>
                <a class="nav-link" href="<%request.getContextPath();%>home">Add_Medikament</a>
                <a class="nav-link" href="#">Pricing</a>
                <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
            </div>
        </div>
    </div>
</nav>

<div class="container">

    <div class="d-flex justify-content-center">
    <div class="text-center border border-primary bg-info shadow-lg rounded-pill">
        <h1>Welcome in Unser Apotheke System</h1>
    </div>
    </div>
</div>
</body>
</html>