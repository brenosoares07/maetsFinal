<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head><meta charset="UTF-8"><title>Cadastro de Clientes</title></head>
    <%@include file="Menu.jsp"%>
    <body>
        <h1>Cliente</h1>
        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador">
            <input type="hidden" name="opcao" value="${opcao}"/>
            <input type="hidden" name="cod" value="${cod}"/>
            <p><label>Nome:</label> <input type="text" name="nome" value="${nome}" required/></p>
            <p><label>CPF:</label> <input type="text" name="cpf" value="${cpf}"/></p>
            <p><label>E-mail:</label> <input type="email" name="email" value="${email}"/></p>
            <p><label>Telefone:</label> <input type="text" name="telefone" value="${telefone}"/></p>
            <input type="submit" value="Salvar"/>
        </form>
        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador">
            <input type="submit" value="Cancelar"/>
            <input type="hidden" name="opcao" value="cancelar"/>
        </form>
        <h2>${mensagem}</h2>
        <c:if test="${not empty listaCliente}">
            <table border="1">
                <tr><th>COD</th><th>NOME</th><th>CPF</th><th>EMAIL</th><th>TELEFONE</th><th>ALTERAR</th><th>EXCLUIR</th></tr>
                <c:forEach var="c" items="${listaCliente}">
                    <tr>
                        <td>${c.cod}</td><td>${c.nome}</td><td>${c.cpf}</td><td>${c.email}</td><td>${c.telefone}</td>
                        <td>
                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador">
                                <input type="hidden" name="opcao" value="enviarAlterar"/>
                                <input type="hidden" name="cod" value="${c.cod}"/>
                                <input type="hidden" name="nome" value="${c.nome}"/>
                                <input type="hidden" name="cpf" value="${c.cpf}"/>
                                <input type="hidden" name="email" value="${c.email}"/>
                                <input type="hidden" name="telefone" value="${c.telefone}"/>
                                <button type="submit">Alterar</button>
                            </form>
                        </td>
                        <td>
                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador">
                                <input type="hidden" name="opcao" value="enviarExcluir"/>
                                <input type="hidden" name="cod" value="${c.cod}"/>
                                <button type="submit">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
