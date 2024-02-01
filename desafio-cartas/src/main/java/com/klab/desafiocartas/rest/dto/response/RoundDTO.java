package com.klab.desafiocartas.rest.dto.response;

import lombok.Data;

@Data
public class RoundDTO {
    Long roundId;
    String deckId;
    int players;
    int cardsPlayer;

    public RoundDTO(Long roundId, String deckId, int drawCount, int playerCount) {
        this.roundId = roundId;
        this.deckId = deckId;
        this.cardsPlayer = drawCount;
        this.players = playerCount;
    }
}
