package br.ufop.web2.domain;

import br.ufop.web2.enums.InvestmentType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvestmentDomain {

    private UUID id;
    private InvestmentType type;
    private String symbol;
    private Integer quantity;
    private Double purchasePrice;
    private LocalDateTime purchaseDateTime;

    private UserDomain user;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
