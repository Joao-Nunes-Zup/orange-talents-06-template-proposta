package com.ot6.proposta.wallet.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.ot6.proposta.card.Card;
import com.ot6.proposta.wallet.Wallet;
import com.ot6.proposta.wallet.WalletType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NewWalletAndCardAssociationRequest {

    @NotBlank
    @Email
    private String email;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NewWalletAndCardAssociationRequest(String email) {
        this.email = email;
    }

    public Wallet toEntity(Card card, WalletType type) {
        return new Wallet(this.email, card, type);
    }
}
