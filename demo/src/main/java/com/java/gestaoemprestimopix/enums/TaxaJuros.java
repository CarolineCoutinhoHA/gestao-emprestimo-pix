package com.java.gestaoemprestimopix.enums;

import lombok.Getter;

@Getter
public enum TaxaJuros {
    ALTO(1, 0.07),
    MEDIO(2, 0.05),
    BAIXO(3,0.03);

    private final int nivel;
    private final Double valor;

    TaxaJuros(int nivel, Double valor){
        this.nivel=nivel;
        this.valor=valor;
    }

}
