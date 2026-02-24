package br.ufop.web2.domain.usecase;

import br.ufop.web2.domain.InvestmentDomain;
import br.ufop.web2.exception.UseCaseException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateInvestmentUseCase {

    @Setter
    private InvestmentDomain investmentDomain;

    public void validate() {
        validateQuantity();
        validatePrice();
        validateDateTime();
        validateSymbol();
    }

    private void validateQuantity() {
        if (this.investmentDomain.getQuantity() == null || this.investmentDomain.getQuantity() <= 0) {
            throw new UseCaseException("ERROR! Quantity must be higher than 0.");
        }
    }

    private void validatePrice() {
        if (this.investmentDomain.getPurchasePrice() == null || this.investmentDomain.getPurchasePrice() <= 0) {
            throw new UseCaseException("ERROR! Price must be higher than 0.");
        }
    }

    private void validateDateTime() {
        if (this.investmentDomain.getPurchaseDateTime() == null) {
            throw new UseCaseException("ERROR! Datetime can't be null.");
        }
        if (this.investmentDomain.getPurchaseDateTime().isAfter(java.time.LocalDateTime.now())) {
            throw new UseCaseException("ERROR! It's not possible to register investments in this date.");
        }
    }

    private void validateSymbol() {
        if (this.investmentDomain.getSymbol() == null || this.investmentDomain.getSymbol().isBlank()) {
            throw new UseCaseException("ERROR! Symbol is required!.");
        }

        this.investmentDomain.setSymbol(this.investmentDomain.getSymbol().toUpperCase().trim());
    }
}