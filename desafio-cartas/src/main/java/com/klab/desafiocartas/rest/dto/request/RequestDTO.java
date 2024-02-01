package com.klab.desafiocartas.rest.dto.request;

import com.klab.desafiocartas.constant.ParamsConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    private int numberOfPlayers = ParamsConstants.getNumberOfPlayers();
    private int drawSize = ParamsConstants.getDrawSize();

}
