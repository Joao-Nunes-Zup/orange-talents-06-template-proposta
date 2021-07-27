package com.ot6.proposta.blockage.dto;

public class BlockageRequest {

    private String sistemaResponsavel;

    public BlockageRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
