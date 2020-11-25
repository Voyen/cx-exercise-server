package io.voyen.exercise.cx.travelplanner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.voyen.exercise.cx.travelplanner.client.DomainMapper;
import io.voyen.exercise.cx.travelplanner.client.OpenWeatherModel;
import io.voyen.exercise.cx.travelplanner.domain.City;
import io.voyen.exercise.cx.travelplanner.service.CityCacheService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class WeatherCacheController {

  private final CityCacheService svc;

  @GetMapping(value = "/{city}", produces = "application/json")
  public OpenWeatherModel getCityWeather(@PathVariable String city, @RequestParam(required = false, defaultValue = "false") boolean refresh)
      throws NotFoundException {
    return DomainMapper.mapToDto(svc.getWeatherByCityName(city, refresh));
  }

  @GetMapping(value = "/cached/{city}", produces = "application/json")
  public City getCachedCity(@PathVariable String city) throws NotFoundException {
    return svc.getWeatherByCityName(city, false);
  }

}
