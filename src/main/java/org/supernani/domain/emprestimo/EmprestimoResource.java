package org.supernani.domain.emprestimo;

import java.util.Map;
import java.util.UUID;

import org.jboss.resteasy.reactive.ClientWebApplicationException;
import org.supernani.domain.taxaJuros.TaxaJurosResponseDTO;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/emprestimo")
@Produces(MediaType.APPLICATION_JSON) 
public class EmprestimoResource {

    @Inject
    private EmprestimoService emprestimoService;
    
    @Path("/clientes/{clienteId}/taxas/{tipoAmortizacao}/elegibilidade")
    @GET
    public Response consultaTaxa(
        @PathParam("clienteId") String idCliente
        , @PathParam("tipoAmortizacao") TipoAmortizacao tipoAmortizacao 
    ){
        try{
            TaxaJurosResponseDTO taxaJurosResponseDTO = emprestimoService.buscaTaxaJurosResponse(UUID.fromString(idCliente));
            return Response.status(200)
                .entity(taxaJurosResponseDTO)
                .build();
        }catch(ClientWebApplicationException e){
            Integer statusResponse = e.getResponse().getStatus();
            if (statusResponse == 400) {
                return Response.status(400)
                    .entity(Map.of("error", "Cliente Inelegível"))
                    .build();
            }
            return Response.status(statusResponse)
                .entity(Map.of("error", "Solicitação Indisponível"))
                .build();
        }
    }

    @Path("/criar")
    @POST
    public Response cadastrarContrato(EmprestimoDTO emprestimoDTO){
        Boolean cadastrado = emprestimoService.cadastrarContrato(emprestimoDTO);
        if(cadastrado){
            return Response.status(201).build();
        }
        return  Response.status(400).build();


    }
}
