package com.klab.desafiocartas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {
    Long playerId;
    List<CardDTO> cards;
    int sum;
}
