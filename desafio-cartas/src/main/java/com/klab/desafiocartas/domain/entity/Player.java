package com.klab.desafiocartas.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_hand_id")
    private Hand playerHand;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private Round round;

    public Player() {
        this.playerHand = new Hand();
    }

    public int getPlayerHandValue() {
        return this.playerHand.getHandSum();
    }

}
