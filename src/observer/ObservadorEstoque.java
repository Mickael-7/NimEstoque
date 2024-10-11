package observer;

import entities.Produto;

public interface ObservadorEstoque {
    void atualizar(Produto produto, String menssagem);
}
