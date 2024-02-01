package com.klab.desafiocartas.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hands")
public class Hand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hand_id")
    private Long handId;

    @OneToMany(mappedBy = "hand", cascade = CascadeType.ALL)
    private List<Card> cards;

    public int getHandSum() {
        if (cards == null) {
            return 0;
        }
        return cards.stream().mapToInt(Card::getIntValue).sum();
    }

}
