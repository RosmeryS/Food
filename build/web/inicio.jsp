<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Food and Drink</title>
        <link rel="shortcut icon" href="Imagenes/Food_Drink.png">
        <style>
            *{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            body{
                font-family: arial;
                background-image: url(Imagenes/ima.jpg);
            }
            .form-login{
                width: 400px;
                height: 340px;
                background: #4e4d4d;
                margin: auto;
                margin-top: 180px;
                box-shadow: 7px 13px 37px #000;
                padding: 20px 30px;
                border-top:4px solid #AD0404;
                color: white;
            }
            .container{
                display: flex;
                flex-wrap: wrap;
                flex-direction: column;
            }
            .container{
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .form-login h5{
                margin: 0;
                text-align: center;
                heigth: 40px;
                margin-bottom: 30px;
                border-bottom: 1px solid;
                font-size: 20px;

            }
            .controls{
                width: 100%;
                border: 1px solid #AD0404;
                margin-bottom: 15px;
                padding: 11px 10px;
                background: #252323;
                font-size: 14px;
                font-weight: bold;
                color: white;
            }
            .buttons{
                width: 100%;
                height: 40px;
                background: #AD0404;
                border: none;
                color: white;
                margin-bottom: 16px;
            }
            .form-login p{
                height: 40px;
                text-align: center;
                border-bottom: 1px solid;
            }
            .form-login a{
                color: white;
                text-decoration: none;
                font-size: 14px;
            }
            .form-login a hover {
                text-decoration: underline;

            }
        </style>
    </head>
    <body>
        <div class="container">
            <center>
                <c:if test="${error!=null}">
                    <c:if test="${error==2}">
                        <p><strong style="color: red">Usuario y/o Contrasenia Incorrectos!</strong></p>
                    </c:if>
                </c:if>
            </center>
            <section class="form-login">
                <div class="modal fade" id="myModal">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Iniciar Sesión</h5>
                            </div>
                            <div class="modal-body">
                                <form name="main" action="Login?accion=login" method="POST">
                                    <div class="form-group">
                                        <input type="text" class="controls" id="txtUsuario" name="txtUsuario"  placeholder="Usuario" required>

                                    </div>
                                    <br>
                                    <div class="form-group">
                                        <input type="password" class="controls" id="txtClave" name="txtClave" placeholder="Contraseña" required>
                                    </div>
                                    <br>
                                    <div id="enviar">
                                        <button type="submit" class="buttons btn btn-success" name="btnEntrar"> Entrar</button>
                                    </div>
                                    <div class="modal-footer">
                                        <p><a href="formCuenta.jsp"> Crear Cuenta </a></p>
                                    </div>
                                </form>
                                </section>
                            </div>
                            <br>
                            </body>
