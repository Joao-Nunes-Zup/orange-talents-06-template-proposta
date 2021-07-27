package com.ot6.proposta.blockage.dto;

import com.ot6.proposta.blockage.Blockage;
import com.ot6.proposta.card.Card;

import javax.validation.constraints.NotBlank;

public class NewBlockageRequest {

    @NotBlank
    private String clientIpAddress;

    @NotBlank
    private String userAgent;

    public NewBlockageRequest(String clientIpAddress, String userAgent) {
        this.clientIpAddress = clientIpAddress;
        this.userAgent = userAgent;
    }

    public Blockage toEntity(Card card) {
        return new Blockage(this.clientIpAddress, this.userAgent, card);
    }
}
