package org.supernani.domain.emprestimo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record EmprestimoDTO(

    UUID idEmprestimo,

    @NotNull(message = "Cliente não informado")
    UUID idCliente,

    @NotNull(message = "Valor tomado não informado")
    @DecimalMin(value = "100.00", message = "Valor mínimo é 100.00")
    @DecimalMax(value = "10000000.00", message = "Valor máximo é 10.000.000,00")
    BigDecimal valorTomado,

    @NotNull(message = "Prazo não informado")
    @Min(value = 1, message = "Prazo deve ser no mínimo 1 mês")
    @Max(value = 480, message = "O prazo deve ser no máximo 480 meses")
    Integer prazoContratado,

    @NotNull(message = "Tipo de amortização não informado")
    TipoAmortizacao tipoAmortizacao,

    StatusEmprestimo statusEmprestimo,

    @DecimalMin(value = "0.0", message = "Taxa de juros não pode ser negativa")
    Double taxaJuros,

    LocalDate dataContratacao

) {
    public static Emprestimo from(EmprestimoDTO dto) {

    if (dto == null) {
        return null;
    }

    BigDecimal saldoDevedoInicial = dto.valorTomado();
    return new Emprestimo(
        null,
        dto.idCliente(),
        dto.valorTomado(),
        dto.prazoContratado(),
        dto.tipoAmortizacao(),
        dto.statusEmprestimo(),
        saldoDevedoInicial, 
        dto.taxaJuros(),
        dto.dataContratacao() != null ? dto.dataContratacao() : LocalDate.now(),
        null
    );
}
}