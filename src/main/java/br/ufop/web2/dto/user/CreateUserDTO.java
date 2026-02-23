package br.ufop.web2.dto.user;

import java.util.UUID;

public record CreateUserDTO(
    String name,
    String email,
    String password
) {
}
