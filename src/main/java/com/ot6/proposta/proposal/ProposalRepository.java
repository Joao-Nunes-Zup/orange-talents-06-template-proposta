package com.ot6.proposta.proposal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends CrudRepository<Proposal, Long> {
}
