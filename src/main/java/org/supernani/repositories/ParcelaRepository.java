package org.supernani.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;
import org.supernani.domain.parcela.Parcela;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ParcelaRepository implements PanacheRepositoryBase<Parcela, UUID> {
    @Inject
    EmprestimoRepository emprestimoRepository;
   
    public List<Parcela> buscaPorIdEmprestimo(UUID idEmprestimo) {
        return list("emprestimo.idEmprestimo", idEmprestimo);
    }
}