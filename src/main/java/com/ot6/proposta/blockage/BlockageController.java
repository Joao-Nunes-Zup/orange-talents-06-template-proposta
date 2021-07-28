package com.ot6.proposta.blockage;

import com.ot6.proposta.blockage.dto.BlockageRequest;
import com.ot6.proposta.blockage.dto.BlockageReturn;
import com.ot6.proposta.card.Card;
import com.ot6.proposta.card.CardClient;
import com.ot6.proposta.card.CardRepository;
import com.ot6.proposta.config.exception.handler.client.dto.ClientErrorResponse;
import com.ot6.proposta.shared.utils.RequestIpRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.apache.HttpClientUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping("/blockages")
public class BlockageController {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BlockageRepository blockageRepository;

    @Autowired
    CardClient cardClient;

    @Transactional
    @PostMapping("/{cardId}")
    public ResponseEntity<?> BlockCard(
            @PathVariable String cardId,
            @RequestHeader("User-Agent") String userAgent,
            HttpServletRequest request
    ) {
        Optional<Card> possibleCard = cardRepository.findById(cardId);

        if (possibleCard.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Card card = possibleCard.get();

        if (card.isBlocked(cardClient)) {
            ClientErrorResponse errorResponse = new ClientErrorResponse("Cartão já bloqueado");
            return ResponseEntity.unprocessableEntity().body(errorResponse);
        }

        String ip = RequestIpRetriever.retrieve(request);
        Blockage blockage = new Blockage(ip, userAgent, card);

        BlockageReturn blockageReturn =
                cardClient.blockCard(cardId, new BlockageRequest("ProposalApi"));

        if (blockageReturn.getResult().equals("BLOQUEADO")) {
            card.blockCard();
            blockageRepository.save(blockage);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
