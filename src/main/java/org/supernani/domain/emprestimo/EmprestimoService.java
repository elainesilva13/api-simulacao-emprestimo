package org.supernani.domain.emprestimo;

import java.time.LocalDate;
import java.util.UUID;

import org.supernani.domain.parcela.ParcelaService;
import org.supernani.domain.taxaJuros.TaxaJurosResponseDTO;
import org.supernani.domain.taxaJuros.TaxaJurosService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class EmprestimoService {
    
    @Inject
    protected TaxaJurosService taxaJurosService;


    public Double buscaTaxaJuros(UUID idCliente){
        return taxaJurosService.buscaTaxaJuros(idCliente);
    }

    public TaxaJurosResponseDTO buscaTaxaJurosResponse(UUID idCliente){
        return taxaJurosService.buscaTaxaJurosResponse(idCliente);
    }

    @Transactional
    public Boolean cadastrarContrato(@Valid EmprestimoDTO emprestimoDTO){
        Double taxa = taxaJurosService.buscaTaxaJuros(emprestimoDTO.idCliente());
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
