<%@include file="../_top.jsp" %>
<link rel="stylesheet" href="CSS/table.css">
<%@include file="../_nav.jsp" %>
<div class="padre">
    <div class="tabla container-fluid p-5">
        <%@include file="../_message.jsp" %>
        <h1 class="mb-1">Lista de ${servlet_name}</h1>
        <nav class="navbar navbar-light bg-light mb-2">
            <form accept-charset="UTF-8" class="form-inline" action="${pageContext.servletContext.contextPath}${servlet}" method="GET">
                <input class="form-control mr-sm-2" type="search" value="${usuario}" name="usuario" placeholder="Buscar..." aria-label="Search">
                <select class="form-control mr-sm-2" name="estado">
                    <option value="0">Opciones...</option>
                    <option value="Pendiente" ${estado == "Pendiente" ? "selected" : ""}>Pendiente</option>
                    <option value="Enviada" ${estado == "Enviada" ? "selected" : ""}>Enviada</option>
                    <option value="Entregada" ${estado == "Entregada" ? "selected" : ""}>Entregada</option>
                    <option value="Cancelada" ${estado == "Cancelada" ? "selected" : ""}>Cancelada</option>
                </select>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar...</button>
                <button class="btn btn-outline-secondary my-2 my-sm-0" type="reset" onclick="limpiarFiltros();">Limpiar filtros</button>
            </form>
        </nav>
        <c:if test="${Ordenes == null}">
            <h3>No hay órdenes</h3>
        </c:if>
        <c:if test="${Ordenes != null}">
            <div class="accordion" id="accordionExample">
                <c:forEach var="i" items="${Ordenes}">
                    <div class="card">
                        <div class="card-header row" id="headingOne">
                            <div class="col-2">
                                <p class="mb-0"><i class="far fa-clock"></i> ${i.fechaOrden}</p>
                                <span class="text-uppercase font-weight-bold d-inline-block mb-3" style="font-size: 14px; letter-spacing: .9px">${i.estado}</span>
                            </div>
                            <div class="col-3">
                                <h5 class="mb-0">${i.usuario}</h5>
                                <p class="mb-0"><i class="far fa-credit-card"></i> ${i.formaPago}</p>
                                <p><i class="fas fa-dollar-sign"></i> ${i.total}</p>
                            </div>
                            <div class="col-3">
                                <p class="mb-0"><i class="fas fa-map-marked-alt"></i> ${i.direccion}</p>
                            </div>
                            <c:if test="${disable == null}">
                                <div class="col-2">
                                    <a href="#" onclick="cambiarEstado(${i.idorden}, 'Enviada');" class="btn btn-info ${i.estado == "Enviada" || i.estado == "Entregada" || i.estado == "Cancelada" ? "disabled" : ""}"  data-toggle="tooltip" data-placement="top" title="Enviar"> <i class="fas fa-truck"></i>  </a>
                                    <a href="#" onclick="cambiarEstado(${i.idorden}, 'Entregada');" class="btn btn-success ${i.estado == "Pendiente" || i.estado == "Entregada" || i.estado == "Cancelada" ? "disabled" : ""}"  data-toggle="tooltip" data-placement="top" title="Entregada"> <i class="fas fa-check-circle"></i>  </a>
                                    <a href="#" onclick="cambiarEstado(${i.idorden}, 'Cancelada');" class="btn btn-danger ${i.estado == "Enviada" || i.estado == "Entregada" || i.estado == "Cancelada"  ? "disabled" : ""}"  data-toggle="tooltip" data-placement="top" title="Cancelar"> <i class="fas fa-window-close"></i>  </a>
                                    <a target="_blank" href="${pageContext.servletContext.contextPath}/Reportes?accion=factura&id=${i.idorden}" class="btn btn-danger"  data-toggle="tooltip" data-placement="top" title="Factura"> <i class="fas fa-print"></i>  </a>
                                    <a href="" class="btn btn-primary"  data-toggle="tooltip" data-placement="top" title="Editar"> <i class="fas fa-edit"></i>  </a>
                                    <a href="" class="btn btn-danger"  data-toggle="tooltip" data-placement="top" title="Eliminar"> <i class="fas fa-trash"></i>  </a>
                                </div>
                            </c:if>
                            <div class="col-2">
                                <button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#collapse${i.idorden}" aria-expanded="true" aria-controls="collapseOne">
                                    Detalles <i class="fas fa-caret-down"></i>
                                </button>
                            </div>
                        </div>

                        <div id="collapse${i.idorden}" class="collapse" aria-labelledby="heading${i.idorden}" data-parent="#accordionExample">
                            <div class="card-body">
                                <ul class="list-group list-group-flush">
                                    <c:forEach var="j" items="${i.getDetalles()}">
                                        <li class="list-group-item">
                                            ${j.menu} <span class="font-weight-bold">${j.getCantidad()} x $${j.precio} = $${j.subtotal}</span>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>
    </div>
</div>
<script>
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    })
</script>
<script>
    function limpiarFiltros() {

        location.href = "${pageContext.servletContext.contextPath}${servlet}" //PRINCIPAL sin parametros
    }
    function cambiarEstado(id, estado) { //AJAX
        const xhr = new XMLHttpRequest()

        xhr.onload = function () {
            location.href = "${pageContext.servletContext.contextPath}${servlet}"
        }

        xhr.open('POST', '${pageContext.servletContext.contextPath}${servlet}')
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded')
        xhr.send("accion=cambiarEstado&idorden=" + id + "&estado=" + estado)
    }
</script>
<%@include file="../_down.jsp" %>