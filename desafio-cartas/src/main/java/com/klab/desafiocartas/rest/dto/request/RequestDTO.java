package com.klab.desafiocartas.rest.dto.request;

import com.klab.desafiocartas.constant.ParamsConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    private int numberOfPlayers = Integer.parseInt(ParamsConstants.NUMBER_OF_PLAYERS);
    private int drawSize = Integer.parseInt(ParamsConstants.DRAW_SIZE);

}
