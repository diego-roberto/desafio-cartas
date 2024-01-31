package com.klab.desafiocartas.domain.repository;

import com.klab.desafiocartas.domain.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
