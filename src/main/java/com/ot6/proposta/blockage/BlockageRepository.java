package com.ot6.proposta.blockage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockageRepository extends CrudRepository<Blockage, Long> {
}
