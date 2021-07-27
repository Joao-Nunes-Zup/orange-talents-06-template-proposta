package com.ot6.proposta.card;

import com.ot6.proposta.card.dto.CardDetailsReturn;
import com.ot6.proposta.proposal.Proposal;
import feign.FeignException;
import org.springframework.web.client.HttpStatusCodeException;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cards")
public class Card {

    @NotBlank
    @Id
    private String id;

    @NotNull
    @Valid
    @OneToOne(mappedBy = "card")
    private Proposal proposal;

    @Deprecated
    public Card() {}

    public Card(
            @NotBlank String id,
            @NotNull @Valid Proposal proposal
    ) {
        this.id = id;
        this.proposal = proposal;
    }

    public String getCardNumber() {
        return this.id;
    }

    public boolean isBlocked(CardClient cardClient) throws FeignException {
        CardDetailsReturn cardReturn = cardClient.findCardDetails(this.id);
        return cardReturn.hasActiveBlockages();
    }
}
