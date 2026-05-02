package org.supernani.domain.exceptions;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ClienteInelegivelExceptionMapper implements ExceptionMapper<ClienteInelegivelException> {

    
    @Override
    public Response toResponse(ClienteInelegivelException exception) {

        String mensagem = exception.getMessage() != null
                ? exception.getMessage()
                : "Cliente inelegível";

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErroResponse("CLIENTE_INELEGIVEL", mensagem))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}

