package io.voyen.exercise.cx.travelplanner;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "myapp")
public class CustomProperties {

  private String openWeatherApiKey;
  
}
