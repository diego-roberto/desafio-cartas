package com.klab.desafiocartas.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoundDTO {
    Long roundId;
    String deckId;
    int players;
    int cardsPlayer;
}
