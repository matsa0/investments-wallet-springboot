package br.ufop.web2.converter;

import br.ufop.web2.domain.InvestmentDomain;
import br.ufop.web2.dto.investment.CreateInvestmentDTO;
import br.ufop.web2.dto.investment.InvestmentDTO;
import br.ufop.web2.entity.InvestmentEntity;
import br.ufop.web2.enums.InvestmentType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InvestmentConverter {

    // RequestDTO --> Domain
    public static InvestmentDomain toDomain(CreateInvestmentDTO dto) {
        if (dto == null) return null;
        return InvestmentDomain.builder()
                .type(InvestmentType.getTypeById(dto.type()))
                .quantity(dto.quantity())
                .symbol(dto.symbol())
                .purchasePrice(dto.purchasePrice())
                .purchaseDateTime(dto.purchaseDateTime())
                .build();
    }

    // Entity --> Domain
    public static InvestmentDomain toDomain(InvestmentEntity entity) {
        if (entity == null) return null;
        return InvestmentDomain.builder()
                .id(entity.getId())
                .type(entity.getType())
                .quantity(entity.getQuantity())
                .symbol(entity.getSymbol())
                .purchasePrice(entity.getPurchasePrice())
                .purchaseDateTime(entity.getPurchaseDateTime())
                .build();
    }

    //Domain --> Entity
    public static InvestmentEntity toEntity(InvestmentDomain domain, br.ufop.web2.entity.UserEntity user) {
        if (domain == null) return null;
        return InvestmentEntity.builder()
                .id(domain.getId())
                .type(domain.getType())
                .quantity(domain.getQuantity())
                .symbol(domain.getSymbol())
                .purchasePrice(domain.getPurchasePrice())
                .purchaseDateTime(domain.getPurchaseDateTime())
                .user(user)
                .build();
    }


    // Entity --> ResponseDTO (
    public static InvestmentDTO toResponseDTO(InvestmentEntity entity) {
        if (entity == null) return null;
        return new InvestmentDTO(
                entity.getId(),
                entity.getUser().getId(),
                entity.getType().getId(),
                entity.getQuantity(),
                entity.getSymbol(),
                entity.getPurchasePrice(),
                entity.getPurchaseDateTime()
        );
    }
}