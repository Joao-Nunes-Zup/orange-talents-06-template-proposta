package com.ot6.proposta.proposal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {

    @Query(
        "select p.cpfOrCnpj from Proposal p where p.cpfOrCnpj = :cpfOrCnpj"
    )
    Optional<Proposal> findByCpfOrCnpj(String cpfOrCnpj);
}
