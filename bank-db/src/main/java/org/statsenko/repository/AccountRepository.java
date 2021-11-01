package org.statsenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.statsenko.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
}
