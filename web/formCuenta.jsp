<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Food and Drink</title>
        <link rel="shortcut icon" href="Imagenes/Food_Drink.png">
        <link rel="stylesheet" href="CSS/style.css">
        <link rel="stylesheet" href="CSS/formCuenta.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
            <a class="navbar-brand brand-logo" href="" > 
                <img src="Imagenes/Food_Drink.png" alt="Food_Drink" id="logo">
                <b> Food & Drink</b>
            </a> 
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation"> 
                <span class="navbar-toggler-icon"></span>
            </button> 
            <div class= "collapse navbar-collapse" id="navbarTogglerDemo02" >
                <ul class= "navbar-nav mr-auto mt-2 mt-lg-0" > 
                    <li class= "nav-item" > 
                        <a class= "nav-link" href= "index.jsp"><i class="fas fa-home"> Inicio </i></a> 
                    </li>
                    <li class= "nav-item" > 
                        <a class= "nav-link" href= "HTML/comida.html"> <i class="fas fa-utensils"> Comidas </i></a> 
                    </li>
                    <li class= "nav-item" > 
                        <a class= "nav-link" href= "HTML/conocenos.html"> <i class="fas fa-book"> Conócenos... </i></a> 
                    </li>  
                    <li class="nav-item">
                        <a class="nav-link" href="HTML/carretilla.html"><i class="fas fa-shopping-cart"> Carrito</i></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="HTML/ordenes.html"><i class="fas fa-book"> Orden </i></a>
                    </li>
                </ul> 

            </div> 
        </nav> 
        <div class="container">
            <div class="modal fade" id="myModal">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Iniciar Sesión</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body">
                            <form>
                                <div class="form-group">
                                    <label for="exampleInputEmail1"> Dirección de correo:</label>
                                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Alicia@gmail.com">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1"> Contraseña: </label>
                                    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Contraseña">
                                </div>
                                <div id="enviar">
                                    <button type="submit" class="btn btn-success"> Entrar</button>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <a href="../HTML/formCuenta.html">Crear cuenta</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="padre">
            <div class="titulo">
                <h3>Crea tu cuenta</h3>
            </div>
            <div class="main-body">
                <div class="row">
                    <form class="divForm col-md-6" method="POST" action="crearUsuario">
                        <hr>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="txtNombre">Nombre:</label>
                                <input type="email" class="form-control" id="txtNombre" name="txtNombre" placeholder="Juán Luis">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="txtApellido">Apellido:</label>
                                <input type="password" class="form-control" id="txtApellido" name="txtApellido" placeholder="Guerra Solis">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="txtCorreo">Correo:</label>
                                <input type="email" class="form-control" id="txtCorreo" name="txtCorreo" placeholder="Usuario@gmail.com">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="txtContraseña">Contraseña:</label>
                                <input type="password" class="form-control" id="txtContraseña" name="txtContraseña" placeholder="Contraseña">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="txtDireccion">Dirección:</label>
                            <input type="text" class="form-control" id="txtDireccion" name="txtDireccion" placeholder="1234 Lomas de San Antonio">
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-5">
                                <label for="datepicker">Fecha de Nacimiento:</label>
                                <input id="datepicker" name="datepicker" placeholder="11/23/2019">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="Departamento">Departamento:</label>
                                <select id="Departamento" class="form-control">
                                    <option selected>Ahuachapán</option>
                                    <option selected>Sonsonate</option>
                                    <option selected>Santa Ana</option>
                                    <option selected>La Libertad</option>
                                    <option selected>Cabañas</option>
                                    <option selected>San Salvador</option>
                                    <option selected>Elegir...</option>
                                </select>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="txtTelefono">Teléfono:</label>
                                <input type="text" class="form-control" id="txtTelefono" name="txtTelefono" placeholder="71594358">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="gridCheck">
                                <label class="form-check-label" for="gridCheck">
                                    Acepto los terminos y condiciones de servicio
                                </label>
                            </div>
                        </div>
                        <div class="divBtnCrear">
                            <button type="submit" class="btn btn-success btnCrear">Crear</button>
                        </div>
                    </form>
                    <div class="anuncio col-md-6">
                        <h3>Con tu cuenta puedes...</h3>
                        <div class="anuncioInterno">
                            <p><i class="fas fa-sync-alt"></i> Tener acceso fácil y rápido a tu historial de ordenes</p>
                            <p><i class="fas fa-search-location"></i> Rastrear tu orden en tiempo real</p>
                            <p><i class="fas fa-exclamation-circle"></i> Disfrutar de promociones exclusivas para usuarios registrados</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <footer class="page-footer font-small bg-dark">
        <div class="container">
            <div class="row">
                <div class="col-md-12 py-5">
                    <a class="fb-ic">
                        <i class="fab fa-facebook-f fa-lg white-text mr-md-5 mr-3 fa-2x"></i>
                    </a>
                    <a class="tw-ic">
                        <i class="fab fa-twitter fa-lg white-text mr-md-5 mr-3 fa-2x"> </i>
                    </a>
                    <a class="gplus-ic">
                        <i class="fab fa-google-plus-g fa-lg white-text mr-md-5 mr-3 fa-2x"> </i>
                    </a>
                    <a class="li-ic">
                        <i class="fab fa-linkedin-in fa-lg white-text mr-md-5 mr-3 fa-2x"> </i>
                    </a>
                    <a class="ins-ic">
                        <i class="fab fa-instagram fa-lg white-text mr-md-5 mr-3 fa-2x"> </i>
                    </a>
                    <a class="pin-ic">
                        <i class="fab fa-pinterest fa-lg white-text fa-2x"> </i>
                    </a>
                </div>
            </div>
        </div>
        <div class="footer-copyright text-center py-3">
            <p><b>© 2020 Copyright: Food & Drink</b> <br>
                Todos los derechos reservados. El nombre Food & Drink, <br>
                logos, imágenes y marcas relacionadas son marcas registradas <br>
                de Food & Drink.  <br>
                S.A. de C.V. El Salvador.
            </p>
        </div>
    </footer>
    <script src="formCuenta.jsp" type="text/javascript"></script>
</html>
