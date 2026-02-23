package br.ufop.web2.dto.user;

import java.util.UUID;

public record UpdateUserDTO (
    UUID id,
    String name,
    String email,
    String password
){
}
