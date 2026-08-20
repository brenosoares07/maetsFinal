<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head><meta charset="UTF-8"><title>Gerenciar Venda</title></head>
    <%@include file="Menu.jsp"%>
    <body>
        <h1>Venda #${venda.cod}</h1>
        <p>Cliente: ${venda.cliente.nome} | Funcionario: ${venda.funcionario.nome} | Pagamento: ${venda.pagamento.metodo}</p>
        <p><strong>Total: R$${venda.valorTotal}</strong></p>

        <h2>Adicionar Produto</h2>
        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/VendaControlador">
            <input type="hidden" name="opcao" value="adicionarItem"/>
            <input type="hidden" name="codVenda" value="${venda.cod}"/>
            <select name="codProduto">
                <c:forEach var="p" items="${listaProduto}">
                    <option value="${p.cod}">${p.nome} - R$${p.precoVenda}</option>
                </c:forEach>
            </select>
            <input type="number" name="quantidade" value="1" min="1" required/>
            <input type="submit" value="Adicionar Item"/>
        </form>

        <table border="1">
            <tr><th>PRODUTO</th><th>QUANTIDADE</th><th>VALOR UNIT.</th><th>TOTAL</th><th>REMOVER</th></tr>
            <c:forEach var="i" items="${itens}">
                <tr>
                    <td>${i.produto.nome}</td>
                    <td>${i.quantidade}</td>
                    <td>R$${i.valorUnitario}</td>
                    <td>R$${i.total}</td>
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/VendaControlador">
                            <input type="hidden" name="opcao" value="removerItem"/>
                            <input type="hidden" name="codItem" value="${i.cod}"/>
                            <input type="hidden" name="codVenda" value="${venda.cod}"/>
                            <button type="submit">Remover</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <h2>Adicionar Servico</h2>
        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/VendaControlador">
            <input type="hidden" name="opcao" value="adicionarServico"/>
            <input type="hidden" name="codVenda" value="${venda.cod}"/>
            <select name="codServico">
                <c:forEach var="s" items="${listaServicos}">
                    <option value="${s.cod}">${s.descricao} - R$${s.valor}</option>
                </c:forEach>
            </select>
            <input type="number" name="quantidade" value="1" min="1" required/>
            <input type="submit" value="Adicionar Servico"/>
        </form>

        <table border="1">
            <tr><th>SERVICO</th><th>QUANTIDADE</th><th>VALOR UNIT.</th><th>SUBTOTAL</th><th>REMOVER</th></tr>
            <c:forEach var="s" items="${servicosVenda}">
                <tr>
                    <td>${s.servico.descricao}</td>
                    <td>${s.quantidade}</td>
                    <td>R$${s.valorUnitario}</td>
                    <td>R$${s.subtotal}</td>
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/VendaControlador">
                            <input type="hidden" name="opcao" value="removerServico"/>
                            <input type="hidden" name="codServicoVenda" value="${s.cod}"/>
                            <input type="hidden" name="codVenda" value="${venda.cod}"/>
                            <button type="submit">Remover</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <p>
            <a href="${pageContext.request.contextPath}${URL_BASE}/VendaControlador?opcao=listar">
                <button type="button">Voltar para Vendas</button>
            </a>
        </p>
    </body>
</html>
