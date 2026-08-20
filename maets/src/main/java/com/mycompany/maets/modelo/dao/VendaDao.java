package com.mycompany.maets.modelo.dao;

import com.mycompany.maets.modelo.entidade.Cliente;
import com.mycompany.maets.modelo.entidade.Funcionario;
import com.mycompany.maets.modelo.entidade.Pagamento;
import com.mycompany.maets.modelo.entidade.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendaDao extends GenericoDAO<Venda> {

    public int criar(Venda v) {
        String sql = "INSERT INTO venda(fk_cod_cliente, fk_cod_funcionario, fk_cod_pagamento, valor_total) VALUES(?, ?, ?, 0)";
        save(sql, v.getCliente().getCod(), v.getFuncionario().getCod(), v.getPagamento().getCod());
        return getLastId();
    }

    public void excluir(int cod) {
        save("DELETE FROM venda WHERE cod=?", cod);
    }

    public List<Venda> buscarTodasVendas() {
        return buscarTodos("SELECT * FROM venda ORDER BY cod DESC", new VendaRowMapper());
    }

    public Venda buscarVendaPorId(int cod) {
        return buscarPorId("SELECT * FROM venda WHERE cod=?", new VendaRowMapper(), cod);
    }

    public void recalcularTotal(int codVenda) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        double totalItens = 0, totalServicos = 0;
        try {
            con = ConnectionFactory.getInstance().getConnection();

            pstmt = con.prepareStatement("SELECT COALESCE(SUM(total),0) AS soma FROM itens_venda WHERE fk_cod_venda=?");
            pstmt.setInt(1, codVenda);
            rs = pstmt.executeQuery();
            if (rs.next()) totalItens = rs.getDouble("soma");
            rs.close();
            pstmt.close();

            pstmt = con.prepareStatement("SELECT COALESCE(SUM(subtotal),0) AS soma FROM servicos_venda WHERE fk_cod_venda=?");
            pstmt.setInt(1, codVenda);
            rs = pstmt.executeQuery();
            if (rs.next()) totalServicos = rs.getDouble("soma");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        save("UPDATE venda SET valor_total=? WHERE cod=?", totalItens + totalServicos, codVenda);
    }

    static class VendaRowMapper implements RowMapper<Venda> {
        ClienteDao clienteDao = new ClienteDao();
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        PagamentoDao pagamentoDao = new PagamentoDao();

        @Override
        public Venda mapRow(ResultSet rs) throws SQLException {
            Venda v = new Venda();
            v.setCod(rs.getInt("cod"));
            if (rs.getTimestamp("data_venda") != null) {
                v.setDataVenda(rs.getTimestamp("data_venda").toLocalDateTime());
            }
            v.setValorTotal(rs.getDouble("valor_total"));

            Cliente c = clienteDao.buscarClientePorId(rs.getInt("fk_cod_cliente"));
            if (c != null) v.setCliente(c);

            Funcionario f = funcionarioDao.buscarFuncionarioPorId(rs.getInt("fk_cod_funcionario"));
            if (f != null) v.setFuncionario(f);

            Pagamento p = pagamentoDao.buscarPagamentoPorId(rs.getInt("fk_cod_pagamento"));
            if (p != null) v.setPagamento(p);

            return v;
        }
    }
}
