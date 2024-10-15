package factory;

import entities.Produto;
import entities.Pedido;
public class PedidoInternacionalFactory implements PedidoFactory {
    @Override
    public Pedido criarPedido(double valor, int quantidade, Produto produto) {
        return new PedidoInternacional(valor,quantidade,produto);
    }
}
