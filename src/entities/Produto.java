package entities;

import observer.ObservadorEstoque;

import java.util.ArrayList;
import java.util.List;

public class Produto {
    private String nome;
    private int quantidade;
    private double preco;

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    private List<ObservadorEstoque> observadores = new ArrayList<>();

    public Produto(String nome, int quantidade,double preco) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public void adicionarObservador(ObservadorEstoque observador) {
        observadores.add(observador);
    }

    public void removerObservador(ObservadorEstoque observador) {
        observadores.remove(observador);
    }

    public void reduzirEstoque(int quantidade) {
        if (this.quantidade >= quantidade) {
            this.quantidade -= quantidade;
            notificarObservadores();
        } else {
            System.out.println("Quantidade insuficiente em estoque.");
        }
    }


    private void notificarObservadores() {
        if (this.quantidade <= 5) { // Se o estoque estiver abaixo de um limite, notifica com urgência
            for (ObservadorEstoque observador : observadores) {
                observador.atualizar(this, "Estoque crítico");
            }
        } else {
            for (ObservadorEstoque observador : observadores) {
                observador.atualizar(this, "Estoque atualizado");
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }


}

