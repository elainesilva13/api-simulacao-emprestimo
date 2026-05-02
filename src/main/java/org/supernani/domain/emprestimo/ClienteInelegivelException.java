package org.supernani.domain.emprestimo;

// import org.supernani.domain.taxaJuros.TaxaJurosErrorResponseDTO;

public class ClienteInelegivelException extends RuntimeException {
    public ClienteInelegivelException(String erroDTO) {
        super(erroDTO);
    }
}