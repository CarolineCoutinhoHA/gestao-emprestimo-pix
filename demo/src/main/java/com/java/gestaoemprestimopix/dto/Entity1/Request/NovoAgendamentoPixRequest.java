package com.java.gestaoemprestimopix.dto.Entity1.Request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NovoAgendamentoPixRequest(
        BigDecimal valor,
        LocalDate dataDebito,
        LocalDate dataVencimento
) {
}
