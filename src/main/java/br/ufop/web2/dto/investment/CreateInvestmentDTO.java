package br.ufop.web2.dto.investment;


import java.time.LocalDateTime;

public record CreateInvestmentDTO (
        Integer type,
        Integer quantity,
        String symbol,
        Double purchasePrice,
        LocalDateTime purchaseDateTime
) {
}
