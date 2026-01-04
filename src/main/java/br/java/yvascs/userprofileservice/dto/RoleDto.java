package br.java.yvascs.userprofileservice.dto;

import br.java.yvascs.userprofileservice.model.enums.Role;

public record RoleDto (String code, String description) {
    public static RoleDto from(Role role) {
        return new RoleDto(role.getCode(), role.getDescription());
    }
}
