package com.klab.desafiocartas.service.impl;

import com.klab.desafiocartas.constant.MessageConstants;
import com.klab.desafiocartas.domain.entity.*;
import com.klab.desafiocartas.domain.repository.RoundRepository;
import com.klab.desafiocartas.exception.OldRoundIdException;
import com.klab.desafiocartas.exception.RoundNotFoundException;
import com.klab.desafiocartas.rest.dto.PlayerDTO;
import com.klab.desafiocartas.rest.dto.request.RequestDTO;
import com.klab.desafiocartas.rest.dto.response.ResponseDTO;
import com.klab.desafiocartas.rest.dto.response.RoundDTO;
import com.klab.desafiocartas.service.HandService;
import com.klab.desafiocartas.service.RoundService;
import com.klab.desafiocartas.service.converter.PlayerDTOConverter;
import com.klab.desafiocartas.service.external.DeckApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class RoundServiceImpl implements RoundService {

    private final RoundRepository roundRepository;

    private final DeckApiService apiService;

    private final HandService handService;

    private final PlayerDTOConverter playerDTOConverter;

    Logger logger = Logger.getLogger(getClass().getName());

    private final RequestDTO request = new RequestDTO();

    private final int drawCount = request.getDrawSize();

    private final int playerCount = request.getNumberOfPlayers();


    @Override
    public RoundDTO newGame() {
        Deck deck = apiService.buildDeck();
        Round round = new Round(deck.getDeck_id(), deck.getRemaining());
        saveNewRound(round);

        return new RoundDTO(
                round.getRoundId(),
                round.getDeckId(),
                drawCount,
                playerCount
        );
    }

    @Override
    public ResponseDTO drawCards(Long roundId) {
        Round round = getRound(roundId);

        round = validateAndDrawCards(round);

        boolean hasWinner = round.getWinner().isPresent();
        boolean isTie = !hasWinner;

        PlayerDTO winnerDTO = getWinnerDTO(round, hasWinner);
        List<PlayerDTO> playersDTO = getPlayersDTO(roundId, hasWinner, winnerDTO, isTie);

        return new ResponseDTO(winnerDTO, playersDTO, hasWinner, isTie, round.getRoundId());
    }

    private Round getRound(Long roundId) {
        Round round = findByRoundId(roundId);
        if (round == null) {
            throw new RoundNotFoundException(MessageConstants.MSG_OLD_ROUND_ID, roundId);
        }
        validateRoundId(round, roundId);
        return round;
    }

    private Round validateAndDrawCards(Round round) {
        round = validateRemainingCards(round);
        return handlePlayersAndDrawCards(round);
    }

    private PlayerDTO getWinnerDTO(Round round, boolean hasWinner) {
        return hasWinner ? playerDTOConverter.toDTO(round.getWinner().get()) : null;
    }

    private List<PlayerDTO> getPlayersDTO(Long roundId, boolean hasWinner, PlayerDTO winnerDTO, boolean isTie) {
        List<PlayerDTO> playersDTO = Collections.emptyList();
        if (hasWinner && winnerDTO != null) {
            playersDTO = playerDTOConverter.toDTOListExcludingWinner(findPlayersByRoundId(roundId), winnerDTO.getPlayerId());
        }
        if (isTie) {
            playersDTO = findDrawPlayers(roundId);
        }
        return playersDTO;
    }

    private void validateRoundId(Round round, Long roundId) {
        Optional<Round> optLastRoundId = roundRepository.findLatestRoundByDeckId(round.getDeckId());
        if (optLastRoundId.isPresent()) {
            Long lastRoundId = optLastRoundId.get().getRoundId();
            if (lastRoundId > roundId) {
                throw new OldRoundIdException(MessageConstants.MSG_OLD_ROUND_ID, roundId);
            }
        }
    }

    private Round validateRemainingCards(Round round) {
        if (round.getRemaining() < (drawCount * playerCount)) {
            return reshuffleAndCreateNewRound(round);
        }
        return round;
    }

    private Round handlePlayersAndDrawCards(Round round) {
        if (round.getPlayers() == null || round.getPlayers().isEmpty()) {
            return createPlayersForRound(round);
        } else {
            List<Player> updatedPlayers = round.getPlayers().stream()
                    .map(player -> {
                        Hand newHand = drawFromDeckToPlayers(round);
                        player.setPlayerHand(newHand);
                        return player;
                    })
                    .collect(Collectors.toList());

            round.setPlayers(updatedPlayers);
            return roundRepository.save(round);
        }
    }

    private Round createPlayersForRound(Round round) {
        List<Player> players = IntStream.range(0, playerCount)
                .mapToObj(i -> createPlayerAndDraw(round))
                .collect(Collectors.toList());

        round.setPlayers(players);
        return roundRepository.save(round);
    }

    private Player createPlayerAndDraw(Round round) {
        Player player = new Player();
        player.setRound(round);
        player.setPlayerHand(drawFromDeckToPlayers(round));
        return player;
    }

    public Hand drawFromDeckToPlayers(Round round) {
        List<Card> cards = apiService.drawFromDeck(round.getDeckId(), String.valueOf(drawCount));
        return handService.saveHand(cards);
    }

    public Round reshuffleAndCreateNewRound(Round round){
        int remaining = apiService.reshuffleDeck(round.getDeckId()).getRemaining();
        Round newRound = new Round(round.getDeckId(), remaining);
        if (round.getPlayers() != null){
            newRound.setPlayers(new ArrayList<>(round.getPlayers()));
        }
        saveNewRound(newRound);
        logger.info(MessageConstants.MSG_NEW_ROUND_CREATED+": "+round.getDeckId()+"!");
        logger.info(MessageConstants.MSG_NEW_ROUND_ID+": "+newRound.getRoundId()+"!");
        return newRound;
    }

    public Round findByRoundId(Long roundId) {
        return roundRepository.findById(roundId)
                .orElseThrow(() -> new RoundNotFoundException(MessageConstants.MSG_ROUND_NOT_FOUND, roundId));
    }

    public List<PlayerDTO> findPlayersByRoundId(Long roundId) {
        Round round = findByRoundId(roundId);
        return playerDTOConverter.toDTOList(round.getPlayers());
    }

    private List<PlayerDTO> findDrawPlayers(Long roundId) {
        Round round = findByRoundId(roundId);
        int maxSum = round.getPlayers().stream().mapToInt(Player::getPlayerHandValue).max().orElse(0);

        return round.getPlayers().stream()
                .filter(player -> player.getPlayerHandValue() == maxSum)
                .map(playerDTOConverter::toDTO)
                .collect(Collectors.toList());
    }

    public Round saveNewRound(Round round) {
        return roundRepository.saveAndFlush(round);
    }

}
