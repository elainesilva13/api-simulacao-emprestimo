package org.supernani.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;
import org.supernani.domain.emprestimo.Emprestimo;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class EmprestimoRepository implements PanacheRepositoryBase<Emprestimo, UUID> {
   
    public List<Emprestimo> buscaPorIdCliente(UUID idCliente) {
        return list("idCliente", idCliente);
    }

    public Emprestimo buscaPorIdEmprestimo(UUID idEmprestimo) {
        return findById(idEmprestimo);
    }
    
}