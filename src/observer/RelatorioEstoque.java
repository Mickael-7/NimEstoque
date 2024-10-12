package observer;

import entities.Produto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RelatorioEstoque implements ObservadorEstoque {
    @Override
    public void atualizar(Produto produto, String mensagem) {
        System.out.println("Produto: " + produto.getNome() + " - Quantidade atualizada: " + produto.getQuantidade());
        System.out.println("Mensagem: " + mensagem);
    }

    public void gerarRelatorio(List<Produto> produtos) {
        try (FileWriter writer = new FileWriter("relatorio_estoque.txt")) {
            for (Produto produto : produtos) {
                writer.write(produto.getNome() + " - Quantidade: " + produto.getQuantidade() + "\n");
            }
            System.out.println("Relatório de estoque salvo em 'relatorio_estoque.txt'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
