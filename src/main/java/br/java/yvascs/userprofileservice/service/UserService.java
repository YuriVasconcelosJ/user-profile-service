package br.java.yvascs.userprofileservice.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.java.yvascs.userprofileservice.dto.UserCreateDto;
import br.java.yvascs.userprofileservice.dto.UserResponseDto;
import br.java.yvascs.userprofileservice.exception.domain.EmailAlreadyExistsException;
import br.java.yvascs.userprofileservice.exception.domain.UserNotFoundException;
import br.java.yvascs.userprofileservice.mapper.UserMapper;
import br.java.yvascs.userprofileservice.model.User;
import br.java.yvascs.userprofileservice.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    /**
     * Função responsável por realizar a listagem de todos os usuários.
     * 
     * @return uma lista contendo os usuários
     */
    public List<UserResponseDto> listAll() {
        return userRepository.findAll().stream().map(userMapper::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    /**
     * 
     * @return
     */
    public UserResponseDto findUserByid(Long id) {
        User user = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.fromEntity(user);
    }

    /**
     * Função responsável por realizar a criação de um usuário
     * 
     * @return um usuário encapsulado em userResponseDto como resposta
     */
    @Transactional
    public UserResponseDto createUser(UserCreateDto userCreateDto) {
        if (userRepository.existsByEmail(userCreateDto.getEmail())) {
            throw new EmailAlreadyExistsException(userCreateDto.getEmail());
        }

        User user = userMapper.fromDto(userCreateDto);
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        User userSaved = userRepository.save(user);
        return userMapper.fromEntity(userSaved);
    }

    /**
     * Função responsável por deletar um usuário
     * 
     * @param email fornecido para realizar identifialçao.
     */
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }

}
