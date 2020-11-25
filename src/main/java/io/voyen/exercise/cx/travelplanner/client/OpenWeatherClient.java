package io.voyen.exercise.cx.travelplanner.client;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.voyen.exercise.cx.travelplanner.CustomProperties;
import javassist.NotFoundException;

@Service
public class OpenWeatherClient {

  private final RestTemplate restTemplate;

  private String fiveDayForecastUri;

  @Autowired
  public OpenWeatherClient(RestTemplate templ, CustomProperties config) {
    this.restTemplate = templ;
    // @formatter:off
    this.fiveDayForecastUri = "https://api.openweathermap.org/data/2.5/forecast?" + 
                              "appid=" + config.getOpenWeatherApiKey() + 
                              "&units=metric";
    // @formatter:on
  }

  public OpenWeatherModel getWeatherByCity(String city) throws NotFoundException {
    String madeUri = fiveDayForecastUri + "&q=" + city;

    String response = restTemplate.getForObject(madeUri, String.class);

    ObjectMapper mapper = new ObjectMapper();
    OpenWeatherModel foundCity = null;
    try {
      foundCity = mapper.readValue(response, OpenWeatherModel.class);
    } catch (IOException e) {
      throw new NotFoundException("Unable to find city by provided name");
    }

    if (foundCity == null) {
      throw new NotFoundException("Unable to find city by provided name");
    } else {
      return foundCity;
    }
  }

}
