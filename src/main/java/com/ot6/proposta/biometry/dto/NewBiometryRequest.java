package com.ot6.proposta.biometry.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.ot6.proposta.biometry.Biometry;
import com.ot6.proposta.card.Card;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.validation.constraints.NotBlank;

public class NewBiometryRequest {

    @NotBlank
    private String fingerprint;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NewBiometryRequest(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public Biometry toEntity(Card card) {
        return new Biometry(this.fingerprint, card);
    }

    public boolean isFingerprintValid() {
        return Base64.isBase64(this.fingerprint);
    }
}
