package br.java.yvascs.userprofileservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO responsável pelas credenciais de autenticação do usuário")
public class LoginRequestDto {

    @Schema(
        description = "Email do usuário",
        example = "admin@admin.com",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email Inválido")
    private String email;

    @Schema(
        description = "Senha de usuário",
        example = "admin123",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Senha é obrigatória")
    private String password;

    
}
