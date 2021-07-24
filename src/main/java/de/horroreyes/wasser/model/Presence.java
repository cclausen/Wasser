package de.horroreyes.wasser.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Presence {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Person person;
    private LocalDateTime start;
    private LocalDateTime end;
}
