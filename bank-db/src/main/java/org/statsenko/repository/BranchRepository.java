package org.statsenko.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.statsenko.entity.Branch;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {
    @Override
    Optional<Branch> findById(Integer integer);

    @Query("SELECT br FROM Branch br LEFT JOIN br.main b WHERE b.bankId = :id")
    List<Branch> getAllBranchOfBank(@Param("id") int BankId);

    @Query("SELECT b FROM Branch b LEFT JOIN b.clients c WHERE c.clientId = :id")
    List<Branch> getBranchOnClient(@Param("id")int ClientId);

}
