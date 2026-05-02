package org.supernani.domain.emprestimo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.supernani.domain.parcela.Parcela;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
//import org.supernani.validation.EmprestimoValido;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@EqualsAndHashCode(callSuper=false)
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Emprestimo extends PanacheEntityBase {

    @Id
    @GeneratedValue
    private UUID idEmprestimo;

    private UUID idCliente;

    private BigDecimal valorTomado;

    private Integer prazoContratado;
    
    private TipoAmortizacao tipoAmortizacao;

    @NotNull(message = "Status do empréstimo não informado")
    private StatusEmprestimo statusEmprestimo;

    //Como ex-gerente de carteira, faz sentido para mim que haja essas duas informações persistidas. Por exemplo, se eu souber a 
    //taxa de juros contratada, consigo buscar uma taxa menor para oferecer um renovação do empréstimo.
    private BigDecimal saldoDevedor;

    private Double taxaJuros;

    private LocalDate dataContratacao;

    @OneToMany(mappedBy = "emprestimo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Parcela> parcelas;


}