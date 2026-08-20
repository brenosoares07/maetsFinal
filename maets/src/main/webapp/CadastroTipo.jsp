<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head><meta charset="UTF-8"><title>Cadastro de Tipos</title></head>
    <%@include file="Menu.jsp"%>
    <body>
        <h1>Tipo</h1>
        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/TipoControlador">
            <input type="hidden" name="opcao" value="${opcao}"/>
            <input type="hidden" name="cod" value="${cod}"/>
            <p><label>Nome:</label> <input type="text" name="nome" value="${nome}" required/></p>
            <input type="submit" value="Salvar"/>
        </form>
        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/TipoControlador">
            <input type="submit" value="Cancelar"/>
            <input type="hidden" name="opcao" value="cancelar"/>
        </form>
        <h2>${mensagem}</h2>
        <c:if test="${not empty listaTipo}">
            <table border="1">
                <tr><th>COD</th><th>NOME</th><th>ALTERAR</th><th>EXCLUIR</th></tr>
                <c:forEach var="t" items="${listaTipo}">
                    <tr>
                        <td>${t.cod}</td><td>${t.nome}</td>
                        <td>
                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/TipoControlador">
                                <input type="hidden" name="opcao" value="enviarAlterar"/>
                                <input type="hidden" name="cod" value="${t.cod}"/>
                                <input type="hidden" name="nome" value="${t.nome}"/>
                                <button type="submit">Alterar</button>
                            </form>
                        </td>
                        <td>
                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/TipoControlador">
                                <input type="hidden" name="opcao" value="enviarExcluir"/>
                                <input type="hidden" name="cod" value="${t.cod}"/>
                                <button type="submit">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
