package com.mycompany.maets.modelo.entidade;

import java.time.LocalDate;

public class Funcionario {
    private Integer cod;
    private String nome;
    private String cargo;
    private Double salario;
    private LocalDate dataAdmissao;

    public Integer getCod() { return cod; }
    public void setCod(Integer cod) { this.cod = cod; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public Double getSalario() { return salario; }
    public void setSalario(Double salario) { this.salario = salario; }
    public LocalDate getDataAdmissao() { return dataAdmissao; }
    public void setDataAdmissao(LocalDate dataAdmissao) { this.dataAdmissao = dataAdmissao; }
}
