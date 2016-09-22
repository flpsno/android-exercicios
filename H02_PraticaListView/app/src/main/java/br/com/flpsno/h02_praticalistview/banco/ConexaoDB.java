package br.com.flpsno.h02_praticalistview.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Felipe on 17/09/2016.
 */
public class ConexaoDB extends SQLiteOpenHelper {

    public ConexaoDB(Context context) {
        super(context, Constantes.DATA_BASE, null, Constantes.VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder sb = new StringBuilder();
        //
        sb.append("CREATE TABLE if not exists produtos (\n" +
                "    idproduto      BIGINT PRIMARY KEY\n" +
                "                          NOT NULL,\n" +
                "    descricao      TEXT   NOT NULL,\n" +
                "    qtd_min        INT    NOT NULL,\n" +
                "    valor_unitario DOUBLE NOT NULL\n" +
                ");");
        //
        sb.append("CREATE TABLE if not exists clientes (\n" +
                "    idcliente      BIGINT PRIMARY KEY\n" +
                "                          NOT NULL,\n" +
                "    nome           TEXT   NOT NULL,\n" +
                "    email          TEXT   NOT NULL,\n" +
                "    telefone       TEXT   NOT NULL,\n" +
                "    estado         TEXT   NOT NULL\n" +
                ");");
        //

        String [] comandos = sb.toString().split(";");
        //
        for (int i = 0; i < comandos.length; i++) {
            db.execSQL(comandos[i].toLowerCase());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        StringBuilder sb = new StringBuilder();
        //
        sb.append("DROP TABLE IF EXISTS produtos;");
        sb.append("DROP TABLE IF EXISTS clientes;");
        //
        String [] comandos = sb.toString().split(";");
        //
        for (int i = 0; i < comandos.length; i++) {
            db.execSQL(comandos[i].toLowerCase());
        }
        //
        onCreate(db);
    }
}
