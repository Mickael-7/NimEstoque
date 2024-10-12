package factory;

import entities.Pedido;
import entities.Produto;

public class PedidoNormal extends Pedido {
    public PedidoNormal(double valor, int quantidade, Produto produto) {
        this.valor = valor;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    @Override
    public void processar() {
        System.out.println("Pedido normal de valor: " + valor + " processado.");
    }
}
