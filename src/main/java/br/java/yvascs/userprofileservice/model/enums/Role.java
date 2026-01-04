package br.java.yvascs.userprofileservice.model.enums;

public enum Role {
    USER("USER", "Usuário"), 
    ADMIN("ADMIN", "Administrador");

    private final String code;
    private final String description;

    Role(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
