package com.mycompany.maets.modelo.dao;

import com.mycompany.maets.modelo.entidade.Servicos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ServicosDao extends GenericoDAO<Servicos> {

    public void salvar(Servicos s) {
        save("INSERT INTO servicos(descricao, valor) VALUES(?, ?)", s.getDescricao(), s.getValor());
    }

    public void alterar(Servicos s) {
        save("UPDATE servicos SET descricao=?, valor=? WHERE cod=?", s.getDescricao(), s.getValor(), s.getCod());
    }

    public void excluir(Servicos s) {
        save("DELETE FROM servicos WHERE cod=?", s.getCod());
    }

    public List<Servicos> buscarTodosServicos() {
        return buscarTodos("SELECT * FROM servicos", new ServicosRowMapper());
    }

    public Servicos buscarServicoPorId(int id) {
        return buscarPorId("SELECT * FROM servicos WHERE cod=?", new ServicosRowMapper(), id);
    }

    static class ServicosRowMapper implements RowMapper<Servicos> {
        @Override
        public Servicos mapRow(ResultSet rs) throws SQLException {
            Servicos s = new Servicos();
            s.setCod(rs.getInt("cod"));
            s.setDescricao(rs.getString("descricao"));
            s.setValor(rs.getDouble("valor"));
            return s;
        }
    }
}
