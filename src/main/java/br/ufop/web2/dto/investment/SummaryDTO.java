package br.ufop.web2.dto.investment;

import java.util.Map;

public record SummaryDTO(
        Double totalInvested,
        Map<String, Double> totalByType,
        Integer assetCount
) {
}
