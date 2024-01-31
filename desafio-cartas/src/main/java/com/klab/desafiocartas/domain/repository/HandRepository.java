package com.klab.desafiocartas.domain.repository;

import com.klab.desafiocartas.domain.entity.Hand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HandRepository extends JpaRepository<Hand, Long> {
}
