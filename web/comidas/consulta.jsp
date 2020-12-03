<%@include file="../_top.jsp" %>
<link rel="stylesheet" href="CSS/table.css">
<%@include file="../_nav.jsp" %>
<div class="padre">
    <div class="tabla container-fluid p-5">
        <%@include file="../_message.jsp" %>
        <h1 class="mb-1">Lista de ${servlet_name}</h1>
        <a class="btn btn-primary mb-3" href="${pageContext.servletContext.contextPath}${servlet}?accion=insertar">Insertar</a>
        ${tabla}
    </div>
</div>
<%@include file="../_down.jsp" %>