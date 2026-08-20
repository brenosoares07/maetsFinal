package com.mycompany.maets.modelo.dao;

import com.mycompany.maets.modelo.entidade.Funcionario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioDao extends GenericoDAO<Funcionario> {

    public void salvar(Funcionario f) {
        String sql = "INSERT INTO funcionario(nome, cargo, salario, data_admissao) VALUES(?, ?, ?, ?)";
        save(sql, f.getNome(), f.getCargo(), f.getSalario(), f.getDataAdmissao());
    }

    public void alterar(Funcionario f) {
        String sql = "UPDATE funcionario SET nome=?, cargo=?, salario=?, data_admissao=? WHERE cod=?";
        save(sql, f.getNome(), f.getCargo(), f.getSalario(), f.getDataAdmissao(), f.getCod());
    }

    public void excluir(Funcionario f) {
        save("DELETE FROM funcionario WHERE cod=?", f.getCod());
    }

    public List<Funcionario> buscarTodosFuncionarios() {
        return buscarTodos("SELECT * FROM funcionario", new FuncionarioRowMapper());
    }

    public Funcionario buscarFuncionarioPorId(int id) {
        return buscarPorId("SELECT * FROM funcionario WHERE cod=?", new FuncionarioRowMapper(), id);
    }

    static class FuncionarioRowMapper implements RowMapper<Funcionario> {
        @Override
        public Funcionario mapRow(ResultSet rs) throws SQLException {
            Funcionario f = new Funcionario();
            f.setCod(rs.getInt("cod"));
            f.setNome(rs.getString("nome"));
            f.setCargo(rs.getString("cargo"));
            f.setSalario(rs.getDouble("salario"));
            if (rs.getDate("data_admissao") != null) {
                f.setDataAdmissao(rs.getDate("data_admissao").toLocalDate());
            }
            return f;
        }
    }
}
