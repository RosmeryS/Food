<c:if test="${resultado != null}">
    <c:if test="${resultado == 0}">
        <div class="alert alert-danger" role="alert">
            No se pudo realizar la acción
        </div>
    </c:if>
    <c:if test="${resultado == 1}">
        <div class="alert alert-success" role="alert">
            Acción realizada correctamente
        </div>
    </c:if>
</c:if>