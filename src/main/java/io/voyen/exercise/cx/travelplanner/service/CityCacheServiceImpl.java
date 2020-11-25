package io.voyen.exercise.cx.travelplanner.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import io.voyen.exercise.cx.travelplanner.client.DomainMapper;
import io.voyen.exercise.cx.travelplanner.client.OpenWeatherClient;
import io.voyen.exercise.cx.travelplanner.domain.City;
import io.voyen.exercise.cx.travelplanner.persistence.CityCacheRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityCacheServiceImpl implements CityCacheService {

  private final CityCacheRepository repo;
  private final OpenWeatherClient client;

  @Override
  public City getWeatherByCityName(String name, boolean refresh) throws NotFoundException {
    if (!refresh) {
      var cached = repo.findByNameIgnoreCase(name);
      if (cached.isPresent() && cached.get().getCachedAt().isAfter(Instant.now().minusSeconds(60L * 60))) {
        return cached.get();
      } 
    }
    
    var live = DomainMapper.mapToCity(client.getWeatherByCity(name));
    repo.save(live);

    return live;
  }

  @Override
  public City addToCache(City city) {
    return repo.save(city);
  }
  
}
