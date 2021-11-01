package org.statsenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.statsenko.entity.AccountType;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType,Integer> {
}
