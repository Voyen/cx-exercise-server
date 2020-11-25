package io.voyen.exercise.cx.travelplanner.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.voyen.exercise.cx.travelplanner.domain.City;

@Repository
public interface WeatherEntryRepository extends JpaRepository<City.WeatherEntry, Long> {
  
}
