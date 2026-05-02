package org.supernani.domain.emprestimo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import io.quarkus.hibernate.panache.PanacheEntityBase;

//import org.supernani.validation.EmprestimoValido;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
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
@EqualsAndHashCode
@Getter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Emprestimo extends PanacheEntityBase {

    @Id
    @GeneratedValue
    private UUID idEmprestimo;

    @NotNull 
    private UUID idCliente;

    @NotNull
    @DecimalMin("100.00")
    @DecimalMax("10000000.00") //10.000.000,00
    private BigDecimal valorTomado;

    @NotNull
    @Min(1)
    @Max(480)
    private Integer prazoContratado;
    
    @NotNull
    private TipoAmortizacao tipoAmortizacao;

    @NotNull
    private StatusEmprestimo statusEmprestimo;

    //Como ex-gerente de carteira, faz sentido para mim que haja essas duas informações persistidas. Por exemplo, se eu souber a 
    //taxa de juros contratada, consigo buscar uma taxa menor para oferecer um renovação do empréstimo.
    @NotNull
    private BigDecimal saldoDevedor;

    @DecimalMin("0.00000000000000000000001")
    private Double taxaJuros;

    private LocalDate dataContratacao;


}