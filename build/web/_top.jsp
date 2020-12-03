<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<% HttpSession sesion = request.getSession();
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    if(sesion.getAttribute("Usuario") == null)
        request.getRequestDispatcher("index.jsp").forward(request, response);
%>
<!DOCTYPE html>
<html> 
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Food and Drink</title>
        <link rel="shortcut icon" href="Imagenes/Food_Drink.png">
        <link rel="stylesheet" href="CSS/style.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <link rel="stylesheet" href="CSS/bootstrap.css">
        <script src="JS/jquery.js"></script>
        <script src="JS/popper.js"></script>
        <script src="JS/bootstrap.js"></script>
    