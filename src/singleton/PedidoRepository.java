package singleton;

import entities.Pedido;
import observer.PedidoObserver;

import java.util.ArrayList;
import java.util.List;

public class PedidoRepository {
    private static PedidoRepository instancia;
    private List<Pedido> pedidos;
    private List<PedidoObserver> observers;


    private PedidoRepository() {
        pedidos = new ArrayList<>();
        observers = new ArrayList<>();
    }


    public static synchronized PedidoRepository getInstancia() {
        if (instancia == null) {
            instancia = new PedidoRepository();
        }
        return instancia;
    }


    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
        notificarObservadores(pedido);
    }

    public void registrarObservador(PedidoObserver observer) {
        observers.add(observer);
    }


    public void removerObservador(PedidoObserver observer) {
        observers.remove(observer);
    }


    private void notificarObservadores(Pedido pedido) {
        for (PedidoObserver observer : observers) {
            observer.atualizar(pedido);
        }
    }


    public List<Pedido> listarPedidos() {
        return pedidos;
    }
}
