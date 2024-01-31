package com.klab.desafiocartas.rest.controller;

import com.klab.desafiocartas.constant.MessageConstants;
import com.klab.desafiocartas.rest.dto.response.RoundDTO;
import com.klab.desafiocartas.rest.dto.response.ResponseDTO;
import com.klab.desafiocartas.service.impl.RoundServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/play")
@Api("API do c-baralho!")
public class DeckController {

    private final RoundServiceImpl service;

    @Transactional
    @GetMapping("/new-game")
    @ApiOperation("Cria um novo jogo")
    @ApiResponses({
            @ApiResponse(code = 200, message = MessageConstants.MSG_GAME_CREATED),
            @ApiResponse(code = 404, message = MessageConstants.MSG_NOT_FOUND),
            @ApiResponse(code = 500, message = MessageConstants.MSG_GAME_ERROR)
    })
    @ResponseStatus(HttpStatus.CREATED)
    public RoundDTO startNewGame() {
        return service.newGame();
    }

    @GetMapping("/{roundId}/draw-cards")
    @ApiOperation("Executa uma rodada")
    @ApiResponses({
            @ApiResponse(code = 200, message = MessageConstants.MSG_ROUND_SUCCESS),
            @ApiResponse(code = 404, message = MessageConstants.MSG_ROUND_NOT_FOUND),
            @ApiResponse(code = 500, message = MessageConstants.MSG_API_ERROR)
    })
    public ResponseDTO drawCardsForRound(@PathVariable String roundId) {
        return service.drawCards(Long.valueOf(roundId));
    }

}
