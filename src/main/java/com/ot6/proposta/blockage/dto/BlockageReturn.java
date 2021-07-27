package com.ot6.proposta.blockage.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class BlockageReturn {

    private String resultado;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BlockageReturn(String resultado) {
        this.resultado = resultado;
    }

    public String getResult() {
        return this.resultado;
    }
}
