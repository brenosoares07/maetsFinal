<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head><meta charset="UTF-8"><title>Vendas</title></head>
    <%@include file="Menu.jsp"%>
    <body>
        <h1>Nova Venda</h1>
        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/VendaControlador">
            <input type="hidden" name="opcao" value="cadastrar"/>
            <p><label>Cliente:</label>
                <select name="codCliente">
                    <c:forEach var="c" items="${listaCliente}">
                        <option value="${c.cod}">${c.nome}</option>
                    </c:forEach>
                </select>
            </p>
            <p><label>Funcionario:</label>
                <select name="codFuncionario">
                    <c:forEach var="f" items="${listaFuncionario}">
                        <option value="${f.cod}">${f.nome}</option>
                    </c:forEach>
                </select>
            </p>
            <p><label>Pagamento:</label>
                <select name="codPagamento">
                    <c:forEach var="p" items="${listaPagamento}">
                        <option value="${p.cod}">${p.metodo}</option>
                    </c:forEach>
                </select>
            </p>
            <input type="submit" value="Iniciar Venda"/>
        </form>

        <h2>Vendas cadastradas</h2>
        <c:if test="${not empty listaVenda}">
            <table border="1">
                <tr><th>COD</th><th>DATA</th><th>CLIENTE</th><th>FUNCIONARIO</th><th>PAGAMENTO</th><th>TOTAL</th><th>GERENCIAR</th><th>EXCLUIR</th></tr>
                <c:forEach var="v" items="${listaVenda}">
                    <tr>
                        <td>${v.cod}</td>
                        <td>${v.dataVenda}</td>
                        <td>${v.cliente.nome}</td>
                        <td>${v.funcionario.nome}</td>
                        <td>${v.pagamento.metodo}</td>
                        <td>R$${v.valorTotal}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}${URL_BASE}/VendaControlador?opcao=gerenciar&cod=${v.cod}">
                                <button type="button">Gerenciar</button>
                            </a>
                        </td>
                        <td>
                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/VendaControlador">
                                <input type="hidden" name="opcao" value="excluirVenda"/>
                                <input type="hidden" name="cod" value="${v.cod}"/>
                                <button type="submit">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
