import entities.Pedido;
import entities.Produto;
import factory.PedidoExpressoFactory;
import factory.PedidoFactory;
import factory.PedidoNormalFactory;
import observer.RelatorioEstoque;
import singleton.Estoque;
import singleton.PedidoRepository;

import javax.swing.*;
import java.util.List;

public class PedidoApp {
    public static void main(String[] args) {
        // Inicializar estoque com singleton
        Estoque estoque = Estoque.getInstancia();
        RelatorioEstoque re = new RelatorioEstoque();

        String[] opcoes = {"Cadastrar Produto", "Criar Pedido","Visualizar Pedidos", "Visualizar Estoque", "Gerar Relatorio", "Sair"};
        int escolha;

        do {
            // Menu de opções
            escolha = JOptionPane.showOptionDialog(
                    null, "Escolha uma opção", "Sistema de Pedidos",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]
            );

            // Verificar se a janela foi fechada
            if (escolha == JOptionPane.CLOSED_OPTION) {
                escolha = 5; // Definir escolha como "Sair"
            }

            switch (escolha) {
                case 0: // Cadastrar Produto
                    cadastrarProduto();
                    break;
                case 1: // Criar Pedido
                    criarPedido();
                    break;
                case 2:
                    listarPedidos();
                    break;
                case 3:// Visualizar Estoque
                    visualizarEstoque();
                    break;
                case 4:
                    re.gerarRelatorio(estoque.getProdutos());
                    break;
                case 5:// Sair
                    JOptionPane.showMessageDialog(null, "Saindo do sistema.");
                    break;
                default:
                    break;
            }
        } while (escolha != 5);
    }

    public static void cadastrarProduto() {
        // Pergunta o nome do produto
        String nomeProduto = JOptionPane.showInputDialog("Informe o nome do produto:");

        String precoProdutoStr = JOptionPane.showInputDialog("Informe a preço do produto:");
        int precoProduto = Integer.parseInt(precoProdutoStr);

        // Pergunta a quantidade do produto
        String quantidadeProdutoStr = JOptionPane.showInputDialog("Informe a quantidade do produto:");
        int quantidadeProduto = Integer.parseInt(quantidadeProdutoStr);

        // Criar e adicionar produto ao estoque
        Produto novoProduto = new Produto(nomeProduto, quantidadeProduto,precoProduto);
        Estoque.getInstancia().adicionarProduto(novoProduto);

        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
    }

    public static void criarPedido() {
        // Perguntar o tipo de pedido
        String[] tiposPedido = {"Normal", "Expresso"};
        String tipoPedido = (String) JOptionPane.showInputDialog(
                null, "Selecione o tipo de pedido:", "Tipo de Pedido",
                JOptionPane.QUESTION_MESSAGE, null, tiposPedido, tiposPedido[0]
        );

        String quantidadePedidoStr = JOptionPane.showInputDialog("Informe a quantidade do produto:");
        int quantidadePedido = Integer.parseInt(quantidadePedidoStr);

        // Selecionar a fábrica de pedido correta
        PedidoFactory pedidoFactory = null;
        if (tipoPedido.equals("Normal")) {
            pedidoFactory = new PedidoNormalFactory();
        } else if (tipoPedido.equals("Expresso")) {
            pedidoFactory = new PedidoExpressoFactory();
        }

        // Perguntar qual produto foi comprado
        Estoque estoque = Estoque.getInstancia();
        String[] produtos = estoque.getProdutos().stream().map(Produto::getNome).toArray(String[]::new);
        String produtoEscolhido = (String) JOptionPane.showInputDialog(
                null, "Selecione o produto:", "Produto",
                JOptionPane.QUESTION_MESSAGE, null, produtos, produtos[0]
        );

        // Atualizar o estoque
        Pedido pedido = pedidoFactory.criarPedido(estoque.buscarProduto(produtoEscolhido).getPreco() * quantidadePedido,quantidadePedido,estoque.buscarProduto(produtoEscolhido));
        pedido.processar();

        PedidoRepository.getInstancia().adicionarPedido(pedido);
        // Criar e processar o pedido
        Produto produto = estoque.buscarProduto(produtoEscolhido);
        produto.reduzirEstoque(quantidadePedido);
    }

    public static void listarPedidos() {
        List<Pedido> pedidos = PedidoRepository.getInstancia().listarPedidos();
        if (pedidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum pedido foi feito até o momento.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Pedido pedido : pedidos) {
                sb.append(pedido.getClass().getSimpleName()).append(" - ").append(pedido.getProduto().getNome())
                        .append(" - R$ ").append(pedido.getValor()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Pedidos", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void visualizarEstoque() {
        Estoque estoque = Estoque.getInstancia();
        StringBuilder sb = new StringBuilder();
        for (Produto produto : estoque.getProdutos()) {
            sb.append(produto.getNome()).append(" - Quantidade: ").append(produto.getQuantidade()).append("\n").append(" - Preço Unitário: ").append(produto.getPreco()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Estoque Atual", JOptionPane.INFORMATION_MESSAGE);
    }
}
