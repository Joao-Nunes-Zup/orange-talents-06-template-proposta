package com.ot6.proposta.blockage.dto;

import java.time.LocalDateTime;

public class BlockageDetailsReturn {

    private String id;
    private LocalDateTime bloqueadoEm;
    private String sistemaResponsavel;
    private Boolean ativo;

    public BlockageDetailsReturn(
            String id,
            LocalDateTime bloqueadoEm,
            String sistemaResponsavel,
            Boolean ativo
    ) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public String getId() {
        return id;
    }

    public Boolean getAtivo() {
        return ativo;
    }
}
