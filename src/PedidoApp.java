import entities.Pedido;
import entities.Produto;
import factory.PedidoExpressoFactory;
import factory.PedidoFactory;
import factory.PedidoInternacionalFactory;
import factory.PedidoNormalFactory;
import observer.GerenciadorEstoque;
import observer.LogEstoque;
import observer.RelatorioEstoque;
import singleton.Estoque;
import singleton.PedidoRepository;

import java.util.List;
import java.util.Scanner;

public class PedidoApp {

    private static GerenciadorEstoque gerenciadorEstoque = new GerenciadorEstoque();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Estoque estoque = Estoque.getInstancia();
        RelatorioEstoque re = new RelatorioEstoque();
        LogEstoque le = new LogEstoque();

        gerenciadorEstoque.adicionarObservador(re);
        gerenciadorEstoque.adicionarObservador(le);

        String[] opcoes = {"Cadastrar Produto", "Criar Pedido", "Visualizar Pedidos", "Visualizar Estoque", "Gerar Relatório", "Sair"};
        int escolha;

        do {
            System.out.println("Escolha uma opção: ");
            for (int i = 0; i < opcoes.length; i++) {
                System.out.println((i+1) + ". " + opcoes[i]);
            }

            escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    cadastrarProduto(scanner);
                    break;
                case 2:
                    if(gerenciadorEstoque.listarProdutos().isEmpty()) {
                        System.out.println("Não existem produtos disponiveis");
                    }else {
                        criarPedido(scanner);
                    }
                    break;
                case 3:
                    listarPedidos();
                    break;
                case 4:
                    if(gerenciadorEstoque.listarProdutos().isEmpty()) {
                        System.out.println("Nenhum produto cadastrado");
                    }else {
                        visualizarEstoque();
                    }
                    break;
                case 5:
                    re.gerarRelatorio(estoque.getProdutos());
                    break;
                case 6:
                    System.out.println("Saindo do sistema.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (escolha != 6);

        scanner.close();
    }

    public static void cadastrarProduto(Scanner scanner) {
        System.out.print("Informe o nome do produto: ");
        String nomeProduto = scanner.nextLine();

        System.out.print("Informe o preço do produto: ");
        double precoProduto = Double.parseDouble(scanner.nextLine());

        System.out.print("Informe a quantidade do produto: ");
        int quantidadeProduto = Integer.parseInt(scanner.nextLine());

        Produto novoProduto = new Produto(nomeProduto, quantidadeProduto, precoProduto);
        Estoque.getInstancia().adicionarProduto(novoProduto);

        gerenciadorEstoque.atualizarProduto(novoProduto, quantidadeProduto);

        System.out.println("Produto cadastrado com sucesso!");
    }

    public static void criarPedido(Scanner scanner) {
        System.out.println("Selecione o tipo de pedido:");
        System.out.println("1. Normal");
        System.out.println("2. Expresso");
        System.out.println("3. Internacional");
        int tipoPedidoEscolhido = Integer.parseInt(scanner.nextLine());

        String tipoPedido = tipoPedidoEscolhido == 1 ? "Normal" :
                            tipoPedidoEscolhido == 2 ? "Expresso" :
                            tipoPedidoEscolhido == 3 ? "Internacional": " ";

        System.out.print("Informe a quantidade do produto: ");
        int quantidadePedido = Integer.parseInt(scanner.nextLine());

        PedidoFactory pedidoFactory;
        if (tipoPedido.equals("Normal")) {
            pedidoFactory = new PedidoNormalFactory();
        } else if (tipoPedido.equals("Expresso")) {
            pedidoFactory = new PedidoExpressoFactory();
        } else if (tipoPedido.equals("Internacional")) {
            pedidoFactory = new PedidoInternacionalFactory();
        } else {
            throw new IllegalArgumentException("Tipo de pedido inválido.");
        }

        Estoque estoque = Estoque.getInstancia();
        String[] produtos = estoque.getProdutos().stream().map(Produto::getNome).toArray(String[]::new);
        System.out.println("Selecione o produto:");
        for (int i = 0; i < produtos.length; i++) {
            System.out.println((i + 1) + ". " + produtos[i]);
        }
        int produtoEscolhidoIndex = Integer.parseInt(scanner.nextLine()) - 1;
        String produtoEscolhido = produtos[produtoEscolhidoIndex];

        Produto produto = estoque.buscarProduto(produtoEscolhido);

        Pedido pedido = pedidoFactory.criarPedido(produto.getPreco() * quantidadePedido, quantidadePedido, produto);
        pedido.processar();

        PedidoRepository.getInstancia().adicionarPedido(pedido);

        produto.reduzirEstoque(quantidadePedido);
        gerenciadorEstoque.atualizarProduto(produto, produto.getQuantidade());
    }

    public static void listarPedidos() {
        List<Pedido> pedidos = PedidoRepository.getInstancia().listarPedidos();
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido foi feito até o momento.");
        } else {
            System.out.println("Lista de Pedidos:");
            for (Pedido pedido : pedidos) {
                System.out.println(pedido.getClass().getSimpleName() + " - " + pedido.getProduto().getNome() + " - R$ " + pedido.getValor());
            }
        }
    }

    public static void visualizarEstoque() {
        Estoque estoque = Estoque.getInstancia();
        System.out.println("Estoque Atual:");
        for (Produto produto : estoque.getProdutos()) {
            System.out.println(produto.getNome() + " - Quantidade: " + produto.getQuantidade() + " - Preço Unitário: R$ " + produto.getPreco());
        }
    }
}
