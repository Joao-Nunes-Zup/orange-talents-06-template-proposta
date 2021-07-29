package com.ot6.proposta.wallet;

import com.ot6.proposta.card.Card;
import com.ot6.proposta.card.CardClient;
import com.ot6.proposta.card.CardRepository;
import com.ot6.proposta.config.exception.handler.validation.dto.FormErrorResponse;
import com.ot6.proposta.wallet.dto.AssociateToWalletRequest;
import com.ot6.proposta.wallet.dto.NewWalletAndCardAssociationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AssociateWalletAndCardController {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    CardClient cardClient;

    @Transactional
    @PostMapping("/wallets/{cardId}")
    public ResponseEntity<?> associateWithPayPal(
            @PathVariable String cardId,
            @RequestBody @Valid NewWalletAndCardAssociationRequest newWalletRequest,
            UriComponentsBuilder uriBuilder
    ) {
        Optional<Card> possibleCard = cardRepository.findById(cardId);

        if (possibleCard.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Card card = possibleCard.get();

        if (card.isAssociatedWithPaypal(cardRepository)) {
            FormErrorResponse errorResponse =
                    new FormErrorResponse("card", "Cartão já associado ao PayPal.");
            return ResponseEntity.unprocessableEntity().body(errorResponse);
        }

        Wallet wallet = newWalletRequest.toEntity(card, WalletType.PAYPALL);
        AssociateToWalletRequest walletRequest = wallet.toAssociateToWalletRequest();

        cardClient.associateToWallet(cardId, walletRequest);
        walletRepository.save(wallet);

        return ResponseEntity.ok(wallet.generateUri(uriBuilder));
    }
}
