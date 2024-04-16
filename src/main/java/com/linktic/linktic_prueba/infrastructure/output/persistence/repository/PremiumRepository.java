package com.linktic.linktic_prueba.infrastructure.output.persistence.repository;

import com.linktic.linktic_prueba.infrastructure.output.persistence.entity.PremiumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PremiumRepository extends JpaRepository<PremiumEntity,Long> {

    @Query("SELECT p FROM PremiumEntity p WHERE :age BETWEEN p.minAge AND p.maxAge")
    List<PremiumEntity> findPremiumsByAge(int age);
}
