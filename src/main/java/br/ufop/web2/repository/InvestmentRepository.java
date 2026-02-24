package br.ufop.web2.repository;

import br.ufop.web2.entity.InvestmentEntity;
import br.ufop.web2.enums.InvestmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InvestmentRepository extends JpaRepository<InvestmentEntity, UUID> {

    List<InvestmentEntity> findAllByUserId(UUID id);
    List<InvestmentEntity> findAllByUserIdAndType(UUID userId, InvestmentType type);
}
