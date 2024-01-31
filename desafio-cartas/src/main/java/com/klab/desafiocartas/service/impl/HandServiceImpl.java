package com.klab.desafiocartas.service.impl;

import com.klab.desafiocartas.domain.entity.Card;
import com.klab.desafiocartas.domain.entity.Hand;
import com.klab.desafiocartas.domain.repository.HandRepository;
import com.klab.desafiocartas.service.HandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HandServiceImpl implements HandService {

    private final HandRepository repository;

    private final CardServiceImpl cardService;

    @Override
    public Hand saveHand() {
        Hand hand = new Hand();
        hand.setHandId((long) hand.hashCode());
        return repository.save(hand);
    }

    @Override
    public Hand saveHand(List<Card> cards) {
        Hand hand = saveHand();
        cards.forEach(card -> card.setHand(hand));
        cards = cardService.saveCards(cards);
        hand.setCards(cards);
        return repository.save(hand);
    }
}
