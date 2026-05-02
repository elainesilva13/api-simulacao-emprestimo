package org.supernani.domain.emprestimo;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record EmprestimoDTO(
    UUID idEmprestimo,
    UUID idCliente,
    BigDecimal valorTomado,
    Integer prazoContratado,    
    TipoAmortizacao tipoAmortizacao,
    StatusEmprestimo statusEmprestimo,
    Double taxaJuros,
    LocalDate dataContratacao

) {
    
}
