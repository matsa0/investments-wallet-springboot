package br.ufop.web2.converter;

import br.ufop.web2.domain.UserDomain;
import br.ufop.web2.dto.user.CreateUserDTO;
import br.ufop.web2.dto.user.UserDTO;
import br.ufop.web2.entity.InvestmentEntity;
import br.ufop.web2.entity.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter {

    // RequestDTO --> Domain (For creation)
    public static UserDomain toDomain(CreateUserDTO dto) {
        if (dto == null) return null;
        return UserDomain.builder()
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
                .build();
    }

    // Entity --> Domain (For database & usecase)
    public static UserDomain toDomain(UserEntity entity) {
        if (entity == null) return null;
        return UserDomain.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .investments(entity.getInvestments() == null ? null :
                        entity.getInvestments().stream()
                                .map(InvestmentConverter::toDomain)
                                .toList())
                .build();
    }

    // Domain --> Entity (For persistence)
    public static UserEntity toEntity(UserDomain domain) {
        if (domain == null) return null;
        UserEntity entity = UserEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .build();

        if (domain.getInvestments() != null) {
            entity.setInvestments(domain.getInvestments().stream()
                    .map(inv -> {
                        InvestmentEntity investmentEntity = InvestmentConverter.toEntity(inv);
                        investmentEntity.setUser(entity); // user reference (avoids StackOverflowError)
                        return investmentEntity;
                    }).toList());
        }
        return entity;
    }

    // Entity --> ResponseDTO (For API response)
    public static UserDTO toResponseDTO(UserEntity entity) {
        if (entity == null) return null;
        return new UserDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail()
        );
    }
}