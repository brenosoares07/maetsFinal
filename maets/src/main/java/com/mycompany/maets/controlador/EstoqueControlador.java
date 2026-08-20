package com.mycompany.maets.controlador;

import com.mycompany.maets.modelo.dao.EstoqueDao;
import com.mycompany.maets.modelo.dao.ProdutoDao;
import com.mycompany.maets.modelo.entidade.Estoque;
import com.mycompany.maets.modelo.entidade.Produto;
import com.mycompany.maets.servico.WebConstante;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH + "/EstoqueControlador")
public class EstoqueControlador extends HttpServlet {

    private Estoque objEstoque = new Estoque();
    private EstoqueDao objEstoqueDao = new EstoqueDao();
    private ProdutoDao objProdutoDao = new ProdutoDao();

    private String cod = "", quantidadeAtual = "", codProduto = "", opcao = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) opcao = "cadastrar";

            cod = request.getParameter("cod");
            quantidadeAtual = request.getParameter("quantidadeAtual");
            codProduto = request.getParameter("codProduto");

            switch (opcao) {
                case "cadastrar": cadastrar(request, response); break;
                case "enviarAlterar": enviarAlterar(request, response); break;
                case "executarAlterar": executarAlterar(request, response); break;
                case "enviarExcluir": enviarExcluir(request, response); break;
                case "executarExcluir": executarExcluir(request, response); break;
                case "cancelar": cancelar(request, response); break;
                default: throw new IllegalArgumentException("Opcao Invalida: " + opcao);
            }
        } catch (NumberFormatException ex) {
            response.getWriter().println("Erro: parametro invalido. " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            response.getWriter().println("Erro: parametro ausente. " + ex.getMessage());
        }
    }

    protected void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objEstoque.setQuantidadeAtual(Integer.valueOf(quantidadeAtual));
        objEstoque.getProduto().setCod(Integer.valueOf(codProduto));
        objEstoqueDao.salvar(objEstoque);
        request.setAttribute("mensagem", "Estoque cadastrado com sucesso!");
        encaminharPagina(request, response);
    }

    protected void encaminharPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Estoque> listaEstoque = objEstoqueDao.buscarTodosEstoques();
        request.setAttribute("listaEstoque", listaEstoque);

        List<Produto> listaProduto = objProdutoDao.buscarTodosProdutos();
        request.setAttribute("listaProduto", listaProduto);

        request.getRequestDispatcher("/CadastroEstoque.jsp").forward(request, response);
    }

    protected void enviarAlterar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAtributosFormulario(request);
        request.setAttribute("opcao", "executarAlterar");
        request.setAttribute("mensagem", "Edite os dados e clique em salvar!");
        encaminharPagina(request, response);
    }

    protected void executarAlterar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objEstoque.setCod(Integer.valueOf(cod));
        objEstoque.setQuantidadeAtual(Integer.valueOf(quantidadeAtual));
        objEstoque.getProduto().setCod(Integer.valueOf(codProduto));
        objEstoqueDao.alterar(objEstoque);
        encaminharPagina(request, response);
    }

    protected void enviarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAtributosFormulario(request);
        request.setAttribute("opcao", "executarExcluir");
        request.setAttribute("mensagem", "Clique em salvar para excluir!");
        encaminharPagina(request, response);
    }

    protected void executarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objEstoque.setCod(Integer.valueOf(cod));
        objEstoqueDao.excluir(objEstoque);
        request.setAttribute("mensagem", "Estoque excluido!");
        encaminharPagina(request, response);
    }

    protected void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cod", 0);
        request.setAttribute("quantidadeAtual", "");
        request.setAttribute("codProduto", 0);
        encaminharPagina(request, response);
    }

    private void setAtributosFormulario(HttpServletRequest request) {
        request.setAttribute("cod", cod);
        request.setAttribute("quantidadeAtual", quantidadeAtual);
        request.setAttribute("codProduto", codProduto);
    }
}
