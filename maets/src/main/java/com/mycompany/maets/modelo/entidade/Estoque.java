package com.mycompany.maets.modelo.entidade;

public class Estoque {
    private Integer cod;
    private Produto produto = new Produto();
    private Integer quantidadeAtual;

    public Integer getCod() { return cod; }
    public void setCod(Integer cod) { this.cod = cod; }
    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }
    public Integer getQuantidadeAtual() { return quantidadeAtual; }
    public void setQuantidadeAtual(Integer quantidadeAtual) { this.quantidadeAtual = quantidadeAtual; }
}
