package com.klab.desafiocartas.exception;

public class DeckNotFoundException extends RuntimeException {

    public DeckNotFoundException(String msg, String deckId) {
        super(msg +": "+ deckId);
    }

}
