package br.ufop.web2.enums;

import br.ufop.web2.exception.UseCaseException;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum InvestmentType {

    STOCK(1, "Ação"),
    CRYPTO(2, "Cripto"),
    FUND(3, "Fundo de Investimento"),
    FIXED(4, "Renda Fixa"),
    OTHER(5, "Outro");

    private Integer id;
    @JsonValue
    private String description;

    public static InvestmentType getTypeById(Integer id) {
        for (InvestmentType type : InvestmentType.values()) {
            if (type.getId().equals(id)) {
                return type;
            }
        }
        throw new UseCaseException("ERROR! Invalid id for InvestmentType.");
    }
}

