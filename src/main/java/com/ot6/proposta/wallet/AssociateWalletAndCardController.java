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
@RequestMapping("/wallets")
public class AssociateWalletAndCardController {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    CardClient cardClient;

    @Transactional
    @PostMapping("/pay_pall/{cardId}")
    public ResponseEntity<?> associateWithPayPal(
            @PathVariable String cardId,
            @RequestBody @Valid NewWalletAndCardAssociationRequest newWalletRequest,
            UriComponentsBuilder uriBuilder
    ) {
        return associate(cardId, newWalletRequest, WalletType.PAY_PALL, uriBuilder);
    }

    @Transactional
    @PostMapping("/samsung_pay/{cardId}")
    public ResponseEntity<?> associateWithSamsungPay(
            @PathVariable String cardId,
            @RequestBody @Valid NewWalletAndCardAssociationRequest newWalletRequest,
            UriComponentsBuilder uriBuilder
    ) {
        return associate(cardId, newWalletRequest, WalletType.SAMSUNG_PAY, uriBuilder);
    }

    private ResponseEntity<?> associate(
            String cardId,
            NewWalletAndCardAssociationRequest newWalletRequest,
            WalletType walletType,
            UriComponentsBuilder uriBuilder
    ) {
        Optional<Card> possibleCard = cardRepository.findById(cardId);

        if (possibleCard.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Card card = possibleCard.get();

        if (card.isAssociatedWithWalletType(walletType, cardRepository)) {
            FormErrorResponse errorResponse =
                    new FormErrorResponse("card", "Cartão já associado ao PayPal.");
            return ResponseEntity.unprocessableEntity().body(errorResponse);
        }

        Wallet wallet = newWalletRequest.toEntity(card, WalletType.PAY_PALL);
        AssociateToWalletRequest walletRequest = wallet.toAssociateToWalletRequest();

        cardClient.associateToWallet(cardId, walletRequest);
        walletRepository.save(wallet);

        return ResponseEntity.created(wallet.generateUri(uriBuilder)).build();
    }
}
