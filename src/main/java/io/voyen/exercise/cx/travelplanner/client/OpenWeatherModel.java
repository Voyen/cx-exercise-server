package io.voyen.exercise.cx.travelplanner.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;

@Data
@JsonDeserialize(using = OpenWeatherDeserializer.class)
public class OpenWeatherModel implements Serializable {

  private static final long serialVersionUID = 6580626288167388288L;

  private long id;
  private String name;
  private String country;
  private List<WeatherDetails> details;

  public void addDetails(WeatherDetails detail) {
    if (details == null) {
      this.details = new ArrayList<>();
    }
    this.details.add(detail);
  }

  @Data
  public static class WeatherDetails implements Serializable {

    private static final long serialVersionUID = -2694856391356534982L;

    private double temp;
    private String description;
    private double cloudiness;
    private String timestamp;

  }

}
