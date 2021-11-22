package org.statsenko.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.statsenko.entity.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer>, JpaSpecificationExecutor<Client> {

    @Query("SELECT c FROM Client c LEFT JOIN c.branch b WHERE b.branchId=:id")
    List<Client> getClientOnBranch(@Param("id") int BranchId);

    Client findClientByFirstNameAndLastName(String firstName, String lastName);
}
