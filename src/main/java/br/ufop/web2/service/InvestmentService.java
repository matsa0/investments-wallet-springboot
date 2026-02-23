package br.ufop.web2.service;

import br.ufop.web2.converter.InvestmentConverter;
import br.ufop.web2.domain.InvestmentDomain;
import br.ufop.web2.dto.investment.*;
import br.ufop.web2.entity.InvestmentEntity;
import br.ufop.web2.entity.UserEntity;
import br.ufop.web2.enums.InvestmentType;
import br.ufop.web2.exception.UseCaseException;
import br.ufop.web2.repository.InvestmentRepository;
import br.ufop.web2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InvestmentService {

    private InvestmentRepository repository;
    private UserRepository userRepository;

    // POST INVESTMENTS
    public InvestmentDTO create(CreateInvestmentDTO createInvestmentDTO) {
        InvestmentDomain investmentDomain = InvestmentConverter.toDomain(createInvestmentDTO);

        // validate (useCase)

        InvestmentEntity investmentEntity = InvestmentConverter.toEntity(investmentDomain);
        repository.save(investmentEntity);
        return InvestmentConverter.toResponseDTO(investmentEntity);
    }

    // GET INVESTMENTS BY USER
    public List<InvestmentDTO> findAllByUserId(UUID userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UseCaseException("ERROR! User doesn't exist");
        }

        List<InvestmentEntity> entities = repository.findAllByUserId(userId);
        if (entities.isEmpty()) {
            throw new UseCaseException("ERROR! This user doesn't have investments");
        }

        return entities.stream().map(InvestmentConverter::toResponseDTO).toList();
    }

    // GET INVESTMENTS BY TYPE
    public List<InvestmentDTO> findByTypeId(Integer typeId) {

        // validate if type id is valid
        InvestmentType type = InvestmentType.getTypeById(typeId);
        List<InvestmentEntity> entities = repository.findAllByType(type);
        if (entities.isEmpty()) {
            throw new UseCaseException("ERROR! No investments found for this type.");
        }

        return entities.stream().map(InvestmentConverter::toResponseDTO).toList();
    }

    // PUT INVESTMENTS
    public InvestmentDTO update(UpdateInvestmentDTO updateInvestmentDTO) {
        Optional<InvestmentEntity> optionalInvestmentEntity = repository.findById(updateInvestmentDTO.id());

        if (optionalInvestmentEntity.isEmpty()) {
            throw new UseCaseException("ERROR! ID not found for user.");
        }

        InvestmentEntity investmentEntity = optionalInvestmentEntity.get();

        investmentEntity.setSymbol(updateInvestmentDTO.symbol());
        investmentEntity.setType(InvestmentType.getTypeById(updateInvestmentDTO.type()));
        investmentEntity.setQuantity(updateInvestmentDTO.quantity());
        investmentEntity.setPurchasePrice(updateInvestmentDTO.purchasePrice());
        investmentEntity.setPurchaseDateTime(updateInvestmentDTO.purchaseDateTime());

        repository.save(investmentEntity);

        return InvestmentConverter.toResponseDTO(investmentEntity);
    }

    // DELETE INVESTMENTS
    public void delete(DeleteInvestmentDTO deleteInvestmentDTO) {
        Optional<InvestmentEntity> investmentEntitityOptional = repository.findById(deleteInvestmentDTO.id());

        if (investmentEntitityOptional.isEmpty()) {
            throw new UseCaseException("ERROR! ID not found for investment.");
        }

        InvestmentEntity userEntity = investmentEntitityOptional.get();
        repository.delete(userEntity);
    }

    // GET INVESTS SUMMARY
    public SummaryDTO summary(UUID userId) {
        List<InvestmentEntity> investments = repository.findAllByUserId(userId);
        // cumulates the total invested by type
        Map<String, Double> totalByType = new HashMap<>();

        if(investments.isEmpty()) {
            return new SummaryDTO(0.0, Map.of(), 0);
        }

        double totalInvested = 0.0;
        int assetCount = investments.size();

        for (InvestmentEntity investment : investments) {
            double valueInvested = investment.getPurchasePrice() * investment.getQuantity();

            totalInvested += valueInvested;

            String typeName = investment.getType().name();
            double totalInvestedByType = totalByType.getOrDefault(typeName, 0.0);
            totalByType.put(typeName, totalInvestedByType + valueInvested);
        }

        return new SummaryDTO(
                totalInvested,
                totalByType,
                assetCount
        );
    }
}
