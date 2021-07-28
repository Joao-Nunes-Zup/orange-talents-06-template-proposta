package com.ot6.proposta.travel;

import com.ot6.proposta.card.Card;
import com.ot6.proposta.card.CardClient;
import com.ot6.proposta.card.CardRepository;
import com.ot6.proposta.shared.utils.RequestIpRetriever;
import com.ot6.proposta.travel.dto.NewTravelNotificationReturn;
import com.ot6.proposta.travel.dto.NewTravelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class TravelController {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    TravelRepository travelRepository;

    @Autowired
    CardClient cardClient;

    @Transactional
    @PostMapping("/travels/{cardId}")
    public ResponseEntity<?> create(
            @PathVariable String cardId,
            @RequestHeader("User-Agent") String userAgent,
            @RequestBody @Valid NewTravelRequest travelRequest,
            HttpServletRequest request
    ) {
        Optional<Card> possibleCard = cardRepository.findById(cardId);

        if (possibleCard.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Card card = possibleCard.get();
        String ip = RequestIpRetriever.retrieve(request);
        Travel travel = travelRequest.toEntity(ip, userAgent, card);

        NewTravelNotificationReturn notificationReturn =
                cardClient.newTravelNotification(cardId, travel.toNewTravelNotificationRequest());

        travelRepository.save(travel);
        return ResponseEntity.ok().build();
    }
}
