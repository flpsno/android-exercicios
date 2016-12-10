package br.com.flpsno.h02_praticalistview.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Calendar;

import br.com.flpsno.h02_praticalistview.AlarmeAcao;
import br.com.flpsno.h02_praticalistview.R;
import br.com.flpsno.h02_praticalistview.banco.Constantes;
import br.com.flpsno.h02_praticalistview.dao.ConfiguracaoDao;
import br.com.flpsno.h02_praticalistview.dao.PedidoDao;
import br.com.flpsno.h02_praticalistview.model.Configuracao;
import br.com.flpsno.h02_praticalistview.transmissao.Transmissao_Env;
import br.com.flpsno.h02_praticalistview.transmissao.Transmissao_Rec;
import br.com.flpsno.mylib.ConstantesLib;
import br.com.flpsno.mylib.ToolBox;

/**
 * Created by Felipe on 05/11/2016.
 */

public class ServiceMain extends IntentService {

    private Context context;
    private ConfiguracaoDao configuracaoDao;
    private Configuracao config;
    //
    private String mensagem;
    //
    private PendingIntent pi;


    public ServiceMain() {
        super("ServiceMain");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        iniciarVariavel();

        try {
            Gson gson = new Gson();
            //
            Transmissao_Env env = new Transmissao_Env();
            env.setUsuario(config.getUSUARIO_WS());
            env.setSenha(ToolBox.md5(config.getSENHA_WS()));


            //
            String resultado = ToolBox.comunicacao(
                    Constantes.WEB_WS_PEDIDO,
                    gson.toJson(env)
            );
            //
            String par[] = resultado.split("#WSFLPSNO#");
            //
            switch (par.length) {
                case 2:
                    if (par[0].equals("0")) {
                        Transmissao_Rec rec = gson.fromJson(
                                par[1],
                                Transmissao_Rec.class
                        );
                        //
                        PedidoDao pedidoDao = new PedidoDao(getApplicationContext());
                        //
                        pedidoDao.inserirListaPedidos(rec.getPedidos());
                        //
                        mensagem = "Sincronismo Realizado com Sucesso!!!";
                    } else {
                        mensagem = "Erro: " + par[1];
                    }
                    break;
                default:
                    mensagem = "Erro: " + par[0];
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            notificacao();
        }



        /*Intent mIntent = new Intent(context, AlarmeAcao.class);
        //
        pi = PendingIntent.getBroadcast(
                context,
                0,
                mIntent,
                0
        );
        //
        AlarmManager am =
                (AlarmManager) context.getSystemService(ALARM_SERVICE);
        //
        am.setRepeating(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + (30 * 1000),
                (5 * 1000),
                pi
        );*/
    }

    private void iniciarVariavel() {
        context = getApplicationContext();

        // carrega as configurações pra ver se é para atualizar somente pelo wifi
        configuracaoDao = new ConfiguracaoDao(context);
        config = configuracaoDao.obterConfiguracao();

    }

    private void notificacao() {
        NotificationManager nm =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //
        Notification.Builder notificacao =
                new Notification.Builder(getApplicationContext());
        //
        notificacao
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentTitle("Sincromismo")
                .setContentText(mensagem);
        //
        notificacao.setDefaults(
                Notification.DEFAULT_SOUND |
                        Notification.DEFAULT_VIBRATE);
        //
        int VERSAO = Build.VERSION.SDK_INT;
        //
        if (VERSAO >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            nm.notify(10, notificacao.build());
        } else {
            nm.notify(10, notificacao.getNotification());
        }
    }
}
