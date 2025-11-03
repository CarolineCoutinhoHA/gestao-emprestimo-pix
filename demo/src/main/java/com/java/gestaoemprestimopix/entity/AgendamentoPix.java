package com.java.gestaoemprestimopix.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class AgendamentoPix {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    @Getter
    @Column()
    private BigDecimal valorParcela;
    private LocalDateTime dataDebito;
    private LocalDateTime dataVencimento;


}
