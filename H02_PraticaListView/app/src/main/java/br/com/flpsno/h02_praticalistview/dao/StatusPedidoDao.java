package br.com.flpsno.h02_praticalistview.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.flpsno.h02_praticalistview.banco.Dao;
import br.com.flpsno.h02_praticalistview.model.StatusPedido;

/**
 * Created by Felipe on 11/12/2016.
 */

public class StatusPedidoDao extends Dao {

    private static final String TABELA = "statuspedido";
    private static final String IDSTATUSPEDIDO = "idstatuspedido";
    private static final String CODIGO = "codigo";
    private static final String DESCRICAO = "descricao";

    public StatusPedidoDao(Context context) {
        super(context);
    }

    public void inserirListaStatusPedido(ArrayList<StatusPedido> statusPedidos) {
        abrirBanco();
        //
        db.beginTransaction();
        //
        try {
            db.delete(TABELA, null, null);
            //
            ContentValues cv = new ContentValues();
            //
            for (StatusPedido spAux : statusPedidos) {
                cv.clear();
                //
                cv.put(IDSTATUSPEDIDO, spAux.getIDSTATUSPEDIDO());
                cv.put(CODIGO, spAux.getCODIGO());
                cv.put(DESCRICAO, spAux.getDESCRICAO());
                //
                db.insert(TABELA, null, cv);
            }
            //
            db.setTransactionSuccessful();
        } catch (Exception e) {

        } finally {
            db.endTransaction();
        }
        //
        fecharBanco();
    }

    public ArrayList<StatusPedido> obterStatusPedido() {
        ArrayList<StatusPedido> statusPedidos = new ArrayList<>();
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            String[] parametros = {};
            StringBuilder SQLComando = new StringBuilder();
            SQLComando
                    .append("select ")
                    .append(IDSTATUSPEDIDO)
                    .append(",")
                    .append(CODIGO)
                    .append(",")
                    .append(DESCRICAO)
                    .append(" from ")
                    .append(TABELA);
//                    .append(" order by ")
//                    .append(CODIGO);
            //
            cursor = db.rawQuery(
                    SQLComando.toString(),
                    parametros
            );
            //
            while (cursor.moveToNext()) {
                StatusPedido spAux = new StatusPedido();
                //
                spAux.setIDSTATUSPEDIDO(cursor.getLong(cursor.getColumnIndex(IDSTATUSPEDIDO)));
                spAux.setCODIGO(cursor.getString(cursor.getColumnIndex(CODIGO)));
                spAux.setDESCRICAO(cursor.getString(cursor.getColumnIndex(DESCRICAO)));
                //
                statusPedidos.add(spAux);
            }
            //
            cursor.close();
            cursor = null;
        } catch (Exception e) {
        }
        //
        fecharBanco();
        //
        return statusPedidos;
    }
}
