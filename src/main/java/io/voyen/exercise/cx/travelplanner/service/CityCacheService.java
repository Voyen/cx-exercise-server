package io.voyen.exercise.cx.travelplanner.service;

import io.voyen.exercise.cx.travelplanner.domain.City;
import javassist.NotFoundException;

public interface CityCacheService {

  public City getWeatherByCityName(String name, boolean refresh) throws NotFoundException;

  public City addToCache(City city);
  
}
