package com.klab.desafiocartas.domain.entity;

import lombok.Data;

import java.util.List;

@Data
public class Deck {

    private boolean success;
    private String deck_id;
    private boolean shuffled;
    private int remaining;

}
