package br.com.flpsno.h02_praticalistview.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Felipe on 17/09/2016.
 */
public class Dao {

    private Context context;
    protected SQLiteDatabase db;

    public Dao(Context context) {
        this.context = context;
    }

    public void abrirBanco() {
        ConexaoDB conexaoDB = new ConexaoDB(context);
        //
        this.db = conexaoDB.getWritableDatabase();
    }

    public void fecharBanco() {
        if (db != null) {
            db.close();
        }
    }
}
