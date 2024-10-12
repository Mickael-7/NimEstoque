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
    private void notificarObservadores() {
        if (this.quantidade <= 5) {
            for (ObservadorEstoque observador : observadores) {
                observador.atualizar(this, "Estoque crítico");
            }
        } else {
            for (ObservadorEstoque observador : observadores) {
                observador.atualizar(this, "Estoque atualizado");
            }
        }
    }

    public void reduzirEstoque(int quantidade) {
        if (this.quantidade >= quantidade) {
            this.quantidade -= quantidade;
            notificarObservadores();
        } else {
            System.out.println("Quantidade insuficiente em estoque.");
        }
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public List<ObservadorEstoque> getObservadores() {
        return observadores;
    }

    public void setObservadores(List<ObservadorEstoque> observadores) {
        this.observadores = observadores;
    }



    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }


}

