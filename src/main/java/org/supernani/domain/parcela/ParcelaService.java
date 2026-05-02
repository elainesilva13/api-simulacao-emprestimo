package org.supernani.domain.parcela;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.supernani.domain.emprestimo.Emprestimo;
import org.supernani.domain.emprestimo.TipoAmortizacao;

@ApplicationScoped
public class ParcelaService {
    public void salvaParcelas(Emprestimo emprestimo){
        if(emprestimo.getTipoAmortizacao()==TipoAmortizacao.PRICE){
            salvaParcelasPRICE(emprestimo);
        }
        if(emprestimo.getTipoAmortizacao()==TipoAmortizacao.SAC){
            salvaParcelasSAC(emprestimo);
        }
    }


    @Transactional
    protected void salvaParcelasSAC(Emprestimo emprestimo) {
        BigDecimal valorTomado = emprestimo.getValorTomado();
        Integer prazoContratado = emprestimo.getPrazoContratado();
        BigDecimal valorAmortizacao = valorTomado.divide(
                BigDecimal.valueOf(prazoContratado), 10, RoundingMode.HALF_UP);

        BigDecimal saldo = emprestimo.getValorTomado();
        BigDecimal taxaJuros = BigDecimal.valueOf(emprestimo.getTaxaJuros());
        LocalDate dataContratacao = emprestimo.getDataContratacao();
        for (Integer i = 1; i <= prazoContratado; i++) {

            BigDecimal valorJuros = saldo.multiply(taxaJuros);
            BigDecimal valorParcela = valorAmortizacao.add(valorJuros);
            saldo = saldo.subtract(valorAmortizacao);

            Parcela parcela = new Parcela(
                null,
                emprestimo,
                i,
                dataContratacao.plusMonths(i),
                valorAmortizacao,
                valorJuros,
                valorParcela,
                StatusParcela.PENDENTE

            );
            parcela.persist();
        }
    }

    @Transactional
    protected void salvaParcelasPRICE(Emprestimo emprestimo) {
        BigDecimal taxaJuros = BigDecimal.valueOf(emprestimo.getTaxaJuros());

        BigDecimal fator = BigDecimal.ONE.add(taxaJuros);

        BigDecimal base = fator.pow(emprestimo.getPrazoContratado());

        BigDecimal saldo = emprestimo.getValorTomado();

        BigDecimal parcelaFixa = saldo.multiply(
                taxaJuros.multiply(base))
                .divide(base.subtract(BigDecimal.ONE), 10, RoundingMode.HALF_UP);


        for (int i = 1; i <= emprestimo.getPrazoContratado(); i++) {

            BigDecimal valorJuros = saldo.multiply(taxaJuros);
            BigDecimal valorAmortizacao = parcelaFixa.subtract(valorJuros);
            saldo = saldo.subtract(valorAmortizacao);

             Parcela parcela = new Parcela(
                null,
                emprestimo,
                i,
                emprestimo.getDataContratacao().plusMonths(i),
                valorAmortizacao,
                valorJuros,
                parcelaFixa,
                StatusParcela.PENDENTE

            );
            parcela.persist();
        }
    }
}
