package com.ot6.proposta.card;

import com.ot6.proposta.wallet.Wallet;
import com.ot6.proposta.wallet.WalletType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, String> {

    @Query(
        "select c.id from Card c join c.wallets w where c.id = :id and w.type = :type"
    )
    List<Card> findAllByIdAndWalletType(String id, WalletType type);
}
