package com.ot6.proposta.proposal;

import com.ot6.proposta.proposal.dto.NewProposalRequest;
import com.ot6.proposta.config.exception.handler.validation.dto.FormErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/proposals")
public class ProposalController {

    @Autowired
    ProposalRepository proposalRepository;

    @Autowired
    ProposalClient client;

    @Transactional
    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody @Valid NewProposalRequest proposalRequest,
            UriComponentsBuilder uriBuilder
    ) {
        Proposal proposal = proposalRequest.toEntity();

        if (proposal.exists(proposalRepository)) {
            FormErrorResponse errorResponse = new FormErrorResponse("cpfOrCnpj", "Documento já em uso");
            return ResponseEntity.unprocessableEntity().body(errorResponse);
        }

        proposalRepository.save(proposal);
        proposal.checkEligibility(client);

        if (!proposal.isEligible()) {
            FormErrorResponse errorResponse = new FormErrorResponse(
                    "eligibility",
                    "Documento possui restrição financeira"
            );
            return ResponseEntity.unprocessableEntity().body(errorResponse);
        }

        URI uri = proposal.generateProposalUri(uriBuilder);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> consult(@PathVariable Long id) {
        Optional<Proposal> proposal = proposalRepository.findById(id);

        if (proposal.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(proposal.get().toProposalDetailsResponse());
    }
}
