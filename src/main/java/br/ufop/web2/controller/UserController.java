package br.ufop.web2.controller;

import br.ufop.web2.dto.user.CreateUserDTO;
import br.ufop.web2.dto.user.DeleteUserDTO;
import br.ufop.web2.dto.user.UpdateUserDTO;
import br.ufop.web2.dto.user.UserDTO;
import br.ufop.web2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable UUID id) {
        Optional<UserDTO> userDTOOptional = service.findById(id);

        if (userDTOOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userDTOOptional.get());
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody CreateUserDTO dto) {
        UserDTO response = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody UpdateUserDTO dto) {
        UserDTO userDTO = service.update(dto);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody DeleteUserDTO deleteUserDTO) {
        service.delete(deleteUserDTO);
        return ResponseEntity.noContent().build();
    }
}
