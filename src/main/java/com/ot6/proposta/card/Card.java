package com.ot6.proposta.card;

import com.ot6.proposta.proposal.Proposal;

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

    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", proposal=" + proposal +
                '}';
    }
}
