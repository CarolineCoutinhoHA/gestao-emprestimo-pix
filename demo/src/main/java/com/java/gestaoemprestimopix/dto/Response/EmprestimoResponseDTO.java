package com.java.gestaoemprestimopix.dto.Response;

import com.java.gestaoemprestimopix.enums.RiscoEmprestimo;
import com.java.gestaoemprestimopix.enums.StatusEmprestimo;


import java.math.BigDecimal;
import java.time.LocalDate;

public record EmprestimoResponseDTO(

        Long idEmprestimo,
        Long idCliente,
        Long idConta,
        BigDecimal valorEmprestimo,
        Integer numeroParcelas,
        BigDecimal valorParcela,
        BigDecimal valorTotal,
        BigDecimal taxaJuros,
        StatusEmprestimo statusEmprestimo,
        RiscoEmprestimo riscoEmprestimo,
        LocalDate dataContratoEmprestimo
) { }
