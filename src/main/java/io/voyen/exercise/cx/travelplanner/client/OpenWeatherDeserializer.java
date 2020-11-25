package io.voyen.exercise.cx.travelplanner.client;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import io.voyen.exercise.cx.travelplanner.client.OpenWeatherModel.WeatherDetails;

public class OpenWeatherDeserializer extends StdDeserializer<OpenWeatherModel> {

  private static final long serialVersionUID = 4538584168000005633L;

  public OpenWeatherDeserializer() {
    this(null);
  }

  public OpenWeatherDeserializer(Class<?> vc) {
    super(vc);
  }

  /**
   * Custom deserialize method to pull only the required information
   */
  @Override
  public OpenWeatherModel deserialize(JsonParser jp, DeserializationContext ctx) {

    JsonNode rawNode = null;

    try {
      rawNode = jp.getCodec().readTree(jp);
    } catch (IOException e) {
      return null;
    }
    OpenWeatherModel model = new OpenWeatherModel();

    // Get top level basics
    model.setId(rawNode.get("city").get("id").longValue());
    model.setName(rawNode.get("city").get("name").textValue());
    model.setCountry(rawNode.get("city").get("country").textValue());

    // For each 3-hour forecast, grab inner details between 12pm - 6pm
    rawNode.get("list").elements().forEachRemaining(d -> {
      // for ease, just going to check the hours position of the text timestamp
      String dt = d.get("dt_txt").textValue();
      int indexOfHour = dt.indexOf(" ") + 1;
      dt = dt.substring(indexOfHour, indexOfHour + 2);

      if (Integer.parseInt(dt) >= 12 && Integer.parseInt(dt) <= 18) {
        WeatherDetails details = new WeatherDetails();
        details.setTemp(d.get("main").get("temp").doubleValue());
        details.setDescription(d.get("weather").get(0).get("description").textValue());
        details.setCloudiness(d.get("clouds").get("all").doubleValue());
        details.setTimestamp(d.get("dt_txt").textValue());
        details.setEpoch(d.get("dt").longValue());
        model.addDetails(details);
      }
    });

    return model;
  }

}
