package org.statsenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.statsenko.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
}
