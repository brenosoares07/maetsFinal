package com.mycompany.maets;

import com.mycompany.maets.modelo.dao.ClienteDao;
import com.mycompany.maets.modelo.dao.ConnectionFactory;
import com.mycompany.maets.modelo.dao.FuncionarioDao;
import com.mycompany.maets.modelo.dao.PagamentoDao;
import com.mycompany.maets.modelo.dao.ProdutoDao;
import com.mycompany.maets.modelo.dao.ServicosDao;
import com.mycompany.maets.modelo.dao.TipoDao;
import com.mycompany.maets.modelo.dao.VendaDao;
import com.mycompany.maets.modelo.entidade.Cliente;
import com.mycompany.maets.modelo.entidade.Funcionario;
import com.mycompany.maets.modelo.entidade.Pagamento;
import com.mycompany.maets.modelo.entidade.Produto;
import com.mycompany.maets.modelo.entidade.Servicos;
import com.mycompany.maets.modelo.entidade.Tipo;
import com.mycompany.maets.modelo.entidade.Venda;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        testarConexao();
        testarClientes();
        testarFuncionarios();
        testarTipos();
        testarProdutos();
        testarPagamentos();
        testarServicos();
        testarVendas();
    }

    private static void testarConexao() {
        System.out.println("=== Testando conexao com o banco 'maets' ===");
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            System.out.println("Conexao ok: " + (con != null && !con.isClosed()));
        } catch (SQLException ex) {
            System.out.println("Falha na conexao: " + ex.getMessage());
        }
    }

    private static void testarClientes() {
        System.out.println("\n=== Clientes ===");
        ClienteDao dao = new ClienteDao();
        for (Cliente c : dao.buscarTodosClientes()) {
            System.out.println(c.getCod() + " - " + c.getNome() + " - " + c.getCpf());
        }
    }

    private static void testarFuncionarios() {
        System.out.println("\n=== Funcionarios ===");
        FuncionarioDao dao = new FuncionarioDao();
        for (Funcionario f : dao.buscarTodosFuncionarios()) {
            System.out.println(f.getCod() + " - " + f.getNome() + " - " + f.getCargo());
        }
    }

    private static void testarTipos() {
        System.out.println("\n=== Tipos ===");
        TipoDao dao = new TipoDao();
        for (Tipo t : dao.buscarTodosTipos()) {
            System.out.println(t.getCod() + " - " + t.getNome());
        }
    }

    private static void testarProdutos() {
        System.out.println("\n=== Produtos ===");
        ProdutoDao dao = new ProdutoDao();
        for (Produto p : dao.buscarTodosProdutos()) {
            System.out.println(p.getCod() + " - " + p.getNome() + " - R$" + p.getPrecoVenda() + " - Tipo: " + p.getTipo().getNome());
        }
    }

    private static void testarPagamentos() {
        System.out.println("\n=== Pagamentos ===");
        PagamentoDao dao = new PagamentoDao();
        for (Pagamento p : dao.buscarTodosPagamentos()) {
            System.out.println(p.getCod() + " - " + p.getMetodo());
        }
    }

    private static void testarServicos() {
        System.out.println("\n=== Servicos ===");
        ServicosDao dao = new ServicosDao();
        for (Servicos s : dao.buscarTodosServicos()) {
            System.out.println(s.getCod() + " - " + s.getDescricao() + " - R$" + s.getValor());
        }
    }

    private static void testarVendas() {
        System.out.println("\n=== Vendas ===");
        VendaDao dao = new VendaDao();
        for (Venda v : dao.buscarTodasVendas()) {
            System.out.println(v.getCod() + " - Cliente: " + v.getCliente().getNome()
                    + " - Funcionario: " + v.getFuncionario().getNome()
                    + " - Total: R$" + v.getValorTotal());
        }
    }
}
