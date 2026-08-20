package com.mycompany.maets.modelo.entidade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private Integer cod;
    private LocalDateTime dataVenda;
    private Cliente cliente = new Cliente();
    private Funcionario funcionario = new Funcionario();
    private Pagamento pagamento = new Pagamento();
    private Double valorTotal;
    private List<ItensVenda> itens = new ArrayList<>();
    private List<ServicosVenda> servicos = new ArrayList<>();

    public Integer getCod() { return cod; }
    public void setCod(Integer cod) { this.cod = cod; }
    public LocalDateTime getDataVenda() { return dataVenda; }
    public void setDataVenda(LocalDateTime dataVenda) { this.dataVenda = dataVenda; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }
    public Pagamento getPagamento() { return pagamento; }
    public void setPagamento(Pagamento pagamento) { this.pagamento = pagamento; }
    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }
    public List<ItensVenda> getItens() { return itens; }
    public void setItens(List<ItensVenda> itens) { this.itens = itens; }
    public List<ServicosVenda> getServicos() { return servicos; }
    public void setServicos(List<ServicosVenda> servicos) { this.servicos = servicos; }
}
