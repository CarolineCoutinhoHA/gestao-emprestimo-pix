package com.java.gestaoemprestimopix.dto.conta;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public class ContaUpdateRequest {

    @NotNull(message = "O valor do saldo não pode estar vazio.")
    private BigDecimal saldo;

    // Constructors
    public ContaUpdateRequest(BigDecimal saldo) {
        this.saldo = saldo;
    }

    // Getters and Setters
    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

}
