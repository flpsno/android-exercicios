package br.com.flpsno.h02_praticalistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TelaPedidos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telapedidos);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
