package org.supernani.domain.exceptions;

import org.supernani.domain.taxaJuros.TaxaJurosErrorResponseDTO;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


@Provider
public class TaxaJurosExceptionMapper implements ExceptionMapper<ClienteInelegivelException> {

    @Override
    public Response toResponse(ClienteInelegivelException exception) {
        TaxaJurosErrorResponseDTO dto = new TaxaJurosErrorResponseDTO(exception.getMessage());

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(dto)
                .build();
    }
}