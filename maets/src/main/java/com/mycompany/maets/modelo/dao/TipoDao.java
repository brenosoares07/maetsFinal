package com.mycompany.maets.modelo.dao;

import com.mycompany.maets.modelo.entidade.Tipo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TipoDao extends GenericoDAO<Tipo> {

    public void salvar(Tipo t) {
        save("INSERT INTO tipo(nome) VALUES(?)", t.getNome());
    }

    public void alterar(Tipo t) {
        save("UPDATE tipo SET nome=? WHERE cod=?", t.getNome(), t.getCod());
    }

    public void excluir(Tipo t) {
        save("DELETE FROM tipo WHERE cod=?", t.getCod());
    }

    public List<Tipo> buscarTodosTipos() {
        return buscarTodos("SELECT * FROM tipo", new TipoRowMapper());
    }

    public Tipo buscarTipoPorId(int id) {
        return buscarPorId("SELECT * FROM tipo WHERE cod=?", new TipoRowMapper(), id);
    }

    static class TipoRowMapper implements RowMapper<Tipo> {
        @Override
        public Tipo mapRow(ResultSet rs) throws SQLException {
            Tipo t = new Tipo();
            t.setCod(rs.getInt("cod"));
            t.setNome(rs.getString("nome"));
            return t;
        }
    }
}
