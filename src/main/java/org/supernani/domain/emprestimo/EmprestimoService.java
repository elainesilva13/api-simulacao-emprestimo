package org.supernani.domain.emprestimo;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.supernani.domain.parcela.EmprestimoComParcelasDTO;
import org.supernani.domain.parcela.Parcela;
import org.supernani.domain.parcela.ParcelaService;
import org.supernani.domain.taxaJuros.TaxaJurosResponseDTO;
import org.supernani.domain.taxaJuros.TaxaJurosService;
import org.supernani.repositories.EmprestimoRepository;
import org.supernani.repositories.ParcelaRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.PathParam;

@ApplicationScoped
public class EmprestimoService {
    
    @Inject
    protected TaxaJurosService taxaJurosService;
    
    @Inject
    protected EmprestimoRepository emprestimoRepository;

    @Inject
    protected ParcelaRepository parcelaRepository;

    
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
            LocalDate.now(),
            null
        );
        emprestimo.persist();

        ParcelaService parcelaService = new ParcelaService();
        parcelaService.salvaParcelas(emprestimo);
        return true;
    }


    public List<EmprestimoComParcelasDTO> buscarEmprestimosComParcelas(@PathParam("clienteId") UUID clienteId) {

        List<Emprestimo> emprestimos =
                emprestimoRepository.buscaPorIdCliente(clienteId);

        if (emprestimos.isEmpty()) {
            return null;
        }

        List<EmprestimoComParcelasDTO> resultado = emprestimos.stream()
                .map(emp -> {
                    List<Parcela> parcelas =
                            parcelaRepository.buscaPorIdEmprestimo(emp.getIdEmprestimo());

                    return new EmprestimoComParcelasDTO(
                            emp.getIdEmprestimo(),
                            parcelas
                    );
                })
                .toList();

        return resultado;
    }

}
