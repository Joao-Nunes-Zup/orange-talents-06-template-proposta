package com.ot6.proposta.card.dto;

import com.ot6.proposta.blockage.dto.BlockageDetailsReturn;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CardDetailsReturn {

    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private List<BlockageDetailsReturn> bloqueios = new ArrayList<>();
    private String idProposta;

    public CardDetailsReturn(
            String id,
            LocalDateTime emitidoEm,
            String titular,
            List<BlockageDetailsReturn> bloqueios,
            String idProposta
    ) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios.addAll(bloqueios);
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public boolean hasActiveBlockages() {
        Set<BlockageDetailsReturn> activeBlockages =
                this.bloqueios.stream().filter(BlockageDetailsReturn::getAtivo).collect(Collectors.toSet());

        Assert.state(activeBlockages.size() <= 1, "Múltiplos bloqueios ativos");

        return activeBlockages.size() == 1;
    }
}
