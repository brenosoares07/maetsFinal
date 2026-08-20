<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head><meta charset="UTF-8"><title>Auditoria</title></head>
    <%@include file="Menu.jsp"%>
    <body>
        <h1>Auditoria</h1>
        <p>Registro somente leitura das alteracoes no sistema.</p>
        <c:if test="${not empty listaAuditoria}">
            <table border="1">
                <tr><th>COD</th><th>TABELA</th><th>USUARIO</th><th>DATA/HORA</th><th>ACAO</th></tr>
                <c:forEach var="a" items="${listaAuditoria}">
                    <tr>
                        <td>${a.cod}</td><td>${a.tabelaAlterada}</td><td>${a.usuario}</td><td>${a.dataHora}</td><td>${a.acaoRealizada}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${empty listaAuditoria}">
            <p>Nenhum registro de auditoria encontrado.</p>
        </c:if>
    </body>
</html>
