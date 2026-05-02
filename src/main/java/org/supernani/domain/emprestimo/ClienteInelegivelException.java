package org.supernani.domain.emprestimo;

public class ClienteInelegivelException extends RuntimeException {
    public ClienteInelegivelException() {
        super("Cliente ineligível");
    }
}