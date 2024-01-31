package com.klab.desafiocartas.exception;

public class RoundNotFoundException extends RuntimeException{
    public RoundNotFoundException(String msg, Long roundId) {
        super(msg +": "+ roundId);
    }
}
