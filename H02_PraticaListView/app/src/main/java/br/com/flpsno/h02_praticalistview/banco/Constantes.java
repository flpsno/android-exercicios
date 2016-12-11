package br.com.flpsno.h02_praticalistview.banco;

/**
 * Created by Felipe on 10/09/2016.
 */
public class Constantes {

    // DATA BASE
    public static final String DATA_BASE = "banco.db3";
    public static final int VERSAO = 8;


    // PARAMETROS
    public static final String PARAMETRO_ID = "parametro_id";


    // NOTIFICAÇÃO
    public static final int ID_NOTIFICACAO_TELAEDICAOCLIENTES = 10;
    public static final int ID_NOTIFICACAO_TELAEDICAOPRODUTOS = 20;


    // URL WB
    public static final String WEB_WS_PEDIDO = "http://flpsno.com.br/restserver/index.php/api/pedido";

    // PEDIDOS
    public static final String STATUS_PEDIDO_OK = "O";
    public static final String STATUS_PEDIDO_INSERIR = "I";
    public static final String STATUS_PEDIDO_ATUALIZAR = "A";
    public static final String STATUS_PEDIDO_DELETAR = "D";



    public static final String [] estados = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES",
            "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS",
            "RO", "RR", "SC", "SP", "SE"};
}
