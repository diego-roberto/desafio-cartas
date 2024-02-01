package com.klab.desafiocartas.domain.entity;

import com.klab.desafiocartas.DesafioCartasApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = DesafioCartasApplication.class)
class RoundTest {

    private Round round;

    @BeforeEach
    void setUp() {
        round = new Round("deckId", 52);
        round.setPlayers(Arrays.asList(
                createPlayerWithRegularHand(),
                createPlayerWithRegularHand(),
                createPlayerWithRegularHand(),
                createPlayerWithWinnerHand()
        ));
    }

    private Player createPlayerWithRegularHand() {
        Player player = new Player();
        player.setPlayerHand(createHandWithRegularCards());
        return player;
    }

    private Hand createHandWithRegularCards() {
        Hand hand = new Hand();
        List<Card> cards = new ArrayList<>();

        List<String> suits = Arrays.asList("DIAMONDS", "HEARTS", "SPADES", "CLUBS");
        List<String> values = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "JACK", "QUEEN", "KING", "ACE");

        for (int i = 0; i < 5; i++) {
            String currentSuit = suits.get(i % suits.size());
            String currentValue = values.get(i % values.size());
            Card currentCard = new Card(currentSuit, currentValue);
            cards.add(currentCard);
        }

        hand.setCards(cards);
        return hand;
    }

    private Player createPlayerWithWinnerHand() {
        Player player = new Player();
        player.setPlayerHand(createHandWithWinnerCards());
        return player;
    }

    private Hand createHandWithWinnerCards() {
        Hand hand = new Hand();
        List<Card> cards = Arrays.asList(
                new Card("DIAMONDS", "10"),
                new Card("HEARTS", "JACK"),
                new Card("SPADES", "QUEEN"),
                new Card("CLUBS", "KING"),
                new Card("DIAMONDS", "ACE")
        );
        hand.setCards(cards); //47
        return hand;
    }

    @Test
    @DisplayName("Teste para verificar o método getWinner quando há vencedor")
    void testGetWinner() {
        Optional<Player> winner = round.getWinner();

        assertTrue(winner.isPresent());
        assertEquals(47, winner.get().getPlayerHandValue());
    }

    @Test
    @DisplayName("Teste para verificar o método getWinner quando há empate")
    void testGetWinnerWithTie() {
        round.getPlayers().get(3).clearCards(); // Remove as cartas do quarto jogador para criar um empate
        Optional<Player> winner = round.getWinner();

        assertFalse(winner.isPresent());
    }

}
