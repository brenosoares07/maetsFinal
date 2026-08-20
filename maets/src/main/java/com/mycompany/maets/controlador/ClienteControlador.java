package com.mycompany.maets.controlador;

import com.mycompany.maets.modelo.dao.ClienteDao;
import com.mycompany.maets.modelo.entidade.Cliente;
import com.mycompany.maets.servico.WebConstante;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH + "/ClienteControlador")
public class ClienteControlador extends HttpServlet {

    private Cliente objCliente = new Cliente();
    private ClienteDao objClienteDao = new ClienteDao();

    private String cod = "", nome = "", cpf = "", email = "", telefone = "", opcao = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) opcao = "cadastrar";

            cod = request.getParameter("cod");
            nome = request.getParameter("nome");
            cpf = request.getParameter("cpf");
            email = request.getParameter("email");
            telefone = request.getParameter("telefone");

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
        objCliente.setNome(nome);
        objCliente.setCpf(cpf);
        objCliente.setEmail(email);
        objCliente.setTelefone(telefone);
        objClienteDao.salvar(objCliente);
        request.setAttribute("mensagem", "Cliente cadastrado com sucesso!");
        encaminharPagina(request, response);
    }

    protected void encaminharPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> listaCliente = objClienteDao.buscarTodosClientes();
        request.setAttribute("listaCliente", listaCliente);
        request.getRequestDispatcher("/CadastroCliente.jsp").forward(request, response);
    }

    protected void enviarAlterar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAtributosFormulario(request);
        request.setAttribute("opcao", "executarAlterar");
        request.setAttribute("mensagem", "Edite os dados e clique em salvar!");
        encaminharPagina(request, response);
    }

    protected void executarAlterar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objCliente.setCod(Integer.valueOf(cod));
        objCliente.setNome(nome);
        objCliente.setCpf(cpf);
        objCliente.setEmail(email);
        objCliente.setTelefone(telefone);
        objClienteDao.alterar(objCliente);
        encaminharPagina(request, response);
    }

    protected void enviarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAtributosFormulario(request);
        request.setAttribute("opcao", "executarExcluir");
        request.setAttribute("mensagem", "Clique em salvar para excluir!");
        encaminharPagina(request, response);
    }

    protected void executarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objCliente.setCod(Integer.valueOf(cod));
        objClienteDao.excluir(objCliente);
        request.setAttribute("mensagem", "Cliente excluido!");
        encaminharPagina(request, response);
    }

    protected void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cod", 0);
        request.setAttribute("nome", "");
        request.setAttribute("cpf", "");
        request.setAttribute("email", "");
        request.setAttribute("telefone", "");
        encaminharPagina(request, response);
    }

    private void setAtributosFormulario(HttpServletRequest request) {
        request.setAttribute("cod", cod);
        request.setAttribute("nome", nome);
        request.setAttribute("cpf", cpf);
        request.setAttribute("email", email);
        request.setAttribute("telefone", telefone);
    }
}
