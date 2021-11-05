package org.statsenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.statsenko.entity.Profile;
@Repository
public interface ProfileRepository extends JpaRepository<Profile,Integer> {
}
