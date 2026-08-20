package com.mycompany.maets.modelo.dao;

import com.mycompany.maets.modelo.entidade.ItensVenda;
import com.mycompany.maets.modelo.entidade.Produto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItensVendaDao extends GenericoDAO<ItensVenda> {

    private ProdutoDao produtoDao = new ProdutoDao();
    private VendaDao vendaDao = new VendaDao();

    public void adicionar(int codVenda, int codProduto, int quantidade) {
        Produto produto = produtoDao.buscarProdutoPorId(codProduto);
        double valorUnitario = produto.getPrecoVenda();
        double total = valorUnitario * quantidade;

        String sql = "INSERT INTO itens_venda(fk_cod_venda, fk_cod_produto, quantidade, valor_unitario, total) VALUES(?, ?, ?, ?, ?)";
        save(sql, codVenda, codProduto, quantidade, valorUnitario, total);

        vendaDao.recalcularTotal(codVenda);
    }

    public void remover(int codItem, int codVenda) {
        save("DELETE FROM itens_venda WHERE cod=?", codItem);
        vendaDao.recalcularTotal(codVenda);
    }

    public List<ItensVenda> buscarPorVenda(int codVenda) {
        return buscarTodos("SELECT * FROM itens_venda WHERE fk_cod_venda=" + codVenda, new ItensVendaRowMapper());
    }

    class ItensVendaRowMapper implements RowMapper<ItensVenda> {
        @Override
        public ItensVenda mapRow(ResultSet rs) throws SQLException {
            ItensVenda i = new ItensVenda();
            i.setCod(rs.getInt("cod"));
            i.setCodVenda(rs.getInt("fk_cod_venda"));
            i.setQuantidade(rs.getInt("quantidade"));
            i.setValorUnitario(rs.getDouble("valor_unitario"));
            i.setTotal(rs.getDouble("total"));
            i.setProduto(produtoDao.buscarProdutoPorId(rs.getInt("fk_cod_produto")));
            return i;
        }
    }
}
