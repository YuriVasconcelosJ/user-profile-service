package br.java.yvascs.userprofileservice.exception.domain;

import br.java.yvascs.userprofileservice.exception.CustomBaseException;

public class UserNotFoundException extends CustomBaseException {

    public UserNotFoundException(Long id) {
        super("Usuário com o ID: " + id + " não encontrado", "NOT_FOUND");
    }

}
