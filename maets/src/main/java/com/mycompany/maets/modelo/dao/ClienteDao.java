package com.mycompany.maets.modelo.dao;

import com.mycompany.maets.modelo.entidade.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClienteDao extends GenericoDAO<Cliente> {

    public void salvar(Cliente c) {
        String sql = "INSERT INTO cliente(nome, cpf, email, telefone) VALUES(?, ?, ?, ?)";
        save(sql, c.getNome(), c.getCpf(), c.getEmail(), c.getTelefone());
    }

    public void alterar(Cliente c) {
        String sql = "UPDATE cliente SET nome=?, cpf=?, email=?, telefone=? WHERE cod=?";
        save(sql, c.getNome(), c.getCpf(), c.getEmail(), c.getTelefone(), c.getCod());
    }

    public void excluir(Cliente c) {
        String sql = "DELETE FROM cliente WHERE cod=?";
        save(sql, c.getCod());
    }

    public List<Cliente> buscarTodosClientes() {
        return buscarTodos("SELECT * FROM cliente", new ClienteRowMapper());
    }

    public Cliente buscarClientePorId(int id) {
        return buscarPorId("SELECT * FROM cliente WHERE cod=?", new ClienteRowMapper(), id);
    }

    static class ClienteRowMapper implements RowMapper<Cliente> {
        @Override
        public Cliente mapRow(ResultSet rs) throws SQLException {
            Cliente c = new Cliente();
            c.setCod(rs.getInt("cod"));
            c.setNome(rs.getString("nome"));
            c.setCpf(rs.getString("cpf"));
            c.setEmail(rs.getString("email"));
            c.setTelefone(rs.getString("telefone"));
            return c;
        }
    }
}
