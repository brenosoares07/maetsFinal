package com.mycompany.maets.controlador;

import com.mycompany.maets.modelo.dao.FuncionarioDao;
import com.mycompany.maets.modelo.entidade.Funcionario;
import com.mycompany.maets.servico.WebConstante;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH + "/FuncionarioControlador")
public class FuncionarioControlador extends HttpServlet {

    private Funcionario objFuncionario = new Funcionario();
    private FuncionarioDao objFuncionarioDao = new FuncionarioDao();

    private String cod = "", nome = "", cargo = "", salario = "", dataAdmissao = "", opcao = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) opcao = "cadastrar";

            cod = request.getParameter("cod");
            nome = request.getParameter("nome");
            cargo = request.getParameter("cargo");
            salario = request.getParameter("salario");
            dataAdmissao = request.getParameter("dataAdmissao");

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
        objFuncionario.setNome(nome);
        objFuncionario.setCargo(cargo);
        objFuncionario.setSalario(salario.isEmpty() ? 0.0 : Double.valueOf(salario));
        objFuncionario.setDataAdmissao(dataAdmissao.isEmpty() ? null : LocalDate.parse(dataAdmissao));
        objFuncionarioDao.salvar(objFuncionario);
        request.setAttribute("mensagem", "Funcionario cadastrado com sucesso!");
        encaminharPagina(request, response);
    }

    protected void encaminharPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Funcionario> listaFuncionario = objFuncionarioDao.buscarTodosFuncionarios();
        request.setAttribute("listaFuncionario", listaFuncionario);
        request.getRequestDispatcher("/CadastroFuncionario.jsp").forward(request, response);
    }

    protected void enviarAlterar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAtributosFormulario(request);
        request.setAttribute("opcao", "executarAlterar");
        request.setAttribute("mensagem", "Edite os dados e clique em salvar!");
        encaminharPagina(request, response);
    }

    protected void executarAlterar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objFuncionario.setCod(Integer.valueOf(cod));
        objFuncionario.setNome(nome);
        objFuncionario.setCargo(cargo);
        objFuncionario.setSalario(salario.isEmpty() ? 0.0 : Double.valueOf(salario));
        objFuncionario.setDataAdmissao(dataAdmissao.isEmpty() ? null : LocalDate.parse(dataAdmissao));
        objFuncionarioDao.alterar(objFuncionario);
        encaminharPagina(request, response);
    }

    protected void enviarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAtributosFormulario(request);
        request.setAttribute("opcao", "executarExcluir");
        request.setAttribute("mensagem", "Clique em salvar para excluir!");
        encaminharPagina(request, response);
    }

    protected void executarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objFuncionario.setCod(Integer.valueOf(cod));
        objFuncionarioDao.excluir(objFuncionario);
        request.setAttribute("mensagem", "Funcionario excluido!");
        encaminharPagina(request, response);
    }

    protected void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cod", 0);
        request.setAttribute("nome", "");
        request.setAttribute("cargo", "");
        request.setAttribute("salario", "");
        request.setAttribute("dataAdmissao", "");
        encaminharPagina(request, response);
    }

    private void setAtributosFormulario(HttpServletRequest request) {
        request.setAttribute("cod", cod);
        request.setAttribute("nome", nome);
        request.setAttribute("cargo", cargo);
        request.setAttribute("salario", salario);
        request.setAttribute("dataAdmissao", dataAdmissao);
    }
}
