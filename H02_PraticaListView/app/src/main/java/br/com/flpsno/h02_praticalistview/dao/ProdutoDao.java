package br.com.flpsno.h02_praticalistview.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.flpsno.h02_praticalistview.banco.Dao;
import br.com.flpsno.h02_praticalistview.banco.HMAux;
import br.com.flpsno.h02_praticalistview.model.Produto;

/**
 * Created by Felipe on 17/09/2016.
 */
public class ProdutoDao extends Dao {

    private static final String TABELA = "produtos";
    private static final String IDPRODUTO = "idproduto";
    private static final String CODIGO = "codigo";
    private static final String DESCRICAO = "descricao";
    private static final String QTD_MIN = "qtd_min";
    private static final String VALOR_UNITARIO = "valor_unitario";

    public ProdutoDao(Context context) {
        super(context);
    }

    public void incluirProduto(Produto produto) {
        abrirBanco();
        //
        ContentValues cv = new ContentValues();
        //
        cv.put(IDPRODUTO, produto.getIdproduto());
        cv.put(CODIGO, produto.getCodigo());
        cv.put(DESCRICAO, produto.getDescricao());
        cv.put(QTD_MIN, produto.getQtd_min());
        cv.put(VALOR_UNITARIO, produto.getValor_unitario());
        //
        db.insert(
                TABELA,
                null,
                cv
        );
        //
        fecharBanco();
    }

    public void atualizarProduto(Produto produto) {
        abrirBanco();
        //
        ContentValues cv = new ContentValues();
        //
        String filtro = IDPRODUTO + " = ? ";
        String[] parametros = {
                String.valueOf(produto.getIdproduto())
        };
        //
        cv.put(CODIGO, produto.getCodigo());
        cv.put(DESCRICAO, produto.getDescricao());
        cv.put(QTD_MIN, produto.getQtd_min());
        cv.put(VALOR_UNITARIO, produto.getValor_unitario());
        //
        db.update(
                TABELA,
                cv,
                filtro,
                parametros
        );
        //
        fecharBanco();
    }

    public void apagarProduto(Long idproduto) {
        abrirBanco();
        //
        String filtro = IDPRODUTO + " = ? ";
        String[] parametros = {
                String.valueOf(idproduto)
        };
        //
        db.delete(
                TABELA,
                filtro,
                parametros
        );
        //
        fecharBanco();
    }

    public Produto obterProdutoByID(long idproduto) {
        Produto pAux = null;
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            String[] parametros = {
                    String.valueOf(idproduto)
            };
            //
            StringBuilder SQLComando = new StringBuilder();
            SQLComando
                    .append("select * from ")
                    .append(TABELA)
                    .append(" where ")
                    .append(IDPRODUTO)
                    .append(" = ? ");
            cursor = db.rawQuery(SQLComando.toString(), parametros);
            //
            while (cursor.moveToNext()) {
                pAux = new Produto();
                //
                pAux.setIdproduto(cursor.getLong(cursor.getColumnIndex(IDPRODUTO)));
                pAux.setCodigo(cursor.getString(cursor.getColumnIndex(CODIGO)));
                pAux.setDescricao(cursor.getString(cursor.getColumnIndex(DESCRICAO)));
                pAux.setQtd_min(cursor.getInt(cursor.getColumnIndex(QTD_MIN)));
                pAux.setValor_unitario(cursor.getDouble(cursor.getColumnIndex(VALOR_UNITARIO)));
            }
            //
            cursor.close();
            cursor = null;

        } catch (Exception e) {
        }
        //
        fecharBanco();
        //
        return pAux;
    }

    public ArrayList<HMAux> obterProdutos_hm() {
        ArrayList<HMAux> produtos = new ArrayList<>();
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            StringBuilder SQLComando = new StringBuilder();
            SQLComando
                    .append("select ")
                    .append(IDPRODUTO)
                    .append(",")
                    .append(DESCRICAO)
                    .append(",")
                    .append(QTD_MIN)
                    .append(",")
                    .append(VALOR_UNITARIO)
                    .append(" from ")
                    .append(TABELA)
                    .append(" order by ")
                    .append(DESCRICAO);
            cursor = db.rawQuery(SQLComando.toString(), null);
            //
            while (cursor.moveToNext()) {
                HMAux pAux = new HMAux();
                //
                pAux.put(HMAux.ID, String.valueOf(cursor.getLong(cursor.getColumnIndex(IDPRODUTO))));
                pAux.put(HMAux.TEXTO_01, cursor.getString(cursor.getColumnIndex(DESCRICAO)));
                pAux.put(HMAux.TEXTO_02, String.valueOf(cursor.getInt(cursor.getColumnIndex(QTD_MIN))));
                pAux.put(HMAux.TEXTO_03, String.valueOf(cursor.getDouble(cursor.getColumnIndex(VALOR_UNITARIO))));
                //
                produtos.add(pAux);
            }
            //
            cursor.close();
            cursor = null;

        } catch (Exception e) {
        }
        //
        fecharBanco();
        //
        return produtos;
    }

    public long proximoID() {
        long idProximo = 0;
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            StringBuilder SQlcomando = new StringBuilder();
            SQlcomando
                    .append("select max(")
                    .append(IDPRODUTO)
                    .append(") + 1 as id")
                    .append(" from ")
                    .append(TABELA);
            cursor = db.rawQuery(SQlcomando.toString(), null);
            //
            while (cursor.moveToNext()) {
                idProximo = cursor.getLong(cursor.getColumnIndex("id"));
            }

            if (idProximo == 0) {
                idProximo = 1;
            }

            cursor.close();
            cursor = null;
        } catch (Exception e) {
        }
        //
        fecharBanco();
        return idProximo;
    }
}
