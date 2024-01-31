package com.klab.desafiocartas.service.impl;

import com.klab.desafiocartas.domain.entity.Card;
import com.klab.desafiocartas.domain.repository.CardRepository;
import com.klab.desafiocartas.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository repository;

    @Override
    public Card saveCard(Card card) {
        card.setCardId((long) card.hashCode());
        return this.repository.save(card);
    }

    @Override
    public List<Card> saveCards(List<Card> cards) {
        cards.forEach(card -> card.setCardId((long) card.hashCode()));
        return repository.saveAll(cards);
    }
}
