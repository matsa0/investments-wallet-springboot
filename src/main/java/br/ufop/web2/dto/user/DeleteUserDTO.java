package br.ufop.web2.dto.user;

import java.util.UUID;

public record DeleteUserDTO(
    UUID id,
    String password
) {
}
