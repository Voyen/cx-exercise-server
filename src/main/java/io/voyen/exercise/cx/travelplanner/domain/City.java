package io.voyen.exercise.cx.travelplanner.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class City implements Serializable {
  private static final long serialVersionUID = -5195035967374111640L;

  @Id
  private long id;
  private String name;
  private String country;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "city_id", referencedColumnName = "id")
  private List<WeatherEntry> weatherEntries = new ArrayList<>();

  @CreatedDate
  private Instant cachedAt;

  @Data
  @Entity
  public static class WeatherEntry implements Serializable {
    private static final long serialVersionUID = 5800547685168054274L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double temp;
    private String description;
    private double cloudiness;
    private String timestamp;
    private long epochSeconds;
  }

}
