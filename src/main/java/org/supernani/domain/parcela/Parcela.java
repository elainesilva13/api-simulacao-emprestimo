package org.supernani.domain.parcela;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.supernani.domain.emprestimo.Emprestimo;

import io.quarkus.hibernate.panache.PanacheEntityBase;


@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Parcela extends PanacheEntityBase {
    
    @Id
    private UUID idParcela;

    @ManyToOne(cascade = CascadeType.ALL)
    private Emprestimo emprestimo;
    
    @NotNull
    @Min(1)
    @Max(480)
    private Integer ordemParcela;

    @NotNull
    private  LocalDate dataVencimento;

    @NotNull
    @DecimalMin("0.00000000000000000000001")
    private BigDecimal valorAmortizacao;

    @NotNull
    @DecimalMin("0.00000000000000000000001")
    private BigDecimal valorJuros;

    @NotNull
    @DecimalMin("0.00000000000000000000001")
    private BigDecimal valorParcela;

    @NotNull
    private StatusParcela statusParcela;
    
}
