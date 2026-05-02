package org.supernani.domain.exceptions;

public class TaxaJurosIntegrationException extends RuntimeException {

    private final int status;
    private final String body;

    public TaxaJurosIntegrationException(int status, String body) {
        super("Erro integração taxa juros");
        this.status = status;
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public String getBody() {
        return body;
    }
}