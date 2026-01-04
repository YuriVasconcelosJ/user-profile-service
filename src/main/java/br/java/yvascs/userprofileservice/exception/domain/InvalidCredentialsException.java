package br.java.yvascs.userprofileservice.exception.domain;

import br.java.yvascs.userprofileservice.exception.CustomBaseException;

public class InvalidCredentialsException extends CustomBaseException {

    public InvalidCredentialsException() {
        super(
                "Credenciais inválidas",
                "INVALID_CREDENTIALS");
    }

}
