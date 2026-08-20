package com.mycompany.maets.controlador;

import com.mycompany.maets.modelo.dao.ClienteDao;
import com.mycompany.maets.modelo.dao.FuncionarioDao;
import com.mycompany.maets.modelo.dao.ItensVendaDao;
import com.mycompany.maets.modelo.dao.PagamentoDao;
import com.mycompany.maets.modelo.dao.ProdutoDao;
import com.mycompany.maets.modelo.dao.ServicosDao;
import com.mycompany.maets.modelo.dao.ServicosVendaDao;
import com.mycompany.maets.modelo.dao.VendaDao;
import com.mycompany.maets.modelo.entidade.Cliente;
import com.mycompany.maets.modelo.entidade.Funcionario;
import com.mycompany.maets.modelo.entidade.ItensVenda;
import com.mycompany.maets.modelo.entidade.Pagamento;
import com.mycompany.maets.modelo.entidade.Produto;
import com.mycompany.maets.modelo.entidade.Servicos;
import com.mycompany.maets.modelo.entidade.ServicosVenda;
import com.mycompany.maets.modelo.entidade.Venda;
import com.mycompany.maets.servico.WebConstante;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH + "/VendaControlador")
public class VendaControlador extends HttpServlet {

    private VendaDao objVendaDao = new VendaDao();
    private ClienteDao objClienteDao = new ClienteDao();
    private FuncionarioDao objFuncionarioDao = new FuncionarioDao();
    private PagamentoDao objPagamentoDao = new PagamentoDao();
    private ProdutoDao objProdutoDao = new ProdutoDao();
    private ServicosDao objServicosDao = new ServicosDao();
    private ItensVendaDao objItensVendaDao = new ItensVendaDao();
    private ServicosVendaDao objServicosVendaDao = new ServicosVendaDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String opcao = request.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) opcao = "listar";

            switch (opcao) {
                case "listar":
                    listar(request, response);
                    break;
                case "cadastrar":
                    cadastrar(request, response);
                    break;
                case "gerenciar":
                    gerenciar(request, response);
                    break;
                case "adicionarItem":
                    adicionarItem(request, response);
                    break;
                case "removerItem":
                    removerItem(request, response);
                    break;
                case "adicionarServico":
                    adicionarServico(request, response);
                    break;
                case "removerServico":
                    removerServico(request, response);
                    break;
                case "excluirVenda":
                    excluirVenda(request, response);
                    break;
                default:
                    throw new IllegalArgumentException("Opcao Invalida: " + opcao);
            }
        } catch (NumberFormatException ex) {
            response.getWriter().println("Erro: parametro invalido. " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            response.getWriter().println("Erro: parametro ausente. " + ex.getMessage());
        }
    }

    protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Venda> listaVenda = objVendaDao.buscarTodasVendas();
        request.setAttribute("listaVenda", listaVenda);

        List<Cliente> listaCliente = objClienteDao.buscarTodosClientes();
        request.setAttribute("listaCliente", listaCliente);

        List<Funcionario> listaFuncionario = objFuncionarioDao.buscarTodosFuncionarios();
        request.setAttribute("listaFuncionario", listaFuncionario);

        List<Pagamento> listaPagamento = objPagamentoDao.buscarTodosPagamentos();
        request.setAttribute("listaPagamento", listaPagamento);

        request.getRequestDispatcher("/Venda.jsp").forward(request, response);
    }

    protected void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Venda v = new Venda();
        v.getCliente().setCod(Integer.valueOf(request.getParameter("codCliente")));
        v.getFuncionario().setCod(Integer.valueOf(request.getParameter("codFuncionario")));
        v.getPagamento().setCod(Integer.valueOf(request.getParameter("codPagamento")));
        int codVenda = objVendaDao.criar(v);

        response.sendRedirect(request.getContextPath() + request.getServletContext().getAttribute("URL_BASE")
                + "/VendaControlador?opcao=gerenciar&cod=" + codVenda);
    }

    protected void gerenciar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cod = Integer.valueOf(request.getParameter("cod"));
        Venda venda = objVendaDao.buscarVendaPorId(cod);
        request.setAttribute("venda", venda);

        List<ItensVenda> itens = objItensVendaDao.buscarPorVenda(cod);
        request.setAttribute("itens", itens);

        List<ServicosVenda> servicos = objServicosVendaDao.buscarPorVenda(cod);
        request.setAttribute("servicosVenda", servicos);

        List<Produto> listaProduto = objProdutoDao.buscarTodosProdutos();
        request.setAttribute("listaProduto", listaProduto);

        List<Servicos> listaServicos = objServicosDao.buscarTodosServicos();
        request.setAttribute("listaServicos", listaServicos);

        request.getRequestDispatcher("/GerenciarVenda.jsp").forward(request, response);
    }

    protected void adicionarItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codVenda = Integer.valueOf(request.getParameter("codVenda"));
        int codProduto = Integer.valueOf(request.getParameter("codProduto"));
        int quantidade = Integer.valueOf(request.getParameter("quantidade"));
        objItensVendaDao.adicionar(codVenda, codProduto, quantidade);
        response.sendRedirect(request.getContextPath() + request.getServletContext().getAttribute("URL_BASE")
                + "/VendaControlador?opcao=gerenciar&cod=" + codVenda);
    }

    protected void removerItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codItem = Integer.valueOf(request.getParameter("codItem"));
        int codVenda = Integer.valueOf(request.getParameter("codVenda"));
        objItensVendaDao.remover(codItem, codVenda);
        response.sendRedirect(request.getContextPath() + request.getServletContext().getAttribute("URL_BASE")
                + "/VendaControlador?opcao=gerenciar&cod=" + codVenda);
    }

    protected void adicionarServico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codVenda = Integer.valueOf(request.getParameter("codVenda"));
        int codServico = Integer.valueOf(request.getParameter("codServico"));
        int quantidade = Integer.valueOf(request.getParameter("quantidade"));
        objServicosVendaDao.adicionar(codVenda, codServico, quantidade);
        response.sendRedirect(request.getContextPath() + request.getServletContext().getAttribute("URL_BASE")
                + "/VendaControlador?opcao=gerenciar&cod=" + codVenda);
    }

    protected void removerServico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codServicoVenda = Integer.valueOf(request.getParameter("codServicoVenda"));
        int codVenda = Integer.valueOf(request.getParameter("codVenda"));
        objServicosVendaDao.remover(codServicoVenda, codVenda);
        response.sendRedirect(request.getContextPath() + request.getServletContext().getAttribute("URL_BASE")
                + "/VendaControlador?opcao=gerenciar&cod=" + codVenda);
    }

    protected void excluirVenda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cod = Integer.valueOf(request.getParameter("cod"));
        objVendaDao.excluir(cod);
        listar(request, response);
    }
}
