package io.voyen.exercise.cx.travelplanner.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.Data;

@Data
@Entity
public class Itinerary implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String name;

  @ElementCollection
  @CollectionTable(name = "saved_cities", joinColumns = @JoinColumn(name = "id"))
  private List<String> cities;

}
