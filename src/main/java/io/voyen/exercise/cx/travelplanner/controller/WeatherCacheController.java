package io.voyen.exercise.cx.travelplanner.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.voyen.exercise.cx.travelplanner.client.OpenWeatherClient;
import io.voyen.exercise.cx.travelplanner.client.OpenWeatherModel;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class WeatherCacheController {

  private final OpenWeatherClient owClient;

  @GetMapping(value = "/{city}", produces = "application/json")
  public OpenWeatherModel getCityWeather(@PathVariable String city) throws JsonProcessingException {

    return owClient.getWeatherByCity(city);
  }

}
