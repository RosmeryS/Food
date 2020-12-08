<%@include file="../_top.jsp" %>
<link rel="stylesheet" href="CSS/table.css">
<%@include file="../_nav.jsp" %>
<div class="padre">
    <div class="tabla container-fluid p-5">
        <%@include file="../_message.jsp" %>
        <h1 class="mb-1">Lista de ${servlet_name}</h1>
        <button  class="btn btn-primary">Guardar cambios</button>
        <nav class="navbar navbar-light bg-light mb-2">
            <form id="form" accept-charset="UTF-8" class="form-inline" action="${pageContext.servletContext.contextPath}${servlet}" method="GET">
                <select class="form-control mr-sm-2" name="idrol" id="idrol">
                    <option value="0">Opciones...</option>
                    <option value="1" ${idrol == "1" ? "selected" : ""}>ADMIN</option>
                    <option value="2" ${idrol == "2" ? "selected" : ""}>USER</option>
                </select>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar...</button>
                <button class="btn btn-outline-secondary my-2 my-sm-0" type="reset" onclick="limpiarFiltros();">Limpiar filtros</button>
            </form>
        </nav>
                <table class="table">
            <thead>
                <tr>
                    <td></td>
                    <td>Menú</td>
                    <td>URL</td>
                    <td>Menú padre</td>
                </tr>
            </thead>
            <tbody>
                <c:if test="${PermisosList == null}">
                    <tr><td>No ha seleccionado un rol</td></tr>
                </c:if>
                <c:forEach var="i" items="${PermisosList}">
                    <tr>
                        <td><input id="${i.idmenu}" class="check" type="checkbox" ${i.ischecked == 1 ? "checked" : ""}></td>
                        <td>${i.menu}</td>
                        <td>${i.url}</td>
                        <td>${i.padre}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
                    <script>
                        const form = document.getElementById("form")
                        const idrol = document.getElementById("idrol")
                        idrol.addEventListener("change", function(){
                            form.submit();
                        })
                    </script>
<%@include file="../_down.jsp" %>