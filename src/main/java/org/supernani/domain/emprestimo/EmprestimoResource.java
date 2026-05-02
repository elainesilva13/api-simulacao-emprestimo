package org.supernani.domain.emprestimo;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jboss.resteasy.reactive.ClientWebApplicationException;
import org.supernani.domain.parcela.EmprestimoComParcelasDTO;
import org.supernani.domain.parcela.Parcela;
import org.supernani.domain.taxaJuros.TaxaJurosResponseDTO;
import org.supernani.repositories.EmprestimoRepository;
import org.supernani.repositories.ParcelaRepository;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/emprestimo")
@Produces(MediaType.APPLICATION_JSON) 
public class EmprestimoResource {

    @Inject
    private EmprestimoService emprestimoService;

    @Inject
    EmprestimoRepository emprestimoRepository;

    @Inject
    ParcelaRepository parcelaRepository;
    
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
            return Response.status(201).entity(Map.of("success", "Sucesso na solicitação")).build();
        }
        return  Response.status(400).entity(Map.of("error","Não há taxas disponíveis para o cliente informado")).build();
    }

    @Authenticated
    @Path("/clientes")
    @GET
    public Response listaEmprestimos(
        @QueryParam("clienteId") String idCliente

    ){
        try{
            UUID.fromString(idCliente);
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST) .entity("clienteId inválido") .build();
        }

        List<Emprestimo> emprestimos = emprestimoRepository.buscaPorIdCliente(UUID.fromString(idCliente));

        if (emprestimos.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(emprestimos).build();
    }

    @Authenticated
    @Path("/{emprestimoId}/parcelas")
    @GET
    public Response listarParcelas(@PathParam("emprestimoId") String emprestimoId) {
        try{
            UUID.fromString(emprestimoId);
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST) .entity("clienteId inválido") .build();
        }

        List<Parcela> parcelas = parcelaRepository.buscaPorIdEmprestimo(UUID.fromString(emprestimoId));

        if (parcelas.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(parcelas).build();
    }
    @Authenticated
    @Path("/clientes/{clienteId}/emprestimos-com-parcelas")
    @GET
    public Response buscarEmprestimosComParcelas(@PathParam("clienteId") String clienteId) {
        try {
            UUID.fromString(clienteId);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("clienteId inválido")
                    .build();
        }
        List<EmprestimoComParcelasDTO> resultado = emprestimoService.buscarEmprestimosComParcelas(UUID.fromString(clienteId));
        if(resultado==null){
             return Response.noContent().build();
        }
        return Response.ok(resultado).build();
    }

    @Transactional
    @Path("/emprestimos/{id}")
    @RolesAllowed("Gerente")
    @DELETE
    public Response deletarEmprestimo(@PathParam("id") String idEmprestimo) {

    Emprestimo emprestimo = emprestimoRepository.findById(UUID.fromString(idEmprestimo));

        if (emprestimo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        emprestimoRepository.delete(emprestimo);

        return Response.noContent().build();
    }
}