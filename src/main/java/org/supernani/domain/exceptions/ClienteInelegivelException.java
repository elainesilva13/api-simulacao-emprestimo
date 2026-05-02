package org.supernani.domain.exceptions;

import lombok.Getter;

@Getter
public class ClienteInelegivelException extends RuntimeException {
    public ClienteInelegivelException(String mensagem) {
        super(mensagem);
    }
}