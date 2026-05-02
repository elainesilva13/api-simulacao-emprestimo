package org.supernani.domain.taxaJuros;

import java.util.UUID;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.ClientWebApplicationException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TaxaJurosService {
    @Inject
    @RestClient
    private TaxaJurosRestClient taxaJurosRestClient;
    
    public TaxaJurosResponseDTO buscaTaxaJurosResponse(UUID idCliente){
        
        return taxaJurosRestClient.buscaTaxaJuros(idCliente);
    
    }

    public Double buscaTaxaJuros(UUID idCliente){
        try{
            TaxaJurosResponseDTO taxaJurosResponseDTO = this.buscaTaxaJurosResponse(idCliente);
            return taxaJurosResponseDTO.taxaJuros();
        }catch(ClientWebApplicationException e){
            return null;
        }   
    }
}
