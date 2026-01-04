package br.java.yvascs.userprofileservice.exception;

import lombok.Getter;

@Getter
public abstract class CustomBaseException extends RuntimeException {
    private final String code;

    public CustomBaseException(String message, String code) {
        super(message);
        this.code = code;
    }
}
