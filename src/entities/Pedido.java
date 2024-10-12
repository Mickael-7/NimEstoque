package entities;

public abstract class Pedido {
    protected double valor;
    protected  int quantidade;
    protected Produto produto;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public abstract void processar();
}
