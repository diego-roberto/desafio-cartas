package com.klab.desafiocartas.exception;

public class OldRoundIdException extends RuntimeException{
    public OldRoundIdException(String msg, Long roundId) {
        super(msg +": "+ roundId);
    }
}
