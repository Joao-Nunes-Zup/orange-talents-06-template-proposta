package com.ot6.proposta.proposal;

import com.ot6.proposta.proposal.dto.NewProposalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/proposal")
public class ProposalController {

    @Autowired
    ProposalRepository proposalRepository;

    @Transactional
    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody @Valid NewProposalRequest proposalRequest,
            UriComponentsBuilder uriBuilder
    ) {
        Proposal proposal = proposalRequest.toEntity();
        proposalRepository.save(proposal);
        URI uri = uriBuilder.path("/proposal/{id}").buildAndExpand(proposal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
