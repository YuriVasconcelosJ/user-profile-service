package br.java.yvascs.userprofileservice.mapper;

import org.springframework.stereotype.Component;

import br.java.yvascs.userprofileservice.dto.LoginResponseDto;
import br.java.yvascs.userprofileservice.dto.RoleDto;
import br.java.yvascs.userprofileservice.dto.UserCreateDto;
import br.java.yvascs.userprofileservice.dto.UserResponseDto;
import br.java.yvascs.userprofileservice.model.User;

@Component
public class UserMapper {

    /**
     * Função responsável por realizar a conversão de um user(entidade) para um
     * userResponse
     * 
     * @param user usuario para conversão
     * @return userResponseDto convertido
     */
    public UserResponseDto fromEntity(User user) {
        return new UserResponseDto(user.getFullname(), user.getEmail(), RoleDto.from(user.getRole()),
                user.getCreatedAt());
    }

    /**
     * Função responsável por realizar a conversão do dto de usercreateDto para a entidade user
     * 
     * @param userCreateDto userDto para conversão
     * @return usuário convertido
     */
    public User fromDto(UserCreateDto userCreateDto) {
        return User.builder().fullname(userCreateDto.getFullname()).email(userCreateDto.getEmail())
                .role(userCreateDto.getRole()).build();
    }

    public LoginResponseDto fromEntity(String token, User user) {
        return new LoginResponseDto(
                token,
                user.getFullname(),
                user.getEmail(),
                RoleDto.from(user.getRole())
        );
    }
    

}
