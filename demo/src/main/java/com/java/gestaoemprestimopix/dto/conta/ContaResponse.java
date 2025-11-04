package com.java.gestaoemprestimopix.dto.conta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.java.gestaoemprestimopix.entity.Usuario;

public class ContaResponse {

    private String idConta;
    private Long numeroConta;
    private String agencia;
    private BigDecimal saldo;
    private LocalDateTime dataCriacao;
    private boolean statusConta;
    private Usuario usuario;

    // Constructors
    public ContaResponse() {
    }

    public ContaResponse(String idConta, Long numeroConta, String agencia, BigDecimal saldo, LocalDateTime dataCriacao,
            boolean statusConta, Usuario usuario) {
        this.idConta = idConta;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.saldo = saldo;
        this.dataCriacao = dataCriacao;
        this.statusConta = statusConta;
        this.usuario = usuario;
    }

    // Getters and Setters
    public String getIdConta() {
        return idConta;
    }

    public void setIdConta(String idConta) {
        this.idConta = idConta;
    }

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
