<%@include file="_top.jsp" %>
<%@include file="_nav.jsp" %>
        <div id="padre">
                <div id="demo" class="carousel slide" data-ride="carousel">
                    <ul class="carousel-indicators">
                        <li data-target="#demo" data-slide-to="0" class="active"></li>
                        <li data-target="#demo" data-slide-to="1"></li>
                        <li data-target="#demo" data-slide-to="2"></li>
                    </ul>
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src="Imagenes/salada.jpg" alt="Imagen" class="imgCar mx-auto d-block">
                            <div class="carousel-caption">
                                <h3>Crepas Saladas</h3>
                                <p>Probala no te quedes con las ganas!</p>
                            </div>  
                        </div>

                        <div class="carousel-item">
                            <img src="Imagenes/banner.jpg" alt="Imagen" class="imgCar mx-auto d-block">
                            <div class="carousel-caption">
                                <h3>Smoothies</h3>
                                <p>No te quedes con las ganas de probar nuestro sabrosos smoothies</p>
                            </div> 
                        </div>

                        <div class="carousel-item">
                            <img src="Imagenes/mucho.jpg" alt="Imagen" class="imgCar mx-auto d-block">
                            <div class="carousel-caption">
                                <h3>Y álgo más</h3>
                                <p>Probalos no te quedes con las ganas! sera un placer atenderte</p>
                            </div> 
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#demo" data-slide="prev">
                        <span class="carousel-control-prev-icon"></span>
                    </a>
                    <a class="carousel-control-next" href="#demo" data-slide="next">
                        <span class="carousel-control-next-icon"></span>
                    </a>     
                </div>
                <div class="llamanos-body">
                    <p class="cPedidos"> <i class="fas fa-phone-square"></i> Realiza tus pedidos al : 2451-6036 </p>
                </div>
                <div class="servicios">
                    <div class="h3C">
                        <h3><i class="fas fa-map-marked-alt">  Contamos con servicio a domicilio en los siguientes departamentos:</i></h3>                    
                    </div>
                    <div class="list-servicios">
                        <ul class="list-dep">
                            <li>Sonsonate</li>
                            <li>Ahuachapán</li>
                            <li>San Salvador</li>
                            <li>La Unión</li>
                        </ul>
                        <ul class="list-dep">
                            <li>Usulután</li>
                            <li>La Libertad</li>
                            <li>San Miguel</li>
                            <li>Cabañas</li>
                        </ul>
                    </div>
                    <div class="map">
                        <div id="map-container-google-1" class="z-depth-1-half map-container">
                            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d992129.4769108025!2d-89.49073057296482!3d13.74834543356967!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8f6327a659640657%3A0x6f9a16eb98854832!2sEl%20Salvador!5e0!3m2!1sen!2ssv!4v1574358435790!5m2!1sen!2ssv" frameborder="0" style="border:0;" allowfullscreen="">
                            </iframe>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="_down.jsp" %>