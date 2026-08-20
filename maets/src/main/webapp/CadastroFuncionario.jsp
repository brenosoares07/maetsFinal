<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head><meta charset="UTF-8"><title>Cadastro de Funcionarios</title></head>
    <%@include file="Menu.jsp"%>
    <body>
        <h1>Funcionario</h1>
        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador">
            <input type="hidden" name="opcao" value="${opcao}"/>
            <input type="hidden" name="cod" value="${cod}"/>
            <p><label>Nome:</label> <input type="text" name="nome" value="${nome}" required/></p>
            <p><label>Cargo:</label> <input type="text" name="cargo" value="${cargo}"/></p>
            <p><label>Salario:</label> <input type="number" step="0.01" name="salario" value="${salario}"/></p>
            <p><label>Data Admissao:</label> <input type="date" name="dataAdmissao" value="${dataAdmissao}"/></p>
            <input type="submit" value="Salvar"/>
        </form>
        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador">
            <input type="submit" value="Cancelar"/>
            <input type="hidden" name="opcao" value="cancelar"/>
        </form>
        <h2>${mensagem}</h2>
        <c:if test="${not empty listaFuncionario}">
            <table border="1">
                <tr><th>COD</th><th>NOME</th><th>CARGO</th><th>SALARIO</th><th>ADMISSAO</th><th>ALTERAR</th><th>EXCLUIR</th></tr>
                <c:forEach var="f" items="${listaFuncionario}">
                    <tr>
                        <td>${f.cod}</td><td>${f.nome}</td><td>${f.cargo}</td><td>R$${f.salario}</td><td>${f.dataAdmissao}</td>
                        <td>
                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador">
                                <input type="hidden" name="opcao" value="enviarAlterar"/>
                                <input type="hidden" name="cod" value="${f.cod}"/>
                                <input type="hidden" name="nome" value="${f.nome}"/>
                                <input type="hidden" name="cargo" value="${f.cargo}"/>
                                <input type="hidden" name="salario" value="${f.salario}"/>
                                <input type="hidden" name="dataAdmissao" value="${f.dataAdmissao}"/>
                                <button type="submit">Alterar</button>
                            </form>
                        </td>
                        <td>
                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador">
                                <input type="hidden" name="opcao" value="enviarExcluir"/>
                                <input type="hidden" name="cod" value="${f.cod}"/>
                                <button type="submit">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
