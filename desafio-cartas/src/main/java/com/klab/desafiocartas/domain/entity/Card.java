package com.klab.desafiocartas.domain.entity;

import com.klab.desafiocartas.constant.ParamsConstants;
import com.klab.desafiocartas.constant.ValueConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "card_suit")
    private String suit;

    @Column(name = "card_value")
    private String value;

    @ManyToOne
    @JoinColumn(name="hand_id")
    private Hand hand;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    public int getIntValue() {
        return switch (getValue()) {
            case "ACE" -> ValueConstants.ACE;
            case "JACK" -> ValueConstants.JACK;
            case "QUEEN" -> ValueConstants.QUEEN;
            case "KING" -> ValueConstants.KING;
            default -> Integer.parseInt(getValue());
        };
    }

}
