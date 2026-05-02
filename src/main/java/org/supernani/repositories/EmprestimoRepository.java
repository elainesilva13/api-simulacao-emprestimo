package org.supernani.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.panache.PanacheRepository;
import java.util.List;
import java.util.UUID;

import org.supernani.domain.emprestimo.Emprestimo;

@ApplicationScoped
public class EmprestimoRepository implements PanacheRepository<Emprestimo> {
   
    public List<Emprestimo> buscaPorIdCliente(UUID idCliente) {
        return list("idCliente", idCliente);
    }

    public Emprestimo buscaPorIdEmprestimo(UUID idEmprestimo) {
        return find("idEmprestimo", idEmprestimo)
            .firstResult();
    }
}