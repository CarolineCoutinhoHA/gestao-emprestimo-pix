package com.java.gestaoemprestimopix.service;

import com.java.gestaoemprestimopix.entity.Simulacao;
import com.java.gestaoemprestimopix.repository.SimulacaoRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class SimulacaoService {
    private final SimulacaoRepository repository;

    private static final Duration tempoDeVida = Duration.ofMinutes(10);

    public SimulacaoService(SimulacaoRepository repository){
        this.repository=repository;
    }

    public void salvar(Simulacao simulacao){
        Instant agora = Instant.now();
        Instant expiracao = agora.plus(tempoDeVida);

        simulacao.setDataHoraExpiracao(expiracao);

        repository.save(simulacao);
    }

    @Scheduled(fixedRate = 60000)
    public void deletar(){
        Instant agora = Instant.now();

        List<Simulacao> expiradas = repository.findByDataHoraExpiracaoBefore(agora);

        if (!expiradas.isEmpty()){
            repository.deleteAll(expiradas);
        }
    }

    public SimulacaoResponseDto calcularParcelas(BigDecimal valorEmprestimo, int numeroParcelas, Cliente cliente){
        if cliente.Status.
    }
}
