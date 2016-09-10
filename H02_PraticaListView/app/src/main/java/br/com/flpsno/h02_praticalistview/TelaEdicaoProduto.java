package br.com.flpsno.h02_praticalistview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Felipe on 10/09/2016.
 */
public class TelaEdicaoProduto extends AppCompatActivity {

    private Context context;
    //
    private ImageView iv_produto;
    private EditText et_nome_produto;
    private EditText et_qtd_minima;
    private EditText et_preco;
    //
    private int imagem_produto;
    private String nome_produto;
    private String qtd_minima;
    private String preco;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaedicaoproduto);
        //
        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        iv_produto = (ImageView) findViewById(R.id.telaedicaopedido_iv_produto);
        et_nome_produto = (EditText) findViewById(R.id.telaedicaoproduto_et_nome_produto);
        et_qtd_minima = (EditText) findViewById(R.id.telaedicaoproduto_et_qtd_minima);
        et_preco = (EditText) findViewById(R.id.telaedicaoproduto_et_preco);
        //
        recuperarParametros();
        //
        iv_produto.setImageResource(imagem_produto);
        et_nome_produto.setText(nome_produto);
        et_qtd_minima.setText(qtd_minima);
        et_preco.setText(preco);

    }

    private void recuperarParametros() {

        imagem_produto = getIntent().getIntExtra(Constantes.VALOR_IMAGEM_PRODUTO, 0);
        nome_produto = getIntent().getStringExtra(Constantes.VALOR_NOME_PRODUTO);
        qtd_minima = getIntent().getStringExtra(Constantes.VALOR_QTD_MINIMA);
        preco = getIntent().getStringExtra(Constantes.VALOR_PRECO);
    }

    private void inicializarAcao() {

    }
}
