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
 * Created by Felipe on 09/09/2016.
 */
public class Adapter_Clientes extends BaseAdapter {

    private LayoutInflater mInflater;
    //
    private int resource;
    private ArrayList<HMAux> dados;

    public Adapter_Clientes(Context context, int resource, ArrayList<HMAux> dados) {
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
        TextView tv_nome = (TextView) convertView.findViewById(R.id.celulaclientes_tv_nome);
        TextView tv_email = (TextView) convertView.findViewById(R.id.celulaclientes_tv_email);
        //
        tv_nome.setText(item.get(HMAux.TEXTO_01));
        tv_email.setText(item.get(HMAux.TEXTO_02));
        //
        return convertView;
    }
}
