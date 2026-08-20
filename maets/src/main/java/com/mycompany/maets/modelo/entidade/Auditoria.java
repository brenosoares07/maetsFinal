package com.mycompany.maets.modelo.entidade;

import java.time.LocalDateTime;

public class Auditoria {
    private Integer cod;
    private String tabelaAlterada;
    private String usuario;
    private LocalDateTime dataHora;
    private String acaoRealizada;

    public Integer getCod() { return cod; }
    public void setCod(Integer cod) { this.cod = cod; }
    public String getTabelaAlterada() { return tabelaAlterada; }
    public void setTabelaAlterada(String tabelaAlterada) { this.tabelaAlterada = tabelaAlterada; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public String getAcaoRealizada() { return acaoRealizada; }
    public void setAcaoRealizada(String acaoRealizada) { this.acaoRealizada = acaoRealizada; }
}
