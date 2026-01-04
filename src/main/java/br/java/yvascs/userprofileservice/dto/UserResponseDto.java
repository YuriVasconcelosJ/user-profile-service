package br.java.yvascs.userprofileservice.dto;

import java.time.LocalDateTime;

public record UserResponseDto(

        String fullname,
        String email,
        RoleDto role,
        LocalDateTime createdAt

) {
}
