package com.mycompany.maets.controlador;

import com.mycompany.maets.modelo.dao.TipoDao;
import com.mycompany.maets.modelo.entidade.Tipo;
import com.mycompany.maets.servico.WebConstante;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH + "/TipoControlador")
public class TipoControlador extends HttpServlet {

    private Tipo objTipo = new Tipo();
    private TipoDao objTipoDao = new TipoDao();

    private String cod = "", nome = "", opcao = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) opcao = "cadastrar";

            cod = request.getParameter("cod");
            nome = request.getParameter("nome");

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
        objTipo.setNome(nome);
        objTipoDao.salvar(objTipo);
        request.setAttribute("mensagem", "Tipo cadastrado com sucesso!");
        encaminharPagina(request, response);
    }

    protected void encaminharPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Tipo> listaTipo = objTipoDao.buscarTodosTipos();
        request.setAttribute("listaTipo", listaTipo);
        request.getRequestDispatcher("/CadastroTipo.jsp").forward(request, response);
    }

    protected void enviarAlterar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAtributosFormulario(request);
        request.setAttribute("opcao", "executarAlterar");
        request.setAttribute("mensagem", "Edite os dados e clique em salvar!");
        encaminharPagina(request, response);
    }

    protected void executarAlterar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objTipo.setCod(Integer.valueOf(cod));
        objTipo.setNome(nome);
        objTipoDao.alterar(objTipo);
        encaminharPagina(request, response);
    }

    protected void enviarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAtributosFormulario(request);
        request.setAttribute("opcao", "executarExcluir");
        request.setAttribute("mensagem", "Clique em salvar para excluir!");
        encaminharPagina(request, response);
    }

    protected void executarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objTipo.setCod(Integer.valueOf(cod));
        objTipoDao.excluir(objTipo);
        request.setAttribute("mensagem", "Tipo excluido!");
        encaminharPagina(request, response);
    }

    protected void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cod", 0);
        request.setAttribute("nome", "");
        encaminharPagina(request, response);
    }

    private void setAtributosFormulario(HttpServletRequest request) {
        request.setAttribute("cod", cod);
        request.setAttribute("nome", nome);
    }
}
