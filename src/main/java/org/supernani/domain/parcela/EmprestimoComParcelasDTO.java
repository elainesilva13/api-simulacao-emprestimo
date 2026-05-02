package org.supernani.domain.parcela;

import java.util.List;
import java.util.UUID;

public class EmprestimoComParcelasDTO {

    public UUID emprestimoId;
    public List<Parcela> parcelas;

    public EmprestimoComParcelasDTO(UUID emprestimoId, List<Parcela> parcelas) {
        this.emprestimoId = emprestimoId;
        this.parcelas = parcelas;
    }
}