package observer;

import entities.Produto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RelatorioEstoque implements ObservadorEstoque {

    private List<Produto> produtos;

    public RelatorioEstoque(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public void atualizar(Produto produto, String mensagem) {
        System.out.println("Atualizando estoque do produto: " + produto.getNome() + " - " + mensagem);

        gerarRelatorio();
    }

    private void gerarRelatorio() {
        try (FileWriter writer = new FileWriter("relatorio_estoque.txt")) {
            for (Produto produto : produtos) {
                writer.write(produto.getNome() + " - Quantidade: " + produto.getQuantidade() + "\n");
            }
            System.out.println("Relatório de estoque atualizado salvo em 'relatorio_estoque.txt'.");
        } catch (IOException e) {
            System.err.println("Erro ao gerar o relatório: " + e.getMessage());
        }
    }
}

