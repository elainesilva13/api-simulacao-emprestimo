package org.supernani.domain.parcela;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import io.quarkus.hibernate.panache.PanacheEntityBase;


@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Parcela extends PanacheEntityBase {
    
    @Id
    private UUID id_parcela;
    
    @NotNull
    @Min(1)
    @Max(480)
    private Integer ordemParcela;

    @NotNull
    private  Date dataVencimento;

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
