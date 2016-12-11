package br.com.flpsno.h02_praticalistview.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.flpsno.h02_praticalistview.banco.Constantes;
import br.com.flpsno.h02_praticalistview.banco.Dao;
import br.com.flpsno.h02_praticalistview.banco.HMAux;
import br.com.flpsno.h02_praticalistview.model.Pedido;
import br.com.flpsno.h02_praticalistview.model.Produto;

/**
 * Created by Felipe on 18/10/2016.
 */

public class PedidoDao extends Dao {

    private static final String STATUS_TODOS = "*";
    private static final String STATUS_PAGOS = "PAGO_ESPERANDO_VENDEDOR";
    private static final String STATUS_AGUARD_PAG = "AGUARDANDO_PAGAMENTO";

    //
    private static final String TABELA = "pedidos";
    private static final String IDPEDIDO = "idpedido";
    private static final String PEDIDO_ELO7 = "pedido_elo7";
    private static final String STATUS_ELO7 = "status_elo7";
    private static final String DATA_PEDIDO = "data_pedido";
    private static final String VALOR_TOTAL = "valor_total";
    private static final String VALOR_FRETE = "valor_frete";
    private static final String TIPO_FRETE = "tipo_frete";
    private static final String COMPRADOR = "comprador";
    private static final String DATA_PEDIDO2 = "data_pedido2";
    private static final String STATUS = "status";

    //

    public PedidoDao(Context context) {
        super(context);
    }

    public void incluirPedido(Pedido pedido) {
        abrirBanco();
        //
        ContentValues cv = new ContentValues();
        //
        cv.put(IDPEDIDO, pedido.getIDPEDIDO());
        cv.put(PEDIDO_ELO7, pedido.getPEDIDO_ELO7());
        cv.put(STATUS_ELO7, pedido.getSTATUS_ELO7());
        cv.put(DATA_PEDIDO, pedido.getDATA_PEDIDO());
        cv.put(VALOR_TOTAL, pedido.getVALOR_TOTAL());
        cv.put(VALOR_FRETE, pedido.getVALOR_FRETE());
        cv.put(TIPO_FRETE, pedido.getTIPO_FRETE());
        cv.put(COMPRADOR, pedido.getCOMPRADOR());
        cv.put(DATA_PEDIDO2, pedido.getDATA_PEDIDO2());
        cv.put(STATUS, Constantes.STATUS_PEDIDO_INSERIR);
        //
        db.insert(
                TABELA,
                null,
                cv
        );
        //
        fecharBanco();
    }

    public void atualizarPedido(Pedido pedido) {
        abrirBanco();
        //
        ContentValues cv = new ContentValues();
        //
        String filtro = IDPEDIDO + " = ? ";
        String[] parametros = {
                String.valueOf(pedido.getIDPEDIDO())
        };
        //
        cv.put(PEDIDO_ELO7, pedido.getPEDIDO_ELO7());
        cv.put(STATUS_ELO7, pedido.getSTATUS_ELO7());
        cv.put(DATA_PEDIDO, pedido.getDATA_PEDIDO());
        cv.put(VALOR_TOTAL, pedido.getVALOR_TOTAL());
        cv.put(VALOR_FRETE, pedido.getVALOR_FRETE());
        cv.put(TIPO_FRETE, pedido.getTIPO_FRETE());
        cv.put(COMPRADOR, pedido.getCOMPRADOR());
        cv.put(DATA_PEDIDO2, pedido.getDATA_PEDIDO2());
        cv.put(STATUS, Constantes.STATUS_PEDIDO_ATUALIZAR);
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

    public void apagarPedido(long idpedido) {
        abrirBanco();
        //
        String filtro = IDPEDIDO + " = ? ";
        String[] parametros = {
                String.valueOf(idpedido)
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

    public Pedido obterPedidoByID(long idpedido) {
        Pedido pAux = null;
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            //
            String[] parametros = {
                    String.valueOf(idpedido)
            };
            //
            StringBuilder SQLComando = new StringBuilder();
            SQLComando
                    .append("select * from ")
                    .append(TABELA)
                    .append(" where ")
                    .append(IDPEDIDO)
                    .append(" = ? ");
            //
            cursor = db.rawQuery(SQLComando.toString(), parametros);
            //
            while (cursor.moveToNext()) {
                pAux = new Pedido();
                //
                pAux.setIDPEDIDO(cursor.getLong(cursor.getColumnIndex(IDPEDIDO)));
                pAux.setPEDIDO_ELO7(cursor.getString(cursor.getColumnIndex(PEDIDO_ELO7)));
                pAux.setSTATUS_ELO7(cursor.getString(cursor.getColumnIndex(STATUS_ELO7)));
                pAux.setDATA_PEDIDO(cursor.getString(cursor.getColumnIndex(DATA_PEDIDO)));
                pAux.setVALOR_TOTAL(cursor.getDouble(cursor.getColumnIndex(VALOR_TOTAL)));
                pAux.setVALOR_FRETE(cursor.getDouble(cursor.getColumnIndex(VALOR_FRETE)));
                pAux.setTIPO_FRETE(cursor.getString(cursor.getColumnIndex(TIPO_FRETE)));
                pAux.setCOMPRADOR(cursor.getString(cursor.getColumnIndex(COMPRADOR)));
                pAux.setDATA_PEDIDO2(cursor.getString(cursor.getColumnIndex(DATA_PEDIDO2)));
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

    public ArrayList<HMAux> obterPedidosTodos_hm(){
        return obterPedidos_hm("", null);
    }

    public ArrayList<HMAux> obterPedidosComFiltro_hm(String codigo, String comprador, String status) {

        String filtro = "";
        ArrayList<String> arrParam = new ArrayList<>();

        if (!codigo.trim().isEmpty()) {
            filtro += " and " + PEDIDO_ELO7 + " like ? ";
            arrParam.add("%" + codigo + "%");
        }
        //
        if (!comprador.trim().isEmpty()) {
            filtro += " and " + COMPRADOR + " like ? ";
            arrParam.add("%" + comprador + "%");
        }
        //
        if (!status.trim().isEmpty()) {
            filtro += " and " + STATUS_ELO7 + " = ? ";
            arrParam.add(status);
        }

        String[] parametros =  new String[arrParam.size()];
        for (int i = 0; i < arrParam.size(); i++) {
            parametros[i] = arrParam.get(i);
        }

        return obterPedidos_hm(filtro, parametros);
    }

    public ArrayList<HMAux> obterPedidos_hm(String filtro, String[] parametro){
        ArrayList<HMAux> pedidos = new ArrayList<>();
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            String filtros = " 0 = 0" + filtro;
            String[] parametros = parametro;
            //
            StringBuilder SQLComando = new StringBuilder();
            SQLComando
                    .append("select ")
                    .append(IDPEDIDO)
                    .append(",")
                    .append(PEDIDO_ELO7)
                    .append(",")
                    .append(STATUS_ELO7)
                    .append(",")
                    .append(DATA_PEDIDO2)
                    .append(",")
                    .append(VALOR_TOTAL)
                    .append(",")
                    .append(COMPRADOR)
                    .append(" from ")
                    .append(TABELA)
                    .append(" where ")
                    .append(filtros);

            SQLComando
                    .append(" order by ")
                    .append(DATA_PEDIDO)
                    .append(" desc ");
            //
            cursor = db.rawQuery(
                    SQLComando.toString(),
                    parametros
            );
            //
            while (cursor.moveToNext()) {
                HMAux pAux = new HMAux();
                //
                pAux.put(HMAux.ID, String.valueOf(cursor.getLong(cursor.getColumnIndex(IDPEDIDO))));
                pAux.put(HMAux.TEXTO_01, cursor.getString(cursor.getColumnIndex(PEDIDO_ELO7)));
                pAux.put(HMAux.TEXTO_02, cursor.getString(cursor.getColumnIndex(COMPRADOR)));
                pAux.put(HMAux.TEXTO_03, cursor.getString(cursor.getColumnIndex(STATUS_ELO7)));
                pAux.put(HMAux.TEXTO_04, String.valueOf(cursor.getDouble(cursor.getColumnIndex(VALOR_TOTAL))));
                pAux.put(HMAux.TEXTO_05, cursor.getString(cursor.getColumnIndex(DATA_PEDIDO2)));
                //
                pedidos.add(pAux);
            }
            //
            cursor.close();
            cursor = null;
        } catch (Exception e) {
        }
        //
        fecharBanco();
        //
        return pedidos;
    }

    public void inserirListaPedidos(ArrayList<Pedido> pedidos){
        abrirBanco();
        //
        db.beginTransaction();
        //
        try {
            db.delete(TABELA, null, null);

            ContentValues cv = new ContentValues();

            for (Pedido pAux : pedidos){
                cv.clear();
                //
                cv.put(IDPEDIDO, pAux.getIDPEDIDO());
                cv.put(PEDIDO_ELO7, pAux.getPEDIDO_ELO7());
                cv.put(STATUS_ELO7, pAux.getSTATUS_ELO7());
                cv.put(DATA_PEDIDO, pAux.getDATA_PEDIDO());
                cv.put(VALOR_TOTAL, pAux.getVALOR_TOTAL());
                cv.put(VALOR_FRETE, pAux.getVALOR_FRETE());
                cv.put(TIPO_FRETE, pAux.getTIPO_FRETE());
                cv.put(COMPRADOR, pAux.getCOMPRADOR());
                cv.put(DATA_PEDIDO2, pAux.getDATA_PEDIDO2());
                cv.put(STATUS, Constantes.STATUS_PEDIDO_INSERIR);
                //
                db.insert(TABELA, null, cv);
            }

            db.setTransactionSuccessful();

        } catch (Exception e){
        } finally {
            db.endTransaction();
        }
        //
        fecharBanco();
    }
}
