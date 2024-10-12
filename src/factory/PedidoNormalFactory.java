package factory;

import entities.Pedido;
import entities.Produto;

public class PedidoNormalFactory implements PedidoFactory {
    @Override
    public Pedido criarPedido(double valor, int quantidade, Produto produto) {
        return new PedidoNormal(valor,quantidade,produto);
    }
}
