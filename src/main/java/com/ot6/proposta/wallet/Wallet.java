package com.ot6.proposta.wallet;

import com.ot6.proposta.card.Card;
import com.ot6.proposta.wallet.dto.AssociateToWalletRequest;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URI;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private WalletType type;

    @ManyToOne
    private Card card;

    public Wallet(
            @NotBlank @Email String email,
            @NotNull @Valid Card card,
            @NotNull WalletType type
    ) {
        this.email = email;
        this.card = card;
        this.type = type;
    }

    public URI generateUri(UriComponentsBuilder uriBuilder) {
        return uriBuilder.path("/wallets/{id}").buildAndExpand(this.id).toUri();
    }

    public AssociateToWalletRequest toAssociateToWalletRequest() {
        return new AssociateToWalletRequest(this.email, this.type.toString());
    }
}
