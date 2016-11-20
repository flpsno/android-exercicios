package br.com.flpsno.h02_praticalistview.service;

import android.app.IntentService;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by Felipe on 05/11/2016.
 */

public class ServiceMain extends IntentService {

    public ServiceMain() {
        super("ServiceMain");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Calendar cAux = Calendar.getInstance();
        //
        cAux.set(
                Calendar.SECOND,
                cAux.get(Calendar.SECOND) + 5
        );
        //
    //    Intent mIntent = new Intent(getApplicationContext(), Alarm)

    }
}
