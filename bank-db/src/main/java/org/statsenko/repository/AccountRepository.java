package org.statsenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.statsenko.entity.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    @Query("SELECT a FROM Account a LEFT JOIN a.client c WHERE c.clientId=:id")
    List<Account> getClientAccount(@Param("id") int ClientId);
}
