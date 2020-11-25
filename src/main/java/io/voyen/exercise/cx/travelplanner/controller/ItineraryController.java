package io.voyen.exercise.cx.travelplanner.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.voyen.exercise.cx.travelplanner.domain.Itinerary;
import io.voyen.exercise.cx.travelplanner.service.ItineraryService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/saved")
@RequiredArgsConstructor
public class ItineraryController {

  private final ItineraryService svc;

  @GetMapping(produces = "application/json")
  public List<Itinerary> getAll() {
    return svc.getAll();
  }

  @GetMapping(value = "/{name}", produces = "application/json")
  public Itinerary getByName(@PathVariable String name) throws NotFoundException {
    return svc.getByName(name).orElseThrow(() -> new NotFoundException("No itinerary found with provided name"));
  }

  @PostMapping
  public Itinerary saveNew(@RequestBody Itinerary input) {
    return svc.save(input);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable long id) {
    svc.deleteById(id);
  }
  
}
