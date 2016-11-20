package br.com.flpsno.h02_praticalistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.flpsno.h02_praticalistview.banco.HMAux;
import br.com.flpsno.h02_praticalistview.R;

/**
 * Created by Felipe on 11/09/2016.
 */
public class Adapter_Pedidos extends BaseAdapter {

    private LayoutInflater mInflater;
    private int resource;
    //
    private ArrayList<HMAux> dados;

    public Adapter_Pedidos(Context context, int resource, ArrayList<HMAux> dados) {
        this.mInflater = LayoutInflater.from(context);
        this.resource = resource;
        this.dados = dados;
    }

    @Override
    public int getCount() {
        return dados.size();
    }

    @Override
    public Object getItem(int position) {
        return dados.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(dados.get(position).get(HMAux.ID));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(resource, parent, false);
        }
        //
        HMAux item = dados.get(position);
        //
        TextView tv_codigo_pedido = (TextView) convertView.findViewById(R.id.celulapedidos_tv_codigo_pedido);
        TextView tv_cliente = (TextView) convertView.findViewById(R.id.celulapedidos_tv_cliente);
        TextView tv_total_item = (TextView) convertView.findViewById(R.id.celulapedidos_tv_total_item);
        TextView tv_valor_total = (TextView) convertView.findViewById(R.id.celulapedidos_tv_valor_total);
        TextView tv_status = (TextView) convertView.findViewById(R.id.celulapedidos_tv_status);
        //
//        float valor_total = Float.parseFloat(item.get(HMAux.TEXTO_03));
        float valor_frete = Float.parseFloat(item.get(HMAux.TEXTO_04));
        //
        tv_codigo_pedido.setText(item.get(HMAux.TEXTO_01));
        tv_cliente.setText(item.get(HMAux.TEXTO_02));
        tv_total_item.setText(item.get(HMAux.TEXTO_03));
        tv_valor_total.setText("R$ " + String.format("%.02f", valor_frete));
        tv_status.setText(item.get(HMAux.TEXTO_05));
        //
        return convertView;
    }
}
