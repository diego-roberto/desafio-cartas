package com.klab.desafiocartas.service;

import com.klab.desafiocartas.rest.dto.response.RoundDTO;
import com.klab.desafiocartas.rest.dto.response.ResponseDTO;

public interface RoundService {

    RoundDTO newGame();

    ResponseDTO drawCards(Long gameId);

}
