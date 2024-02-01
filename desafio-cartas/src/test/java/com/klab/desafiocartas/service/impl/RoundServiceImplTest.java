package com.klab.desafiocartas.service.impl;

import com.klab.desafiocartas.DesafioCartasApplication;
import com.klab.desafiocartas.domain.entity.Deck;
import com.klab.desafiocartas.domain.entity.Round;
import com.klab.desafiocartas.domain.repository.RoundRepository;
import com.klab.desafiocartas.exception.RoundNotFoundException;
import com.klab.desafiocartas.rest.dto.response.RoundDTO;
import com.klab.desafiocartas.service.external.DeckApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = DesafioCartasApplication.class)
class RoundServiceImplTest {

    @Autowired
    private RoundServiceImpl roundService;

    @MockBean
    private RoundRepository roundRepository;

    @MockBean
    private DeckApiService apiService;

    @Test
    @DisplayName("Simulando o comportamento do serviço para construir um baralho")
    void testNewGame() {
        // Configuração do mock para apiService.buildDeck()
        Deck defaultDeck = new Deck();
        defaultDeck.setDeck_id("deckId");
        defaultDeck.setRemaining(52);
        Mockito.when(apiService.buildDeck()).thenReturn(defaultDeck);

        // Configuração do mock para roundRepository.save()
        Mockito.when(roundRepository.save(Mockito.any(Round.class))).thenAnswer(invocation -> {
            Round savedRound = invocation.getArgument(0);
            savedRound.setRoundId(1L);
            return savedRound;
        });

        RoundDTO roundDTO = roundService.newGame();
        assertNotNull(roundDTO);
        assertEquals("deckId", roundDTO.getDeckId());
        assertEquals(52, defaultDeck.getRemaining());
        assertEquals(4, roundDTO.getPlayers());
        assertEquals(5, roundDTO.getCardsPlayer());
    }

    @Test
    @DisplayName("Teste para lidar com roundId nulo")
    void testDrawCardsWithNullRoundId() {
        assertThrows(RoundNotFoundException.class, () -> roundService.drawCards(null));
    }

}
