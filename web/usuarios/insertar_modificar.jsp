<%@include file="../_top.jsp" %>
<link rel="stylesheet" href="CSS/form.css">
<%@include file="../_nav.jsp" %>
<div class="padre p-5">
    <h1>${op} ${servlet_name}</h1>
    <form class="mw-100 w-50" action="${pageContext.servletContext.contextPath}${servlet}" method="POST">
        <div class="form-group w-25">
            <label for="idusuario">ID</label>
            <input class="form-control" ${block_id == "true" ? "readonly" : ""} id="idusuario" name="tidusuario" value="${valor.idUsuario}" type="text">
        </div>
        <c:if test="${valor != null}">
            <div class="form-row mb-3 align-items-center">
                <label class="col-12" for="clave">Clave</label>
                <div class="col-7"><input readonly class="form-control" autocomplete="off" id="clave" name="tclave" type="text"></div>
                <div class="col">
                    <div class="form-check mb-2">
                        <input class="form-check-input" type="checkbox" onchange="activarInput(this, 'clave');">
                      <label class="form-check-label" for="autoSizingCheck">
                        Modificar
                      </label>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${valor == null}">
            <div class="form-row mb-3 align-items-center">
                <label class="col-12" for="clave">Clave</label>
                <div class="col-7"><input class="form-control" autocomplete="off" id="clave" name="tclave" type="text"></div>
            </div>
        </c:if>
        <div class="form-group">
            <label for="nombre">Nombres</label>
            <input class="form-control" id="nombre" name="nombre" value="${valor.nombre}" type="text">
        </div>
        <div class="form-group">
            <label for="apellido">Apellidos</label>
            <input class="form-control" id="apellido" name="apellido" value="${valor.apellido}" type="text">
        </div>
        <div class="form-group">
            <label for="telefono">Teléfono</label>
            <input class="form-control" id="telefono" name="telefono" value="${valor.telefono}" type="text">
        </div>
        <div class="form-group">
            <label for="direccion">Dirección</label>
            <input class="form-control" id="direccion" name="direccion" value="${valor.direccion}" type="text">
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input class="form-control" id="email" name="email" value="${valor.email}" type="text">
        </div>
        <div class="form-group">
            <label for="fecha_nacimiento">Fecha de nacimiento</label>
            <input class="form-control" id="fecha_nacimiento" name="fecha_nacimiento" value="${valor.fechaNac}" type="date">
        </div>
        <div class="form-row mb-3">
            <label class="col-12" for="idrol">Rol</label>
            <div class="col"><input id="idrol" name="idrol" value="${valor.idRol}" readonly type="text" class="form-control"></div>
            <div class="col-7"><input id="rol" name="rol" value="${rol.rol}" readonly type="text" class="form-control"></div>
            <div class="col d-flex align-items-center"><a href="javascript:void(0);" onclick='openWindow("${pageContext.servletContext.contextPath}${servlet}?accion=rol");'><i class="fas fa-search"></i></a></div>
        </div>
        <input class="btn btn-primary" type="submit" value="Guardar">
        <a class="btn btn-danger" href="${pageContext.servletContext.contextPath}${servlet}">Cancelar</a>
    </form>
</div>
        <script>
            function setDataRol(idrol, rol){
                document.getElementById("idrol").value = idrol;
                document.getElementById("rol").value = rol;
            }
        </script>
<%@include file="../_down.jsp" %>
