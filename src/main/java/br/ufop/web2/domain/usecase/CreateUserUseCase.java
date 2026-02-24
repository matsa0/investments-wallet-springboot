package br.ufop.web2.domain.usecase;

import br.ufop.web2.domain.UserDomain;
import br.ufop.web2.exception.UseCaseException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateUserUseCase {

    @Setter
    private UserDomain userDomain;

    public void validate() {
        validateName();
        validateEMail();
        validadePassword();
    }

    private void validateName() {
        if (this.userDomain.getName() == null) {
            throw new UseCaseException("Name is null");
        }
    }

    private void validateEMail() {
        if (this.userDomain.getEmail() == null) {
            throw new UseCaseException("E-mail is null.");
        }
    }

    private void validadePassword() {
        if (this.userDomain.getPassword() == null) {
            throw new UseCaseException("Password is null");
        }
    }
}
