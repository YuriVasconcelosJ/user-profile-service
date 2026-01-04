package br.java.yvascs.userprofileservice.dto;

import br.java.yvascs.userprofileservice.model.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "DTO utilizado para a criação do usuário")
public class UserCreateDto {

    @Schema(
        description = "Email do usuário",
        example = "usuario@gmail.com",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email é inválido")
    private String email;

    @Schema(
        description = "Senha do usuário",
        example = "senha123",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Senha é obrigatória")
    private String password;

    @Size(min = 10, max = 50)
    @NotBlank(message = "Nome completo é obrigatório")
    @Pattern(
        regexp = "^[^0-9]*$",
        message = "Nome não deve conter números"
    )
    private String fullname;
   
    @Schema(
        description = "Perfil de acesso do usuário",
        example = "ADMIN"
    )
    private Role role;
}
