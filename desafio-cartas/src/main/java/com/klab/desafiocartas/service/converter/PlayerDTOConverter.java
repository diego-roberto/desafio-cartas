package com.klab.desafiocartas.service.converter;

import com.klab.desafiocartas.domain.entity.Card;
import com.klab.desafiocartas.domain.entity.Player;
import com.klab.desafiocartas.rest.dto.CardDTO;
import com.klab.desafiocartas.rest.dto.PlayerDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlayerDTOConverter {

    private final ModelMapper modelMapper;

    public PlayerDTO toDTO(Player player) {
        PlayerDTO playerDTO = modelMapper.map(player, PlayerDTO.class);

        int handSum = player.getPlayerHand().getCards().stream()
                .mapToInt(Card::getIntValue)
                .sum();
        playerDTO.setSum(handSum);

        List<CardDTO> cardDTOList = player.getPlayerHand().getCards().stream()
                .map(CardDTO::fromCard)
                .collect(Collectors.toList());

        playerDTO.setCards(cardDTOList);

        return playerDTO;
    }

    public List<PlayerDTO> toDTOList(List<Player> players) {
        return players.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<PlayerDTO> toDTOListExcludingWinner(List<PlayerDTO> players, Long winnerId) {
        return players.stream()
                .filter(player -> !player.getPlayerId().equals(winnerId))
                .collect(Collectors.toList());
    }
}
