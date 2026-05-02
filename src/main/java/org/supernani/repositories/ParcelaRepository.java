package org.supernani.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.quarkus.hibernate.panache.PanacheRepository;
import java.util.List;
import java.util.UUID;

import org.supernani.domain.emprestimo.Emprestimo;
import org.supernani.domain.parcela.Parcela;

@ApplicationScoped
public class ParcelaRepository implements PanacheRepository<Parcela> {
    @Inject
    EmprestimoRepository emprestimoRepository;
   
    public List<Parcela> buscaPorIdEmprestimo(UUID idEmprestimo) {
        Emprestimo emprestimoBuscado = (Emprestimo) emprestimoRepository.find("idEmprestimo", idEmprestimo);
        return list("emprestimo", emprestimoBuscado);
    }
}