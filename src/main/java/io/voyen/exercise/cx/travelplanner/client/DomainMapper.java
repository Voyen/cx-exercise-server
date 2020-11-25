package io.voyen.exercise.cx.travelplanner.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import io.voyen.exercise.cx.travelplanner.domain.City;

@Component
public class DomainMapper {

  private DomainMapper(){}

  public static City mapToCity(OpenWeatherModel model) {
    City city = new City();
    city.setId(model.getId());
    city.setName(model.getName());
    city.setCountry(model.getCountry());
    city.setCachedAt(model.getCachedAt());

    List<City.WeatherEntry> weatherEntries = new ArrayList<>();
    model.getDetails().stream().forEach(m -> {
      var entry = new City.WeatherEntry();
      entry.setTemp(m.getTemp());
      entry.setDescription(m.getDescription());
      entry.setCloudiness(m.getCloudiness());
      entry.setTimestamp(m.getTimestamp());
      entry.setEpochSeconds(m.getEpoch());
      weatherEntries.add(entry);
    });

    city.setWeatherEntries(weatherEntries);

    return city;
  }

  public static OpenWeatherModel mapToDto(City city) {
    OpenWeatherModel model = new OpenWeatherModel();
    model.setId(city.getId());
    model.setName(city.getName());
    model.setCountry(city.getCountry());
    model.setCachedAt(city.getCachedAt());

    List<OpenWeatherModel.WeatherDetails> details = new ArrayList<>();
    city.getWeatherEntries().stream().forEach(c -> {
      var entry = new OpenWeatherModel.WeatherDetails();
      entry.setTemp(c.getTemp());
      entry.setDescription(c.getDescription());
      entry.setCloudiness(c.getCloudiness());
      entry.setTimestamp(c.getTimestamp());
      entry.setEpoch(c.getEpochSeconds());
      details.add(entry);
    });

    model.setDetails(details);

    return model;
  }
  
}
