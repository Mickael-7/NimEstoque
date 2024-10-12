package factory;

import entities.Pedido;
import entities.Produto;

public interface PedidoFactory {
    Pedido criarPedido(double valor, int quantidade, Produto produto);
}
