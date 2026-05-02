package org.supernani.domain.emprestimo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import io.quarkus.hibernate.panache.PanacheEntityBase;
//import org.supernani.validation.EmprestimoValido;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@EqualsAndHashCode(callSuper=false)
@ToString
@Setter
@Getter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Emprestimo extends PanacheEntityBase {

    @Id
    @GeneratedValue
    private UUID idEmprestimo;

    private UUID idCliente;

    private BigDecimal valorTomado;

    private Integer prazoContratado;
    
    private TipoAmortizacao tipoAmortizacao;

    private StatusEmprestimo statusEmprestimo;

    //Como ex-gerente de carteira, faz sentido para mim que haja essas duas informações persistidas. Por exemplo, se eu souber a 
    //taxa de juros contratada, consigo buscar uma taxa menor para oferecer um renovação do empréstimo.
    private BigDecimal saldoDevedor;

    private Double taxaJuros;

    private LocalDate dataContratacao;


}