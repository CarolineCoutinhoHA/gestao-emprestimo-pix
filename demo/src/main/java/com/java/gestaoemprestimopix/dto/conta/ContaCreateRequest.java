package com.java.gestaoemprestimopix.dto.conta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ContaCreateRequest {

    @NotNull(message = "O número da conta é obrigatório.")
    private Long numeroConta;
    @NotBlank(message = "O número da agência é obrigatório.")
    private String agencia;
    private BigDecimal saldo;
    private LocalDateTime dataCriacao;
    private boolean statusConta;
    private String usuarioId;

    // Constructors
    public ContaCreateRequest() {
    }

    public ContaCreateRequest(Long numeroConta, String agencia, BigDecimal saldo, LocalDateTime dataCriacao,
            boolean statusConta, String usuarioId) {
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.saldo = saldo;
        this.dataCriacao = dataCriacao;
        this.statusConta = statusConta;
        this.usuarioId = usuarioId;
    }

    // Getters and Setters
    public Long getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Long numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean isStatusConta() {
        return statusConta;
    }

    public void setStatusConta(boolean statusConta) {
        this.statusConta = statusConta;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

}
