</head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
            <a class="navbar-brand brand-logo" href="index.html" > 
                <img src="Imagenes/Food_Drink.png" alt="Food_Drink" id="logo">
                <b> Food & Drink</b>
            </a> 
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbar-togglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class= "collapse navbar-collapse" id="navbarTogglerDemo02" >
                <ul class= "navbar-nav mr-auto mt-2 mt-lg-0" >
<!--                    <c:forEach var="i" items="${MenuPrincipal}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.servletContext.contextPath}${i.url}"><i class="${i.icono != null ? "fas fa-" : ""}${i.icono}">${i.menu}</i></a>
                        </li>
                    </c:forEach>-->
                    <c:forEach var="i" items="${FullMenus}">
                        <c:if test="${i.sub_menus.size() != 0}">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle d-flex align-items-center" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="mr-1 ${i.menu_principal.icono != null ? "fas fa-" : ""}${i.menu_principal.icono}"></i>${i.menu_principal.menu}</a>
                                <div  class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <c:forEach var="j" items="${i.sub_menus}">
                                        <a class="dropdown-item" href="${pageContext.servletContext.contextPath}${j.url}">${j.menu}</a>
                                    </c:forEach>
                                </div>
                            </li>
                        </c:if>
                        <c:if test="${i.sub_menus.size() == 0}">
                            <li class="nav-item">
                                <a class="nav-link d-flex align-items-center" href="${pageContext.servletContext.contextPath}${i.menu_principal.url}"><i class="mr-1 ${i.menu_principal.icono != null ? "fas fa-" : ""}${i.menu_principal.icono}"></i>${i.menu_principal.menu}</a>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>      
            </div> 
        </nav> 
