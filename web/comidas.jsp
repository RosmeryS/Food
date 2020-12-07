<%@include file="_top.jsp" %>
<link rel="stylesheet" href="CSS/comida.css">
<%@include file="_nav.jsp" %>
        <div class="padre">
            <div class="main-body">
                <section class="row">
                    <div class="container">
                        <h2>Tu Mejor Menú</h2>
                        <p>Escoje tu crepas y Smoothies favoritos aprovecha de incribles promociones que NO te puedes perder....</p>
                        <div class="row">
                            <c:forEach var="i" items="${Comidas}">
                                <div class="col-md-4">
                                    <div class="thumbnail">
                                        <a href="${i.imagen_url}" target="_blank">
                                            <img src="${i.imagen_url}" style="object-fit: cover;object-position: center;" alt="${i.menu}" class="menuFotos">
                                        </a>
                                        <div class="caption">
                                            <h3>${i.menu}</h3>
                                            <p>${i.descripcion}</p>
                                            <span>${i.precio}</span>
                                            <div class="galBtn">
                                                <button type="button" class="btn btn-success " onclick="addToCart(${i.idcomida},'${i.menu}',${i.precio},'${i.descripcion}','${i.imagen_url}')"><i class="fas fa-shopping-cart"> Añadir al Carrito</i></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </section>
            </div>
</div>
<script>
    function addToCart(id, menu, precio, descripcion, imagen_url){
        if(sessionStorage.getItem('isOrdered') == null){
            let comida = {
                "id": id,
                "menu": menu,
                "precio": parseFloat(precio),
                "descripcion": descripcion,
                "imagen_url": imagen_url
            }
            let arr = localStorage.getItem("comidasArray")
            if(arr != null){
                let comidasArray = JSON.parse(localStorage.getItem("comidasArray"))
                let found = comidasArray.comidas.find(i => i.id === comida.id)
                if(found === undefined){
                    comida["cantidad"] = 1
                }else{                 
                    comida["cantidad"] = found.cantidad + 1
                    comidasArray["comidas"] = comidasArray.comidas.filter(i => i.id !== comida.id)
                }
                comidasArray.comidas.push(comida)
                localStorage.setItem("comidasArray", JSON.stringify(comidasArray))
            }else{
                comida["cantidad"] = 1
                let comidasArray = {
                    "comidas": [comida]
                }
                let stringified = JSON.stringify(comidasArray)
                localStorage.setItem("comidasArray", JSON.stringify(comidasArray))
            }
        }else{
            alert('Usted ya confirmó una orden, le agradeceremos su paciencia')
        }
    }
</script>
<%@include file="_down.jsp" %>