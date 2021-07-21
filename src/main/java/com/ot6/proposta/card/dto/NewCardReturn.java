package com.ot6.proposta.card.dto;

import com.ot6.proposta.card.Card;
import com.ot6.proposta.proposal.Proposal;
import com.ot6.proposta.proposal.ProposalRepository;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;

public class NewCardReturn {

    @NotBlank
    @CreditCardNumber
    private String id;

    @NotNull
    @Positive
    private Long idProposta;

    public NewCardReturn(
            @NotBlank @CreditCardNumber String id,
            @NotNull @Positive Long idProposta
    ) {
        this.id = id;
        this.idProposta = idProposta;
    }

    public Card toEntity(Proposal proposal) {
        return new Card(this.id, proposal);
    }
}
