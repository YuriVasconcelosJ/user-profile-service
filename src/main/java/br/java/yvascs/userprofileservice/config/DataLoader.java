package br.java.yvascs.userprofileservice.config;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.java.yvascs.userprofileservice.model.User;
import br.java.yvascs.userprofileservice.model.enums.Role;
import br.java.yvascs.userprofileservice.repository.UserRepository;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (userRepository.count() == 0) {

            User admin = User.builder()
                    .fullname("Administrador")
                    .email("admin@admin.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .createdAt(LocalDateTime.now())
                    .updateAt(LocalDateTime.now())
                    .build();

            userRepository.save(admin);

            System.out.println("✔ Usuário ADMIN criado com sucesso");
        }
    }
}
