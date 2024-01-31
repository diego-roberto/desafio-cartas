package com.klab.desafiocartas.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rounds")
public class Round {

    @Id
    @Column(name = "round_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roundId;

    @Column(name = "deck_id")
    private String deckId;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL)
    private List<Player> players;

    @Column(name = "remaining")
    private int  remaining;

    public Round(String deckId, int remaining) {
        this.deckId = deckId;
        this.remaining = remaining;
    }

    public Round(String deckId) {
        this.deckId = deckId;
    }

    public Optional<Player> getWinner() {
        return players.stream()
                .max(Comparator.comparingInt(Player::getPlayerHandValue))
                .filter(player -> players.stream()
                        .noneMatch(otherPlayer ->
                                otherPlayer != player &&
                                otherPlayer.getPlayerHandValue() == player.getPlayerHandValue()
                        )
                );
    }

    public void addPlayer(Player player) {
        if (this.players == null) {
            this.players = new ArrayList<>();
        }
        this.players.add(player);
    }

}
