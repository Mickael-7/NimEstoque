package singleton;

import entities.Produto;

import java.util.ArrayList;
import java.util.List;

public class Estoque {

    private static Estoque instancia;
    private List<Produto> produtos;


    private Estoque() {
        produtos = new ArrayList<>();
    }



    public static Estoque getInstancia() {
        if (instancia == null) {
            instancia = new Estoque();
        }
        return instancia;
    }



}
