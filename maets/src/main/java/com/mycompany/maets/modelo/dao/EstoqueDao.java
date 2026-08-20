package com.mycompany.maets.modelo.dao;

import com.mycompany.maets.modelo.entidade.Estoque;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EstoqueDao extends GenericoDAO<Estoque> {

    public void salvar(Estoque e) {
        String sql = "INSERT INTO estoque(fk_cod_produto, quantidade_atual) VALUES(?, ?)";
        save(sql, e.getProduto().getCod(), e.getQuantidadeAtual());
    }

    public void alterar(Estoque e) {
        String sql = "UPDATE estoque SET fk_cod_produto=?, quantidade_atual=? WHERE cod=?";
        save(sql, e.getProduto().getCod(), e.getQuantidadeAtual(), e.getCod());
    }

    public void excluir(Estoque e) {
        save("DELETE FROM estoque WHERE cod=?", e.getCod());
    }

    public List<Estoque> buscarTodosEstoques() {
        return buscarTodos("SELECT * FROM estoque", new EstoqueRowMapper());
    }

    public Estoque buscarEstoquePorId(int id) {
        return buscarPorId("SELECT * FROM estoque WHERE cod=?", new EstoqueRowMapper(), id);
    }

    static class EstoqueRowMapper implements RowMapper<Estoque> {
        ProdutoDao produtoDao = new ProdutoDao();

        @Override
        public Estoque mapRow(ResultSet rs) throws SQLException {
            Estoque e = new Estoque();
            e.setCod(rs.getInt("cod"));
            e.setQuantidadeAtual(rs.getInt("quantidade_atual"));
            e.setProduto(produtoDao.buscarProdutoPorId(rs.getInt("fk_cod_produto")));
            return e;
        }
    }
}
