package com.ot6.proposta.biometry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiometryRepository extends CrudRepository<Biometry, Long> {
}
