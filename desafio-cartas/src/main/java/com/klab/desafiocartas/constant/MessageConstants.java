package com.klab.desafiocartas.constant;

public class MessageConstants {

    private MessageConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String MSG_API_ERROR = "Erro ao chamar a API de deck";
    public static final String MSG_GAME_ERROR = "Erro ao criar jogo";
    public static final String MSG_GAME_CREATED = "Jogo criado com sucesso";
    public static final String MSG_ROUND_SUCCESS = "Cartas sorteadas com sucesso";
    public static final String MSG_NOT_FOUND = "Não encontrado / erro interno";
    public static final String MSG_DECK_NOT_FOUND = "ID do deck não encontrada";
    public static final String MSG_ROUND_NOT_FOUND = "ID de Jogo não encontrada";
    public static final String MSG_NEW_ROUND_CREATED =  "Nova rodada criada para o Baralho";
    public static final String MSG_NEW_ROUND_ID =  "Novo ID de rodada é";
    public static final String MSG_OLD_ROUND_ID =  "Rodada encerrada! Por favor utilize uma rodada aberta";

}
