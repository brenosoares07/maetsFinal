package com.mycompany.maets.modelo.entidade;

public class Produto {
    private Integer cod;
    private String nome;
    private Double precoVenda;
    private String fabricante;
    private String genero;
    private Tipo tipo = new Tipo();

    public Integer getCod() { return cod; }
    public void setCod(Integer cod) { this.cod = cod; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getPrecoVenda() { return precoVenda; }
    public void setPrecoVenda(Double precoVenda) { this.precoVenda = precoVenda; }
    public String getFabricante() { return fabricante; }
    public void setFabricante(String fabricante) { this.fabricante = fabricante; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo tipo) { this.tipo = tipo; }
}
