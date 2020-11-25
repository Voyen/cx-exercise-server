package io.voyen.exercise.cx.travelplanner.service;

import java.util.List;
import java.util.Optional;

import io.voyen.exercise.cx.travelplanner.domain.Itinerary;

public interface ItineraryService {

  public List<Itinerary> getAll();

  public Optional<Itinerary> getByName(String name);

  public Itinerary save(Itinerary itinerary);
  
}
