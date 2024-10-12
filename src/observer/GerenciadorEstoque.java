package observer;

import entities.Produto;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorEstoque {

    private List<ObservadorEstoque> observadores = new ArrayList<>();
    private List<Produto> produtos = new ArrayList<>();

    public void adicionarObservador(ObservadorEstoque observador) {
        observadores.add(observador);
    }

    public void removerObservador(ObservadorEstoque observador) {
        observadores.remove(observador);
    }

    private void notificarObservadores(Produto produto, String mensagem) {
        for (ObservadorEstoque observador : observadores) {
            observador.atualizar(produto, mensagem);
        }
    }

    public void atualizarProduto(Produto produto, int novaQuantidade) {
        produto.setQuantidade(novaQuantidade);

        if (!produtos.contains(produto)) {
            produtos.add(produto);
        }

        notificarObservadores(produto, "Quantidade atualizada para " + novaQuantidade);
    }

    public List<Produto> listarProdutos() {
        return produtos;
    }
}
