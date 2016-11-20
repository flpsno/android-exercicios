package br.com.flpsno.mylib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Felipe on 05/11/2016.
 */

public class ToolBox {

    public static String comunicacao(String urlEn, String params) {
        StringBuilder sb = new StringBuilder();
        //
        URL url;
        HttpURLConnection conn = null;
        //
        try {

            url = new URL(urlEn);
            conn = (HttpURLConnection) url.openConnection();
            //
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //
            StringBuilder paramsFormatados = new StringBuilder();
            paramsFormatados.append(URLEncoder.encode("json", "UTF-8"));
            paramsFormatados.append("=");
            paramsFormatados.append(URLEncoder.encode(params, "UTF-8"));
            //
            // Envio dos Parametros
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(paramsFormatados.toString());
            writer.flush();
            writer.close();
            os.close();
            //
            sb.append(readStreamHugo(conn.getInputStream()));
        } catch (Exception e) {
            sb.append(e.toString());
        }

        return sb.toString();
    }

    // Ler a Resposta do Servidor (OBS Nem sempre vem dele)
    private static String readStreamHugo(InputStream inputStream) {
        Reader reader = null;
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        //
        try {

            reader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8")
            );
            //
            int n;
            //
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //
        return writer.toString();
    }
/*
    public String checkConnectionStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                 context.getSystemService(CONNECTIVITY_SERVICE);
        //
        NetworkInfo mWifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mMobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        //
        if (mWifi.isAvailable() && mWifi.isConnected()) {
            return ConstantesLib.STATUS_REDE_WIFI;
        }
        //
        if (mMobile.isAvailable() && mMobile.isConnected()) {
            return ConstantesLib.STATUS_REDE_OPERADORA;
        }
        //
        return ConstantesLib.STATUS_REDE_SEM_CONEXAO;
    }*/

}
