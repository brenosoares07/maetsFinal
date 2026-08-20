package com.mycompany.maets.controlador;

import com.mycompany.maets.modelo.dao.AuditoriaDao;
import com.mycompany.maets.modelo.entidade.Auditoria;
import com.mycompany.maets.servico.WebConstante;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH + "/AuditoriaControlador")
public class AuditoriaControlador extends HttpServlet {

    private AuditoriaDao objAuditoriaDao = new AuditoriaDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Auditoria> listaAuditoria = objAuditoriaDao.buscarTodasAuditorias();
        request.setAttribute("listaAuditoria", listaAuditoria);
        request.getRequestDispatcher("/Auditoria.jsp").forward(request, response);
    }
}
