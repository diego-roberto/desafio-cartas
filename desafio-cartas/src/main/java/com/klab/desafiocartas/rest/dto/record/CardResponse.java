package com.klab.desafiocartas.rest.dto.record;

import java.util.Map;

public record CardResponse(
        String code,
        String image,
        Map<String,String> images,
        String value,
        String suit
) {}
