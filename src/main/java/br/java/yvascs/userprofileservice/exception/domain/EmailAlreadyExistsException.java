package br.java.yvascs.userprofileservice.exception.domain;

import br.java.yvascs.userprofileservice.exception.CustomBaseException;

public class EmailAlreadyExistsException extends CustomBaseException{

    public EmailAlreadyExistsException(String email) {
        super("Email já cadastrado: " + email, "EMAIL_ALREADY_EXISTS");
    }

}
