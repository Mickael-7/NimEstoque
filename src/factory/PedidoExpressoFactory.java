package factory;

import entities.Produto;
import entities.Pedido;
public class PedidoExpressoFactory implements PedidoFactory {
    @Override
    public Pedido criarPedido(double valor, int quantidade, Produto produto) {
        return new PedidoExpresso(valor,quantidade,produto);
    }
}
