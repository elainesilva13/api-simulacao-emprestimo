package org.supernani.domain.emprestimo;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.supernani.domain.taxaJuros.TaxaJurosResponseDTO;
import org.supernani.domain.taxaJuros.TaxaJurosRestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EmprestimoService {
    
    @Inject
    @RestClient
    protected TaxaJurosRestClient taxaJurosRestClient;

    protected TaxaJurosResponseDTO buscaTaxaJuros(String idCliente){
        TaxaJurosResponseDTO taxaJurosResponseDTO = taxaJurosRestClient.buscaTaxaJuros(idCliente);
        return taxaJurosResponseDTO;
    }
}
