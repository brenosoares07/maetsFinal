package com.mycompany.maets.modelo.dao;

import com.mycompany.maets.modelo.entidade.Auditoria;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AuditoriaDao extends GenericoDAO<Auditoria> {

    public List<Auditoria> buscarTodasAuditorias() {
        return buscarTodos("SELECT * FROM auditoria ORDER BY data_hora DESC", new AuditoriaRowMapper());
    }

    static class AuditoriaRowMapper implements RowMapper<Auditoria> {
        @Override
        public Auditoria mapRow(ResultSet rs) throws SQLException {
            Auditoria a = new Auditoria();
            a.setCod(rs.getInt("cod"));
            a.setTabelaAlterada(rs.getString("tabela_alterada"));
            a.setUsuario(rs.getString("usuario"));
            if (rs.getTimestamp("data_hora") != null) {
                a.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
            }
            a.setAcaoRealizada(rs.getString("acao_realizada"));
            return a;
        }
    }
}
