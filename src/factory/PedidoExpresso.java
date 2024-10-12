package factory;

import entities.Produto;
import entities.Pedido;
public class PedidoExpresso extends Pedido {

    public PedidoExpresso(double valor, int quantidade, Produto produto) {
        this.valor = valor;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    @Override
    public void processar() {
        System.out.println("Pedido expresso de valor: " + valor + " processado.");
    }
}
