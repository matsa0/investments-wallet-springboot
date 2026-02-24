package br.ufop.web2.service;

import br.ufop.web2.converter.UserConverter;
import br.ufop.web2.domain.UserDomain;
import br.ufop.web2.domain.usecase.CreateUserUseCase;
import br.ufop.web2.dto.user.CreateUserDTO;
import br.ufop.web2.dto.user.DeleteUserDTO;
import br.ufop.web2.dto.user.UpdateUserDTO;
import br.ufop.web2.dto.user.UserDTO;
import br.ufop.web2.entity.UserEntity;
import br.ufop.web2.exception.UseCaseException;
import br.ufop.web2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final CreateUserUseCase createUserUseCase;

    public List<UserDTO> findAll() {
        List<UserEntity> entities = repository.findAll();

        return entities.stream().map(UserConverter::toResponseDTO).toList();
    }

    public Optional<UserDTO> findById(UUID id) {
        Optional<UserEntity> entityOptional = repository.findById(id);

        if(entityOptional.isEmpty()) {
            throw new UseCaseException("ERROR! ID not found for user.");
        }

        UserDTO userResponse = UserConverter.toResponseDTO(entityOptional.get());
        return Optional.of(userResponse);
    }

    public UserDTO create(CreateUserDTO userDTO) {
        UserDomain userDomain = UserConverter.toDomain(userDTO);

        createUserUseCase.setUserDomain(userDomain);
        createUserUseCase.validate();

        UserEntity userEntity = repository.save(UserConverter.toEntity(userDomain));
        return UserConverter.toResponseDTO(userEntity);
    }

    public UserDTO update(UpdateUserDTO updateUserDTO) {
        Optional<UserEntity> userEntityOptional = repository.findById(updateUserDTO.id());

        if (userEntityOptional.isEmpty()) {
            throw new UseCaseException("ERROR! ID not found for user.");
        }

        UserEntity userEntity = userEntityOptional.get();

        userEntity.setName(updateUserDTO.name());
        userEntity.setEmail(updateUserDTO.email());
        userEntity.setPassword(updateUserDTO.password());

        repository.save(userEntity);

        return UserConverter.toResponseDTO(userEntity);
    }

    public void delete(DeleteUserDTO deleteUserDTO) {
        Optional<UserEntity> userEntityOptional = repository.findById(deleteUserDTO.id());

        if (userEntityOptional.isEmpty()) {
            throw new UseCaseException("ERROR! ID not found for user.");
        }

        UserEntity userEntity = userEntityOptional.get();

        if (!userEntity.getPassword().equals(deleteUserDTO.password())) {
            throw new UseCaseException("ERROR deleting! Invalid password.");
        }

        repository.delete(userEntity);
    }
}
