<c:if test="${resultado != null}">
    <c:if test="${resultado == 0}">
        <div class="alert alert-danger" role="alert">
            No se pudo realizar la acci�n
        </div>
    </c:if>
    <c:if test="${resultado == 1}">
        <div class="alert alert-success" role="alert">
            Acci�n realizada correctamente
        </div>
    </c:if>
</c:if>