package br.com.flpsno.h02_praticalistview.transmissao;

import java.util.ArrayList;

import br.com.flpsno.h02_praticalistview.model.Pedido;

/**
 * Created by nalmir on 15/10/2016.
 */
public class Transmissao_Env {

    private ArrayList<Pedido> pedidos;

    public ArrayList<Pedido> getContatos() {
        return pedidos;
    }

    public void setContatos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
