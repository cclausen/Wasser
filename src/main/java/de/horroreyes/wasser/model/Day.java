package de.horroreyes.wasser.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Day {
    @Id
    @GeneratedValue
    Long id;
    @ManyToOne
    Place place;
    LocalDateTime start;
    LocalDateTime end;
}
