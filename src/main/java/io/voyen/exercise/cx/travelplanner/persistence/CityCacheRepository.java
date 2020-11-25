package io.voyen.exercise.cx.travelplanner.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.voyen.exercise.cx.travelplanner.domain.City;

@Repository
public interface CityCacheRepository extends JpaRepository<City, Long> {

  public Optional<City> findByNameIgnoreCase(String name);
  
}
