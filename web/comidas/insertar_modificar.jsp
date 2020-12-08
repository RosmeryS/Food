<%@include file="../_top.jsp" %>
<link rel="stylesheet" href="CSS/form.css">
<%@include file="../_nav.jsp" %>
<div class="padre p-5">
    <h1>${op} ${servlet_name}</h1>
    <form class="mw-100 w-50" action="${pageContext.servletContext.contextPath}${servlet}" method="POST">
        <div class="form-group w-25">
            <label for="idcomida">ID</label>
            <input class="form-control" readonly id="idcomida" name="idcomida" value="${valor.idcomida}" type="text">
        </div>
        <div class="form-group">
            <label for="menu">Menú</label>
            <input placeholder="Crepas de Fresa" class="form-control" id="menu" name="menu" value="${valor.menu}" type="text">
        </div>
        <div class="form-group">
            <label for="precio">Precio</label>
            <input placeholder="0.00" class="form-control" id="precio" name="precio" value="${valor.precio}" type="text">
        </div>
        <div class="form-group">
            <label for="descripcion">Descripción</label>
            <textarea placeholder="Descripcion del Menu que desea ordenar" class="form-control" id="descripcion" name="descripcion">${valor.descripcion}</textarea>
        </div>
        <div class="form-group">
            <label for="imagen_url">URL</label>
            <input class="form-control" id="imagen_url" name="imagen_url" value="${valor.imagen_url}" type="text">
        </div>
        <input class="btn btn-primary" type="submit" value="Guardar">
        <a class="btn btn-danger" href="${pageContext.servletContext.contextPath}${servlet}">Cancelar</a>
    </form>
</div>
<%@include file="../_down.jsp" %>
