package io.voyen.exercise.cx.travelplanner.controller;

import java.util.Set;

import io.voyen.exercise.cx.travelplanner.domain.City;
import lombok.Data;

@Data
public class ItineraryModel {

  private long id;
  private String name;
  private Set<City> entries;

}