package com.ot6.proposta.proposal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProposalRepository extends CrudRepository<Proposal, Long> {

    @Query(
        "select p.cpfOrCnpj from Proposal p where p.cpfOrCnpj = :cpfOrCnpj"
    )
    Optional<Proposal> findByCpfOrCnpj(String cpfOrCnpj);

    @Query(
        "select p from Proposal p where p.eligibility = 'ELEGIVEL' and p.card = null"
    )
    Set<Proposal> findUnrestrictedProposalsWithoutCard();
}
