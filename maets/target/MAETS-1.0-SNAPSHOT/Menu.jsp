<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}/index.jsp">HOME</a></li>
        <li><a href="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador?opcao=cancelar">CLIENTE</a></li>
        <li><a href="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador?opcao=cancelar">FUNCIONARIO</a></li>
        <li><a href="${pageContext.request.contextPath}${URL_BASE}/TipoControlador?opcao=cancelar">TIPO</a></li>
        <li><a href="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador?opcao=cancelar">PRODUTO</a></li>
        <li><a href="${pageContext.request.contextPath}${URL_BASE}/PagamentoControlador?opcao=cancelar">PAGAMENTO</a></li>
        <li><a href="${pageContext.request.contextPath}${URL_BASE}/ServicosControlador?opcao=cancelar">SERVICOS</a></li>
        <li><a href="${pageContext.request.contextPath}${URL_BASE}/EstoqueControlador?opcao=cancelar">ESTOQUE</a></li>
        <li><a href="${pageContext.request.contextPath}${URL_BASE}/VendaControlador?opcao=listar">VENDA</a></li>
        <li><a href="${pageContext.request.contextPath}${URL_BASE}/AuditoriaControlador">AUDITORIA</a></li>
    </ul>
</nav>
