package org.supernani.domain.emprestimo;

import org.supernani.domain.taxaJuros.TaxaJurosResponseDTO;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/emprestimo")
@Produces(MediaType.APPLICATION_JSON) 
public class EmprestimoResource {

    @Inject
    private EmprestimoService emprestimoService;
    
    @Path("/clientes/{clienteId}/taxas/{tipoAmortizacao}/elegibilidade")
    @GET
    public TaxaJurosResponseDTO consultaTaxa(
        @PathParam("clienteId") String idCliente
        , @PathParam("tipoAmortizacao") TipoAmortizacao tipoAmortizacao 
    ){
        return emprestimoService.buscaTaxaJuros(idCliente);
    }
}
