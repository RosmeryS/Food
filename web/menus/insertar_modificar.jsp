<%@include file="../_top.jsp" %>
<link rel="stylesheet" href="CSS/form.css">
<%@include file="../_nav.jsp" %>
<div class="padre p-5">
    <h1>${op} ${servlet_name}</h1>
    <form class="mw-100 w-50" action="${pageContext.servletContext.contextPath}${servlet}" method="POST">
        <div class="form-group w-25">
            <label for="idmenu">ID</label>
            <input class="form-control" readonly id="idmenu" name="idmenu" value="${valor.idmenu}" type="text">
        </div>
        <div class="form-group">
            <label for="menu">Menú</label>
            <input class="form-control" id="menu" name="menu" value="${valor.menu}" type="text">
        </div>
        <div class="form-group">
            <label for="descripcion">Descripción</label>
            <input class="form-control" readonly="" id="descripcion" name="descripcion" value="${valor.descripcion}" type="text">
        </div>
        <div class="form-group">
            <label for="url">URL</label>
            <input class="form-control" readonly="" id="url" name="url" value="${valor.url}" type="text">
        </div>
        <div class="form-group">
            <label for="idpadre">Padre</label>
            <input class="form-control col-2" readonly="" id="idpadre" name="idpadre" value="${valor.idpadre}" type="text">
            <input class="form-control col-6" readonly="" id="padre" name="padre" value="${p.menu}" type="text">
            <button class="button">...</button>
        </div>
        <div class="form-group">
            <label for="icono">Ícono</label>
            <input class="form-control" readonly="" id="icono" name="icono" value="${valor.icono}" type="text">
        </div>
        <input class="btn btn-primary" type="submit" value="Guardar">
        <a class="btn btn-danger" href="${pageContext.servletContext.contextPath}${servlet}">Cancelar</a>
    </form>
</div>
<%@include file="../_down.jsp" %>
