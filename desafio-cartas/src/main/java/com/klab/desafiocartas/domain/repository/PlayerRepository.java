package com.klab.desafiocartas.domain.repository;

import com.klab.desafiocartas.domain.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
