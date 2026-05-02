package org.supernani.domain.exceptions;
import java.util.Arrays;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InvalidFormatExceptionMapper implements ExceptionMapper<InvalidFormatException> {

    @Override
    public Response toResponse(InvalidFormatException exception) {

        String campo = "desconhecido";
        if (!exception.getPath().isEmpty()) {
            campo = exception.getPath().get(0).getFieldName();
        }

        Object valorRecebido = exception.getValue();

        String mensagem = String.format(
                "Valor inválido '%s' para o campo '%s'",
                valorRecebido, campo
        );

        // Se for enum, adiciona valores válidos
        Class<?> targetType = exception.getTargetType();
        if (targetType != null && targetType.isEnum()) {
            Object[] valoresValidos = targetType.getEnumConstants();
            mensagem += ". Valores permitidos: " + Arrays.toString(valoresValidos);
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErroResponse("VALIDATION_ERROR", mensagem))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}