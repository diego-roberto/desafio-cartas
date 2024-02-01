package com.klab.desafiocartas.rest.dto.response;

import com.klab.desafiocartas.rest.dto.PlayerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    PlayerDTO winner;
    List<PlayerDTO> players;
    boolean hasWinner;
    boolean isTie;
    Long roundId;
}
