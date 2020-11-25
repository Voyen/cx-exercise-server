package io.voyen.exercise.cx.travelplanner.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.voyen.exercise.cx.travelplanner.domain.Itinerary;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
  
  public Optional<Itinerary> findByName(String name);
  
}
