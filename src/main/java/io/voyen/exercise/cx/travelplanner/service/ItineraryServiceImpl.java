package io.voyen.exercise.cx.travelplanner.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.voyen.exercise.cx.travelplanner.domain.Itinerary;
import io.voyen.exercise.cx.travelplanner.persistence.ItineraryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItineraryServiceImpl implements ItineraryService {

  private final ItineraryRepository repo;
  
  @Override
  public List<Itinerary> getAll() {
    return repo.findAll();
  }

  @Override
  public Optional<Itinerary> getByName(String name) {
    return repo.findByName(name);
  }

  @Override
  public Itinerary save(Itinerary itinerary) {
    return repo.save(itinerary);
  }

  @Override
  public void deleteById(long id) {
    repo.deleteById(id);
  }

}
