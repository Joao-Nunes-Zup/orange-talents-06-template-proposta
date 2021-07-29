package com.ot6.proposta.proposal;

import com.ot6.proposta.card.dto.ProposalDetailsResponse;
import com.ot6.proposta.proposal.dto.NewProposalRequest;
import com.ot6.proposta.config.exception.handler.validation.dto.FormErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${encrypt.password}")
    private String password;

    @Value("${encrypt.password}")
    private String key;

    @Transactional
    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody @Valid NewProposalRequest proposalRequest,
            UriComponentsBuilder uriBuilder
    ) {
        Proposal proposal = proposalRequest.toEntity(password, key);

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

        URI uri = proposal.generateUri(uriBuilder);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> consult(@PathVariable Long id) {
        Optional<Proposal> proposal = proposalRepository.findById(id);

        if (proposal.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ProposalDetailsResponse proposalDetailsResponse =
                proposal.get().toProposalDetailsResponse(password, key);

        return ResponseEntity.ok(proposalDetailsResponse);
    }
}
