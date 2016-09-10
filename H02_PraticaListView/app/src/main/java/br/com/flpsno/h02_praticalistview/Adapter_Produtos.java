package br.com.flpsno.h02_praticalistview;

import android.content.Context;
import android.support.annotation.IntegerRes;
import android.support.v4.view.LayoutInflaterFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Felipe on 10/09/2016.
 */
public class Adapter_Produtos extends BaseAdapter {

    private Context context;
    //
    private LayoutInflater mInflater;
    private int resource;
    private ArrayList<HMAux> dados;

    public Adapter_Produtos(Context context, int resource, ArrayList<HMAux> dados) {
        this.context = context;
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
        ImageView iv_produto = (ImageView) convertView.findViewById(R.id.celulaprodutos_iv_produto);
        TextView tv_nome_produto = (TextView) convertView.findViewById(R.id.celulaprodutos_tv_nome_produto);
        TextView tv_qtd_minima = (TextView) convertView.findViewById(R.id.celulaprodutos_tv_qtd_min);
        TextView tv_preco = (TextView) convertView.findViewById(R.id.celulaprodutos_tv_preco);
        //
        iv_produto.setImageResource(Integer.parseInt(item.get(HMAux.TEXTO_01)));
        tv_nome_produto.setText(item.get(HMAux.TEXTO_02));
        tv_qtd_minima.setText(item.get(HMAux.TEXTO_03) + " un");
        tv_preco.setText("R$ " + item.get(HMAux.TEXTO_04));
        //
        return convertView;
    }
}
