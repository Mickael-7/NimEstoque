package factory;

import entities.Produto;
import entities.Pedido;
public class PedidoInternacional extends Pedido {

    public PedidoInternacional(double valor, int quantidade, Produto produto) {
        this.valor = valor;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    @Override
    public void processar() {
        System.out.println("Pedido Internacional de valor: " + valor + " processado.");
    }
}
