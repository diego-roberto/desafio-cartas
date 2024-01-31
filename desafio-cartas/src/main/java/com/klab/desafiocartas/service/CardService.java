package com.klab.desafiocartas.service;

import com.klab.desafiocartas.domain.entity.Card;

import java.util.List;

public interface CardService {

    public Card saveCard(Card card);

    public List<Card> saveCards(List<Card> cards);

}
