package com.java.gestaoemprestimopix.repository;

import com.java.gestaoemprestimopix.entity.Simulacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface SimulacaoRepository extends JpaRepository<Simulacao,Long> {

    List<Simulacao> findByDataHoraExpiracaoBefore(Instant dataHora);
}
