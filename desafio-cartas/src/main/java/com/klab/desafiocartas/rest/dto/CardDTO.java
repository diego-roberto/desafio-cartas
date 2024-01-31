package com.klab.desafiocartas.rest.dto;

import com.klab.desafiocartas.domain.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    int value;
    String suit;

    public static CardDTO fromCard(Card card) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setSuit(card.getSuit());
        cardDTO.setValue(card.getIntValue());
        return cardDTO;
    }

}
