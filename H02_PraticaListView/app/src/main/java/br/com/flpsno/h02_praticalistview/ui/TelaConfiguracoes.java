package br.com.flpsno.h02_praticalistview.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.com.flpsno.h02_praticalistview.R;
import br.com.flpsno.h02_praticalistview.dao.ConfiguracaoDao;
import br.com.flpsno.h02_praticalistview.model.Configuracao;
import br.com.flpsno.mylib.ToolBox;

/**
 * Created by Felipe on 21/10/2016.
 */

public class TelaConfiguracoes extends AppCompatActivity {

    private Context context;
    //
    private Toolbar toolbar;
    //
    private CheckBox cb_somente_wifi;
    private EditText et_usuario_ws;
    private EditText et_senha_ws;
    private Button btn_salvar;
    //
    private ConfiguracaoDao configuracaoDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaconfiguracoes);

        iniciarVariavel();
        iniciarAcao();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void iniciarVariavel() {
        context = getBaseContext();
        //
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //
        cb_somente_wifi = (CheckBox) findViewById(R.id.telaconfiguracoes_cb_somente_wifi);
        et_usuario_ws = (EditText) findViewById(R.id.telaconfiguracoes_et_usuario_ws);
        et_senha_ws = (EditText) findViewById(R.id.telaconfiguracoes_et_senha_ws);
        btn_salvar = (Button) findViewById(R.id.telaconfiguracoes_btn_salvar);
        //
        configuracaoDao = new ConfiguracaoDao(context);
        //
        Configuracao config = configuracaoDao.obterConfiguracao();
        cb_somente_wifi.setChecked(config.isSOMENTE_WIFI());
        et_usuario_ws.setText(config.getUSUARIO_WS());
        et_senha_ws.setText(config.getSENHA_WS());
    }

    private void iniciarAcao() {
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarConfiguracoes();
            }
        });
    }

    private void salvarConfiguracoes() {
        Configuracao cAux = new Configuracao();
        //
        cAux.setSOMENTE_WIFI(cb_somente_wifi.isChecked());
        cAux.setUSUARIO_WS(et_usuario_ws.getText().toString());
        cAux.setSENHA_WS(et_senha_ws.getText().toString());
        //
        configuracaoDao.atualizaConfiguracao(cAux);
        //
        Toast.makeText(
                context,
                "Salvo com sucesso!!",
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent mIntent = new Intent(context, MainActivity.class);
        startActivity(mIntent);
        //
        finish();
    }
}
