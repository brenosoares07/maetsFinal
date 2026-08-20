package com.mycompany.maets.modelo.dao;

import com.mycompany.maets.modelo.entidade.Pagamento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PagamentoDao extends GenericoDAO<Pagamento> {

    public void salvar(Pagamento p) {
        save("INSERT INTO pagamento(metodo) VALUES(?)", p.getMetodo());
    }

    public void alterar(Pagamento p) {
        save("UPDATE pagamento SET metodo=? WHERE cod=?", p.getMetodo(), p.getCod());
    }

    public void excluir(Pagamento p) {
        save("DELETE FROM pagamento WHERE cod=?", p.getCod());
    }

    public List<Pagamento> buscarTodosPagamentos() {
        return buscarTodos("SELECT * FROM pagamento", new PagamentoRowMapper());
    }

    public Pagamento buscarPagamentoPorId(int id) {
        return buscarPorId("SELECT * FROM pagamento WHERE cod=?", new PagamentoRowMapper(), id);
    }

    static class PagamentoRowMapper implements RowMapper<Pagamento> {
        @Override
        public Pagamento mapRow(ResultSet rs) throws SQLException {
            Pagamento p = new Pagamento();
            p.setCod(rs.getInt("cod"));
            p.setMetodo(rs.getString("metodo"));
            return p;
        }
    }
}
