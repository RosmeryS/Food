<%@include file="../_top.jsp" %>
<link rel="stylesheet" href="CSS/form.css">
<%@include file="../_nav.jsp" %>
<div class="padre p-5">
    <h1>${op} ${servlet_name}</h1>
    <form class="mw-100 w-50" action="${pageContext.servletContext.contextPath}${servlet}" method="POST">
        <div class="form-group w-25">
            <label for="idrol">ID</label>
            <input class="form-control" readonly id="idrol" name="idrol" value="${valor.idRol}" type="text">
        </div>
        <div class="form-group">
            <label for="rol">Rol</label>
            <input class="form-control" id="rol" name="rol" value="${valor.rol}" type="text">
        </div>
        <input class="btn btn-primary" type="submit" value="Guardar">
        <a class="btn btn-danger" href="${pageContext.servletContext.contextPath}${servlet}">Cancelar</a>
    </form>
</div>
<%@include file="../_down.jsp" %>
