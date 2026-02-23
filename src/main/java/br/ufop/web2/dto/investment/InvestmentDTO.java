package br.ufop.web2.dto.investment;

import java.time.LocalDateTime;
import java.util.UUID;

public record InvestmentDTO(
    UUID id,
    Integer type,
    Integer quantity,
    String symbol,
    Double purchasePrice,
    LocalDateTime purchaseDateTime
) {
}
