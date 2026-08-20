package com.mycompany.maets.modelo.entidade;

public class ServicosVenda {
    private Integer cod;
    private Integer codVenda;
    private Servicos servico = new Servicos();
    private Integer quantidade;
    private Double valorUnitario;
    private Double subtotal;

    public Integer getCod() { return cod; }
    public void setCod(Integer cod) { this.cod = cod; }
    public Integer getCodVenda() { return codVenda; }
    public void setCodVenda(Integer codVenda) { this.codVenda = codVenda; }
    public Servicos getServico() { return servico; }
    public void setServico(Servicos servico) { this.servico = servico; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public Double getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(Double valorUnitario) { this.valorUnitario = valorUnitario; }
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
}
