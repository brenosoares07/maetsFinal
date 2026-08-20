package com.mycompany.maets.controlador;

import com.mycompany.maets.modelo.dao.ProdutoDao;
import com.mycompany.maets.modelo.dao.TipoDao;
import com.mycompany.maets.modelo.entidade.Produto;
import com.mycompany.maets.modelo.entidade.Tipo;
import com.mycompany.maets.servico.WebConstante;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH + "/ProdutoControlador")
public class ProdutoControlador extends HttpServlet {

    private Produto objProduto = new Produto();
    private ProdutoDao objProdutoDao = new ProdutoDao();
    private TipoDao objTipoDao = new TipoDao();

    private String cod = "", nome = "", precoVenda = "", fabricante = "", genero = "", codTipo = "", opcao = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) opcao = "cadastrar";

            cod = request.getParameter("cod");
            nome = request.getParameter("nome");
            precoVenda = request.getParameter("precoVenda");
            fabricante = request.getParameter("fabricante");
            genero = request.getParameter("genero");
            codTipo = request.getParameter("codTipo");

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
        objProduto.setNome(nome);
        objProduto.setPrecoVenda(Double.valueOf(precoVenda));
        objProduto.setFabricante(fabricante);
        objProduto.setGenero(genero);
        objProduto.getTipo().setCod(Integer.valueOf(codTipo));
        objProdutoDao.salvar(objProduto);
        request.setAttribute("mensagem", "Produto cadastrado com sucesso!");
        encaminharPagina(request, response);
    }

    protected void encaminharPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Produto> listaProduto = objProdutoDao.buscarTodosProdutos();
        request.setAttribute("listaProduto", listaProduto);

        List<Tipo> listaTipo = objTipoDao.buscarTodosTipos();
        request.setAttribute("listaTipo", listaTipo);

        request.getRequestDispatcher("/CadastroProduto.jsp").forward(request, response);
    }

    protected void enviarAlterar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAtributosFormulario(request);
        request.setAttribute("opcao", "executarAlterar");
        request.setAttribute("mensagem", "Edite os dados e clique em salvar!");
        encaminharPagina(request, response);
    }

    protected void executarAlterar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objProduto.setCod(Integer.valueOf(cod));
        objProduto.setNome(nome);
        objProduto.setPrecoVenda(Double.valueOf(precoVenda));
        objProduto.setFabricante(fabricante);
        objProduto.setGenero(genero);
        objProduto.getTipo().setCod(Integer.valueOf(codTipo));
        objProdutoDao.alterar(objProduto);
        encaminharPagina(request, response);
    }

    protected void enviarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAtributosFormulario(request);
        request.setAttribute("opcao", "executarExcluir");
        request.setAttribute("mensagem", "Clique em salvar para excluir!");
        encaminharPagina(request, response);
    }

    protected void executarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objProduto.setCod(Integer.valueOf(cod));
        objProdutoDao.excluir(objProduto);
        request.setAttribute("mensagem", "Produto excluido!");
        encaminharPagina(request, response);
    }

    protected void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cod", 0);
        request.setAttribute("nome", "");
        request.setAttribute("precoVenda", "");
        request.setAttribute("fabricante", "");
        request.setAttribute("genero", "");
        request.setAttribute("codTipo", 0);
        encaminharPagina(request, response);
    }

    private void setAtributosFormulario(HttpServletRequest request) {
        request.setAttribute("cod", cod);
        request.setAttribute("nome", nome);
        request.setAttribute("precoVenda", precoVenda);
        request.setAttribute("fabricante", fabricante);
        request.setAttribute("genero", genero);
        request.setAttribute("codTipo", codTipo);
    }
}
