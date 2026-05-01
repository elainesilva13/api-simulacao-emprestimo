package org.supernani.domain.emprestimo;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;




public class IdCliente {
    @Pattern(
        regexp = "^[0-9]{11}$|^[0-9A-Z]{12}[0-9]{2}$",
        message = "IdCliente inválido"
    )
    @NotBlank(message = "IdCliente inválido")
    
    private final UUID id;

    public IdCliente(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("IdCliente não pode ser nulo");
        }

        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}