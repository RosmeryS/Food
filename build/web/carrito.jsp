<%@include file="_top.jsp" %>
<link rel="stylesheet" href="CSS/table.css">
<%@include file="_nav.jsp" %>
<div class="padre">
    <div id="tabla" class="tabla container-fluid p-5">
        <%@include file="_message.jsp" %>
        <h1 class="mb-4">Detalle de orden</h1>
        <div class="row">
            <div class="col-2">
                <select id="formaPago" class="form-control">
                    <option value="Efectivo">Efectivo</option>
                    <option value="Credito">Crédito/Débito</option>
                </select>
            </div>
            <div class="col-2">
                <a class="btn btn-primary mb-3" href="javascript:void(0);" onclick="sendData();">Confirmar orden</a>
            </div>
        </div>
        <table class="table table-hover" id="tablaOrdenes">
            <thead>
                <tr>
                    <th colspan="2" class="col-6">Comida</th>
                    <th class="col-2">Precio</th>
                    <th class="col-2">Cantidad</th>
                    <th class="col-2">Subtotal</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
            <tfoot></tfoot>
        </table>
    </div>
    <div id="succesCard" style="display:none" class="card text-center m-5">
        <div class="card-body">
            <h5 class="card-title">Su orden se registró correctamente</h5>
            <p class="card-text">En estos momentos prepararemos su orden, agradeceremos su espera.</p>
            <a href='${pageContext.servletContext.contextPath}/Principal' class="btn btn-primary">Ir a inicio</a>
        </div>
    </div>
</div>
<script>
    const tabla_body = document.getElementById("tablaOrdenes").querySelector("tbody")
    const tabla_foot = document.getElementById("tablaOrdenes").querySelector("tfoot")

    const optionsFormat = {style: 'currency', currency: 'USD'};
    const numberFormat = new Intl.NumberFormat('en-US', optionsFormat);

    updateTable();

    function updateTable() {
        let comidasArray = JSON.parse(localStorage.getItem("comidasArray"))
        tabla_body.innerHTML = ''
        comidasArray.comidas.forEach(item => {
            tabla_body.innerHTML +=
                    "<tr>" +
                    "<td><img style='height:60px; width: 60px; object-fit: cover; object-position:center;' src='" + item.imagen_url + "'></td>" +
                    "<td>" + item.menu + "<br>" + item.descripcion + "</td>" +
                    "<td>" + numberFormat.format(item.precio) + "</td>" +
                    "<td>" + item.cantidad + "</td>" +
                    "<td>" + (numberFormat.format(item.precio * item.cantidad)) + "</td>" +
                    "</tr>"
        })

        let total = comidasArray.comidas.reduce((total, item) => {
            return total += (item.precio * item.cantidad)
        }, 0)
        tabla_foot.innerHTML = "<tr><th colspan='3'></th><th>Total</th><th>" + numberFormat.format(total) + "</th><tr>"
    }

    function sendData() {
        let comidasArray = JSON.parse(localStorage.getItem("comidasArray"))
        let formaPago = document.getElementById('formaPago')
        const xhr = new XMLHttpRequest()

        xhr.onload = function () {
            let tabla = document.getElementById("tabla")
            let succesCard = document.getElementById("succesCard")
            
            tabla.style = "display: none"
            succesCard.style = "display: block"
        }

        let values = comidasArray.comidas
                .map(item => {
                    return 'idcomidas=' + item.id + '&cantidades=' + item.cantidad
                })
                .join('&')
        
        let total = comidasArray.comidas.reduce((total, item) => {
            return total += (item.precio * item.cantidad)
        }, 0)
        xhr.open('POST', '${pageContext.servletContext.contextPath}/Ordenes')
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded')
        xhr.send(values+'&formaPago='+formaPago.value+"&total="+total)
    }
</script>
<%@include file="_down.jsp" %>