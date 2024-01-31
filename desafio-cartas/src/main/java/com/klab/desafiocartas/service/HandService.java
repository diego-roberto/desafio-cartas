package com.klab.desafiocartas.service;

import com.klab.desafiocartas.domain.entity.Card;
import com.klab.desafiocartas.domain.entity.Hand;

import java.util.List;

public interface HandService {

    public Hand saveHand();

    public Hand saveHand(List<Card> cards);

}
