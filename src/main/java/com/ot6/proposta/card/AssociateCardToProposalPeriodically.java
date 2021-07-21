package com.ot6.proposta.card;

import com.ot6.proposta.card.dto.NewCardRequest;
import com.ot6.proposta.card.dto.NewCardReturn;
import com.ot6.proposta.proposal.Proposal;
import com.ot6.proposta.proposal.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import javax.transaction.Transactional;
import java.util.Set;

@Component
public class AssociateCardToProposalPeriodically {

    @Autowired
    ProposalRepository proposalRepository;

    @Autowired
    CardClient client;

    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void associate() {
        Set<Proposal> proposalsWithoutCard =
                proposalRepository.findUnrestrictedProposalsWithoutCard();

        proposalsWithoutCard.forEach(proposal -> {
            NewCardRequest cardRequest = proposal.toNewCardRequest();

            try {
                NewCardReturn cardReturn = client.newCard(cardRequest);
                Card card = cardReturn.toEntity(proposal);
                proposal.associateCard(card);
                proposalRepository.save(proposal);
            } catch (HttpStatusCodeException exception) {
                System.out.println(
                    "Erro na geração do cartão para proposta de id: " + proposal.getId()
                    + "status code: " + exception.getStatusCode().toString()
                );
            }
        });
    }
}
