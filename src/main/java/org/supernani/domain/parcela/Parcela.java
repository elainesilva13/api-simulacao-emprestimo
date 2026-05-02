package org.supernani.domain.parcela;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.supernani.domain.emprestimo.Emprestimo;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@Setter
@EqualsAndHashCode(callSuper=false)
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Parcela extends PanacheEntityBase {
    
    @Id
    @GeneratedValue
    private UUID idParcela;
    
    @ManyToOne
    @JoinColumn(name = "emprestimo_id")    
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
