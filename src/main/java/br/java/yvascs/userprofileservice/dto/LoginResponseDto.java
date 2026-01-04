package br.java.yvascs.userprofileservice.dto;


public record LoginResponseDto (String toke, String fullname, String email, RoleDto role) {

}
