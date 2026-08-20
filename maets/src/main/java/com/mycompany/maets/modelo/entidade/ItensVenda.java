package com.mycompany.maets.modelo.entidade;

public class ItensVenda {
    private Integer cod;
    private Integer codVenda;
    private Produto produto = new Produto();
    private Integer quantidade;
    private Double valorUnitario;
    private Double total;

    public Integer getCod() { return cod; }
    public void setCod(Integer cod) { this.cod = cod; }
    public Integer getCodVenda() { return codVenda; }
    public void setCodVenda(Integer codVenda) { this.codVenda = codVenda; }
    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public Double getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(Double valorUnitario) { this.valorUnitario = valorUnitario; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
}
