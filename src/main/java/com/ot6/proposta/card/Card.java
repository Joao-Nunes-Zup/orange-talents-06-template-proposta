package com.ot6.proposta.card;

import com.ot6.proposta.card.dto.CardDetailsReturn;
import com.ot6.proposta.proposal.Proposal;
import com.ot6.proposta.wallet.Wallet;
import com.ot6.proposta.wallet.WalletRepository;
import com.ot6.proposta.wallet.WalletType;
import feign.FeignException;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cards")
public class Card {

    @NotBlank
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private CardStatus status = CardStatus.DESBLOQUEADO;

    @NotNull
    @Valid
    @OneToOne(mappedBy = "card")
    private Proposal proposal;

    @NotNull
    @Valid
    @OneToMany(mappedBy = "card")
    private Set<Wallet> wallets = new HashSet<>();

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

    public void blockCard() {
        this.status = CardStatus.BLOQUEADO;
    }

    public boolean isAssociatedWithWalletType(WalletType walletType, CardRepository cardRepository) {
        List<Card> cards = cardRepository.findAllByIdAndWalletType(this.id, walletType);
        Assert.state(
            cards.size() <= 1,
            "Múltiplos cartões associados a um mesmo serviço de pagamento: " + walletType
        );
        return cards.size() == 1;
    }
}
