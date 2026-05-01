package org.supernani.domain.emprestimo;

import java.math.BigDecimal;
import java.util.UUID;

import io.quarkus.hibernate.panache.PanacheEntityBase;

//import org.supernani.validation.EmprestimoValido;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
//import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
//@Data
@NoArgsConstructor
@AllArgsConstructor

public class Emprestimo extends PanacheEntityBase {

    @Id
    @GeneratedValue
    private UUID idEmprestimo;

    @NotBlank 
    private UUID idCliente;

    @NotNull
    @DecimalMin("100.00")
    @DecimalMax("10000000.00") //10.000.000,00
    private BigDecimal valorTomado;

    @NotNull
    @Min(1)
    @Max(480)
    private Integer prazoContratado;
    
    @NotBlank
    private TipoAmortizacao tipoAmortizacao;

    @NotBlank
    private StatusEmprestimo statusEmprestimo;

    //Como ex-gerente de carteira, faz sentido para mim que haja essas duas informações persistidas. Por exemplo, se eu souber a 
    //taxa de juros contratada, consigo buscar uma taxa menor para oferecer um renovação do empréstimo.
    @NotNull
    private BigDecimal saldoDevedor;

    @DecimalMin("0.00000000000000000000001")
    private Double taxaJuros;


}