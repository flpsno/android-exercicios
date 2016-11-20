package br.com.flpsno.h02_praticalistview.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.flpsno.h02_praticalistview.banco.Dao;
import br.com.flpsno.h02_praticalistview.banco.HMAux;
import br.com.flpsno.h02_praticalistview.model.Cliente;

/**
 * Created by Felipe on 21/09/2016.
 */
public class ClienteDao extends Dao {

    private static final String TABELA = "clientes";
    private static final String IDCLIENTE = "idcliente";
    private static final String NOME = "nome";
    private static final String EMAIL = "email";
    private static final String TELEFONE = "telefone";
    private static final String CIDADE = "cidade";
    private static final String ESTADO = "estado";

    public ClienteDao(Context context) {
        super(context);
    }

    public void incluirCliente(Cliente cliente) {
        abrirBanco();
        //
        ContentValues cv = new ContentValues();
        //
        cv.put(IDCLIENTE, cliente.getIdcliente());
        cv.put(NOME, cliente.getNome());
        cv.put(EMAIL, cliente.getEmail());
        cv.put(TELEFONE, cliente.getTelefone());
        cv.put(CIDADE, cliente.getCidade());
        cv.put(ESTADO, cliente.getEstado());
        //
        db.insert(
                TABELA,
                null,
                cv
        );
        //
        fecharBanco();
    }

    public void atualizarCliente(Cliente cliente) {
        abrirBanco();
        //
        ContentValues cv = new ContentValues();
        //
        String filtro = IDCLIENTE + " = ? ";
        String [] parametros = {
            String.valueOf(cliente.getIdcliente())
        };
        //
        cv.put(NOME, cliente.getNome());
        cv.put(EMAIL, cliente.getEmail());
        cv.put(TELEFONE, cliente.getTelefone());
        cv.put(CIDADE, cliente.getCidade());
        cv.put(ESTADO, cliente.getEstado());
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

    public void apagarCliente(long idcliente) {
        abrirBanco();
        //
        String filtro = IDCLIENTE + " = ? ";
        String [] parametros = {
                String.valueOf(idcliente)
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

    public Cliente obterClienteByID(long idcliente) {
        Cliente cAux = null;
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            String [] parametros = {
                String.valueOf(idcliente)
            };
            //
            StringBuilder SQLComando = new StringBuilder();
            SQLComando
                    .append("select * from ")
                    .append(TABELA)
                    .append(" where ")
                    .append(IDCLIENTE)
                    .append(" = ? ");
            cursor = db.rawQuery(SQLComando.toString(), parametros);
            //
            while (cursor.moveToNext()) {
                cAux = new Cliente();
                //
                cAux.setIdcliente(cursor.getLong(cursor.getColumnIndex(IDCLIENTE)));
                cAux.setNome(cursor.getString(cursor.getColumnIndex(NOME)));
                cAux.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
                cAux.setTelefone(cursor.getString(cursor.getColumnIndex(TELEFONE)));
                cAux.setCidade(cursor.getString(cursor.getColumnIndex(CIDADE)));
                cAux.setEstado(cursor.getString(cursor.getColumnIndex(ESTADO)));
            }
            //
            cursor.close();
            cursor = null;
        } catch (Exception e) {
        }
        //
        fecharBanco();
        //
        return cAux;
    }

    public ArrayList<HMAux> obterClientes_hm() {
        ArrayList<HMAux> clientes = new ArrayList<>();
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            StringBuilder SQLComando = new StringBuilder();
            SQLComando
                    .append("select ")
                    .append(IDCLIENTE)
                    .append(",")
                    .append(NOME)
                    .append(",")
                    .append(EMAIL)
                    .append(" from ")
                    .append(TABELA)
                    .append(" order by ")
                    .append(NOME);
            cursor = db.rawQuery(SQLComando.toString(), null);
            //
            while (cursor.moveToNext()) {
                HMAux cAux = new HMAux();
                //
                cAux.put(HMAux.ID, String.valueOf(cursor.getLong(cursor.getColumnIndex(IDCLIENTE))));
                cAux.put(HMAux.TEXTO_01, cursor.getString(cursor.getColumnIndex(NOME)));
                cAux.put(HMAux.TEXTO_02, cursor.getString(cursor.getColumnIndex(EMAIL)));
                //
                clientes.add(cAux);
            }
            //
            cursor.close();
            cursor = null;

        } catch (Exception e) {
        }
        //
        fecharBanco();
        return clientes;
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
                    .append(IDCLIENTE)
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
