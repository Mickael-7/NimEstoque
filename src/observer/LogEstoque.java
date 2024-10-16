package observer;

import entities.Produto;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogEstoque implements ObservadorEstoque {
    @Override
    public void atualizar(Produto produto, String mensagem) {
        try (FileWriter writer = new FileWriter("log_estoque.txt", true)) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            String timestamp = dtf.format(LocalDateTime.now());
            writer.write("[" + timestamp + "] Produto: " + produto.getNome() + " - " + mensagem + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao registrar no log: " + e.getMessage());
        }
    }

    public void apagarLog() {
        Path logPath = Paths.get("log_estoque.txt");
        try {
            Files.deleteIfExists(logPath);
            System.out.println("Log apagado com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao apagar o log: " + e.getMessage());
        }
    }
}