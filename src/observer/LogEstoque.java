package observer;

import entities.Produto;
import java.io.FileWriter;
import java.io.IOException;

public class LogEstoque implements ObservadorEstoque {
    @Override
    public void atualizar(Produto produto, String mensagem) {
        try (FileWriter writer = new FileWriter("log_estoque.txt", true)) {
            writer.write("Produto: " + produto.getNome() + " - " + mensagem + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}