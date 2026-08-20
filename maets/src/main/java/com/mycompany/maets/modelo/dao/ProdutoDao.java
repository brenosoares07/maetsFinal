package com.mycompany.maets.modelo.dao;

import com.mycompany.maets.modelo.entidade.Produto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProdutoDao extends GenericoDAO<Produto> {

    public void salvar(Produto p) {
        String sql = "INSERT INTO produto(nome, preco_venda, fk_cod_tipo, fabricante, genero) VALUES(?, ?, ?, ?, ?)";
        save(sql, p.getNome(), p.getPrecoVenda(), p.getTipo().getCod(), p.getFabricante(), p.getGenero());
    }

    public void alterar(Produto p) {
        String sql = "UPDATE produto SET nome=?, preco_venda=?, fk_cod_tipo=?, fabricante=?, genero=? WHERE cod=?";
        save(sql, p.getNome(), p.getPrecoVenda(), p.getTipo().getCod(), p.getFabricante(), p.getGenero(), p.getCod());
    }

    public void excluir(Produto p) {
        save("DELETE FROM produto WHERE cod=?", p.getCod());
    }

    public List<Produto> buscarTodosProdutos() {
        return buscarTodos("SELECT * FROM produto", new ProdutoRowMapper());
    }

    public Produto buscarProdutoPorId(int id) {
        return buscarPorId("SELECT * FROM produto WHERE cod=?", new ProdutoRowMapper(), id);
    }

    static class ProdutoRowMapper implements RowMapper<Produto> {
        TipoDao tipoDao = new TipoDao();

        @Override
        public Produto mapRow(ResultSet rs) throws SQLException {
            Produto p = new Produto();
            p.setCod(rs.getInt("cod"));
            p.setNome(rs.getString("nome"));
            p.setPrecoVenda(rs.getDouble("preco_venda"));
            p.setFabricante(rs.getString("fabricante"));
            p.setGenero(rs.getString("genero"));
            p.setTipo(tipoDao.buscarTipoPorId(rs.getInt("fk_cod_tipo")));
            return p;
        }
    }
}
