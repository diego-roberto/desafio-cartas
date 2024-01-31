package com.klab.desafiocartas.rest.dto.record;

import java.util.List;

public record DeckResponse(
        boolean success,
        String deck_id,
        List<CardResponse> cards,
        int remaining

) {}
