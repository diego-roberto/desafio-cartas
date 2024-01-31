package com.klab.desafiocartas.service.external;

import com.klab.desafiocartas.constant.MessageConstants;
import com.klab.desafiocartas.domain.entity.Card;
import com.klab.desafiocartas.domain.entity.Deck;
import com.klab.desafiocartas.domain.entity.Round;
import com.klab.desafiocartas.domain.repository.RoundRepository;
import com.klab.desafiocartas.rest.dto.record.DeckResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeckApiService {

    private static final String DECK_OF_CARDS_API_URL = "https://deckofcardsapi.com/api/deck/";
    private static final String NEW_SHUFFLE_CARDS_URL = "new/shuffle/?deck_count=1";
    private static final String SHUFFLE_CARDS_URL = "/shuffle/";
    private static final String DRAW_A_CARD_URL = "/draw/?count=";
    private final RestTemplate template = new RestTemplate();

    private final RoundRepository roundRepository;


    public Deck buildDeck() {
        String url = DECK_OF_CARDS_API_URL + NEW_SHUFFLE_CARDS_URL;

        try {
            return template.getForObject(url, Deck.class);
        } catch (RestClientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public Deck reshuffleDeck(String deckId) {
        String url = DECK_OF_CARDS_API_URL + deckId + SHUFFLE_CARDS_URL;

        try {
            return template.getForObject(url, Deck.class);
        } catch (RestClientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public List<Card> drawFromDeck(String deckId, String cardsCount) {
        String uri = DECK_OF_CARDS_API_URL + deckId + DRAW_A_CARD_URL + cardsCount;

        try {
            DeckResponse response = template.getForObject(uri, DeckResponse.class);
            updateRemainingCards(response.deck_id(), response.remaining());

            return Objects.requireNonNull(response).cards().stream()
                    .map(card -> new Card(card.suit(), card.value()))
                    .collect(Collectors.toList());

        } catch (RestClientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MessageConstants.MSG_API_ERROR + e.getMessage(), e);
        }
    }

    public void updateRemainingCards(String deckId, int remaining){
        Optional<Round> optional = roundRepository.findLatestRoundByDeckId(deckId);
        if (optional.isPresent()){
            Round round = optional.get();
            round.setRemaining(remaining);
            roundRepository.saveAndFlush(round);
        }
    }

}
