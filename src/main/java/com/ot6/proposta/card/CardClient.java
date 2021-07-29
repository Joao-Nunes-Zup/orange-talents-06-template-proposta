package com.ot6.proposta.card;

import com.ot6.proposta.blockage.dto.BlockageRequest;
import com.ot6.proposta.blockage.dto.BlockageReturn;
import com.ot6.proposta.card.dto.CardDetailsReturn;
import com.ot6.proposta.card.dto.NewCardRequest;
import com.ot6.proposta.card.dto.NewCardReturn;
import com.ot6.proposta.travel.dto.NewTravelNotificationRequest;
import com.ot6.proposta.wallet.dto.AssociateToWalletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "cards", url = "${api-card-manager}")
public interface CardClient {

    @RequestMapping(
            path = "/api/cartoes",
            method = RequestMethod.POST,
            consumes = "application/json"
    )
    NewCardReturn newCard(NewCardRequest cardRequest);

    @RequestMapping(
            path = "/api/cartoes/{id}",
            method = RequestMethod.GET,
            consumes = "application/json"
    )
    CardDetailsReturn findCardDetails(@PathVariable String id);

    @RequestMapping(
            path = "/api/cartoes/{id}/bloqueios",
            method = RequestMethod.POST,
            consumes = "application/json"
    )
    BlockageReturn blockCard(@PathVariable String id, BlockageRequest proposalApi);

    @RequestMapping(
            path = "/api/cartoes/{id}/avisos",
            method = RequestMethod.POST,
            consumes = "application/json"
    )
    void newTravelNotification(
            @PathVariable String id,
            NewTravelNotificationRequest notificationRequest
    );

    @RequestMapping(
            path = "/api/cartoes/{id}/carteiras",
            method = RequestMethod.POST,
            consumes = "application/json"
    )
    void associateToWallet(
            @PathVariable String id,
            AssociateToWalletRequest associateToWalletRequest
    );
}
