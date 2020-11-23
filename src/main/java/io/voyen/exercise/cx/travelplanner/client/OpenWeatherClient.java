package io.voyen.exercise.cx.travelplanner.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.voyen.exercise.cx.travelplanner.CustomProperties;

@Component
public class OpenWeatherClient {

  private final RestTemplate restTemplate;

  private String fiveDayForecastUri;

  @Autowired
  public OpenWeatherClient(RestTemplate templ, CustomProperties config) {
    this.restTemplate = templ;
    this.fiveDayForecastUri = "https://api.openweathermap.org/data/2.5/forecast?" + 
                              "appid=" + config.getOpenWeatherApiKey() + 
                              "&units=metric";
  }

  public OpenWeatherModel getWeatherByCity(String city) throws JsonProcessingException {
    String madeUri = fiveDayForecastUri + "&q=" + city;

    String response = restTemplate.getForObject(madeUri, String.class);

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(response, OpenWeatherModel.class);
  }
  
}
