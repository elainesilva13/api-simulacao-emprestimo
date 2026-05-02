package org.supernani.domain.emprestimo;

import java.time.LocalDate;
import java.util.UUID;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.supernani.domain.parcela.ParcelaService;
import org.supernani.domain.taxaJuros.TaxaJurosResponseDTO;
import org.supernani.domain.taxaJuros.TaxaJurosRestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EmprestimoService {
    
    @Inject
    @RestClient
    protected TaxaJurosRestClient taxaJurosRestClient;

    protected TaxaJurosResponseDTO buscaTaxaJurosResponse(UUID idCliente){
        return taxaJurosRestClient.buscaTaxaJuros(idCliente);
        
    }

    protected Double buscaTaxaJuros(UUID idCliente){
        try{
            TaxaJurosResponseDTO taxaJurosResponseDTO = this.buscaTaxaJurosResponse(idCliente);
            return taxaJurosResponseDTO.taxaJuros();
        }catch(Exception e){
            return null;
        }   
    }

    @Transactional
    protected Boolean cadastrarContrato(EmprestimoDTO emprestimoDTO){
        Double taxa = this.buscaTaxaJuros(emprestimoDTO.idCliente());
        if(taxa == null){
            return false;
        }
        Emprestimo emprestimo = new Emprestimo(
            null,
            emprestimoDTO.idCliente(),
            emprestimoDTO.valorTomado(),
            emprestimoDTO.prazoContratado(),
            emprestimoDTO.tipoAmortizacao(),
            StatusEmprestimo.PENDENTE,
            emprestimoDTO.valorTomado(), 
            taxa,
            LocalDate.now()
        );
        emprestimo.persist();

        ParcelaService parcelaService = new ParcelaService();
        parcelaService.salvaParcelas(emprestimo);
        return true;
    }

}
