package com.klab.desafiocartas.domain.repository;

import com.klab.desafiocartas.domain.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoundRepository extends JpaRepository<Round, Long> {

    Optional<Round> findByDeckId(String deckId);

    @Query("SELECT r FROM Round r " +
            "WHERE r.deckId = :deckId AND r.roundId = " +
                "(SELECT MAX(rr.roundId) FROM Round rr WHERE rr.deckId = :deckId)")
    Optional<Round> findLatestRoundByDeckId(@Param("deckId") String deckId);

}
