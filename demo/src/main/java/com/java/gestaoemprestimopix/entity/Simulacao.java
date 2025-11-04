package com.java.gestaoemprestimopix.entity;


import com.java.gestaoemprestimopix.enums.TaxaJuros;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

@Entity
@Getter
@Setter
public class Simulacao {
    private Long idSimulacao;
    private Instant dataHoraExpiracao;
    private BigDecimal valorEmprestimo;
    private int numeroParcelas;
    private TaxaJuros taxaJuros;
    private Cliente cliente;
}
