package com.mycompany.maets.modelo.dao;

import com.mycompany.maets.modelo.entidade.ServicosVenda;
import com.mycompany.maets.modelo.entidade.Servicos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ServicosVendaDao extends GenericoDAO<ServicosVenda> {

    private ServicosDao servicosDao = new ServicosDao();
    private VendaDao vendaDao = new VendaDao();

    public void adicionar(int codVenda, int codServico, int quantidade) {
        Servicos servico = servicosDao.buscarServicoPorId(codServico);
        double valorUnitario = servico.getValor();
        double subtotal = valorUnitario * quantidade;

        String sql = "INSERT INTO servicos_venda(fk_cod_venda, fk_cod_servico, quantidade, valor_unitario, subtotal) VALUES(?, ?, ?, ?, ?)";
        save(sql, codVenda, codServico, quantidade, valorUnitario, subtotal);

        vendaDao.recalcularTotal(codVenda);
    }

    public void remover(int codServicoVenda, int codVenda) {
        save("DELETE FROM servicos_venda WHERE cod=?", codServicoVenda);
        vendaDao.recalcularTotal(codVenda);
    }

    public List<ServicosVenda> buscarPorVenda(int codVenda) {
        return buscarTodos("SELECT * FROM servicos_venda WHERE fk_cod_venda=" + codVenda, new ServicosVendaRowMapper());
    }

    class ServicosVendaRowMapper implements RowMapper<ServicosVenda> {
        @Override
        public ServicosVenda mapRow(ResultSet rs) throws SQLException {
            ServicosVenda s = new ServicosVenda();
            s.setCod(rs.getInt("cod"));
            s.setCodVenda(rs.getInt("fk_cod_venda"));
            s.setQuantidade(rs.getInt("quantidade"));
            s.setValorUnitario(rs.getDouble("valor_unitario"));
            s.setSubtotal(rs.getDouble("subtotal"));
            s.setServico(servicosDao.buscarServicoPorId(rs.getInt("fk_cod_servico")));
            return s;
        }
    }
}
