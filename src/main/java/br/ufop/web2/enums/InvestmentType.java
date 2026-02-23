package br.ufop.web2.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum InvestmentType {

    STOCK(1, "Ação"),
    CRYPTO(2, "Cripto"),
    FUND(3, "Fundo de Investimento"),
    FIXED(4, "Renda Fixa"),
    OTHER(5, "Outro");

    private Integer id;
    private String description;
}
