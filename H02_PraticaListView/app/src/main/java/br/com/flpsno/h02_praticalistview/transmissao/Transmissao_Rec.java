package br.com.flpsno.h02_praticalistview.transmissao;

import java.util.ArrayList;

import br.com.flpsno.h02_praticalistview.model.Pedido;
import br.com.flpsno.h02_praticalistview.model.StatusPedido;

/**
 * Created by nalmir on 15/10/2016.
 */
public class Transmissao_Rec {

    private ArrayList<Pedido> pedidos;

    private ArrayList<StatusPedido> statusPedidos;

    public ArrayList<StatusPedido> getStatusPedidos() {
        return statusPedidos;
    }

    public void setStatusPedidos(ArrayList<StatusPedido> statusPedidos) {
        this.statusPedidos = statusPedidos;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

}
