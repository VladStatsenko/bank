package org.statsenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.statsenko.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank,Integer> {

    boolean existsByBankName(String bankName);
}
